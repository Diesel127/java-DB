# User Profiles API

Spring Boot 3 REST API managing user profiles with:
- CRUD endpoints
- Validation (Hibernate Validator)
- Exception handling
- DTO mapping (MapStruct)
- JWT auth with RBAC (Spring Security)
- OpenAPI (Swagger UI)
- Layered architecture (Controller → Service → Repository)
- Unit tests for service & controller

## Run

```bash
mvn spring-boot:run
```

Swagger: `http://localhost:8080/swagger-ui/index.html`  
H2 Console: `http://localhost:8080/h2-console` (JDBC URL: `jdbc:h2:mem:userdb`)

## Auth

- Register: `POST /api/auth/register` `{ "email":"a@b.com", "password":"secret123", "fullName":"Alice" }`
- Login: `POST /api/auth/login` → `{ "token": "Bearer-Token" }`
- Use header: `Authorization: Bearer <token>`

## Profiles

- `GET /api/profiles/{id}` owner or ADMIN
- `PUT /api/profiles/{id}` owner or ADMIN
- `GET /api/profiles` ADMIN
- `POST /api/profiles` ADMIN
- `DELETE /api/profiles/{id}` ADMIN

> **Note**: For demo the JWT secret is in `application.yml`. Provide a strong secret in production via env var `JWT_SECRET`.
