# Project Instructions

## Project overview

Java 17 application based on Spring Boot and Gradle.

## Architecture

- controller: REST endpoints.
- service: business logic.
- repository: data access.
- entity: persistence entities.
- dto: request and response objects.

## Coding conventions

- Use constructor injection.
- Keep business logic inside service classes.
- Use DTOs for REST requests and responses.
- Follow the existing project structure.

## Build and tests

- Build: ./gradlew build
- Tests: ./gradlew test

## Git and workflow rules

- The project currently uses only the main branch.
- Work directly on the current branch unless explicitly instructed otherwise.
- Never create, switch or delete branches without asking first.
- Never execute git reset --hard, git clean -fd or force push without explicit authorization.
- Never overwrite or discard existing user changes.
- Before finishing a task, run ./gradlew test.
- Always summarize the modified files and test results.
