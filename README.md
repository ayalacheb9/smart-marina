# Smart Marina

Projet Atelier SOA (ISET Mahdia) : plateforme de gestion de bornes énergétiques pour ports de plaisance, en microservices Spring Boot.

## Architecture

| Composant | Port | Base de données |
|---|---|---|
| api-gateway | 8080 | aucune |
| pedestal-service | 8081 | postgres-pedestal (5434) |
| port-service | 8082 | postgres-port (5433) |
| consumption-service | 8083 | postgres-consumption (5435) |
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
   - `cd consumption-service` puis `.\mvnw.cmd spring-boot:run`
   - `cd api-gateway` puis `.\mvnw.cmd spring-boot:run`

## Routes (via la Gateway, port 8080)

| Méthode | Route | Service |
|---|---|---|
| GET, POST | `/api/v1/ports` | port-service |
| GET, PUT, DELETE | `/api/v1/ports/{id}` | port-service |
| GET, POST | `/api/v1/pedestals` | pedestal-service |
| GET, PUT, DELETE | `/api/v1/pedestals/{id}` | pedestal-service |
| GET, POST | `/api/v1/consumptions` | consumption-service |
| GET | `/api/v1/consumptions/{id}` | consumption-service |

Les mesures de consommation ne sont ni modifiables ni supprimables : une mesure est un fait passé. La liste accepte le filtre `?pedestalId=`.

## Règles de gestion

- Une borne est rattachée à un port par son identifiant (`portId`), vérifié par un appel REST à port-service.
- Une mesure est rattachée à une borne par son identifiant (`pedestalId`), vérifié par un appel REST à pedestal-service.
- Chaque mesure possède un identifiant unique (`measurementId`) pour éviter les doublons. Il est généré si le client n'en fournit pas.
- L'unité est déduite de la ressource : kWh pour l'électricité, m3 pour l'eau et le gaz.
- La date de mesure (`measuredAt`) et la date de réception (`receivedAt`) sont enregistrées séparément.

## Codes HTTP

| Code | Signification |
|---|---|
| 200 | Succès |
| 201 | Création |
| 204 | Suppression |
| 400 | Données invalides, port ou borne inexistant |
| 404 | Ressource introuvable |
| 409 | Numéro de série ou mesure déjà enregistré |
| 503 | Service appelé indisponible |

## Tests

Collection Postman dans `docs/smart-marina-collection.json`.