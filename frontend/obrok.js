document.addEventListener('DOMContentLoaded', () => {
    const breakfastDiv = document.getElementById('breakfast-recipes');
    const lunchDiv = document.getElementById('lunch-recipes');
    const dinnerDiv = document.getElementById('dinner-recipes');
    const mealDateInput = document.getElementById('meal-date');
    const form = document.getElementById('meal-plan-form');
    const mealPlansContainer = document.getElementById('meal-plan-cards');

    // Set default date to the next day
    const today = new Date();
    const nextDay = new Date(today);
    nextDay.setDate(today.getDate() + 1);
    mealDateInput.value = nextDay.toISOString().split('T')[0];

    // Fetch and display recipes by type
    const loadRecipes = async (type, container) => {
        try {
            const response = await fetch(`http://localhost:8080/recepti/tip/${type}`);
            if (!response.ok) throw new Error(`Failed to load recipes for ${type}`);
            const recipes = await response.json();
            container.innerHTML = ''; // Clear previous content
            recipes.forEach(recipe => {
                const recipeDiv = document.createElement('div');
                recipeDiv.className = 'recipe-item';
                recipeDiv.innerHTML = `
                    <img src="/sliki/${recipe.slika}" alt="${recipe.naziv}" class="recipe-image">
                    <span>${recipe.naziv}</span>
                    <input type="hidden" value="${recipe.idRecepti}">
                `;
                recipeDiv.addEventListener('click', () => {
                    container.querySelectorAll('.recipe-item').forEach(item => item.classList.remove('selected'));
                    recipeDiv.classList.add('selected');
                });
                container.appendChild(recipeDiv);
            });
        } catch (error) {
            console.error('Error loading recipes:', error);
            container.innerHTML = `<p>Error loading recipes for ${type}.</p>`;
        }
    };

    // Load recipes for all meal types
    loadRecipes('zajtrk', breakfastDiv);
    loadRecipes('kosilo', lunchDiv);
    loadRecipes('večerja', dinnerDiv);

    const formatDate = (dateString) => {
        const date = new Date(dateString);
    
        // Define the options for formatting the date (weekday only)
        const weekdayOptions = { weekday: 'long' };
        const formattedDay = new Intl.DateTimeFormat('sl-SI', weekdayOptions).format(date);
    
        // Format the full date (day, month, year) with periods
        const formattedDate = new Intl.DateTimeFormat('sl-SI').format(date);
    
        // Capitalize the first letter of the day
        const capitalizedDay = formattedDay.charAt(0).toUpperCase() + formattedDay.slice(1);
    
        // Return the formatted string with day and date in parentheses, with periods between the day, month, and year
        return `${capitalizedDay} (${formattedDate})`;
    };

    // Fetch and display existing meal plans
    const fetchMealPlans = async () => {
        try {
            const response = await fetch('http://localhost:8080/meal-plans');
            if (!response.ok) throw new Error('Failed to fetch meal plans');
            const mealPlans = await response.json();

            mealPlansContainer.innerHTML = ''; // Clear existing meal plans

            if (mealPlans.length > 0) {
                mealPlans.forEach(async (plan) => {
                    const card = document.createElement('div');
                    card.classList.add('meal-plan-card');
                    card.innerHTML = `
                     <h3>${formatDate(plan.datum)}</h3>
                     <hr class="meal-divider"/> 
                    <div class="meal-plan-details">
                        <div class="meal-plan-item">
                            <h4>
                            Zajtrk
                            <img src="sliki/breakfast.png" alt="" style="width: 30px; height: 30px; vertical-align: middle;"/>
                            </h4>
                            <div>${await getRecipeDetails(plan, 'ZAJTRK')}</div>
                        </div>
                        <hr class="meal-divider"/> <!-- Border after Zajtrk -->
                        <div class="meal-plan-item">
                            <h4>
                            Kosilo
                            <img src="sliki/meal.png" alt="" style="width: 30px; height: 30px; vertical-align: middle;"/>
                            </h4>
                            <div>${await getRecipeDetails(plan, 'KOSILO')}</div>
                        </div>
                        <hr class="meal-divider"/> <!-- Border after Kosilo -->
                        <div class="meal-plan-item">
                            <h4>
                            Večerja
                            <img src="sliki/vecera.png" alt="" style="width: 30px; height: 30px; vertical-align: middle;"/>
                            </h4>
                            <div>${await getRecipeDetails(plan, 'VEČERJA')}</div>
                        </div>
                        <hr class="meal-divider"/> <!-- Border after Večerja -->
                    </div>
                    <!-- Add the button inside each card -->
                    <button class="show-ingredients-btn">
                        Prikaži potrebne sestavine
                        <img src="sliki/grocery-cart.png" alt="" style="width: 30px; height: 30px; vertical-align: middle;"/>
                    </button>
                `;
                    mealPlansContainer.appendChild(card);
                });
            } else {
                mealPlansContainer.innerHTML = '<p>No meal plans available.</p>';
            }
        } catch (error) {
            console.error('Error fetching meal plans:', error);
            mealPlansContainer.innerHTML = '<p>Error loading meal plans.</p>';
        }
    };

    // Helper function to get recipe details (name, image, and ingredients) based on meal type
    const getRecipeDetails = async (plan, mealType) => {
        const recipe = plan.recipes.find(r => r.mealType === mealType);
        if (recipe) {
            const recipeDetails = await loadRecipeDetails(recipe.idRecepti);
            return recipeDetails ? `${renderRecipeDetails(recipeDetails)}` : 'Recipe details not available';
        }
        return 'No recipe available';
    };

    // Function to load recipe details by ID (fetches photo and ingredients)
    const loadRecipeDetails = async (recipeId) => {
        try {
            const response = await fetch(`http://localhost:8080/recepti/${recipeId}`);
            if (!response.ok) throw new Error('Failed to load recipe details');
            const recipe = await response.json();
            return recipe;
        } catch (error) {
            console.error('Error fetching recipe details:', error);
            return null;
        }
    };

    // Function to render the recipe details with photo, name, and ingredients
    const renderRecipeDetails = (recipe) => {
        return `
            <div class="recipe-detail">
                <img src="/sliki/${recipe.slika}" alt="${recipe.naziv}" class="recipe-photo" style="width: 140px; height: 130px; vertical-align: middle;" >
                <span><normal>${recipe.naziv}</normal></span>
            </div>
        `;
    };

    // Load existing meal plans on page load
    fetchMealPlans();

    form.addEventListener('submit', async (e) => {
        e.preventDefault();

        const mealDate = mealDateInput.value;

        try {
            // Check if a meal plan exists for the selected date
            const checkResponse = await fetch(`http://localhost:8080/meal-plans/check?datum=${mealDate}`);
            if (!checkResponse.ok) throw new Error('Failed to check existing meal plans.');

            const { exists } = await checkResponse.json();
            if (exists) {
                alert(`A meal plan already exists for ${mealDate}. Please choose a different date.`);
                return; // Prevent further submission
            }

            const zajtrkRecipeId = breakfastDiv.querySelector('.selected input')?.value;
            const kosiloRecipeId = lunchDiv.querySelector('.selected input')?.value;
            const vecerjaRecipeId = dinnerDiv.querySelector('.selected input')?.value;

            if (!zajtrkRecipeId || !kosiloRecipeId || !vecerjaRecipeId) {
                alert('Please select one recipe for each meal type!');
                return;
            }

            // Create the meal plan request
            const mealPlanRequest = {
                datum: mealDate,
                recipes: [
                    { recipeId: zajtrkRecipeId, mealType: 'ZAJTRK' },
                    { recipeId: kosiloRecipeId, mealType: 'KOSILO' },
                    { recipeId: vecerjaRecipeId, mealType: 'VEČERJA' },
                ],
            };

            const response = await fetch('http://localhost:8080/meal-plans/create', {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify(mealPlanRequest),
            });

            if (response.ok) {
                const createdMealPlan = await response.json(); // Get the response with the generated ID
                alert(`Meal plan created successfully! ID: ${createdMealPlan.idNacrtObrokov}`);
                form.reset();
                [breakfastDiv, lunchDiv, dinnerDiv].forEach((container) => {
                    container.querySelectorAll('.recipe-item').forEach((item) => item.classList.remove('selected'));
                });

                // Reload the meal plans after creation
                fetchMealPlans();
            } else {
                const error = await response.text();
                alert(`Failed to create meal plan: ${error}`);
            }
        } catch (error) {
            console.error('Error creating meal plan:', error);
            alert('Error creating meal plan. Please try again later.');
        }
    });
});
