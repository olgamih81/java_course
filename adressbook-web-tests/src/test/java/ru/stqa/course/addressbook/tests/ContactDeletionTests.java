package ru.stqa.course.addressbook.tests;

import org.testng.annotations.Test;

public class ContactDeletionTests extends TestBase {
    @Test
    public void testContactDeleted() {
        app.getContactHelper().goContactHome();
        app.getContactHelper().selectedContact();
        app.getContactHelper().deletedContact();
        app.getContactHelper().alertContactDeleted();
    }
}
