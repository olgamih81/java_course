package ru.stqa.course.mantis.appmanager;

import org.openqa.selenium.By;

public class NavigationHelper extends HelperBase {

    public NavigationHelper(ApplicationManager app) {
        super(app);
    }

    public void menuManagement() {
        click(By.cssSelector("a[href='/mantisbt-2.24.2/manage_overview_page.php']"));
        //click((By.xpath("//div[@id='sidebar']/ul/li[6]/a/i")));
    }

    public void menuUsers() {
        click(By.cssSelector("a[href='/mantisbt-2.24.2/manage_user_page.php']"));
    }
}
