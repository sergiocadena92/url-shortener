# Project Name
Url Shortener RestAPI

## Description
Simple API REST application to shortens a valid URL   

## Installation
### Prerequisites
- Java version: Java 17
- Build tool: Gradle
- Database: PostgreSQL

### Steps to Build
1. Build the project using Gradle:
    ``bash
    ./gradlew build
    ``

## Usage
### Running the Application
    ```bash
    java -jar libs/url-shortener-0.0.1-SNAPSHOT.jar
    ```

### Generate docker image
    ```bash
    docker build -t url-shortener .
    ```
### Run docker container
    ```bash
    docker run -p 8080:8080 url-shortener
    ```

### API Endpoints (if applicable)
- List the main RESTful API endpoints with brief descriptions.
    - `GET /`: Retrieves the original URL given the short URL then redirects.
    - `POST /url`: Creates a new URL resource.

### Example Requests
- Examples of how to interact with the application, using `curl`, Postman, or any other tool.

- `POST /url`:
  `curl --location 'http://localhost:8080/url' \
  --header 'Content-Type: application/json' \
  --data '{
  "originalUrl": "https://github.com"
  }'`

- `GET /`:
  `curl --location --request GET 'http://localhost:8080/hw4LYS' \
  --header 'Content-Type: application/json' \
  --data ''`
- 
## Features
- List the key features of the project.
    - Feature 1: Shorten a given URL and return shortened link.
    - Feature 2: Expand a shortened URL into the original one and redirect to the original location.
    - Feature 3: Implements an in-memory caching strategy to handle high volume or load.
    - Feature 4: Incoming URLs are decoded to prevent malicious code.
    - Feature 5: PostgreSQL relational database used as a persistence engine.
    - Feature 6: Dockerfile ready to create images and containers once the application is built.
    - Feature 7: Scheduled automatic removal for old records in the database.

## Configuration
### Application Properties
- Main configuration settings in `application.yml`

## Testing
### Unit Tests
- To run the unit tests with gradle run the command.
    ```bash
    ./gradlew test
    ```

### Test Coverage
- JaCoCo test coverage command.
  ```bash
  ./gradlew test jacocoTestReport
  ```

## Contact
- Sergio Alejandro Cadena Gonzalez - sergiocadena92@gmail.com

