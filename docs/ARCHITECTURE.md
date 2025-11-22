# Tire Test Reporting Platform

## 1. High-level architecture overview
- **Backend:** Quarkus REST API exposing resources for labs, tires, and test runs. Layered as JAX-RS resources → services (business rules, validation) → repositories (Panache) → domain entities.
- **Frontend:** Angular app with feature modules for labs and tests, core services for API calls, and shared UI. Routing maps `/labs`, `/tests`, `/tests/:id`, and `/tests/create`.
- **Communication:** Frontend consumes backend JSON APIs over HTTP. Backend persists to PostgreSQL via JPA/Hibernate with Flyway migrations.
- **Docker/docker-compose:** Compose starts PostgreSQL, backend, and frontend containers. Backend depends on DB; frontend served via nginx and points to backend service.

## 2. Repository structure (monorepo)
```
tire-test-reporting-platform/
  backend/    # Quarkus project (REST API, Flyway, tests)
  frontend/   # Angular app (labs/tests features)
  infra/      # Dockerfiles and docker-compose
  docs/       # Architecture notes
  .github/    # CI pipeline
```

## 3. Backend summary
- **Dependencies:** RESTEasy Reactive, Jackson, Hibernate ORM + Panache, Bean Validation, PostgreSQL driver, Flyway, JUnit/Mockito for tests.
- **Domain:** Lab, Tire, TestRun, TestMetric with relations and validation.
- **DTOs & mapping:** Records for API payloads with manual mappers.
- **Services:** LabService, TireService, TestRunService (validates metrics present, resolves lab/tire, persists cascade).
- **Resources:** `/api/labs`, `/api/tires`, `/api/tests` (filters by lab/date), stats endpoint `/api/tests/stats/average-grip`.

## 4. Database schema & migrations
- Flyway `V1__initial_schema.sql` creates `labs`, `tires`, `test_runs`, `test_metrics`, FK constraints, and index on `(lab_id, test_date)`.
- Oracle adaptation: replace `BIGSERIAL` with sequences + triggers or `NUMBER`, and `DOUBLE PRECISION` with `NUMBER(10,2)`.

## 5. Frontend summary
- **Structure:** Core models/services, shared feature modules (`labs`, `tests`), app routing.
- **Screens:** Lab list, test list with filters, test detail, test create form with dynamic metrics.
- **Practices:** Reactive forms, async pipe, service abstraction over HttpClient.

## 6. Docker & compose
- Backend Dockerfile: multi-stage Maven build → JRE runtime exposing 8080.
- Frontend Dockerfile: Node build → nginx serve on 80; API base URL expected at `/api` (proxied by compose network).
- docker-compose: services `db` (PostgreSQL), `backend`, `frontend`; environment variables for DB credentials.

## 7. CI pipeline (GitHub Actions)
- Workflow triggers on push/PR to main. Steps: checkout, set up JDK 17, run `mvn -f backend/pom.xml verify`; set up Node 20, run `npm ci` + `npm run build` in frontend.

## 8. How to run locally
- Prereqs: Docker/Docker Compose (or Java 17 + Maven, Node 20 if running manually).
- Build & start: `docker compose -f infra/docker-compose.yml up --build`.
- URLs: Backend `http://localhost:8080/api`, Frontend `http://localhost:8081` (nginx). For dev, `npm start` serves at `http://localhost:4200`.
- Quick test: `curl http://localhost:8080/api/labs` returns labs list (empty on fresh DB).
