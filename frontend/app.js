const modal = document.getElementById("recipeModal");
const openModalBtn = document.getElementById("openModalBtn");
const closeModalBtn = document.querySelector(".close");
document.getElementById('addIngredient').addEventListener('click', function () {
    const naziv = document.getElementById('ingredientNaziv').value.trim();
    const kolicina = document.getElementById('ingredientKolicina').value.trim();
    const enota = document.getElementById('ingredientEnota').value;

    if (!naziv || !kolicina || !enota) {
        alert("Prosim, izpolnite vsa polja za sestavino.");
        return;
    }

    const ingredientsContainer = document.getElementById('ingredientsContainer');
    const hiddenIngredients = document.getElementById('hiddenIngredients');

    // Create a new ingredient tag
    const tag = document.createElement('div');
    tag.classList.add('ingredient-tag');
    tag.textContent = `${naziv} ${kolicina}${enota}`;

    // Add a remove button to the tag
    const removeButton = document.createElement('button');
    removeButton.textContent = '✖';
    removeButton.classList.add('remove-tag');
    tag.appendChild(removeButton);

    // Append the tag to the container
    ingredientsContainer.appendChild(tag);

    // Update the hidden input field
    updateHiddenIngredients();

    // Clear input fields
    document.getElementById('ingredientNaziv').value = '';
    document.getElementById('ingredientKolicina').value = '';
    document.getElementById('ingredientEnota').value = 'g';

    // Handle tag removal
    removeButton.addEventListener('click', function () {
        tag.remove();
        updateHiddenIngredients();
    });
});

function updateHiddenIngredients() {
    const ingredientsContainer = document.getElementById('ingredientsContainer');
    const hiddenIngredients = document.getElementById('hiddenIngredients');
    const tags = ingredientsContainer.querySelectorAll('.ingredient-tag');

    const ingredients = Array.from(tags).map(tag => tag.textContent.replace('✖', '').trim());
    hiddenIngredients.value = ingredients.join(',');
}


// Open the modal when "Dodaj Recept" is clicked
openModalBtn.addEventListener("click", function() {
    modal.style.display = "block";
});

// Close the modal when the close button is clicked
closeModalBtn.addEventListener("click", function() {
    modal.style.display = "none";
});
// Handle form submission for adding a recipe
const recipeForm = document.getElementById("recipeForm");

recipeForm.addEventListener("submit", async function(event) {
    event.preventDefault(); // Prevent the default form submission

    const formData = new FormData(recipeForm);
    const tip = document.getElementById("tip").value;
    formData.append("tip", tip);

    // Extract and parse the ingredients from the hidden input
    const hiddenIngredients = document.getElementById("hiddenIngredients").value;
    const ingredientsArray = hiddenIngredients.split(',').map(ingredient => {
        const match = ingredient.match(/(.*)\s(\d+\.?\d*)(\w+)/); // Parse format "Milk 200ml"
        if (match) {
            return {
                naziv: match[1].trim(),
                kolicina: parseFloat(match[2].trim()),
                enota: match[3].trim(),
            };
        }
    }).filter(Boolean); // Remove any null/undefined values

    try {
        // Step 1: Submit the recipe
        const recipeResponse = await fetch("http://localhost:8080/recepti", {
            method: "POST",
            body: formData
        });

        if (!recipeResponse.ok) {
            alert("Failed to add recipe. Please try again.");
            return;
        }

        const recipe = await recipeResponse.json(); // Retrieve the newly created recipe object
        const idRecepti = recipe.idRecepti; // Store the recipe ID for ingredient submission

        // Step 2: Submit each ingredient associated with the new recipe ID in parallel
        const ingredientPromises = ingredientsArray.map(ingredient => {
            const ingredientPayload = {
                naziv: ingredient.naziv,
                kolicina: ingredient.kolicina,
                enota: ingredient.enota,
                idRecepti: idRecepti, // Send the recipe ID to associate with the ingredient
            };

            return fetch("http://localhost:8080/sestavine", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json",
                },
                body: JSON.stringify(ingredientPayload),
            }).then(response => {
                if (!response.ok) {
                    return response.text().then(text => {
                        throw new Error(`Failed to add ingredient: ${text}`);
                    });
                }
                return response.json(); // Handle successful ingredient response
            });
        });

        // Wait for all ingredient submissions to complete
        await Promise.all(ingredientPromises);

        alert("Recipe and ingredients added successfully!");
        modal.style.display = "none"; // Close modal on success
        loadRecipes(); // Optionally reload the recipe list via AJAX
    } catch (error) {
        console.error("Error adding recipe or ingredients:", error);
        alert("An error occurred. Please try again.");
    }
});



async function loadRecipes() {
    try {
        const response = await fetch('http://localhost:8080/recepti');
        const recipes = await response.json();
        
        const recipeCardsContainer = document.getElementById('recipeCards');
        recipeCardsContainer.innerHTML = '';  // Clear existing content if any

        // Using 'for...of' loop to await async operations properly
        for (const recipe of recipes) {
            try {
                // Fetch ingredients for each recipe
                const ingredientsResponse = await fetch(`http://localhost:8080/sestavine/recepti/${recipe.idRecepti}`);
                const ingredients = await ingredientsResponse.json();

                // Check if ingredients are found
                if (!ingredients || ingredients.length === 0) {
                    console.log(`No ingredients found for recipe ${recipe.idRecepti}`);
                }

                // Format ingredients as "Milk 200ml, Sugar 100g"
                const ingredientsText = ingredients.map(ingredient => {
                    return `${ingredient.naziv} ${ingredient.kolicina} ${ingredient.enota.toLowerCase()}`;
                }).join(', ');

                // Create the card for each recipe
                const card = `
                    <div class="col-md-4">
                        <div class="card border shadow-sm mb-4">
                            <img class="card-img-top" src="sliki/${recipe.slika}" alt="${recipe.naziv}">
                            <div class="card-body">
                                <h5 class="card-title">${recipe.naziv}</h5>
                            </div>
                            <div class="card-footer">
                                <p class="text-muted">Vrsta obroka: ${recipe.tip.charAt(0).toUpperCase() + recipe.tip.slice(1).toLowerCase()}</p>
                            </div>
                            <div class="card-footer">
                                <p class="card-text">${ingredientsText}</p> <!-- Display ingredients -->
                            </div>
                            <div class="card-footer">
                                <p class="text-muted">${recipe.opis}</p>
                            </div>
                            <div class="button-container">
                                <!-- Edit Button -->
                                <button class="circle-btn" onclick="editRecipe(${recipe.idRecepti})">
                                    <i class="fas fa-pen"></i>
                                </button>
                                <!-- Delete Button -->
                                <button class="circle-btn" onclick="deleteRecipe(${recipe.idRecepti})">
                                    <i class="fas fa-trash"></i>
                                </button>
                            </div>
                        </div>
                    </div>
                `;
                recipeCardsContainer.innerHTML += card;
            } catch (ingredientError) {
                console.error(`Error fetching ingredients for recipe ${recipe.idRecepti}:`, ingredientError);
            }
        }
    } catch (error) {
        console.error('Error fetching recipes:', error);
    }
}


function editRecipe(id) {
    // Fetch the recipe data by ID
    fetch(`http://localhost:8080/recepti/${id}`)
        .then(response => response.json())
        .then(recipe => {
            // Populate the modal with recipe data
            document.getElementById('updateId').value = recipe.idRecepti;
            document.getElementById('updateNaziv').value = recipe.naziv;
            document.getElementById('updateSestavine').value = recipe.sestavine;
            document.getElementById('updateOpis').value = recipe.opis;

            // Set the `tip` in the update modal (set the selected option)
            document.getElementById('updateTip').value = recipe.tip;

            // Open the modal for editing
            document.getElementById('updateModal').style.display = 'block';
        })
        .catch(error => {
            console.error('Error fetching recipe:', error);
        });
}

// Close modal for update
document.getElementById('updateClose').onclick = function() {
    document.getElementById('updateModal').style.display = 'none';
};

// Close modal when clicking outside of it
window.onclick = function(event) {
    const modal = document.getElementById('updateModal');
    if (event.target === modal) {
        modal.style.display = 'none';
    }
};

function updateRecept(id) {
    const naziv = document.getElementById('updateNaziv').value;
    const sestavine = document.getElementById('updateSestavine').value;
    const opis = document.getElementById('updateOpis').value;
    const tip = document.getElementById('updateTip').value; // Get the selected tip

    const updatedRecipe = {
        naziv: naziv,
        sestavine: sestavine,
        opis: opis,
        tip: tip  // Add the tip to the updated recipe data
    };

    console.log('Updating recipe with ID:', id);
    console.log('Updated Recipe Data:', updatedRecipe);

    fetch(`http://localhost:8080/recepti/${id}`, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(updatedRecipe),
    })
    .then(response => {
        console.log('Response Status:', response.status);
        return response.json().then(data => {
            return { status: response.status, data }; // Return both status and data
        });
    })
    .then(({ status, data }) => {
        if (status === 200) {
            document.getElementById('updateModal').style.display = 'none';
            loadRecipes(); // Reload recipes
        } else {
            throw new Error(data.message || 'Failed to update recipe');
        }
    })
    .catch(error => {
        console.error('Error updating recipe:', error);
        alert(error.message); // Show the error message
    });
}

function deleteRecipe(id) {
    fetch(`http://localhost:8080/recepti/${id}`, {
        method: 'DELETE'
    }).then(response => {
        if (response.ok) {
            alert('Recipe deleted');
            loadRecipes(); // Refresh the recipe list
        } else {
            alert('Error deleting recipe');
        }
    });
}

document.getElementById('searchButton').addEventListener('click', function() {
    const searchTerm = document.getElementById('searchBar').value.toLowerCase(); // Get the search term
    const recipeCards = document.getElementsByClassName('card'); // Get all recipe cards

    Array.from(recipeCards).forEach(card => {
        const title = card.querySelector('.card-title').textContent.toLowerCase();
        // Show/hide based on whether the title includes the search term
        card.style.display = title.includes(searchTerm) ? 'block' : 'none'; 
    });
});

// Initial load of recipes
document.addEventListener("DOMContentLoaded", loadRecipes);




function filterRecipesWithUI(element) {
    const filterBox = element.querySelector('.filter-box');
    const isChecked = filterBox.classList.contains('checked'); // Check if the filter is already selected
    const filterType = element.getAttribute('data-tip'); // Get the filter type

    if (isChecked) {
        // Uncheck the filter and reload all recipes
        filterBox.classList.remove('checked');
        loadRecipes(); // Fetch and display all recipes
    } else {
        // Check the filter and fetch filtered recipes
        document.querySelectorAll('.filter-box').forEach(box => box.classList.remove('checked')); // Uncheck all other filters
        filterBox.classList.add('checked'); // Check the current filter

        // Fetch and display filtered recipes
        fetch(`http://localhost:8080/recepti/tip/${filterType}`)
            .then(response => response.json())
            .then(recipes => {
                const recipeCardsContainer = document.getElementById('recipeCards');
                recipeCardsContainer.innerHTML = ''; // Clear existing content

                recipes.forEach(recipe => {
                    const card = `
                    <div class="col-md-4">
                    <div class="card border shadow-sm mb-4">
                        <img class="card-img-top" src="sliki/${recipe.slika}" alt="${recipe.naziv}">
                        <div class="card-body">
                            <h5 class="card-title">${recipe.naziv}</h5>
                        </div>
                        <div class="card-footer">
                        <p class="text-muted">Vrsta obroka: ${recipe.tip.charAt(0).toUpperCase() + recipe.tip.slice(1).toLowerCase()}</p>
                    </div>
                        <div class="card-footer">
                        <p class="card-text">${recipe.sestavine}</p>
                        </div>
                       
                        <div class="card-footer">
                            <p class="text-muted">${recipe.opis}</p>
                        </div>
                        <div class="button-container">
                            <!-- Edit Button -->
                            <button class="circle-btn" onclick="editRecipe(${recipe.idRecepti})">
                                <i class="fas fa-pen"></i>
                            </button>
                            <!-- Delete Button -->
                            <button class="circle-btn" onclick="deleteRecipe(${recipe.idRecepti})">
                                <i class="fas fa-trash"></i>
                            </button>
                        </div>
                    </div>
                </div>
                    `;
                    recipeCardsContainer.innerHTML += card;
                });
            })
            .catch(error => {
                console.error('Error fetching filtered recipes:', error);
            });
    }
}