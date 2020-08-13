package ru.stqa.course.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.course.addressbook.model.ContactData;

public class ContactDeletionTests extends TestBase {
    @Test
    public void testContactDeleted() {
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
            before = before + 1;
        }
        app.getContactHelper().selectedContact();
        app.getContactHelper().deletedContact();
        app.getContactHelper().alertContactDeleted();
        app.getContactHelper().goContactHome();

        int after = app.getContactHelper().getContactCount();
        Assert.assertEquals(after, before - 1);
    }
}
