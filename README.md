# AppDocs API (Java 8, Spring Boot 2.7.x)

## Quick Start (IntelliJ, Java 8)
1. File → Open → select this folder (`app-docs-java8`).
2. Wait for Maven to import.
3. Run `AppDocsApplication` (green play button).

API base: `http://localhost:8080`  
Swagger UI: `http://localhost:8080/swagger-ui.html`

## cURL Examples
See `AppDocs.postman_collection.json` or use the examples in the README.

## Docker (optional)
```
docker build -t app-docs-java8 .
docker run --rm -p 8080:8080 app-docs-java8
```
