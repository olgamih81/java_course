package ru.stqa.course.mantis.tests;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.course.mantis.model.UserData;
import ru.stqa.course.mantis.model.Users;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.Iterator;

import static org.testng.Assert.assertTrue;

public class TestPasswordChange extends TestBase{

    @BeforeMethod
    public void startMailServer() {
        app.mail().start();
    }

    @Test
    public void testPasswordChange() throws IOException, MessagingException {
        Users users  = app.db().users();
        UserData user = users.iterator().next();

        int userId = user.getId();
        String userName = user.getUsername();
        String userEmail = user.getEmail();

        long now = System.currentTimeMillis();
        String password = String.format("pass%s", now);

        app.login().loginAdmin();
        app.navigation().menuManagement();
        app.navigation().menuUsers();
        app.navigation().selectUser(userId);
        app.navigation().resetPassword();
        app.mail().confirmationMessage(userEmail);
        app.registration().changePassword(userName, password);
        assertTrue(app.newSession().login(userName, password));
    }

    @AfterMethod(alwaysRun = true)
    public void stopMailServer() {
        app.mail().stop();
    }
}
