import os
import re

service_dir = "/Volumes/data/code/trade/tradebe/src/main/kotlin/com/trade/securities/application/service"
adapter_dir = "/Volumes/data/code/trade/tradebe/src/main/kotlin/com/trade/securities/adapter/out/external"

# 1. Refactor services
for root, _, files in os.walk(service_dir):
    for file in files:
        if file.endswith(".kt"):
            path = os.path.join(root, file)
            with open(path, "r", encoding="utf-8") as f:
                content = f.read()
            
            # Replace Service with ApplicationScoped
            content = content.replace("import org.springframework.stereotype.Service", "import jakarta.enterprise.context.ApplicationScoped")
            content = content.replace("@Service", "@ApplicationScoped")
            
            with open(path, "w", encoding="utf-8") as f:
                f.write(content)
            print(f"Refactored service: {file}")

# 2. Refactor adapters
for root, _, files in os.walk(adapter_dir):
    for file in files:
        if file.endswith(".kt"):
            path = os.path.join(root, file)
            with open(path, "r", encoding="utf-8") as f:
                content = f.read()
            
            # Replacements
            content = content.replace("import org.springframework.stereotype.Component", "import jakarta.enterprise.context.ApplicationScoped")
            content = content.replace("@Component", "@ApplicationScoped")
            
            content = content.replace("import org.springframework.beans.factory.annotation.Value", "import org.eclipse.microprofile.config.inject.ConfigProperty")
            content = content.replace("import org.springframework.web.util.UriComponentsBuilder", "import com.trade.securities.infrastructure.UriComponentsBuilder")
            content = content.replace("import org.springframework.web.client.RestClient", "import com.trade.securities.infrastructure.RestClient")
            
            # Replace @Value("${...}") with @ConfigProperty(name = "...")
            # Pattern: @Value("\${path}")
            # Pattern: @Value("${path}")
            content = re.sub(r'@Value\("\$(?:\\)?\{([^}]+)\}"\)', r'@ConfigProperty(name = "\1")', content)
            
            with open(path, "w", encoding="utf-8") as f:
                f.write(content)
            print(f"Refactored adapter/dto: {file}")
