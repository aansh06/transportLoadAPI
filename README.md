# Transport Load API

## Description
Transport Load API is a RESTful API built with Spring Boot that facilitates the management and transportation of loads. It allows users to create, read, update, and delete load records, making it easier to manage logistics operations.

## Features
- Create new load records
- Retrieve load records
- Update existing load records
- Delete load records
- Built using Spring Boot and Hibernate for data persistence
- HikariCP for efficient database connection pooling

## Technologies Used
- Java 23
- Spring Boot
- Spring Data JPA
- Hibernate
- PostgreSQL
- HikariCP
- Maven for dependency management

## Requirements
- Java 23 or higher
- PostgreSQL database
- Maven

## Installation

### Clone the Repository
```bash
git clone https://github.com/aansh06/transportLoadAPI.git
cd transportLoadAPI
```

### Configure Database
1. Create a new PostgreSQL database.
2. Update the `src/main/resources/application.properties` file with your database credentials:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/${DB_NAME}
spring.datasource.username=${DB_USERNAME}
spring.datasource.password=${DB_PASSWORD}
```


### Set Environment Variables
Set the required environment variables before running the application:
```bash
export DB_NAME=your_database
export DB_USERNAME=your_username
export DB_PASSWORD=your_password
```

### Build the Project
Use Maven to build the project:
```bash
mvn clean install
```

### Run the Application
Run the application with the following command:
```bash
mvn spring-boot:run
```

The API will be available at `http://localhost:8080`.

## API Endpoints

### Create Load
- **URL**: `/loads`
- **Method**: `POST`
- **Body**: JSON object with load details.
- **Response**: Load details added successfully.

### Get All Loads
- **URL**: `/loads`
- **Method**: `GET`
- **Response**: List of all load records.

### Get Load by ID
- **URL**: `/loads/{id}`
- **Method**: `GET`
- **Response**: Load record with the specified ID.

### Update Load
- **URL**: `/loads/{id}`
- **Method**: `PUT`
- **Body**: JSON object with updated load details.
- **Response**: Updated load record.

### Delete Load
- **URL**: `/loads/{id}`
- **Method**: `DELETE`

