# Job Portal — Web Application

A Java and Spring Boot job portal project for exploring job listings and application workflows. The application source is organized in the `job-portal/` directory.

## Project Goals

- Organize job listings in one place
- Provide role-oriented workflows for job seekers and other users supported by the application
- Practice backend development, server-rendered web pages, and database integration

## Tech Stack

The repository contains a Maven-based Java project. Confirm the exact framework and dependency versions in `job-portal/pom.xml` before setting up the environment.

## Project Structure

```text
job-portal/
├── .gitignore
├── .vscode/
└── job-portal/
    ├── pom.xml
    └── src/
```

## Getting Started

1. Clone this repository.
2. Open a terminal in the application directory:

   ```bash
   cd job-portal
   ```

3. Check `pom.xml` for the required Java version.
4. Configure the database connection in the application's configuration file. Keep passwords and other secrets out of Git.
5. Run the application with Maven:

   ```bash
   mvn spring-boot:run
   ```

6. Check the startup logs for the configured local port, then open the application in your browser.

## Configuration and Security

Use your own local database credentials. Before presenting this project as production-ready, verify authentication and role-based authorization, input validation, error handling, and the database setup instructions.

## Author

Geethika B. — [GitHub profile](https://github.com/geethika3338)
