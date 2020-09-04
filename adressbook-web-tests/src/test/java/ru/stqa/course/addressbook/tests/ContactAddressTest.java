package ru.stqa.course.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.course.addressbook.model.ContactData;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

public class ContactAddressTest extends TestBase{
    @BeforeMethod
    public void ensurePreconditions() {
        app.contact().home();
        if (app.contact().all().size() == 0) {
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
    public void testContactAddress() {
        ContactData contact = app.contact().all().iterator().next();
        ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

        assertThat(contact.getAddress(), equalTo(contactInfoFromEditForm.getAddress()));
    }
}
