package test.agent.helpers;

import lombok.extern.slf4j.Slf4j;
import test.agent.AgentApplication;

import java.io.IOException;
import java.util.Properties;

@Slf4j
public final class AppProperties {
    private static final String PROPERTIES_FILE_NAME = "config.properties";
    private static Properties PROPERTIES = new Properties() {{
        defaults = new Properties();
        defaults.put(Names.AGENCY_URL, "http://localhost:8080");
        defaults.put(Names.PUSH_INTERVAL, "60000");
    }};
    static {
        try {
            PROPERTIES.load(AgentApplication.class.getClassLoader().getResourceAsStream(PROPERTIES_FILE_NAME));
        } catch (IOException | NullPointerException e) {
            log.warn("Failed to read application properties from [{}], using defaults.", PROPERTIES_FILE_NAME);
        }
    }

    private AppProperties() {
    }

    public static String getAgencyUrl() {
        return PROPERTIES.getProperty(Names.AGENCY_URL);
    }

    public static Integer getInterval() {
        return Integer.parseInt(PROPERTIES.getProperty(Names.PUSH_INTERVAL));
    }

    public static String getUsername() {
        return PROPERTIES.getProperty(Names.USERNAME);
    }

    public static String getKey() {
        return PROPERTIES.getProperty(Names.KEY);
    }

    private static class Names {
        static final String USERNAME = "auth.username";
        static final String KEY = "auth.key";
        static final String AGENCY_URL = "agency.url";
        static final String PUSH_INTERVAL = "push.interval";
    }

}
