package ru.stqa.course.mantis.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.course.mantis.model.UserData;
import ru.stqa.course.mantis.model.Users;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.Iterator;

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

        app.login().loginAdmin();
        app.navigation().menuManagement();
        app.navigation().menuUsers();
        app.navigation().selectUser(userId);
        app.navigation().resetPassword();
        app.mail().confirmationMessage(userEmail);
    }

    @AfterMethod(alwaysRun = true)
    public void stopMailServer() {
        app.mail().stop();
    }
}
