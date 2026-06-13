# Spring Boot to Quarkus Migration with HTTP & gRPC

Migrate the current Spring Boot 3.x application to Quarkus 3.x while preserving the Gradle build system (`build.gradle.kts`). Expose both HTTP REST endpoints and gRPC services sharing port 8080.

## User Review Required

> [!IMPORTANT]
> - **Port Sharing**: By default, Quarkus co-locates the gRPC server and HTTP router on the same port (`8080`) using HTTP/2 protocol negotiation. No separate port is needed.
> - **Spring to Jakarta REST (JAX-RS) and CDI**: Web controllers and beans will be refactored to use standard JAX-RS (`@Path`, `@GET`, `@QueryParam`) and CDI (`@ApplicationScoped`, `@Inject`) annotations.
> - **Custom RestClient**: We will implement a lightweight `RestClient` utility in `com.trade.securities.infrastructure` mimicking Spring's `RestClient` builder pattern, avoiding the need to refactor 11 external adapters.

## Proposed Changes

### Build Configuration

#### [MODIFY] [build.gradle.kts](file:///Volumes/data/code/trade/tradebe/build.gradle.kts)
- Replace Spring Boot plugins with the Quarkus Gradle plugin (`io.quarkus` version `3.15.2` or latest stable).
- Remove Spring starter dependencies and add Quarkus dependencies:
  - `io.quarkus:quarkus-kotlin`
  - `io.quarkus:quarkus-rest` (standard RESTEasy Reactive replacement for Spring Web)
  - `io.quarkus:quarkus-rest-jackson`
  - `io.quarkus:quarkus-grpc`
- Retain Kotlin JVM and other utility libraries.
- Configure source sets for generated gRPC classes if necessary.

### Application Properties

#### [DELETE] [application.yaml](file:///Volumes/data/code/trade/tradebe/src/main/resources/application.yaml)
#### [DELETE] [application-local.yaml](file:///Volumes/data/code/trade/tradebe/src/main/resources/application-local.yaml)
#### [DELETE] [application-prod.yaml](file:///Volumes/data/code/trade/tradebe/src/main/resources/application-prod.yaml)
#### [NEW] [application.properties](file:///Volumes/data/code/trade/tradebe/src/main/resources/application.properties)
- Define settings in Quarkus property format (using profiles e.g. `%prod.`, `%dev.`).
- Set `quarkus.http.port=8080` and `quarkus.grpc.server.use-origin-co-located-server=true` to enable shared port 8080.

### Infrastructure & REST Client

#### [MODIFY] [RestClientConfig.kt](file:///Volumes/data/code/trade/tradebe/src/main/kotlin/com/trade/securities/infrastructure/RestClientConfig.kt) (or replacement)
- Implement a lightweight wrapper matching the Spring `RestClient` API using `java.net.http.HttpClient` or Vert.x WebClient.
- Define a custom `UriComponentsBuilder` utilizing `jakarta.ws.rs.core.UriBuilder` to avoid refactoring external adapters.

#### [MODIFY] [CorsConfig.kt](file:///Volumes/data/code/trade/tradebe/src/main/kotlin/com/trade/securities/infrastructure/CorsConfig.kt)
- Replace Spring's CORS config with Quarkus built-in HTTP CORS properties in `application.properties` (e.g. `quarkus.http.cors=true`, `quarkus.http.cors.origins=...`).

### Application Services & Web Adapters

#### [MODIFY] all controllers in `com.trade.securities.adapter.in.web`
- Change `@RestController` -> `@Path`
- Change `@GetMapping` -> `@GET` + `@Path`
- Change `@RequestParam` -> `@QueryParam`
- Change Spring's `ResponseEntity` to JAX-RS `Response` or return the domain object directly.

#### [MODIFY] all services in `com.trade.securities.application.service` and adapters in `com.trade.securities.adapter.out.external`
- Change `@Service` / `@Component` -> `@ApplicationScoped`.
- Change `@Value` -> `@ConfigProperty`.

#### [DELETE] [SecuritiesApplication.kt](file:///Volumes/data/code/trade/tradebe/src/main/kotlin/com/trade/securities/SecuritiesApplication.kt)
- In Quarkus, a main class is not required unless custom startup logic is needed.

### gRPC Support

#### [NEW] [securities.proto](file:///Volumes/data/code/trade/tradebe/src/main/proto/securities.proto)
- Define a protobuf file declaring the RPC service and endpoints mapping to all the current services.

#### [NEW] [SecuritiesGrpcService.kt](file:///Volumes/data/code/trade/tradebe/src/main/kotlin/com/trade/securities/adapter/in/grpc/SecuritiesGrpcService.kt)
- Implement the generated gRPC service interface, delegating calls to the application service use cases.

## Verification Plan

### Automated Tests
- Run `./gradlew build` to ensure the compilation and packaging pass successfully.

### Manual Verification
- Start Quarkus in development mode using `./gradlew quarkusDev`.
- Verify REST endpoints using curl:
  - `curl http://localhost:8080/api/market-data`
- Verify gRPC endpoints using `grpcurl` or a test gRPC request:
  - `grpcurl -plaintext localhost:8080 list` (ensure reflection is enabled or proto is provided).
