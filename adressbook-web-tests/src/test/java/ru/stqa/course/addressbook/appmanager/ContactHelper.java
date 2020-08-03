package ru.stqa.course.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import ru.stqa.course.addressbook.model.ContactData;

public class ContactHelper extends HelperBase {

  public ContactHelper(WebDriver wd) {
    super(wd);
  }

  public void returnToContactPage() {
    click(By.linkText("home page"));
  }

  public void submitContact() {
    click(By.xpath("(//input[@name='submit'])[2]"));
  }

  public void fillContactForm(ContactData contactData) {
    type(By.name("firstname"), contactData.getFirstname());
    type(By.name("middlename"), contactData.getMiddlename());
    type(By.name("lastname"), contactData.getLastname());
    type(By.name("nickname"), contactData.getNickname());
    type(By.name("title"), contactData.getTittle());
    type(By.name("company"), contactData.getCompany());
    type(By.name("address"), contactData.getAddress());
    type(By.name("home"), contactData.getHome());
    type(By.name("mobile"), contactData.getMobile());
    type(By.name("work"), contactData.getWork());
    type(By.name("fax"), contactData.getFax());
    type(By.name("email"), contactData.getEmail());
    type(By.name("email2"), contactData.getEmail2());
    type(By.name("email3"), contactData.getEmail3());
    type(By.name("homepage"), contactData.getHomepage());

    click(By.name("new_group"));
    new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getNewgroup());

    type(By.name("address2"), contactData.getAddress2());
    type(By.name("phone2"), contactData.getPhone2());
    type(By.name("notes"), contactData.getNotes());
  }

  public void fillAnniversary(String aday, String amonth, String ayear) {
    click(By.name("aday"));
    new Select(wd.findElement(By.name("aday"))).selectByVisibleText(aday);
    click(By.name("amonth"));
    new Select(wd.findElement(By.name("amonth"))).selectByVisibleText(amonth);
    type(By.name("ayear"), ayear);
  }

  public void fillBirthday(String bday, String bmonth, String byear) {
    click(By.name("bday"));
    new Select(wd.findElement(By.name("bday"))).selectByVisibleText(bday);
    click(By.name("bmonth"));
    new Select(wd.findElement(By.name("bmonth"))).selectByVisibleText(bmonth);
    type(By.name("byear"), byear);
  }

  public void initContactCreation() {
    click(By.linkText("add new"));
  }

  public void goContactHome() {
    click(By.linkText("home"));
  }

  public void editContact() {
    click(By.xpath("//img[@alt='Edit']"));
  }

  public void updateContact() {
    click(By.name("update"));
  }

  public void editContactForm(String firstName, String lastName) {
    type(By.name("firstname"), firstName);
    type(By.name("middlename"), lastName);
  }

  public void selectedContact() {
    wd.findElements(By.name("selected[]")).get(0).click();
  }

  public void deletedContact() {
    click(By.xpath("//input[@value='Delete']"));
  }

  public void alertContactDeleted() {
    wd.switchTo().alert().accept();
  }
}
