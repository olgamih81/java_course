package ru.stqa.course.rest.appmanager;

import java.util.Properties;

public class ApplicationManager {

    private final String browser;
    private RestHelper restHelper;
    private final Properties properties;

    public ApplicationManager(String browser) {
        this.browser = browser;
        properties = new Properties();
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }
/*
    public RestHelper soap() {
        if (restHelper == null) {
            restHelper = new RestHelper(this);
        }
        return restHelper;
    }*/

}
