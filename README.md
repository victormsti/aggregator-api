# API Aggregator

## Overview
This project implements an API aggregator service that connects to the Kenect Labs API to retrieve contact information, transform it into a common model, and expose it through a RESTful API. The service handles pagination by making multiple requests to the external API and aggregates the results.

## Features
- **Fetch Contacts**: Retrieve a list of contacts from the Kenect Labs API.
- **Get Contact by ID**: Retrieve detailed information for a specific contact using their unique ID.
- **Error Handling**: Graceful handling of potential errors during API requests.

## Local Setup

### Prerequisites
- Java 17 or higher
- Maven
- An internet connection to access the external API

### Building the app
- Run the following command:
  - ```mvn clean package```

- If you are using the Maven wrapper, you can run:
  - ```./mvnw clean package```

This command will:

- Clean the project (remove previously compiled files)
- Compile the source code
- Run the tests
- Package the application into a JAR file, which will be placed in the `target` directory.

### Running tests

This project has unit and integration tests

To run all tests, use the following command:
- ```mvn test```


- If you are using the Maven wrapper, you can run:
  - ```./mvnw test```


- To run a specific test class, you can use the following command:
  - ```mvn -Dtest=YourTestClassName.java test```


- If you are using the Maven wrapper, you can run:
  - ```./mvnw -Dtest=YourTestClassName.java test```


### Running the App via IDE
To run the application locally, you need to set up the **local** profile. 

You can do so by:

- Setting the local profile in your `application.yml` file:
  - ```yaml
        spring:
          profiles:
            active: local

- Or you can set this environment variable before running the app:
  - ```spring.profiles.active=local```

### Running the App via Command Line

Alternatively, we can run the app via command line by the following commands

```export SPRING_PROFILES_ACTIVE=local```

```mvn spring-boot:run```

**Running with Maven Wrapper:** If you don’t have Maven installed, you can use the Maven wrapper:

```./mvnw spring-boot:run```

## API Endpoints

#### Note: you need a valid token to access the contact endpoints. 
#### For that, you need to call the authentication endpoint first to generate the token. 
#### Note that the username and password are **fixed** just for demonstration purposes:

- **Endpoint**: `POST /api/v1/auth/token/login`
- **Description**: Generates a JWT token using the fixed credentials.
- **Request Body**:
  ```json
  {
      "username": "bestuser",
      "password": "bestpassword"
  }
- **Response Example**:
  ```json
  {
    "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
  }
- **Note**: You need this token for accessing the contact endpoints. Pass it in the **Authorization** header as `Bearer <your-token>`

### 1. Fetch All Contacts
- **Endpoint**: `GET /api/v1/contacts`
- **Description**: Retrieves all contacts from the Kenect Labs API and returns them in a JSON array.
- **Authentication**:a JWT Bearer token generated from `api/v1//auth/token`.
- **Response Example**:
  ```json
  {
      contacts: [
        {
            "id": 1,
            "name": "Mrs. Willian Bradtke",
            "email": "jerold@example.net",
            "createdAt": "2020-06-24T19:37:16.688Z",
            "updatedAt": "2020-06-24T19:37:16.688Z"
        },
        {
            "id": 2,
            "name": "John Doe",
            "email": "johndoe@example.net",
            "createdAt": "2021-02-10T11:10:09.987Z",
            "updatedAt": "2022-05-05T15:27:17.547Z"
        }
    ]
  }

- **Endpoint**: `GET api/v1/contacts/{id}`
- **Description**: Retrieves a specific contact by their unique ID.
- **Authentication**:a JWT Bearer token generated from `api/v1//auth/token`.
- **Response Example**:
  ```json
  {
    "id": 2,
    "name": "John Doe",
    "email": "johndoe@example.net",
    "createdAt": "2021-02-10T11:10:09.987Z",
    "updatedAt": "2022-05-05T15:27:17.547Z"
  }

That's it!

If you have any questions, please feel free to reach out to me at: [victormsti@gmail.com](mailto:victormsti@gmail.com)