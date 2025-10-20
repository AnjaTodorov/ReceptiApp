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
                    <button class="show-ingredients-btn"  data-id="${plan.idNacrtObrokov}">
                        Prikaži potrebne sestavine
                        <img src="sliki/grocery-cart.png" alt="" style="width: 30px; height: 30px; vertical-align: middle;"/>
                    </button>
                       <button class="calculate-nutrition-btn" data-id="${plan.idNacrtObrokov}">
                        Izračunaj hranilne vrednosti
                        <img src="sliki/7757741.png" alt="" style="width: 30px; height: 30px; vertical-align: middle;"/>
                    </button>
                `;
                    mealPlansContainer.appendChild(card);
                    mealPlansContainer.addEventListener('click', async (event) => {
                        const button = event.target.closest('.show-ingredients-btn');
                        if (button) {
                            const mealPlanId = button.getAttribute('data-id');
                             
                            try {
                                const ingredients = await fetchIngredients(mealPlanId);
                    
                                showPopup(ingredients); 
                            } catch (error) {
                                console.error('Error fetching ingredients:', error);
                                alert('Could not fetch ingredients. Please try again.');
                            }
                        }
                    });
                    
                    
                });
            } else {
                //mealPlansContainer.innerHTML = '<p>Ni na voljo načrtov obrokov.</p>';
            }
        } catch (error) {
            console.error('Error fetching meal plans:', error);
            mealPlansContainer.innerHTML = '<p>Error loading meal plans.</p>';
        }
    };

    const fetchIngredients = async (mealPlanId) => {
        try {
            // Fetch ingredients from the meal plan
            const response = await fetch(`http://localhost:8080/meal-plans/meal-plan/${mealPlanId}/ingredients`);
            if (!response.ok) throw new Error('Failed to fetch ingredients');
            const data = await response.json();
    
            const ingredients = data.ingredients;
            const recipeIds = data.recipeIds || [];
    
            // Log the fetched ingredients and recipeIds
            console.log('Fetched ingredients:', ingredients);
            console.log('Fetched recipeIds:', recipeIds);
    
            // Fetch all recipes
            const recipesResponse = await fetch('http://localhost:8080/recepti'); // Adjust URL to fetch recipes
            if (!recipesResponse.ok) throw new Error('Failed to fetch recipes');
            const recipes = await recipesResponse.json();
    
            // Log the fetched recipes
            console.log('Fetched recipes:', recipes);
    
            // Loop through recipe IDs and match them with the recipes in the meal plan
            const matchedRecipes = recipeIds.map(recipeId => {
                const recipe = recipes.find(r => r.idRecepti === recipeId);
                if (!recipe) {
                    console.warn(`Recipe not found for recipeId: ${recipeId}`);
                    return null;
                }
                return recipe;
            }).filter(recipe => recipe !== null); // Filter out null values
    
            // Log matched recipes
            console.log('Matched recipes:', matchedRecipes);
    
            // Now iterate over ingredients and match them to recipes
            const adjustedIngredients = ingredients.map(ingredient => {
                const recipe = matchedRecipes.find(r => r.naziv.trim().toLowerCase() === ingredient.name.trim().toLowerCase());
    
                if (!recipe) {
                    console.warn(`Recipe not found for ingredient: ${ingredient.name}`);
                    return ingredient; // Return the ingredient as is if no matching recipe is found
                }
    
                // Get the number of portions for the recipe (default to 1 if undefined)
                const numberOfPortions = recipe.osebe || 1;
    
                // Adjust the ingredient quantity by dividing it by the number of portions
                const adjustedQuantity = ingredient.quantity / numberOfPortions;
    
                return {
                    name: ingredient.name,
                    quantity: adjustedQuantity,
                    unit: ingredient.unit
                };
            });
    
            // Log the adjusted ingredients
            console.log('Adjusted ingredients:', adjustedIngredients);
    
            return adjustedIngredients;
    
        } catch (error) {
            console.error('Error fetching ingredients:', error);
            throw error;
        }
    };
    
    
    
    
    
    
    const showPopup = (ingredients) => {
        const existingPopup = document.querySelector('.popup');
        if (existingPopup) {
            document.body.removeChild(existingPopup);
        }
    
        // Format the ingredients by creating a list of strings like 'name - quantity unit'
        const formattedIngredients = ingredients
            .map(item => {
                // Ensure quantity is defined before calling toFixed
                const quantity = item.quantity !== undefined && item.quantity !== null ? item.quantity.toFixed(2) : 'N/A'; 
                return `${item.name} - ${quantity} ${item.unit}`;
            })
            .filter(item => item !== ""); 
    
        const overlay = document.createElement('div');
        overlay.classList.add('popup-overlay');
        document.body.appendChild(overlay);
    
        const popup = document.createElement('div');
        popup.classList.add('popup');
        popup.innerHTML = `
            <div class="popup-content">
                <h3>Potrebne sestavine 🍽️</h3><br>
                <ol> <!-- Use <ol> for a numbered list -->
                    ${formattedIngredients.map((ingredient) => `<li>${ingredient}</li>`).join('')}
                </ol>
                <button class="close-popup-btn">Zapri</button>
            </div>
        `;
        document.body.appendChild(popup);
    
        const closeButton = popup.querySelector('.close-popup-btn');
        closeButton.addEventListener('click', () => {
            document.body.removeChild(popup); 
            document.body.removeChild(overlay); 
        });
    };
    
    
    
    
    

    // Helper function to get recipe details (name, image, and ingredients) based on meal type
    const getRecipeDetails = async (plan, mealType) => {
        const recipe = plan.recipes.find(r => r.mealType === mealType);
        if (recipe) {
            const numberOfPortions = recipe.portions; // Number of portions for the specific recipe
            const recipeDetails = await loadRecipeDetails(recipe.idRecepti);
            return recipeDetails ? `${renderRecipeDetails(recipeDetails, numberOfPortions)}` : 'Recipe details not available';
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
                //alert(`Meal plan created successfully! ID: ${createdMealPlan.idNacrtObrokov}`);
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
 // Add event listener for the calculate nutrition button
mealPlansContainer.addEventListener('click', async (event) => {
    const button = event.target.closest('.calculate-nutrition-btn');
    if (button) {
        const mealPlanId = button.getAttribute('data-id');

        try {
            // Fetch nutritional data for the meal plan
            const nutritionalData = await fetchNutritionalData(mealPlanId);

            // Show the modal with nutritional comparison
            showNutritionModal(nutritionalData);
        } catch (error) {
            console.error('Error fetching nutritional data:', error);
            alert('Could not fetch nutritional data. Please try again.');
        }
    }
});

// Fetch nutritional data for a meal plan
const fetchNutritionalData = async (mealPlanId) => {
    const response = await fetch(`http://localhost:8080/meal-plans/${mealPlanId}/nutritional-values`);
    if (!response.ok) throw new Error('Failed to fetch nutritional data');
    return await response.json(); // Example format: { "Energy": 2207, "Carbs": 102, "Protein": 78, "Sugar": 118 }
};

const showNutritionModal = (nutritionData) => {
    // Priporočene vrednosti na dan (primer, prilagodite po potrebi)
    const recommendedValues = {
        Energija: 8700, // v kilodžulih
        Beljakovine: 50,  // v gramih
        Maščobe: 70,      // v gramih
        NasičeneMaščobe: 24, // v gramih
        OgljikoviHidrati: 310, // v gramih
        Sladkor: 90,   // v gramih
        Natrij: 2.3,  // v gramih
        PrehranskaVlaknina: 30 // v gramih
    };

    // Prag za označevanje "preseženih" vrednosti
    const exceedThresholds = {
        Energija: 200, // kJ za Energijo
        Beljakovine: 20, // g za Beljakovine
        Maščobe: 20,     // g za Maščobe
        NasičeneMaščobe: 20, // g za Nasičene Maščobe
        OgljikoviHidrati: 20,   // g za Ogljikove hidrate
        Sladkor: 20,   // g za Sladkor
        Natrij: 0.5, // g za Natrij
        PrehranskaVlaknina: 5 // g za Prehranska vlaknina
    };

    // Filtriraj razpoložljive hranilne snovi v odgovoru
    const availableNutrients = Object.keys(nutritionData).filter(key => nutritionData[key] !== undefined);

    // Ustvari primerjalno tabelo za razpoložljive hranilne snovi
    const comparison = availableNutrients.map((key) => {
        const userValue = nutritionData[key] || 0;  // Privzeto 0, če hranilna snov ni na voljo
        const recommendedValue = recommendedValues[key] || 'N/A'; // Obravnava primer, ko priporočena vrednost ni na voljo

        let status = '⚠️ Pod priporočenim';
        let emoji = '';
        
        if (recommendedValue !== 'N/A') {
            if (userValue >= recommendedValue) {
                status = '✅ Zadostuje priporočilu';
            }
            // Preveri, če vrednost presega priporočeno
            if (userValue >= recommendedValue + exceedThresholds[key]) {
                status = '❗ Presega priporočilo';
                emoji = '';
            }
        }

        return `
            <tr>
                <td>${key}</td>
                <td>${userValue}</td>
                <td>${recommendedValue}</td>
                <td>${emoji} ${status}</td>
            </tr>
        `;
    }).join('');

    // Ustvari HTML za modalno okno
    const modalHtml = `
        <div class="popup-overlay"></div>
        <div class="popup">
            <div class="popup-content">
                <h3>Primerjava hranil</h3>
                <table>
                    <thead>
                        <tr>
                            <th>Hranilna snov</th>
                            <th>Vaš vnos</th>
                            <th>Priporočeno</th>
                            <th>Status</th>
                        </tr>
                    </thead>
                    <tbody>
                        ${comparison}
                    </tbody>
                </table>
                <button class="close-popup-btn">Zapri</button>
            </div>
        </div>
    `;
    // Append the modal to the body
    const overlay = document.createElement('div');
    overlay.innerHTML = modalHtml;
    document.body.appendChild(overlay);

    // Add event listener to close the modal
    const closeButton = overlay.querySelector('.close-popup-btn');
    closeButton.addEventListener('click', () => {
        document.body.removeChild(overlay);
    });
};
});
