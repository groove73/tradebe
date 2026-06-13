# Migration Walkthrough: Spring Boot 3.x to Quarkus 3.x

We have successfully migrated the `tradebe` backend from Spring Boot 3.x to Quarkus 3.36.x, while maintaining Gradle and configuring the app to co-locate both HTTP and gRPC services on port 8080.

## Changes Made

### 1. Build and Dependency Configuration
- Updated [build.gradle.kts](file:///Volumes/data/code/trade/tradebe/build.gradle.kts):
  - Applied the Quarkus Gradle plugin version `3.36.2`.
  - Added Quarkus core dependencies (`io.quarkus:quarkus-kotlin`, `io.quarkus:quarkus-rest`, `io.quarkus:quarkus-rest-jackson`, `io.quarkus:quarkus-grpc`, `io.quarkus:quarkus-config-yaml`).
  - Added protobuf-kotlin support (`com.google.protobuf:protobuf-kotlin`).

### 2. Configuration Conversion to YAML
- Replaced `.properties` files with a unified [application.yaml](file:///Volumes/data/code/trade/tradebe/src/main/resources/application.yaml).
- Co-located gRPC server with the origin router on HTTP port `8080`:
  ```yaml
  quarkus:
    http:
      port: 8080
    grpc:
      server:
        use-origin-co-located-server: true
        port: 8080
  ```

### 3. JAX-RS / Jakarta REST Refactoring
- All REST web controllers in `com.trade.securities.adapter.in.web` were refactored to use standard JAX-RS annotations:
  - `@RestController` -> `@Path`
  - `@GetMapping` -> `@GET`
  - `@RequestParam` -> `@QueryParam` with `@DefaultValue`
  - `@PathVariable` -> `@PathParam`

### 4. CDI & Core Architecture Refactoring
- Services and external adapters were updated to use standard Jakarta CDI annotations (`@ApplicationScoped`, `@ConfigProperty`) instead of Spring stereotypes (`@Service`, `@Component`, `@Value`).
- Deleted Spring-specific initialization [SecuritiesApplication.kt](file:///Volumes/data/code/trade/tradebe/src/main/kotlin/com/trade/securities/SecuritiesApplication.kt) and CORS configuration [CorsConfig.kt](file:///Volumes/data/code/trade/tradebe/src/main/kotlin/com/trade/securities/infrastructure/CorsConfig.kt).

### 5. Custom REST Client & Helper
- Implemented a lightweight [RestClientConfig.kt](file:///Volumes/data/code/trade/tradebe/src/main/kotlin/com/trade/securities/infrastructure/RestClientConfig.kt) mimicking Spring's `RestClient` and `UriComponentsBuilder` API using standard Java HTTP Client and JAX-RS UriBuilder, avoiding modifications to the 11 external adapters.

### 6. gRPC Integration
- Created [securities.proto](file:///Volumes/data/code/trade/tradebe/src/main/proto/securities.proto) defining RPC operations matching all REST resources.
- Implemented [SecuritiesGrpcService.kt](file:///Volumes/data/code/trade/tradebe/src/main/kotlin/com/trade/securities/adapter/in/grpc/SecuritiesGrpcService.kt) to delegate incoming gRPC requests to the respective application service use cases.

### 7. Documentation Updates
- Updated dev server command in [README.md](file:///Volumes/data/code/trade/tradebe/README.md) to `./gradlew quarkusDev`.
- Updated tech stacks and architecture structures in [PORTFOLIO_KR.md](file:///Volumes/data/code/trade/tradebe/PORTFOLIO_KR.md) and [PORTFOLIO_EN.md](file:///Volumes/data/code/trade/tradebe/PORTFOLIO_EN.md).

## Verification Results
- Successfully built with `./gradlew compileKotlin` with output:
  `BUILD SUCCESSFUL`
