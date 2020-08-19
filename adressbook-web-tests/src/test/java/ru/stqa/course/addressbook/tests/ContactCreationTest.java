package ru.stqa.course.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.course.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactCreationTest extends TestBase {

  @Test
  public void testContactCreation() throws Exception {
    app.getContactHelper().goContactHome();
    List<ContactData> before = app.getContactHelper().getContactList();
    ContactData contact = new ContactData(
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
            "test_notes");
    app.getContactHelper().createContact(contact);
    List<ContactData> after = app.getContactHelper().getContactList();
    Assert.assertEquals(after.size(), before.size() + 1);
    before.add(contact);

    Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
  }
}



