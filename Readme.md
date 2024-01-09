## CRUD Application with Spring Boot, MySQL, and JWT
Description

This application is a simple CRUD (Create, Read, Update, Delete) 
blog application built using Spring Boot and documented using Springdoc OpenAPI. 
The application features basic functionalities for managing posts, comments, categories, 
as well as authentication and authorization using JSON Web Token (JWT). Data is stored in a MySQL database.

## Features
- Post Management
    - Create, read, update, and delete posts (admin privileges required).
    - Retrieve posts by ID and category.
  
- Comment Management
    - Create, read, update, and delete comments for a specific post.
    - Retrieve comments by post ID and comment ID.
  
- Category Management
    - Create, read, update, and delete categories (admin privileges required).
    - Retrieve posts by category ID.

- User Authentication and Authorization
    - User login using JWT.
    - User registration.
    - Endpoint protection using JWT.

## Technology Stack
- Java 21
- Spring Boot 3.2.1
- MySQL 8.0.35
- JSON Web Token (JWT) 0.11.5
- Springdoc OpenAPI 2.3.0

## Running the Application
1. Ensure that MySQL is installed on your system.
2. Adjust the database configuration in the application.properties file.
3. Make sure you have Java and Maven installed.
4. Clone this repository: git clone https://github.com/Ruswanda2020/Springboot-Blog-App-RESTAPIs.git
5. Navigate to the project directory. cd Springboot-Blog-App-RESTAPIs
6. Run the following command to start the application: mvn spring-boot:run.

## API Endpoints
Access the API documentation at http://localhost:8080/swagger-ui.html.