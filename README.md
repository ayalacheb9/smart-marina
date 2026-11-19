# Smart Marina

Projet Atelier SOA (ISET Mahdia) : plateforme de gestion de bornes énergétiques pour ports de plaisance, en microservices Spring Boot.

## Architecture

| Composant | Port | Base de données |
|---|---|---|
| api-gateway | 8080 | aucune |
| pedestal-service | 8081 | postgres-pedestal (5434) |
| port-service | 8082 | postgres-port (5433) |
| Kafka | 9092 | |

Chaque service possède sa propre base. Les services ne partagent aucune relation JPA : ils échangent des identifiants et des appels REST.

## Prérequis

Java 21, Docker Desktop, Postman. Maven est fourni par le wrapper `mvnw` de chaque service.

## Lancement

1. Démarrer l'infrastructure, à la racine du dépôt :
   `docker compose up -d`
2. Lancer chaque service dans sa propre fenêtre :
   - `cd port-service` puis `.\mvnw.cmd spring-boot:run`
   - `cd pedestal-service` puis `.\mvnw.cmd spring-boot:run`
   - `cd api-gateway` puis `.\mvnw.cmd spring-boot:run`

## Routes (via la Gateway, port 8080)

| Méthode | Route | Service |
|---|---|---|
| GET, POST | `/api/v1/ports` | port-service |
| GET, PUT, DELETE | `/api/v1/ports/{id}` | port-service |
| GET, POST | `/api/v1/pedestals` | pedestal-service |
| GET, PUT, DELETE | `/api/v1/pedestals/{id}` | pedestal-service |

## Codes HTTP

201 création, 200 succès, 204 suppression, 400 données invalides ou port inexistant, 404 ressource introuvable, 409 numéro de série déjà utilisé, 503 port-service indisponible.

## Tests

Collection Postman dans `docs/smart-marina-collection.json`.