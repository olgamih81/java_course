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

public class ContactModificationTests extends TestBase {
    @BeforeMethod
    public void ensurePreconditions() {
        app.contact().home();
        if (app.contact().all().size() == 0) {
            app.contact().create(new ContactData().
                    withFirstname("test_name").withMiddlename("test_middle").withLastname("test_lastname").withNickname("test_nickname").
                    withTittle("test_title").withCompany("test_company").withAddress("test_address").
                    //withHome("test_home").withMobile("test_mobile").withWork("test_work").withPhone2("test_phone2").
                    withFax("test_fax").withEmail("test_email").withEmail2("test_email2").withEmail3("test_email3").
                    withHomepage("test_homepage").withAddress2("test_address2").
                    withNewgroup("TestGroupName1").withNotes("test_notes"));
        }
    }

    @Test
    public void testContactModification() {
        Contacts before = app.contact().all();
        ContactData modifiedContact = before.iterator().next(); //выбор элемента
        ContactData contact = new ContactData().withId(modifiedContact.getId()).
                withFirstname("test_name2").withMiddlename("test_middle2").withLastname("test_lastname2").withNickname("test_nickname2").
                withTittle("test_title2").withCompany("test_company2").withAddress("test_address2").
                //withHome("test_home2").withMobile("test_mobile2").withWork("test_work2").withPhone2("test_phone22").
                withFax("test_fax2").withEmail("test_email2").withEmail2("test_email22").withEmail3("test_email32").
                withHomepage("test_homepage2").withAddress2("test_address22").
                withNewgroup("TestGroupName1").withNotes("test_notes2");
        app.contact().modify(contact);
        assertThat(app.contact().count(), equalTo(before.size()));
        Contacts after = app.contact().all();

        assertThat(after, equalTo(before.withOut(modifiedContact).withAdded(contact)));

        /*assertThat(after.size(), equalTo(before.size()));
        Assert.assertEquals(after.size(), before.size());
        before.remove(modifiedContact);
        before.add(contact);
        Assert.assertEquals(before, after);*/
    }
}
