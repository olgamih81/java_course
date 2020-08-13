package ru.stqa.course.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.course.addressbook.model.ContactData;

public class ContactModificationTests extends TestBase {
    @Test
    public void testContactModification() {
        app.getContactHelper().goContactHome();
        int before = app.getContactHelper().getContactCount();
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
        app.getContactHelper().editContact();
        app.getContactHelper().fillContactForm(new ContactData(
                "test1", "test2",
                null, null, null, null, null, null, null, null,
                null, null, null, null, null, null, null, null,
                null), false);
        app.getContactHelper().updateContact();
        app.getContactHelper().returnToContactPage();
        int after = app.getContactHelper().getContactCount();
        Assert.assertEquals(after, before);
    }
}
