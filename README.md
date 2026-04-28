# Smart Kanban Board

Runnable Spring Boot Kanban app with cards, labels, and status movement across columns.

## Run

```bash
mvn spring-boot:run
```

Open `http://localhost:8080`.

## Tech Stack

- Java 21
- Spring Boot 3.5.x
- Spring Web and Bean Validation
- Vanilla browser UI

## What I Am Learning

- Workflow state transitions.
- CRUD-style REST APIs.
- Frontend state refresh after server updates.

## APIs

- `GET /api/cards`
- `POST /api/cards`
- `PATCH /api/cards/{id}/move`
