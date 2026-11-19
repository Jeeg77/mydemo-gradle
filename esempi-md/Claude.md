# Project Instructions

## Project overview

This is a Java 17 Spring Boot application built with Maven.

## Architecture

- `controller`: REST controllers and API endpoints.
- `client`: external service clients.
- `entity`: JPA entities and repositories.

## Coding conventions

- Use constructor injection instead of field injection.
- Follow existing naming conventions.
- Keep business logic inside service classes.
- Use DTOs for API request and response objects.

## Build and tests

- Build: `mvn clean install`
- Tests: `mvn test`

## Important rules

- You can push directly to the `main` branch.
- Do not modify database schemas without updating Liquibase changelogs.
- Preserve existing authentication and authorization rules.
