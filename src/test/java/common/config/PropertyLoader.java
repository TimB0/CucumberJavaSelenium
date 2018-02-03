package common.config;

import java.io.IOException;
import java.util.Objects;
import java.util.Properties;

public class PropertyLoader {

    private static PropertyLoader propertyLoader;

    private Properties properties;

    private PropertyLoader(){
        properties = new Properties();
        try {
            properties.load(getClass().getClassLoader().getResourceAsStream("config.properties"));
        } catch (IOException e) {
            System.err.println("Exception occurred while loading properties file., exception = " + e.getMessage());
        }
    }

    public static PropertyLoader getPropertyLoader() {
        if(Objects.isNull(propertyLoader)) {
            propertyLoader = new PropertyLoader();
        }
        return propertyLoader;
    }

    public String getProperty(String key, String defaultValue) {
        return properties.getProperty(key, defaultValue);
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }

    public String getParsedProperty(String key) {
        String parsedKey = transform(key);
        System.out.println("parsed key = " + parsedKey);
        return properties.getProperty(parsedKey);
    }

    private String transform(String key) {
        return String.join(".", key.toLowerCase().split(" "));
    }

}
