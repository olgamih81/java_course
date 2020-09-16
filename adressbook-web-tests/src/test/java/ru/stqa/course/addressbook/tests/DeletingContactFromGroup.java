package ru.stqa.course.addressbook.tests;

import org.openqa.selenium.By;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.course.addressbook.model.ContactData;
import ru.stqa.course.addressbook.model.GroupData;
import ru.stqa.course.addressbook.model.Groups;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

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
        ContactData cn = null;
        Groups contactGroup = null;
        int groupId = 0;

        Iterator<ContactData> contactDataIterator = app.db().contacts().iterator();

        // выбираем контакт с группами
        while (contactGroup == null) {

            if (contactDataIterator.hasNext()) {
                cn = contactDataIterator.next();
                if (cn.getGroups().size() > 0) {
                    contact = cn;
                    contactGroup = contact.getGroups();
                    System.out.println(contactGroup);
                }
            } else {
                createContact(cn);
                contactDataIterator = app.db().contacts().iterator();
            }
        }

        Optional<GroupData> selectedGroup = contactGroup.stream().findFirst();
        groupId = selectedGroup.get().getId();

        // открываем в фильтре выбранную группу
        app.contact().filtrSelectedGroup(groupId);
        // удаляем контакт из группы
        app.contact().deletedContactFromGroup(contact, groupId);

    }
    private void createContact(ContactData cn) {
        Groups groups = app.db().groups();
        ContactData newContact = cn.inGroup(groups.iterator().next());
        app.contact().create(newContact);

    }
}
