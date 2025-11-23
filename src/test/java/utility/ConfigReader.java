package utility;

import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;
import java.util.Map;

public class ConfigReader {

    private static Map<String, Object> config;

    static {
        loadYaml();
    }

    private static void loadYaml() {
        try {
            Yaml yaml = new Yaml();
            InputStream inputStream = ConfigReader.class
                    .getClassLoader()
                    .getResourceAsStream("config.yaml");

            if (inputStream == null) {
                throw new RuntimeException("config.yaml NOT found in src/test/resources or src/main/resources");
            }

            config = yaml.load(inputStream);

        } catch (Exception e) {
            throw new RuntimeException("Failed to load YAML config file. Reason: " + e.getMessage(), e);
        }
    }

    @SuppressWarnings("unchecked")
    public static String get(String key) {
        String[] parts = key.split("\\.");

        Map<String, Object> currentMap = config;
        Object value = null;

        for (String part : parts) {
            value = currentMap.get(part);

            if (value instanceof Map) {
                currentMap = (Map<String, Object>) value;
            }
        }

        return value == null ? null : value.toString();
    }
}
