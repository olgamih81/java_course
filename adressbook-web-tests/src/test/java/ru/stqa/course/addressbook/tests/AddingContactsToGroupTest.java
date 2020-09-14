package ru.stqa.course.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.course.addressbook.model.ContactData;
import ru.stqa.course.addressbook.model.GroupData;
import ru.stqa.course.addressbook.model.Groups;

import java.util.Optional;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;
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
                    //withNewgroup("TestGroupName1").
                            withNotes("test_notes"));
        }
    }

    @BeforeMethod
    public void ensurePreconditionsGroup() {
        if (app.db().groups().size() == 0) {
            addNewGroup();
        }
    }

    @BeforeMethod
    public void selectAllGroups() {
        app.contact().selectAllGroupsInContact(); //вынести в before
    }

    @Test
    public void testAddingContactsToGroup() {
        ContactData contact = app.db().contacts().iterator().next();
        Groups groups = app.db().groups();
        Groups contactGroup = contact.getGroups();

        int groupId = 0;
        String groupName = null;

        if (contactGroup.size() > 0) {
            while (groupId == 0) {
                if ((contactGroup.size() != groups.size())) {
                    Optional<GroupData> selectedGroup = groups.stream()
                            .filter(g -> !contactGroup.contains(g))
                            .findFirst();
                    System.out.println(selectedGroup);
                    groupId = selectedGroup.get().getId();
                    groupName = selectedGroup.get().getName();

                } else {
                    System.out.println("добавляем новую группу");
                    addNewGroup();
                    groups = app.db().groups();
                    app.contact().returnToGroupPageContact();
                }
            }
        } else {
            groupId = groups.stream().findFirst().get().getId();
            groupName = groups.stream().findFirst().get().getName();
        }
        app.contact().addingContactToGroup(contact, groupId, groupName); // не работает :_(

        Groups contactGroupAfter = contact.getGroups();
        assertThat(contactGroupAfter.size(), equalTo(contactGroup.size()+1));
    }

    public void addNewGroup() {
        app.goTo().groupPage();
        app.group().create(new GroupData().withName("NewGroup"));
    }


}
