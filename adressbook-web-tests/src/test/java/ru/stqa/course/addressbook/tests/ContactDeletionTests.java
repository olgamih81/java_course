package ru.stqa.course.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.course.addressbook.model.ContactData;
import ru.stqa.course.addressbook.model.Contacts;

import java.util.Set;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

public class ContactDeletionTests extends TestBase {
    @BeforeMethod
    public void ensurePreconditions() {
        app.contact().home();
        if (app.contact().all().size() == 0) {
            app.contact().create(new ContactData().
                    withFirstname("test_name").withMiddlename("test_middle").withLastname("test_lastname").withNickname("test_nickname").
                    withTittle("tes_ttitle").withCompany("test_company").withAddress("test_address").withHome("test_home").
                    withMobile("test_mobile").withWork("test_work").withFax("test_fax").
                    withEmail("test_email").withEmail2("test_email2").withEmail3("test_email3").
                    withHomepage("test_homepage").withAddress2("test_address2").withPhone2("test_phone2").
                    withNewgroup("TestGroupName1").withNotes("test_notes"));
        }
    }

    @Test
    public void testContactDeleted() {
        Contacts before = app.contact().all();
        ContactData deletedContact = before.iterator().next();
        app.contact().delete(deletedContact);

        Contacts after = app.contact().all();
        assertThat(after.size(), equalTo(before.size() - 1));
        assertThat(after, equalTo(before.withOut(deletedContact)));

        //Assert.assertEquals(after.size(), before.size()-1);
        //before.remove(deletedContact);
        //Assert.assertEquals(before, after);
    }
}
