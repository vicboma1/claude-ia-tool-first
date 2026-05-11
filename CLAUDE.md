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

# Run via Docker (mirrors CI)
docker compose run --rm app mvn clean compile
docker compose run --rm app mvn clean test

# Package
mvn clean package
```

## Architecture

Spring Boot 3.3 / Java 21 REST API with a **Tool-First** architecture for AI-driven development.

**Package root:** `com.example.demo`

**Layers:**
- `controller/` — HTTP endpoints only, no business logic
- `service/` — All business logic lives here
- `model/` (if present) — Domain objects

**Tool-First workflow** — features are implemented by following specs in `.claude/specs/` through the workflow defined in `.claude/workflows/implement-spec.yaml`. The steps are: `search_code → apply_patch → run_tests → run_lint`. Each step maps to a script in `.claude/tools/`.

## Specs & Workflows

- Feature specs live in `.claude/specs/` and are the source of truth for what to implement.
- To implement a spec, follow `.claude/workflows/implement-spec.yaml` step by step.
- Tools available: `search.sh`, `test.sh`, `patch.sh`, `lint.sh` (in `.claude/tools/`).

## Rules

Project-specific rules are in `.claude/rules/` — read and apply them for every change:

- **`java.md`** — Constructor injection required; no `@Autowired` on fields; SLF4J for all logging; no business logic in controllers.
- **`sql.md`** — Never `SELECT *`; indexes required; migrations must be reversible.

## CI/CD

GitLab CI (`.gitlab-ci.yml`) runs build and test stages using `maven:3.9.6-eclipse-temurin-21` via Docker Compose. The Maven `.m2` cache is preserved between runs.
