
# Library Management System

## Overview

This is a **Library Management System** built using **Spring Boot**. It provides a RESTful API to manage books in a library, allowing users to perform operations such as adding, retrieving, and deleting books.

## Technologies Used

- Java
- Spring Boot
- Spring Data JPA
- H2 Database (for testing)
- Maven
- Jakarta Persistence

## API Endpoints

### 1. Add Books

- **URL**: `/api/v1/book/saveBooks`
- **Method**: `POST`
- **Request Body**:
    - JSON array of book objects.
    - Example:
      ```json
      [
          {
              "title": "To Kill a Mockingbird",
              "author": "Harper Lee"
          },
          {
              "title": "1984",
              "author": "George Orwell"
          }
      ]
      ```
- **Response**: Returns the saved book list with HTTP status `201 Created`.

### 2. Retrieve All Books

- **URL**: `/api/v1/book/all`
- **Method**: `GET`
- **Response**: Returns a list of all books with HTTP status `200 OK`.

### 3. Retrieve a Book by ID

- **URL**: `/api/v1/book/{id}`
- **Method**: `GET`
- **Path Variable**:
    - `id`: The ID of the book to retrieve.
- **Response**: Returns the book with HTTP status `200 OK`, or an error message with HTTP status `404 Not Found` if the book does not exist.

### 4. Delete a Book by ID

- **URL**: `/api/v1/book/{id}`
- **Method**: `DELETE`
- **Path Variable**:
    - `id`: The ID of the book to delete.
- **Response**: Returns HTTP status `204 No Content`, or an error message with HTTP status `404 Not Found` if the book does not exist.

### 5. Delete All Books

- **URL**: `/api/v1/book/deleteAll`
- **Method**: `DELETE`
- **Response**: Returns HTTP status `204 No Content`.

## Exception Handling

The application includes global exception handling for various error scenarios, such as:

- Invalid request body
- Book not found
- Internal server errors

Error responses are standardized with appropriate HTTP status codes.

## Running the Application

1. **Clone the Repository**:
   ```bash
   git clone https://github.com/sinshubhamkumar66/Library-Management.git

### Notes:
- Replace `<repository-url>`, `<your-username>`, and `<your-password>` with your actual repository URL and MySQL credentials.
- You can customize the sections further based on additional features or configuration settings specific to your application.
- Make sure to update the **Technologies Used** section if you add or change any libraries or frameworks.
