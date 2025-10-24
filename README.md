#  MELI Order Management System – Spring Boot 3.0

MELI, a leading e-commerce company, faced severe disruptions in its order management system due to misconfigured production environments and database node failures.  
MELI handles millions of transactions daily. Reliability is crucial — any downtime directly impacts revenue and customer trust.  
This project ensures **operational stability**, **traceability**, and **future scalability** through a well-documented and tested backend structure.
  
**Duration:** October 14 – October 23  

If the system is rebuilt with **Spring Boot 3.0**, using structured environment profiles, robust database connections, and **Swagger-based documentation**,  
then the order management platform will achieve:
- Higher stability and maintainability
- Lower error rates
- Faster debugging and onboarding for new developers
- Improved customer satisfaction

This project is a **RESTful API** for managing orders in an online store.  
It provides full **CRUD** (Create, Read, Update, Delete) functionality for `Order` entities, including validation and global exception handling.

Developed using **Spring Boot**, **Spring Boot 3.0**, **Java 17** , and **Gradle** as the build system.

---

## Project Scope

The project includes:
- Creation of `Order` entity and CRUD operations
- Integration of **SpringDoc OpenAPI 2.7.0**
- Configuration of **Spring Data JPA** with H2 (dev)
- Environment profiles: `application-dev.yml`, `application-test.yml`, `application-prod.yml`
- `OpenApiConfig.java` for documentation setup
- `@Tag`, `@Operation`, and `@ApiResponses` annotations on endpoints
- Unit and integration tests using JUnit
- ThunderClient collection for API validation
- Startup script for application launch

---

## Technologies Used

I used Java version 17. Create a Project using Grindl. And an Visual Studio Code extension called Spring Boot Dashboard so I can execute the project.

###  API Documentation
- `OpenApiConfig.java` – OpenAPI configuration bean  
- `swagger-config.yaml` – Swagger UI customization  
- `docs/openapi.json` – generated API specification  
- Interactive Swagger UI: `http://localhost:8080/swagger-ui.html`


## Roadmap

| **Phase**  | **Description**                                | **Duration** |
|------------|------------------------------------------------|---------------|
| Phase 1    | Project setup and dependency configuration     | 2 days |
| Phase 2    | CRUD operations and database setup             | 3 days |
| Phase 3    | Unit and integration testing                   | 2 days |
| Phase 4    | Profiles, environment variables, startup script | 2 days |
| Phase 5    | Documentation and GitHub delivery              | 1 day |
| Phase 6    | Peer review and final validation               | 1 day |
| **Total Duration** | — | **11 days (Oct 14 – Oct 23)** |



## Financial Investment

| **Service**                         | **Hours** | **Rate (MXN/hr)** | **Subtotal (MXN)** | **Description** 
|-------------------------------------|-----------|-------------------|--------------------|-----------------
| Spring Boot setup & entity creation | 8         | $280              | $2,240             | Project setup, base entity, dependencies 
| CRUD & database integration         | 10        | $290              | $2,900             | OrderController, Service, Repository 
| Swagger/OpenAPI integration         | 8         | $280              | $2,240             | SpringDoc setup, Swagger UI config 
| Unit & integration testing          | 10        | $290              | $2,900             | JUnit + Postman test cases 
| Profiles & scripts setup            | 5         | $260              | $1,300             | Profiles, env vars, startup script
| Documentation & README              | 4         | $250              | $1,000             | Technical documentation and usage guide |
| GitHub setup & access config        | 3         | $220               | $660               | Upload, permissions, repository setup |
| Peer review & debugging             | 6         | $270               | $1,620             | Code review, bug fixes, test validation |

**Subtotal:** $14,860 MXN  
**Margin (12%)**: $1,783.20 MXN  
**Total  Cost:** **$16,643.20 MXN**



## How It Works

1. The API receives HTTP requests (POST, GET, PUT, DELETE).  
2. `OrderController` processes each request and delegates logic to `OrderService`.  
3. `OrderService` validates input and interacts with the database via `OrderRepository`.  
4. Responses are returned in JSON format.  
5. Swagger UI documents and allows real-time testing of endpoints.  
6. Unit and integration tests ensure stability across environments.

## Dependencies used

- **Spring Boot Starter Web:** For building RESTful APIs  
- **Spring Boot Starter Data JPA:** For database access  
- **H2 Database:** In-memory database for development  
- **Spring Boot Starter Test:** For unit and integration testing  
- **Lombok:** To reduce boilerplate code (getters, setters, constructors) 

