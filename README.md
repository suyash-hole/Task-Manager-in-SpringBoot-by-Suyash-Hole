# Task Manager Application

A simple CRUD application using Spring Boot and H2 database to manage tasks.

## Developer

Suyash Hole

## Project Structure

```
task-manager
├── src
│   ├── main
│   │   ├── java
│   │   │   └── com
│   │   │       └── example
│   │   │           └── taskmanager
│   │   │               ├── controller
│   │   │               │   └── TaskManagerController.java
│   │   │               ├── model
│   │   │               │   └── Task.java
│   │   │               ├── repository
│   │   │               │   └── TaskRepository.java
│   │   │               └── service
│   │   │                   ├── TaskManagerService.java
│   │   │                   
│   │   └── resources
│   │       ├── application.properties
│   │       └── data.sql
│   └── test
│       └── java
│           └── com
│               └── example
│                   └── taskmanager
│                       └── controller
│                           └── TaskManagerControllerTest.java
└── pom.xml
```

## Technologies Used

- Java
- Spring Boot
- H2 Database
- Spring Data JPA
- Mockito
- JUnit 5

## Getting Started

### Prerequisites

- Java 8 or later
- Maven
- IDE (Prefer: Eclipse/STS)

### Setup

1. Clone the repository

```bash
git [clone https://github.com/your-username/task-manager.git](https://github.com/suyash-hole/Task-Manager-in-SpringBoot-by-Suyash-Hole.git)
cd 'Folder'
```

2. Build the project using Maven

```bash
mvn clean install
```

3. Run the application

```bash
mvn spring-boot:run
```

### Configuration

The application uses an H2 in-memory database. The database schema and initial data can be found in `src/main/resources/data.sql`.

### API Endpoints

The application exposes the following RESTful endpoints:

- **GET /api/taskmanager/tasks**: Retrieve all tasks
- **GET /api/taskmanager/tasks/{id}**: Retrieve a task by ID
- **POST /api/taskmanager/createtask**: Create a new task
- **PUT /api/taskmanager/updatetask/{id}**: Update an existing task
- **DELETE /api/taskmanager/deletetask/{id}**: Delete a task

### Running Tests

To run the tests, use the following command:

```bash
mvn test
```

```
