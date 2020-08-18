package ru.stqa.course.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.course.addressbook.model.ContactData;

import java.util.List;

public class ContactCreationTest extends TestBase {

  @Test
  public void testContactCreation() throws Exception {
    app.getContactHelper().goContactHome();
    List<ContactData> before = app.getContactHelper().getContactList();
    app.getContactHelper().
            createContact(
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
                    "TestGroupName1", // надо сделать получение по xpath
                    "test_address2",
                    "test_phone2",
                    "test_notes"));
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size() + 1);
    after.remove(0);
    Assert.assertEquals(after, before);
  }
}



