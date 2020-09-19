package ru.stqa.course.mantis.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.course.mantis.model.UserData;
import ru.stqa.course.mantis.model.Users;

import java.util.Iterator;

public class TestPasswordChange extends TestBase{

    @BeforeMethod
    public void startMailServer() {
        app.mail().start();
    }

    @Test
    public void testPasswordChange() {
        Users users  = app.db().users();
        //Iterator<UserData> userDataIterator = users.iterator();
        UserData user = users.iterator().next();
        int userId = user.getId();

        app.login().loginAdmin();
        app.navigation().menuManagement();
        app.navigation().menuUsers();
        app.navigation().selectUser(userId);
        app.navigation().resetPassword();
    }

    @AfterMethod(alwaysRun = true)
    public void stopMailServer() {
        app.mail().stop();
    }
}
