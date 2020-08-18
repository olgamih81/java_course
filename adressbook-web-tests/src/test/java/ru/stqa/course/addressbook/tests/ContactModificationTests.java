package ru.stqa.course.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.course.addressbook.model.ContactData;

import java.util.HashSet;
import java.util.List;

public class ContactModificationTests extends TestBase {
    @Test
    public void testContactModification() {
        app.getContactHelper().goContactHome();
        if (! app.getContactHelper().existenceOfContact()) {
            app.getContactHelper().createContact(new ContactData("test_name",
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
                    "TestGroupName111",
                    "test_address2",
                    "test_phone2",
                    "test_notes"));
        }
        List<ContactData> before = app.getContactHelper().getContactList();
        app.getContactHelper().selectedContact(before.size()-1);
        app.getContactHelper().editContact();
        ContactData contact = new ContactData(before.get(before.size()-1).getId(),
                "test1", "test2",
                null, null, null, null, null, null, null, null,
                null, null, null, null, null, null, null, null,
                null);
        app.getContactHelper().fillContactForm(contact, false);
        app.getContactHelper().updateContact();
        app.getContactHelper().returnToContactPage();
        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size());

        before.remove(before.size()-1);
        before.add(contact);
        Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
    }
}
