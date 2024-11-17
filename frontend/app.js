const modal = document.getElementById("recipeModal");
const openModalBtn = document.getElementById("openModalBtn");
const closeModalBtn = document.querySelector(".close");

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
    event.preventDefault();  // Prevent the default form submission

    const formData = new FormData(recipeForm);  // Collect form data
    // Get the selected tip from the dropdown
    const tip = document.getElementById("tip").value;
    formData.append("tip", tip);  // Append the tip to the form data

    try {
        const response = await fetch("http://localhost:8080/recepti", {
            method: "POST",
            body: formData
        });

        if (response.ok) {
            alert("Recipe added successfully!");
            modal.style.display = "none";  // Close modal on success
            loadRecipes(); 
            location.reload(); 
        } else {
            alert("Failed to add recipe. Please try again.");
        }
    } catch (error) {
        console.error("Error adding recipe:", error);
        alert("An error occurred. Please try again.");
    }
});

// Load recipes function
async function loadRecipes() {
    try {
        const response = await fetch('http://localhost:8080/recepti');
        const recipes = await response.json();
        
        const recipeCardsContainer = document.getElementById('recipeCards');
        recipeCardsContainer.innerHTML = '';  // Clear existing content if any

        recipes.forEach(recipe => {
            const card = `
                <div class="col-md-4">
                    <div class="card border shadow-sm mb-4">
                        <img class="card-img-top" src="sliki/${recipe.slika}" alt="${recipe.naziv}">
                        <div class="card-body">
                            <h5 class="card-title">${recipe.naziv}</h5>
                            <p class="card-text">${recipe.sestavine}</p>
                        </div>
                        <div class="card-footer">
                            <p class="text-muted">${recipe.opis}</p>
                            <p class="text-muted">Tip: ${recipe.tip}</p> <!-- Display the recipe type -->
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
