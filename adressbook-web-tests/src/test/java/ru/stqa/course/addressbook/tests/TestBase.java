package ru.stqa.course.addressbook.tests;

import org.openqa.selenium.remote.BrowserType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.testng.annotations.*;
import ru.stqa.course.addressbook.appmanager.ApplicationManager;

import java.lang.reflect.Method;

//@Listeners(MyTestListener.class)
public class TestBase {

    Logger logger = LoggerFactory.getLogger(TestBase.class);

    protected static final ApplicationManager app = new ApplicationManager(System
            .getProperty("browser", BrowserType.IE));

    @BeforeSuite(alwaysRun = true)
    public void setUp() throws Exception {
        app.init();
        //context.setAttribute("app", app);
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
