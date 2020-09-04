package ru.stqa.course.addressbook.tests;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.course.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

public class ContactPhoneTest extends TestBase {
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
    public void testContactPhones() {
        ContactData contact = app.contact().all().iterator().next();
        ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

        assertThat(contact.getAllPhones(), equalTo(mergePhones(contactInfoFromEditForm)));

        /*assertThat(contact.getHomePhone(), equalTo(cleaned(contactInfoFromEditForm.getHomePhone())));
        assertThat(contact.getMobilePhone(), equalTo(cleaned(contactInfoFromEditForm.getMobilePhone())));
        assertThat(contact.getWorkPhone(), equalTo(cleaned(contactInfoFromEditForm.getWorkPhone())));
        assertThat(contact.getPhone2(), equalTo(cleaned(contactInfoFromEditForm.getPhone2())));*/
    }

    private String mergePhones(ContactData contact) {
       return Arrays.asList(contact.getHomePhone(), contact.getMobilePhone(), contact.getWorkPhone(), contact.getPhone2())
               .stream().filter((s)-> ! s.equals(""))
               .map(ContactPhoneTest::cleaned)
               .collect(Collectors.joining("\n"));
    }

    public static String cleaned(String phone) {
        return phone.replaceAll("\\s","").replaceAll("[-()]","");
    }
}
