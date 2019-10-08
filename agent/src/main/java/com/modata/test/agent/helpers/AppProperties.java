package com.modata.test.agent.helpers;

import com.modata.test.agent.Application;

import java.io.IOException;
import java.util.Properties;

public class AppProperties {
    static Properties properties = new Properties();
    static {
        try {
            properties.load(Application.class.getClassLoader().getResourceAsStream("config.properties"));
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static String getAgencyUrl() {
        return properties.getProperty("agency.url");
    }

    public static Integer getInterval() {
        return Integer.parseInt(properties.getProperty("push.interval"));
    }
}
