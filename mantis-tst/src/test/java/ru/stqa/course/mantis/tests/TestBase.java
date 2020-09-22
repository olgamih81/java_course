package ru.stqa.course.mantis.tests;

import org.openqa.selenium.remote.BrowserType;
import org.testng.SkipException;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import ru.stqa.course.mantis.appmanager.ApplicationManager;
import ru.stqa.course.mantis.model.Issue;

import javax.xml.rpc.ServiceException;
import java.net.MalformedURLException;
import java.rmi.RemoteException;


public class TestBase {

    protected static final ApplicationManager app = new ApplicationManager(System.getProperty("browser", BrowserType.FIREFOX));

    @BeforeSuite(alwaysRun = true)
    public void setUp() throws Exception {
        app.init();
    }

    public boolean isIssueOpen(int issueId) throws RemoteException, ServiceException, MalformedURLException {
        var issueStatus = app.soap().getIssue(issueId).getStatus();
        if (issueStatus == 80 || issueStatus == 90) {
            return false;
        }
        return true;
    }

    public void skipIfNotFixed(int issueId) throws RemoteException, ServiceException, MalformedURLException {
        if (isIssueOpen(issueId)) {
            throw new SkipException("Ignored because of issue " + issueId);
        }
    }

    @AfterSuite(alwaysRun = true)
    public void tearDown() throws Exception {
        //app.ftp().restore("config_inc.php.bak", "config_inc.php");
        app.stop();
    }
}
