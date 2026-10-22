package com.framework.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class ConfigReader {

    private static final String CONFIG_FILE = "config.properties";
    private static final Properties props = new Properties();

    static {
        try (InputStream is = ConfigReader.class.getClassLoader().getResourceAsStream(CONFIG_FILE)) {
            if (is == null) {
                throw new IllegalStateException(CONFIG_FILE + " not found on classpath");
            }
            props.load(is);
        } catch (IOException e) {
            throw new IllegalStateException("Unable to load " + CONFIG_FILE, e);
        }
    }

    private ConfigReader() {
    }

    public static String get(String key) {
        String value = props.getProperty(key);
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException("Missing config key: " + key);
        }
        return value.trim();
    }

    public static int getInt(String key) {
        return Integer.parseInt(get(key));
    }

    public static boolean getBoolean(String key) {
        return Boolean.parseBoolean(get(key));
    }

    public static String getBrowser() {
        return get("browser").toLowerCase();
    }

    public static String getBaseUrl() {
        return get("baseUrl");
    }

    public static boolean isHeadless() {
        return getBoolean("isHeadless");
    }

    public static int getImplicitWait() {
        return getInt("implicitWait");
    }
}