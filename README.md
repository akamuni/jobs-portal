# AppDocs API (Java 8, Spring Boot 2.7.x)

## Quick Start (IntelliJ, Java 8)
1. File → Open → select this folder (`app-docs-java8`).
2. Wait for Maven to import.
3. Run `AppDocsApplication` (green play button).

API base: `## Deployment
This project is deployed on AWS ECS (Fargate/EC2) and accessible publicly at:

http://jobsportal-lb-301757636.us-east-1.elb.amazonaws.com/swagger-ui/index.html

The container is built from the Dockerfile in this repo and hosted via ECS Service (Fargate) behind a public IP.
`  

## cURL Examples
See `AppDocs.postman_collection.json` or use the examples in the README.

## Docker (optional)
```
docker build -t app-docs-java8 .
docker run --rm -p 8080:8080 app-docs-java8
```
