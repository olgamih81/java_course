package ru.stqa.course.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.course.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

public class ContactCreationTest extends TestBase {

  @Test
  public void testContactCreation() throws Exception {
    app.contact().home();
    Set<ContactData> before = app.contact().all();
    ContactData contact = new ContactData().
            withFirstname("test_name").withMiddlename("test_middle").withLastname("test_lastname").withNickname("test_nickname").
            withTittle("tes_ttitle").withCompany("test_company").withAddress("test_address").withHome("test_home").
            withMobile("test_mobile").withWork("test_work").withFax("test_fax").
            withEmail("test_email").withEmail2("test_email2").withEmail3("test_email3").
            withHomepage("test_homepage").withAddress2("test_address2").withPhone2("test_phone2").
            withNewgroup("TestGroupName1").withNotes("test_notes");

    app.contact().create(contact);
    Set<ContactData> after = app.contact().all();
    Assert.assertEquals(after.size(), before.size() + 1);

    contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt());
    before.add(contact);
    Assert.assertEquals(before, after);
  }
}



