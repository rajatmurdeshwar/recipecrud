# Recipe Saver Backend API

A backend API for saving and managing recipes, built with Spring Boot, JWT authentication, and MySQL.

## Features

- **User Authentication**:
  - User registration and login.
  - JWT-based authentication.
- **Recipe Management**:
  - CRUD operations for recipes.
  - Pagination, sorting, and filtering.
  - Search functionality.
- **Database**:
  - MySQL for storing user and recipe data.

## Technologies Used

- **Backend**: Spring Boot, Spring Security, JWT.
- **Database**: MySQL.
- **Tools**: Docker, Swagger, Postman.

## API Documentation

### Endpoints

#### User Authentication
- `POST /auth/signup` - Register a new user.
- `POST /auth/login` - Login and get a JWT token.

#### Recipe Management
- `GET /recipes` - Get all recipes (supports pagination and sorting).
- `GET /recipes/search?query=pasta` - Search recipes by name or ingredients.
- `GET /recipes/filter?category=vegetarian` - Filter recipes by category.
- `POST /recipes` - Create a new recipe.
- `PUT /recipes/{id}` - Update a recipe.
- `DELETE /recipes/{id}` - Delete a recipe.

### Example Requests

#### Get All Recipes (Paginated and Sorted)
