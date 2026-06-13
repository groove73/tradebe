import os
import re

adapter_dir = "/Volumes/data/code/trade/tradebe/src/main/kotlin/com/trade/securities/adapter/out/external"

# Pattern to match @Value("\${name:default}") or @Value("\${name}")
# Note that in Kotlin source code it is written as @Value("\${...}") or @Value("\\${...}") depending on escaping.
# In the file, it is literally @Value("\${...}") or @Value("\\${...}")
# Let's write a regex that matches '@Value("' followed by optional backslashes, '$', '{', key name, optional ':default', '}')'
pattern = r'@Value\("(?:\S+)?\$[^{]*\{([^}]+)\}"\)'

for root, _, files in os.walk(adapter_dir):
    for file in files:
        if file.endswith(".kt"):
            path = os.path.join(root, file)
            with open(path, "r", encoding="utf-8") as f:
                content = f.read()
            
            # Find all @Value(...) and replace them
            # Let's do it with a custom replacer function
            def repl(match):
                expression = match.group(1) # e.g. "krx.api.key" or "fsc.api.urls.beneficiary:https://..."
                if ":" in expression:
                    key, default_val = expression.split(":", 1)
                    return f'@ConfigProperty(name = "{key}", defaultValue = "{default_val}")'
                else:
                    return f'@ConfigProperty(name = "{expression}")'
            
            # Let's match: @Value("\${expression}") or @Value("\\${expression}")
            new_content = re.sub(r'@Value\("(?:\S+)?\$[^{]*\{([^}]+)\}"\)', repl, content)
            
            # Let's double check if we missed any by matching @Value(anything)
            # In case the regex didn't catch the exact format, let's try a broader one:
            # Match @Value("...${...}...")
            new_content = re.sub(r'@Value\("[\\$]*\{([^}]+)\}"\)', repl, new_content)
            # Let's also cover standard ones where backslash is escaped in regex:
            new_content = re.sub(r'@Value\("\\\$\{([^}]+)\}"\)', repl, new_content)
            new_content = re.sub(r'@Value\("\$\{([^}]+)\}"\)', repl, new_content)
            
            if new_content != content:
                with open(path, "w", encoding="utf-8") as f:
                    f.write(new_content)
                print(f"Updated @Value in: {file}")
