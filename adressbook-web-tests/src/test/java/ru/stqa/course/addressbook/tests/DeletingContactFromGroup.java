package ru.stqa.course.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.course.addressbook.model.ContactData;
import ru.stqa.course.addressbook.model.GroupData;
import ru.stqa.course.addressbook.model.Groups;

import java.util.Iterator;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class DeletingContactFromGroup extends TestBase{

    @BeforeMethod
    public void ensurePreconditionsGroup() {
        if (app.db().groups().size() == 0) {
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("NewGroup"));
            app.contact().home();
        }
    }

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
        app.contact().filtrAllGroups();
    }

    @Test
    public void testDeletingContactFromGroup() {
        ContactData contact = null;
        Groups contactGroup = null;
        int groupId = 0;
        Groups groups = app.db().groups();

        Iterator<ContactData> contactDataIterator = app.db().contacts().iterator();

        // выбираем контакт с группами
        while (contactGroup == null) {

            if (contactDataIterator.hasNext()) {
                contact = contactDataIterator.next();
                if (contact.getGroups().size() > 0) {
                    contactGroup = contact.getGroups();
                }
            } else {
                groupId = groups.stream().findFirst().get().getId();
                app.contact().addingContactToGroup(contact, groupId);
                ContactData finalContact = contact;
                contactGroup = app.db().contacts().stream().
                        filter(c -> c.getId() == finalContact.getId()).
                        findFirst().get().getGroups();
                break;
            }
        }

        Optional<GroupData> selectedGroup = contactGroup.stream().findFirst();
        groupId = selectedGroup.get().getId();

        // открываем в фильтре выбранную группу
        app.contact().filtrSelectedGroup(groupId);
        // удаляем контакт из группы
        app.contact().deletedContactFromGroup(contact, groupId);

        // проверка удаления контакта из группы
        ContactData finalContact = contact;
        Groups contactGroupAfter = app.db().contacts()
                .stream()
                .filter(c -> c.getId() == finalContact.getId())
                .findFirst()
                .get()
                .getGroups();


        assertThat(contactGroupAfter.size(), equalTo(contactGroup.size()-1));

    }
}
