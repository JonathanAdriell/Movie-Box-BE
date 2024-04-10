# Movie-Box-BE

MovieBox is a project that provides functionality for retrieving movies, as well as creating, editing, and deleting reviews for movies stored in a database. This repository contains the backend implementation of MovieBox, built using Spring Boot. It's designed to interact with MongoDB and is equipped with unit tests created using JUnit and Mockito. Additionally, code coverage reports are generated using JaCoCo.

## Getting Started

To get started with MovieBox, follow these steps:

1. Clone this repository to your local machine.
2. Create a `.env` file based on the template provided in `.env.example`.
3. Configure the MongoDB connection settings in the `.env` file.
4. Simply run `MovieBoxApplication.java`.
5. Access the application endpoints to interact with the movie and review resources.

To get the code coverage, follow these steps:

1. Simply execute `mvn clean` followed by `mvn test`.
2. Navigate to the `target/site/jacoco`.
3. Open `index.html`.

## API Endpoints

- GET `api/v1/movie`: Retrieves all movies.
- GET `api/v1/movie/{imdbId}`: Retrieves an existing movie.
- POST `/api/v1/review/create`: Creates a review.
- PATCH `/api/v1/review/update/{id}`: Updates an existing review.
- DELETE `/api/v1/review/delete/{id}`: Deletes an existing review.