package ru.stqa.course.mantis.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class NavigationHelper extends HelperBase {

    public NavigationHelper(ApplicationManager app) {
        super(app);
    }

    public void menuManagement() {
        click(By.cssSelector("a[href='/mantisbt-2.24.2/manage_overview_page.php']"));
    }

    public void menuUsers() {
        click(By.cssSelector("a[href='/mantisbt-2.24.2/manage_user_page.php']"));
    }

    public void selectUser(int userId) {
        click(By.cssSelector(String.format("a[href='manage_user_edit_page.php?user_id=%s'", userId)));
        //manage_user_edit_page.php?user_id=3
    }

    public void resetPassword() {
        WebElement form = wd.findElement(By.id("manage-user-reset-form"));
        WebElement button = form.findElement(By.cssSelector("input[type='submit']")); 
        button.click();
    }
}
