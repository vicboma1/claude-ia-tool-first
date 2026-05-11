# project-tool-first (Opción A)

Arquitectura **Tool-First mínima**, basada en:

- tools deterministas
- workflows explícitos
- specs como fuente de verdad

No hay agentes inteligentes.
Ideal para:
- demos
- CI/CD
- cambios simples y reproducibles

## Claude

Los specs definidos en `.claude/specs/` han sido implementados en local por Claude Code siguiendo el workflow `implement-spec.yaml`. Cada spec tiene su propia rama de feature y se encuentra en una Merge Request abierta del proyecto pendiente de mergear a `main`:

- **!1** `feature/spec/export-users-csv.md` — Exportación de usuarios activos en CSV con streaming
- **!2** `feature/spec/async-controllers` — Controllers reactivos con Mono y Flux (WebFlux)
