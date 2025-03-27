package util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Utility {

    public static String fetchPropertyValue(String key) throws IOException {
        FileInputStream file = new FileInputStream("/home/sandile/Desktop/github/data-driven-framework/src/test/java/config/config.properties");
        Properties property = new Properties();
        property.load(file);
        return property.get(key).toString();
    }

    public static String fetchElementValue(String key) throws IOException {
        FileInputStream file = new FileInputStream("/home/sandile/Desktop/github/data-driven-framework/src/test/java/config/elements.properties");
        Properties property = new Properties();
        property.load(file);
        return property.get(key).toString();
    }

    public static String fetchTestData(String key) throws IOException {
        FileInputStream file = new FileInputStream("/home/sandile/Desktop/github/data-driven-framework/src/test/java/config/testData.properties");
        Properties property = new Properties();
        property.load(file);
        return property.get(key).toString();
    }

}
