package ru.stqa.course.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.course.addressbook.model.ContactData;

public class ContactModificationTests extends TestBase {
    @Test
    public void testContactModification() {
        app.getContactHelper().goContactHome();
        app.getContactHelper().editContact();
        app.getContactHelper().editContactForm("Name1", "Name2");
        app.getContactHelper().updateContact();
        app.getContactHelper().returnToContactPage();
    }
}
