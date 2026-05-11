## Export Active Users CSV

### Objetivo
Permitir exportar usuarios activos en formato CSV.

### Endpoint
GET /api/users/export

### Requisitos
- Streaming obligatorio (no cargar todo en memoria)
- Encoding UTF-8
- Soportar hasta 1M de usuarios

### Casos borde
- Emails nulos
- Caracteres Unicode
