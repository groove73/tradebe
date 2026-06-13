# Migration Tasks

- `[x]` Update `build.gradle.kts` with Quarkus dependencies and plugins.
- `[x]` Replace application YAML configuration with unified `application.properties`.
- `[x]` Implement custom lightweight `RestClient` and `UriComponentsBuilder` in `com.trade.securities.infrastructure`.
- `[x]` Refactor web controllers from Spring MVC to Jakarta REST (JAX-RS).
- `[x]` Refactor services and adapters from Spring stereotypes/annotations to Jakarta CDI (`@ApplicationScoped`, `@ConfigProperty`).
- `[x]` Create `securities.proto` definition.
- `[x]` Implement the gRPC service class `SecuritiesGrpcService`.
- `[x]` Delete Spring-specific classes (like `SecuritiesApplication`, Spring `CorsConfig`, etc.).
- `[x]` Build and verify the application.
- `[x]` Convert application configuration to YAML.
