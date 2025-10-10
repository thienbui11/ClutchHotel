# DevOps Setup for HotelClutch

This repo includes Dockerized services, Postgres, and GitHub Actions for CI and container publishing.

## Services
- Backend: Spring Boot app on `8040`
- Frontend: React served via Nginx on `3000`
- Database: Postgres on `5432`

## Quick start
```bash
# Build and run all services
docker compose up -d --build

# View logs
docker compose logs -f backend
```

The frontend proxies `/api` to the backend via Nginx.

## Environment
- Postgres credentials are set in `docker-compose.yml` for local dev.
- Backend uses env vars: `DB_HOST=db`, `DB_PORT=5432`, `DB_NAME`, `DB_USER`, `DB_PASSWORD`.

## CI
Workflow: `.github/workflows/ci.yml`
- Backend: JDK 17, Maven build and tests
- Frontend: Node 20, npm ci, test, build

## Publish images
Workflow: `.github/workflows/docker-publish.yml`
- Triggers on tags like `v1.2.3`
- Publishes to GitHub Container Registry (`ghcr.io`)
  - `ghcr.io/<org>/hotelclutch-backend:latest`
  - `ghcr.io/<org>/hotelclutch-frontend:latest`

No extra secrets required; uses `GITHUB_TOKEN` for GHCR.

## Local image build
```bash
# Backend
docker build -t hotelclutch-backend:dev ./backend
# Frontend
docker build -t hotelclutch-frontend:dev --build-arg REACT_APP_API_BASE_URL=/api ./frontend
```

## Notes
- Adjust `REACT_APP_API_BASE_URL` if backend path changes.
- DB data is persisted in the `db-data` Docker volume.
