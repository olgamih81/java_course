package ru.stqa.course.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import ru.stqa.course.addressbook.model.ContactData;

import java.util.concurrent.TimeUnit;

public class ApplicationManager {

    private final GroupHelper groupHelper = new GroupHelper();

    public void init() {
        groupHelper.wd = new FirefoxDriver();
        groupHelper.wd.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        login("admin", "secret");
    }

    private void login(String username, String password) {
      groupHelper.wd.get("https://localhost/addressbook/");
      groupHelper.wd.findElement(By.name("user")).click();
      groupHelper.wd.findElement(By.name("user")).clear();
      groupHelper.wd.findElement(By.name("user")).sendKeys(username);
      groupHelper.wd.findElement(By.name("pass")).click();
      groupHelper.wd.findElement(By.name("pass")).clear();
      groupHelper.wd.findElement(By.name("pass")).sendKeys(password);
      groupHelper.wd.findElement(By.xpath("//input[@value='Login']")).click();
    }

    public void gotoGroupPage() {
      groupHelper.wd.findElement(By.linkText("groups")).click();
    }

    public void stop() {
        groupHelper.wd.quit();
    }

    private boolean isElementPresent(By by) {
      try {
        groupHelper.wd.findElement(by);
        return true;
      } catch (NoSuchElementException e) {
        return false;
      }
    }

    private boolean isAlertPresent() {
      try {
        groupHelper.wd.switchTo().alert();
        return true;
      } catch (NoAlertPresentException e) {
        return false;
      }
    }

    public void returnToContactPage() {
      groupHelper.wd.findElement(By.linkText("home page")).click();
    }

    public void submitContact() {
      groupHelper.wd.findElement(By.xpath("(//input[@name='submit'])[2]")).click();
    }

    public void fillContactForm(ContactData contactData) {
      groupHelper.wd.findElement(By.name("firstname")).click();
      groupHelper.wd.findElement(By.name("firstname")).clear();
      groupHelper.wd.findElement(By.name("firstname")).sendKeys(contactData.getFirstname());
      groupHelper.wd.findElement(By.name("middlename")).click();
      groupHelper.wd.findElement(By.name("middlename")).clear();
      groupHelper.wd.findElement(By.name("middlename")).sendKeys(contactData.getMiddlename());
      groupHelper.wd.findElement(By.name("lastname")).click();
      groupHelper.wd.findElement(By.name("lastname")).clear();
      groupHelper.wd.findElement(By.name("lastname")).sendKeys(contactData.getLastname());
      groupHelper.wd.findElement(By.name("nickname")).click();
      groupHelper.wd.findElement(By.name("nickname")).clear();
      groupHelper.wd.findElement(By.name("nickname")).sendKeys(contactData.getNickname());
      groupHelper.wd.findElement(By.name("title")).click();
      groupHelper.wd.findElement(By.name("title")).clear();
      groupHelper.wd.findElement(By.name("title")).sendKeys(contactData.getTittle());
      groupHelper.wd.findElement(By.name("company")).click();
      groupHelper.wd.findElement(By.name("company")).clear();
      groupHelper.wd.findElement(By.name("company")).sendKeys(contactData.getCompany());
      groupHelper.wd.findElement(By.name("address")).click();
      groupHelper.wd.findElement(By.name("address")).clear();
      groupHelper.wd.findElement(By.name("address")).sendKeys(contactData.getAddress());
      groupHelper.wd.findElement(By.name("home")).click();
      groupHelper.wd.findElement(By.name("home")).clear();
      groupHelper.wd.findElement(By.name("home")).sendKeys(contactData.getHome());
      groupHelper.wd.findElement(By.name("mobile")).click();
      groupHelper.wd.findElement(By.name("mobile")).clear();
      groupHelper.wd.findElement(By.name("mobile")).sendKeys(contactData.getMobile());
      groupHelper.wd.findElement(By.name("work")).click();
      groupHelper.wd.findElement(By.name("work")).clear();
      groupHelper.wd.findElement(By.name("work")).sendKeys(contactData.getWork());
      groupHelper.wd.findElement(By.name("fax")).click();
      groupHelper.wd.findElement(By.name("fax")).clear();
      groupHelper.wd.findElement(By.name("fax")).sendKeys(contactData.getFax());
      groupHelper.wd.findElement(By.name("email")).click();
      groupHelper.wd.findElement(By.name("email")).clear();
      groupHelper.wd.findElement(By.name("email")).sendKeys(contactData.getEmail());
      groupHelper.wd.findElement(By.name("email2")).click();
      groupHelper.wd.findElement(By.name("email2")).clear();
      groupHelper.wd.findElement(By.name("email2")).sendKeys(contactData.getEmail2());
      groupHelper.wd.findElement(By.name("email3")).click();
      groupHelper.wd.findElement(By.name("email3")).clear();
      groupHelper.wd.findElement(By.name("email3")).sendKeys(contactData.getEmail3());
      groupHelper.wd.findElement(By.name("homepage")).click();
      groupHelper.wd.findElement(By.name("homepage")).clear();
      groupHelper.wd.findElement(By.name("homepage")).sendKeys(contactData.getHomepage());

      groupHelper.wd.findElement(By.name("new_group")).click();
      new Select(groupHelper.wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getNewgroup());

      groupHelper.wd.findElement(By.name("address2")).click();
      groupHelper.wd.findElement(By.name("address2")).clear();
      groupHelper.wd.findElement(By.name("address2")).sendKeys(contactData.getAddress2());
      groupHelper.wd.findElement(By.name("phone2")).click();
      groupHelper.wd.findElement(By.name("phone2")).clear();
      groupHelper.wd.findElement(By.name("phone2")).sendKeys(contactData.getPhone2());
      groupHelper.wd.findElement(By.name("notes")).click();
      groupHelper.wd.findElement(By.name("notes")).clear();
      groupHelper.wd.findElement(By.name("notes")).sendKeys(contactData.getNotes());
    }

    public void fillAnniversary(String aday, String amonth, String ayear) {
      groupHelper.wd.findElement(By.name("aday")).click();
      new Select(groupHelper.wd.findElement(By.name("aday"))).selectByVisibleText(aday);
      groupHelper.wd.findElement(By.name("amonth")).click();
      new Select(groupHelper.wd.findElement(By.name("amonth"))).selectByVisibleText(amonth);
      groupHelper.wd.findElement(By.name("ayear")).click();
      groupHelper.wd.findElement(By.name("ayear")).clear();
      groupHelper.wd.findElement(By.name("ayear")).sendKeys(ayear);
    }

    public void fillBirthday(String bday, String bmonth, String byear) {
      groupHelper.wd.findElement(By.name("bday")).click();
      new Select(groupHelper.wd.findElement(By.name("bday"))).selectByVisibleText(bday);
      groupHelper.wd.findElement(By.name("bmonth")).click();
      new Select(groupHelper.wd.findElement(By.name("bmonth"))).selectByVisibleText(bmonth);
      groupHelper.wd.findElement(By.name("byear")).click();
      groupHelper.wd.findElement(By.name("byear")).clear();
      groupHelper.wd.findElement(By.name("byear")).sendKeys(byear);
    }

    public void initContactCreation() {
      groupHelper.wd.findElement(By.linkText("add new")).click();
    }

    public GroupHelper getGroupHelper() {
        return groupHelper;
    }
}
