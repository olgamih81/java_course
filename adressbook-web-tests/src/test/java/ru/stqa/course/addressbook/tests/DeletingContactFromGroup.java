package ru.stqa.course.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.course.addressbook.model.ContactData;

public class DeletingContactFromGroup extends TestBase{

    @BeforeMethod
    public void ensurePreconditionsContact() {
        if (app.db().contacts().size() == 0) {
            app.contact().home();
            app.contact().create(new ContactData().
                    withFirstname("test_name").withMiddlename("test_middle").withLastname("test_lastname").withNickname("test_nickname").
                    withTittle("test_title").withCompany("test_company").withAddress("test_address").
                    withHomePhone("111").withMobilePhone("222").withWorkPhone("333").withPhone2("444").
                    withFax("test_fax").withEmail("test_email").withEmail2("test_email2").withEmail3("test_email3").
                    withHomepage("test_homepage").withAddress2("test_address2").

                    withNotes("test_notes"));
        }
    }

    @BeforeMethod
    public void selectAllGroups() {
        app.contact().selectAllGroupsInContact();
    }

    @Test
    public void testDeletingContactFromGroup() {

    }
}
