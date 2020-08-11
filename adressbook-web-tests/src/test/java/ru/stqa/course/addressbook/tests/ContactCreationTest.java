package ru.stqa.course.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.course.addressbook.model.ContactData;

public class ContactCreationTest extends TestBase {

  @Test
  public void testContactCreation() throws Exception {
    //app.getContactHelper().initContactCreation();
    app.getContactHelper().createContact(
            new ContactData(
                    "test_name",
                    "test_middle",
                    "test_lastname",
                    "test_nickname",
                    "tes_ttitle",
                    "test_company",
                    "test_address",
                    "test_home",
                    "test_mobile",
                    "test_work",
                    "test_fax",
                    "test_email",
                    "test_email2",
                    "test_email3",
                    "test_homepage",
                    "TestGroupName111", // надо сделать получение по xpath
                    "test_address2",
                    "test_phone2",
                    "test_notes"));

    //app.getContactHelper().fillBirthday("15", "January", "2001");
    //app.getContactHelper().fillAnniversary("18", "December", "2020");
    //app.getContactHelper().submitContact();
    //app.getContactHelper().returnToContactPage();
  }
}



