---
name: create-rest-endpoint
description: Creates REST endpoints following the project's architecture and coding conventions. Use when asked to implement a new API endpoint.
---

# Create REST Endpoint

When implementing a new REST endpoint:

1. Analyze existing controllers and services.
2. Create the required DTOs.
3. Add the endpoint to the appropriate controller.
4. Implement business logic in the service layer.
5. Use the existing repository where necessary.
6. Respect authentication and authorization rules.
7. Add unit tests.

Before finishing:

- Run ./gradlew test.
- Report the files created or modified.
- Report any test failures.

Read references/api-conventions.md before implementing the endpoint.
