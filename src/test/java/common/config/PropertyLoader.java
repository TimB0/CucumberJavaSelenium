package common.config;

import java.io.IOException;
import java.util.Objects;
import java.util.Properties;

/**
 * Making this class singleton - so that only 1 instance exists
 * and hence only once properties file is read
 */
public class PropertyLoader {

    private static PropertyLoader propertyLoader;

    private Properties properties;

    private PropertyLoader() {
        properties = new Properties();
        try {
            properties.load(getClass().getClassLoader().getResourceAsStream("config.properties"));
        } catch (IOException e) {
            System.err.println("Exception occurred while loading properties file., exception = " + e.getMessage());
        }
    }

    public static PropertyLoader getPropertyLoader() {
        if (Objects.isNull(propertyLoader)) {
            propertyLoader = new PropertyLoader();
        }
        return propertyLoader;
    }

    /**
     * @param key          - key to search in properties file
     * @param defaultValue - default value to return if not found
     * @return - value found using key or default value
     */
    public String getProperty(String key, String defaultValue) {
        return properties.getProperty(key, defaultValue);
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }

    /**
     * we are creating property key by splitting them by spaces and joining them by a full-stop.
     *
     * @param key - key to be parsed to get from property file
     * @return - returns transformed key
     */
    public String getParsedProperty(String key) {
        String parsedKey = transform(key);
        System.out.println("parsed key = " + parsedKey);
        return properties.getProperty(parsedKey);
    }

    /**
     * key is turned to lowercase and then split using spaces and joined using '.' - a full-stop.
     * for eg:- 'AA BB' will be transformed to 'aa.bb'
     *
     * @param key - key to be transformed
     * @return transformed key
     */
    private String transform(String key) {
        return String.join(".", key.toLowerCase().split(" "));
    }

}
