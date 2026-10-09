# Job Portal — Java Spring Boot Web Application

The Job Portal is a Java-based web application developed to explore job listing management, job applications, user roles, and database-backed web development. It uses Spring Boot for backend development, Spring Security for security-related functionality, Thymeleaf for server-rendered pages, and MySQL for data persistence.

The project provides an opportunity to understand how a job portal can organize job listings and application workflows for different types of users.

## Table of Contents

- [Project Overview](#project-overview)
- [Project Objectives](#project-objectives)
- [Features](#features)
- [Technology Stack](#technology-stack)
- [Application Architecture](#application-architecture)
- [Project Structure](#project-structure)
- [User Roles and Workflows](#user-roles-and-workflows)
- [Data Model Overview](#data-model-overview)
- [Security and Validation](#security-and-validation)
- [Getting Started](#getting-started)
- [Database Configuration](#database-configuration)
- [Running the Application](#running-the-application)
- [Testing and Troubleshooting](#testing-and-troubleshooting)
- [Current Scope and Limitations](#current-scope-and-limitations)
- [Possible Future Improvements](#possible-future-improvements)
- [Learning Outcomes](#learning-outcomes)
- [Author](#author)

## Project Overview

The Job Portal explores the development of a web application that connects job listings with application-related workflows. The backend is built with Java and Spring Boot, while Thymeleaf supports rendering the application's web pages.

The application uses a relational database to persist its data and includes Spring Security dependencies for handling security-related behaviour. Its source code is organized as a Maven project.

## Project Objectives

- Develop a database-backed web application using Java and Spring Boot.
- Understand the organization of controllers and application components.
- Explore job listing and job application workflows.
- Integrate a relational database using Spring Data JPA.
- Use Thymeleaf to render web pages on the server.
- Explore authentication, authorization, and input validation.
- Practice application configuration, debugging, and testing.

## Features

### Job Listing Workflows

- Organize job listings within the application.
- Support job-related operations through the available user workflows.
- Store job-related information using the application's database layer.

### Job Application Workflows

- Represent job applications as part of the application's data model.
- Support application-related operations implemented in the current source code.
- Connect application records with the relevant user and job information.

### Role-Oriented Functionality

The codebase includes role-oriented components for student, employer, and administrator workflows.

- **Student:** Job-seeker-related pages and operations.
- **Employer:** Employer-related job management workflows.
- **Administrator:** Administrative functionality.

The precise permissions and actions available to each role depend on the current controller and security configuration.

### Backend and Database Integration

- Java-based backend using Spring Boot.
- Spring MVC for web request handling.
- Spring Data JPA for persistence-related functionality.
- MySQL database integration.
- Thymeleaf templates for server-rendered pages.
- Spring Security dependencies for security-related functionality.
- Validation support through the Spring validation ecosystem.

## Technology Stack

| Area | Technology |
|---|---|
| Programming language | Java 17 |
| Backend framework | Spring Boot 3.2.4 |
| Web framework | Spring Web / Spring MVC |
| Persistence | Spring Data JPA |
| Security | Spring Security |
| Frontend rendering | Thymeleaf |
| Frontend technologies | HTML, CSS, JavaScript, where present |
| Validation | Spring Boot Validation |
| Database | MySQL |
| Build and dependency management | Maven |
| Testing framework | Spring Boot Starter Test |

## Application Architecture

The application follows a typical Spring Boot web architecture.

1. **Presentation layer:** Thymeleaf templates render pages for users.
2. **Controller layer:** Spring MVC controllers receive requests and coordinate application operations.
3. **Application logic:** Backend code handles job listings, user-related workflows, and application processing.
4. **Persistence layer:** Spring Data JPA supports interactions with relational data.
5. **Database:** MySQL stores the application's persistent information.
6. **Security layer:** Spring Security supports configured authentication and authorization behaviour.

Conceptual request flow:

`Browser → Spring Security → Spring MVC Controller → Application Logic → Repository → MySQL`

The exact execution path varies by request and by the application's security configuration.

## Project Structure

The Maven application is located inside the `job-portal/` directory.

```text
job-portal/
├── .gitignore
├── .vscode/
└── job-portal/
    ├── pom.xml
    └── src/
        ├── main/
        │   ├── java/
        │   └── resources/
        └── test/
```

### Important Directories and Files

- `pom.xml` — Maven project configuration and dependencies.
- `src/main/java/` — Java source code, including application and web components.
- `src/main/resources/` — application properties, templates, and other resources where present.
- `src/test/` — test source directory.

Explore the Java packages to locate the controllers, entity classes, repositories, configuration, and other components actually present in the repository.

## User Roles and Workflows

The project contains role-oriented controller components for student, employer, and administrator functionality.

### Student workflow

The student-facing workflow is intended to support job-seeker activities. Explore the current implementation to confirm which listing, application, and account operations are available.

### Employer workflow

Employer-related components provide the foundation for employer-facing functionality. The available job creation, management, and application review operations should be confirmed from the source code.

### Administrator workflow

Administrative components support the functionality implemented for application administration. The exact actions and permissions depend on the current configuration.

These role descriptions explain the codebase's organization; they do not imply that every workflow or permission has been fully implemented or tested.

## Data Model Overview

The project includes data model concepts for users, jobs, and job applications.

| Model | Purpose |
|---|---|
| `User` | Represents application users. |
| `Job` | Represents job listing information. |
| `JobApplication` | Represents an application associated with a job and a user. |

The exact fields, relationships, constraints, and lifecycle rules are defined in the entity classes.

## Security and Validation

The project includes Spring Security and validation dependencies.

When running or extending the application, review:

- How users authenticate.
- How roles are assigned and checked.
- Which routes are accessible without authentication.
- Whether sensitive operations are restricted to the correct roles.
- How incoming form data is validated.
- How invalid input and failed operations are handled.
- How database credentials are supplied to the application.

Having a security dependency in the project does not by itself establish that all routes and user permissions are correctly protected.

## Getting Started

### Prerequisites

- JDK 17
- MySQL Server
- Maven
- Git
- An IDE such as Eclipse or IntelliJ IDEA, or a terminal

### 1. Clone the repository

```bash
git clone https://github.com/geethika3338/job-portal.git
```

### 2. Navigate to the Spring Boot application

```bash
cd job-portal/job-portal
```

### 3. Configure the database

Create or select the MySQL database expected by your local application configuration.

Update the database URL and credentials in your local configuration. Do not publish passwords or other sensitive values in the repository.

### 4. Build the project

```bash
mvn clean install
```

### 5. Run the application

```bash
mvn spring-boot:run
```

Check the terminal output for successful application startup and any database or configuration errors.

## Database Configuration

The application uses MySQL with Spring Data JPA.

For a safer local setup, configure the connection using environment variables rather than storing credentials directly in a tracked file.

For example, the datasource settings can use:

```properties
spring.datasource.url=${DB_URL}
spring.datasource.username=${DB_USERNAME}
spring.datasource.password=${DB_PASSWORD}
```

Set the corresponding environment variables in your terminal before launching the application. Ensure that the database name and user permissions match your local MySQL setup.

If the project uses automatic schema updates, review the JPA configuration before using real or important data. Automatic schema updates should not be treated as a replacement for a controlled production database migration process.

## Running the Application

The local port is controlled by the application's configuration. If you retain the current local configuration, the application is set to use port `8082`.

```text
http://localhost:8082
```

Use the port shown in your current configuration and startup logs if it has changed.

If the application redirects to a login page or restricts a route, inspect the configured Spring Security rules and the available user setup instructions.

## Testing and Troubleshooting

### MySQL connection failure

- Confirm that MySQL Server is running.
- Verify the database name and JDBC URL.
- Check the username, password, and database permissions.
- Review the startup logs for connection errors.

### Application fails to start

- Confirm that Java 17 is installed and selected.
- Check `java -version` and `mvn -version`.
- Run Maven commands from the directory containing `pom.xml`.
- Review the exception reported in the startup logs.

### Access or login issues

- Inspect the Spring Security configuration.
- Confirm how users and roles are created.
- Verify that the requested route is permitted for the signed-in role.
- Check whether the application requires initial database records or other setup.

### Form validation problems

Review the form fields, validation rules, controller handling, and error messages.

## Current Scope and Limitations

This project is intended for learning and development. Production readiness should not be assumed without further verification.

Areas that may require additional work include:

- Thorough testing of authentication and role-based access.
- Strong input validation and consistent error handling.
- Secure storage and management of credentials.
- Well-defined job and application lifecycle rules.
- Database constraints and transaction handling.
- Automated tests for controllers, services, security, and persistence.
- Clear deployment and database migration instructions.

## Possible Future Improvements

- Improve job search and filtering.
- Add pagination for larger job listings.
- Improve job application status tracking.
- Strengthen role-based authorization and account management.
- Improve form validation and user-facing error messages.
- Add unit and integration tests.
- Document the exact routes and supported request formats.
- Improve logging and exception handling.
- Prepare environment-specific configuration for deployment.

These are possible future enhancements and are not claims that they are already available.

## Learning Outcomes

This project provides practical exposure to:

- Java backend application development.
- Spring Boot and Spring MVC.
- Spring Data JPA and relational data modelling.
- MySQL integration.
- Thymeleaf-based web applications.
- Spring Security concepts.
- Validation and request handling.
- Maven project setup and debugging.
- Organizing user, job, and application-related components.

## Author

**Geethika B.**

- GitHub: https://github.com/geethika3338
