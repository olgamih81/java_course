package ru.stqa.course.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.course.addressbook.model.ContactData;
//import ru.stqa.course.addressbook.model.GroupData;

import java.util.ArrayList;
import java.util.List;

public class ContactHelper extends HelperBase {

  private List<WebElement> elements;
  private List<WebElement> elements1;

  public ContactHelper(WebDriver wd) {
    super(wd);
  }

  public void returnToContactPage() {
    click(By.linkText("home page"));
  }

  public void submitContact() {
    click(By.xpath("(//input[@name='submit'])[2]"));
  }

  public void fillContactForm(ContactData contactData, boolean creation) {
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

    //click(By.name("new_group"));
   if (creation) {
      new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getNewgroup());
    } else {
      Assert.assertFalse(isElementPresent(By.name("new_group")));
    }
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

  public void editContact(int index) {
    var row = wd.findElements(By.name("entry")).get(index);
    row.findElement(By.cssSelector("img[title='Edit']")).click();
  }

  public void updateContact() {
    click(By.name("update"));
  }

  public void editContactForm(String firstName, String lastName) {
    type(By.name("firstname"), firstName);
    type(By.name("middlename"), lastName);
  }

  public void selectedContact(int index) {
    wd.findElements(By.name("selected[]")).get(index).click();
  }

  public void deletedContact() {
    click(By.xpath("//input[@value='Delete']"));
  }

  public void alertContactDeleted() {
    wd.switchTo().alert().accept();
  }

  public boolean existenceOfContact() {
    return isElementPresent(By.name("selected[]"));
  }

  public void createContact(ContactData contact) {
    initContactCreation();
    fillContactForm(contact, true);
    fillBirthday("15", "January", "2001");
    fillAnniversary("18", "December", "2020");
    submitContact();
    returnToContactPage();
  }

    public int getContactCount() {
    return wd.findElements(By.name("selected[]")).size();
    }

    public List<ContactData> getContactList() {
    List<ContactData> contact = new ArrayList<>();
    List<WebElement> elements = wd.findElements(By.name("entry"));

    for (WebElement row : elements) {
        List<WebElement> cells = row.findElements(By.tagName("td"));
        int id = Integer.parseInt(row.findElement(By.tagName("input")).getAttribute("value"));
        String name = cells.get(2).getText();
        String lname = cells.get(1).getText();
        ContactData cont= new ContactData(id, name, null, lname, null, null, null,
                null, null, null, null, null, null, null, null, null,
                null, null, null, null);
        contact.add(cont);
      }
    return contact;
    }
}

