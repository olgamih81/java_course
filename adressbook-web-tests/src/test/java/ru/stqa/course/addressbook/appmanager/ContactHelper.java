package ru.stqa.course.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.course.addressbook.model.ContactData;
import ru.stqa.course.addressbook.model.Contacts;
import java.util.ArrayList;
import java.util.List;

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

  public void fillContactForm(ContactData contactData, boolean creation) {
    type(By.name("firstname"), contactData.getFirstname());
    type(By.name("middlename"), contactData.getMiddlename());
    type(By.name("lastname"), contactData.getLastname());
    type(By.name("nickname"), contactData.getNickname());
    type(By.name("title"), contactData.getTittle());
    type(By.name("company"), contactData.getCompany());
    type(By.name("address"), contactData.getAddress());
    type(By.name("home"), contactData.getHomePhone());
    type(By.name("mobile"), contactData.getMobilePhone());
    type(By.name("work"), contactData.getWorkPhone());
    type(By.name("fax"), contactData.getFax());
    type(By.name("email"), contactData.getEmail());
    type(By.name("email2"), contactData.getEmail2());
    type(By.name("email3"), contactData.getEmail3());
    type(By.name("homepage"), contactData.getHomepage());
    type(By.name("address2"), contactData.getAddress2());
    type(By.name("notes"), contactData.getNotes());
    //attach(By.name("photo"), contactData.getPhoto());

   if (creation) {
     if (contactData.getGroups().size() > 0) {
       Assert.assertTrue(contactData.getGroups().size() == 1 );
       new Select(wd.findElement(By.name("new_group"))).
               selectByVisibleText(contactData.getGroups().iterator().next().getName());
     }
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

  public void home() {
    click(By.linkText("home"));
  }

  public void selectedContact(int index) {
    wd.findElements(By.name("selected[]")).get(index).click();
  }

  private void selectedContactById(int id) {
    wd.findElement(By.cssSelector("input[value='" + id + "']")).click();
  }

  public void editContact(int id) {
    //wd.findElement(By.cssSelector("a[href='edit.php?id=" + id + "']")).click();
    wd.findElement(By.cssSelector(String.format("a[href='edit.php?id=%s']", id))).click();;
  }

  public void updateContact() {
    click(By.name("update"));
  }

  public void deletedContact() {
    click(By.xpath("//input[@value='Delete']"));
  }

  public void alertContactDeleted() {
    wd.switchTo().alert().accept();
  }

  public void create(ContactData contact) {
    initContactCreation();
    fillContactForm(contact, true);
    fillBirthday("15", "January", "2001");
    fillAnniversary("18", "December", "2020");
    submitContact();
    contactCache = null;
    returnToContactPage();
  }

  public void modify(ContactData contact) {
    selectedContactById(contact.getId());
    editContact(contact.getId());
    fillContactForm(contact, false);
    updateContact();
    contactCache = null;
    returnToContactPage();
  }

  public void delete(ContactData contact) {
    selectedContactById(contact.getId());
    deletedContact();
    alertContactDeleted();
    contactCache = null;
    home();
  }

  public List<ContactData> list() {
    List<ContactData> contact = new ArrayList<>();
    List<WebElement> elements = wd.findElements(By.name("entry"));

    for (WebElement row : elements) {
        List<WebElement> cells = row.findElements(By.tagName("td"));
        int id = Integer.parseInt(row.findElement(By.tagName("input")).getAttribute("value"));
        String lname = cells.get(1).getText();
        String name  = cells.get(2).getText();

        ContactData cont= new ContactData().withId(id).withFirstname(name).withLastname(lname);
        contact.add(cont);
      }
    return contact;
    }

  private Contacts contactCache = null;

  public Contacts all() {
    if (contactCache != null) {
      return new Contacts(contactCache);
    }
    contactCache = new Contacts();
    List<WebElement> elements = wd.findElements(By.name("entry"));

    for (WebElement row : elements) {
      List<WebElement> cells = row.findElements(By.tagName("td"));
      int id = Integer.parseInt(row.findElement(By.tagName("input")).getAttribute("value"));
      String name = cells.get(2).getText();
      String lname = cells.get(1).getText();
      String address = cells.get(3).getText();
      String allEmails = cells.get(4).getText();
      String allPhones = cells.get(5).getText();
      ContactData cont = new ContactData().withId(id).withFirstname(name).withLastname(lname)
              .withAddress(address)
              .withAllEmails(allEmails)
              .withAllPhones(allPhones);
      contactCache.add(cont);
    }
    return contactCache;
  }

  public ContactData infoFromEditForm(ContactData contact) {
    selectedContactById(contact.getId());
    editContact(contact.getId());
    String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
    String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
    String home = wd.findElement(By.name("home")).getAttribute("value");
    String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
    String work = wd.findElement(By.name("work")).getAttribute("value");
    String phone2 = wd.findElement(By.name("phone2")).getAttribute("value");
    String address = wd.findElement(By.name("address")).getAttribute("value"); //????????
    String email = wd.findElement(By.name("email")).getAttribute("value");
    String email2 = wd.findElement(By.name("email2")).getAttribute("value");
    String email3 = wd.findElement(By.name("email3")).getAttribute("value");
    wd.navigate().back();
    return new ContactData().withId(contact.getId()).
            withFirstname(firstname).withLastname(lastname).withHomePhone(home).
            withMobilePhone(mobile).withWorkPhone(work).withPhone2(phone2)
            .withEmail(email).withEmail2(email2).withEmail3(email3)
            .withAddress(address);
  }

  public int count() {
    return wd.findElements(By.name("selected[]")).size();
  }

  public void addingContactToGroup(ContactData addingContact, int groupId, String groupName) {
    selectedContactById(addingContact.getId());
    click(By.name("to_group"));
    new Select(wd.findElement(By.name("to_group"))).selectByVisibleText(groupName);
    wd.findElement(By.cssSelector(String.format("option[value='%s']", groupId))).click();
    wd.findElement(By.name(("add"))).click();

    //selectToGroup(groupId, groupName);
    returnToGroupPageContact();
  }

  public void returnToGroupPageContact() {
    click(By.linkText("home"));
  }

  private void addTo() {
    click(By.name("add"));
  }

  public void selectAllGroupsInContact() {
    click(By.name("group"));
    new Select(wd.findElement(By.name("group"))).selectByVisibleText("[all]");
    click(By.xpath("//option[@value='']"));

  }

  public void selectToGroup(int groupId, String groupName) {
    click(By.name("to_group"));
    new Select(wd.findElement(By.name("to_group"))).selectByVisibleText("Тест1");
    //var x = wd.findElement(By.cssSelector(String.format("option[value='%s']", groupId)));
    click(By.cssSelector(String.format("option[value='%s']", groupId)));
    //wd.findElement(By.xpath("(//option[@value='176'])[2]")).click();
    wd.findElement(By.name(("add"))).click();
  }

}

