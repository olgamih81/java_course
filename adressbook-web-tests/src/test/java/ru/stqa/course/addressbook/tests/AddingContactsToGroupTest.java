package ru.stqa.course.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.course.addressbook.model.ContactData;
import ru.stqa.course.addressbook.model.GroupData;
import ru.stqa.course.addressbook.model.Groups;

import java.util.Iterator;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class AddingContactsToGroupTest extends TestBase{

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
    public void ensurePreconditionsGroup() {
        if (app.db().groups().size() == 0) {
            addNewGroup();
            app.contact().home();
        }
    }

    @BeforeMethod
    public void selectAllGroups() {
        app.contact().filtrAllGroups(); //вынести в before
    }

    @Test
    public void testAddingContactsToGroup() {
        Groups groups = app.db().groups();
        ContactData contact = null;
        Groups contactGroup = null;
        int groupId = 0;

        Iterator<ContactData> contactDataIterator = app.db().contacts().iterator();

        while (groupId == 0) {
            if (contactDataIterator.hasNext()) {
                contact = contactDataIterator.next();
                contactGroup = contact.getGroups();

                if (contactGroup.size() != groups.size()) {
                    Groups finalContactGroup = contactGroup;
                    Optional<GroupData> selectedGroup = groups.stream()
                            .filter(g -> !finalContactGroup.contains(g))
                            .findFirst();
                    groupId = selectedGroup.get().getId();
                }
            }
            else {
                addNewGroup();
                groups = app.db().groups();
                contactDataIterator = app.db().contacts().iterator();
                app.contact().home();
            }
        }
        //добавляем контакт в группу
        app.contact().addingContactToGroup(contact, groupId);

        // проверка добавления контакта в группы
        ContactData finalContact = contact;
        Groups contactGroupAfter = app.db().contacts()
                .stream()
                .filter(c -> c.getId() == finalContact.getId())
                .findFirst()
                .get()
                .getGroups();


        assertThat(contactGroupAfter.size(), equalTo(contactGroup.size()+1));
    }

    public void addNewGroup() {
        app.goTo().groupPage();
        app.group().create(new GroupData().withName("NewGroup"));
    }


}
