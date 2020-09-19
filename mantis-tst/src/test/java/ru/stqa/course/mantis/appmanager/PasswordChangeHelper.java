package ru.stqa.course.mantis.appmanager;

import org.openqa.selenium.By;

public class PasswordChangeHelper extends HelperBase {

    public PasswordChangeHelper(ApplicationManager app) {
        super(app);
    }

    public void loginAdmin() {
        String username = app.getProperty("web.admLogin");

        wd.get(app.getProperty("web.baseUrl") + "login_page.php");
        type(By.name("username"), username);
        //click((By.xpath("//input[@value='Войти']")));
        click(By.xpath("//form[@id='login-form']/fieldset/input[2]"));
        type(By.name("password"), app.getProperty("web.admPassword"));
        //click((By.xpath("//input[@value='Войти']")));
        click(By.xpath("//input[@type='submit']"));
    }
}
