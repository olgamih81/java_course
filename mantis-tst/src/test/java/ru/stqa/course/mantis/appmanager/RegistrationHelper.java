package ru.stqa.course.mantis.appmanager;

import org.openqa.selenium.By;

public class RegistrationHelper extends HelperBase{

    public RegistrationHelper(ApplicationManager app) {
        super(app);
    }

    public void start(String username, String email) {
        wd.get(app.getProperty("web.baseUrl") + "signup_page.php");
        type(By.name("username"), username);
        type(By.name("email"), email);
        //click(By.cssSelector("input[value='Зарегистрироваться']")); //???
        click(By.xpath("//form[@id='signup-form']/fieldset/input[2]"));
    }

    public void changePassword(String userName, String newpassword) {
        type(By.name("realname"), userName);
        type(By.name("password"),newpassword);
        type(By.name("password_confirm"),newpassword);
        //click(By.cssSelector("input[value='Update User']")); //"input[type='submit']"
        click(By.className("bigger-110"));
    }

    public void finish(String confirmationLink, String password) {
        wd.get(confirmationLink);
        type(By.name("password"),password);
        type(By.name("password_confirm"),password);
        click(By.cssSelector("input[value='Update User']"));
    }
}
