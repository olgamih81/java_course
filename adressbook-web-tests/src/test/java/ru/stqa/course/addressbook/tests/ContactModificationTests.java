package ru.stqa.course.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.course.addressbook.model.ContactData;
import ru.stqa.course.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

public class ContactModificationTests extends TestBase {
    @BeforeMethod
    public void ensurePreconditions() {
        if (app.db().contacts().size() == 0) {
            app.contact().home();
            app.contact().create(new ContactData().
                    withFirstname("test_name").withMiddlename("test_middle").withLastname("test_lastname").withNickname("test_nickname").
                    withTittle("test_title").withCompany("test_company").withAddress("test_address").
                    withHomePhone("111").withMobilePhone("222").withWorkPhone("333").withPhone2("444").
                    withFax("test_fax").withEmail("test_email").withEmail2("test_email2").withEmail3("test_email3").
                    withHomepage("test_homepage").withAddress2("test_address2").
                    withNewgroup("TestGroupName1").withNotes("test_notes"));
        }
    }

    @Test
    public void testContactModification() {
        //Contacts before = app.contact().all();
        Contacts before = app.db().contacts();
        ContactData modifiedContact = before.iterator().next(); //выбор элемента
        ContactData contact = new ContactData().withId(modifiedContact.getId()).
                withFirstname("test_name2").withMiddlename("test_middle2").withLastname("test_lastname2").withNickname("test_nickname2").
                withTittle("test_title2").withCompany("test_company2").withAddress("test_address2").
                withHomePhone("111-11").withMobilePhone("222-22").withWorkPhone("333-33").withPhone2("444-44").
                withFax("test_fax2").withEmail("test_email2").withEmail2("test_email22").withEmail3("test_email32").
                withHomepage("test_homepage2").withAddress2("test_address22").
                withNewgroup("TestGroupName1").withNotes("test_notes2");
        app.contact().modify(contact);
        assertThat(app.contact().count(), equalTo(before.size()));
        //Contacts after = app.contact().all();
        Contacts after = app.db().contacts();

        assertThat(after, equalTo(before.withOut(modifiedContact).withAdded(contact)));
    }
}
