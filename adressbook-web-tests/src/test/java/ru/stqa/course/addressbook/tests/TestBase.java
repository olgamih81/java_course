package ru.stqa.course.addressbook.tests;

import org.openqa.selenium.remote.BrowserType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import ru.stqa.course.addressbook.appmanager.ApplicationManager;

import java.lang.reflect.Method;

public class TestBase {
    //Logger logger = (Logger) LoggerFactory.getLogger(TestBase.class);
    Logger logger = LoggerFactory.getLogger(TestBase.class);

    protected static final ApplicationManager app = new ApplicationManager(System.getProperty("browser", BrowserType.FIREFOX));

    @BeforeSuite(alwaysRun = true)
    public void setUp() throws Exception {
        app.init();
    }

    @AfterSuite(alwaysRun = true)
    public void tearDown() throws Exception {
        app.stop();
    }

    @BeforeMethod
    public void logTestStart(Method m) {
        logger.info("Start test: " + m.getName());
    }

    @AfterMethod(alwaysRun = true)
    public void logTeatStop(Method m) {
        logger.info("Stop test: "+ m.getName());
    }
}
