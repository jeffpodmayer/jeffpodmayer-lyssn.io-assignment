# Task 1
This is a simple Java application that interacts with a PostgreSQL database to manage user data. The application includes methods for connecting to the database, getting all users, finding a user by user ID and updating a user. It is built in Java utilizing the JDBC API.

## Features
- **Database Connection**: Connects to a PostgreSQL database with connection 'jdbc:postgresql://localhost:5432/mydb'
- **User Management**:
  - Get all users
  - Retrieve users by their `userId`.
  - Update a user 
- **Testable**: JUnit tests are provided for verifying the functionality of the methods
  
```java
// Get all users
Collection<User> users = User.getAllUsers();

// Retrieve user by userId
User user = User.getUserByUserId(1L);

// Update a user
user.fname = "Jeff"
user.name = "Podmayer"
User.updateUser(user);
```

## Requirements
- **Java 8 or later+**
- **PostgreSQL Database**
- **JUnit 5** for unit testing
- **IDE** (e.g., IntelliJ IDEA) 


# Task 2 
## Personal Java Project
- **Project Name:** hikerhunger
- **Project Description:** Inspired by my experience on long distance hiking trails like the Appalachian Trail and Colorado Trail, this web application is designed to help backpackers plan backcountry camping and hiking recipes/meals. The app allows users to create recipes, store them, and then plan a trip by adding existing recipes to a trip itinerary. The app automatically adjusts recipe ingredient quantities and weight based on the number of people on the trip. Then, it calculates the total weight of food per person per day, ensuring users have packed a sufficient amount of food for their trip. 
- **GitHub Repo:** https://github.com/jeffpodmayer/hikerhunger
- **Live Site:** https://hikerhunger-production.up.railway.app/

## Briefly explain a part of the project you are proud of as well as any challenges you faced.
### Part I am proud of:
In this application, I used Java for the backend and JavaScript for the frontend to create a dynamic user experience. I am particularly proud of this project because I guided it through the entire process—from design to development to deployment. One aspect I am especially proud of was leveraging JavaScript's Fetch API to facilitate communication between the client and server. This involved sending data to specific endpoints, such as for createRecipe or saveIngredient.
On the server side, the incoming JSON data was deserialized using Java's built-in libraries, like Jackson, to map the JSON objects to their corresponding Java entities. This process allowed me to efficiently parse the data and ensure it was correctly mapped for further processing. I then implemented service-layer logic, such as a ```createIngredient()``` method, to validate and process this data, ensuring its accuracy and integrity. Once the data was validated, it was handed off to the Spring Data JPA repository layer. Using Hibernate as the JPA implementation, I ensured that the validated data was efficiently mapped to the corresponding database tables in the MySQL database and persisted reliably.

Here is an example of how I handled this in the saveIngredient() method:

```java
@PostMapping("/saveIngredient/{recipeId}")
      public ResponseEntity<Ingredient> saveIngredient(@RequestBody Ingredient newIngredientData, @PathVariable Long recipeId){
        Optional<Recipe> optionalRecipe = recipeService.findById(recipeId);
        if (optionalRecipe.isPresent()) {
            Recipe recipe = optionalRecipe.get();
            Ingredient newIngredient = ingredientService.createIngredient(newIngredientData, recipe);
            ingredientService.save(newIngredient);
            return ResponseEntity.status(HttpStatus.CREATED).body(newIngredient);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
```
And a snippet of the JavaScript function that hit the endpoint above:
```javascript
const submitAddIngredient = async function () {
  const ingredientData = {
    ingredientName: ingredientNameInput.value,
    quantity: quantityInput.value,
    unit: unitInput.value,
    weightInGrams: weightInput.value,
    notes: notesInput.value,
  };

  const validationError = validateIngredientData(ingredientData);
  if (validationError !== null) {
    alert(validationError);
    return;
  }

  try {
    const response = await fetch(`/saveIngredient/${recipeIdNumber}`, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(ingredientData),
    });

    if (response.ok) {
      const savedIngredient = await response.json();
      renderIngredient(savedIngredient); // Function to render ingredient dynamically on screen for user with the response
```

### Challenges I Faced:
Throughout the development of this project, I encountered several significant challenges, with one of the most prominent being the need to revisit my initial database schema and entity design. At first, during the intial brainstorm and design of the project, I considered two possible models: one using three entities and another with five. To keep things simple, I decided to go with the three-entity model: ```Recipe```, ```Ingredient```, and ```Trip```.

As development progressed, I reached a point where users needed to associate recipes and ingredients with specific trips and, therefore, the quantities and weights of the ingredients had to adjust based on the number of people on the trip. During testing, I ran into an issue: the original ```Recipe``` entities, which users had created and stored in their Recipe list, were being modified whenever they were added to a Trip. This was happening because there was no mechanism in place to preserve the original recipe while capturing the modified quantities for the trip.

After some investigation, I realized that I needed two additional entities: ```tripRecipe``` and ```tripIngredient```. These new entities would store the trip-specific changes to ```Recipe``` and ```Ingredient``` data, such as adjusted quantities and weights, without affecting the original entities in the database. This solution allowed me to separate the trip-specific changes from the original ```Recipe``` and ```Ingredient``` data, enabling the original data to be reused for future trip meal planning.

Introducing ```tripRecipe``` and ```tripIngredient``` helped create a more structured and efficient data model, ensuring that changes to recipes would not affect the original data. This challenge highlighted the importance of comprehensive data modeling and considering potential entity relationships early in the development process. It also reinforced the value of an iterative approach to schema design, where changes can be incorporated as new requirements emerge.



