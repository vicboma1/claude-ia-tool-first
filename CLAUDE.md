# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Build & Test Commands

```bash
# Compile
mvn clean compile

# Run all tests
mvn clean test

# Run a single test class
mvn test -Dtest=UserServiceTest

# Package
mvn clean package

# Run via Docker (mirrors CI)
docker compose run --rm app mvn clean compile
docker compose run --rm app mvn clean test
```

## Architecture

Spring Boot 3.3.0 / Java 21 REST API with a **Tool-First** architecture for AI-driven development.

**Package root:** `com.example.demo`

**Layers:**
- `controller/` — HTTP endpoints only, no business logic
- `service/` — All business logic lives here
- `model/` (if present) — Domain objects

**Current classes:**
- `DemoApplication` — Spring Boot entry point (`@SpringBootApplication`)
- `user/UserController` — `GET /api/users/export` returns CSV as plain text
- `user/UserService` — Business logic for user data operations

**Tool-First workflow** — features are implemented by following specs in `.claude/specs/` through the workflow defined in `.claude/workflows/implement-spec.yaml`. Steps: `search_code → apply_patch → run_tests → run_lint`. Each step maps to a script in `.claude/tools/`.

## Specs & Workflows

Feature specs in `.claude/specs/` are the source of truth for what to implement:

| Spec | Description | Status |
|------|-------------|--------|
| `export-users-csv.md` | `GET /api/users/export` — streaming CSV, UTF-8, handles 1M users | In progress (MR !1) |
| `async-controllers.md` | Reactive WebFlux controllers returning `Mono`/`Flux` | In progress (MR !2) |

To implement a spec, follow `.claude/workflows/implement-spec.yaml` step by step.

**Tools available** (`/.claude/tools/`):
- `search.sh` — `grep -R "$1" src/`
- `test.sh` — `./mvnw test`
- `patch.sh` — Apply patches (manual placeholder)
- `lint.sh` — Checkstyle/SpotBugs (not yet implemented)

## Rules

Project-specific rules are in `.claude/rules/` — read and apply them for every change:

- **`java.md`** — Constructor injection required; no `@Autowired` on fields; SLF4J for all logging; no business logic in controllers; unit tests required.
- **`sql.md`** — Never `SELECT *`; indexes required; migrations must be reversible.

## CI/CD

GitHub Actions (`.github/workflows/maven.yml`) runs `build` and `test` stages using the `maven:3.9.6-eclipse-temurin-21` container. Maven `.m2` dependencies are cached between runs. Docker Compose (`docker-compose.yml`) uses the same image for local parity with CI.

## Testing

- Framework: JUnit 5 (via `spring-boot-starter-test`)
- Test source dir: `src/test/`
- Tests instantiate services directly — no Spring context needed for unit tests
- Run a single class: `mvn test -Dtest=UserServiceTest`

## Notes

- No database or persistence layer is configured yet; SQL rules apply when one is added.
- No `application.properties` exists; Spring Boot defaults are in effect.
