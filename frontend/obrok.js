document.addEventListener('DOMContentLoaded', () => {
    const breakfastDiv = document.getElementById('breakfast-recipes');
    const lunchDiv = document.getElementById('lunch-recipes');
    const dinnerDiv = document.getElementById('dinner-recipes');
    const mealDateInput = document.getElementById('meal-date');
    const form = document.getElementById('meal-plan-form');

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
                    <img src="/sliki/${recipe.slika}" alt="${recipe.naziv}">
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