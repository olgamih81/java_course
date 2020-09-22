package ru.stqa.course.rest.tests;

//import org.openqa.selenium.remote.BrowserType;
import org.testng.SkipException;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import ru.stqa.course.rest.appmanager.ApplicationManager;
import ru.stqa.course.rest.appmanager.RestHelper;
//import ru.stqa.course.mantis.appmanager.ApplicationManager;


import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.RemoteException;


public class TestBase {

    protected static final ApplicationManager app = null;

 /*   protected static final ApplicationManager app = new ApplicationManager(System.getProperty("browser", BrowserType.FIREFOX));

    @BeforeSuite(alwaysRun = true)
    public void setUp() throws Exception {
        app.init();
    }*/

    public boolean isIssueOpen(int issueId) throws IOException {
       //var issueStatus = app.soap().getIssue(issueId).getStatus();
        String issueStatus = RestHelper.getIssueStatus(issueId).getAsJsonObject().get("state_name").getAsString();

        if (issueStatus.equals("Closed")) {
            return false;
        }
        return true;
    }

    public void skipIfNotFixed(int issueId) throws IOException {
        if (isIssueOpen(issueId)) {
            throw new SkipException("Ignored because of issue " + issueId);
        }
    }

    /*@AfterSuite(alwaysRun = true)
    public void tearDown() throws Exception {
        app.stop();
    }*/
}
