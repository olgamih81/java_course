package ru.stqa.course.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.course.addressbook.model.GroupData;
import ru.stqa.course.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;
//модификация выьранной группы
public class GroupModificationTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        if (app.db().groups().size() == 0) {
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("TestGroupName1"));
        }
    }

    @Test
    public void testGroupModifacation() {
        Groups before = app.db().groups();      // получение списка групп из bd
        GroupData modifiedGroup = (GroupData) before.iterator().next();
        GroupData group = new GroupData().
                withId(modifiedGroup.getId()).withName("TestGroupName2").
                withHeader("TestGroupHead2").withFooter("TestGroupFoot2");
        app.goTo().groupPage();
        app.group().modify(group);
        assertThat(app.group().count(), equalTo(before.size()));
        Groups after = app.db().groups();

        assertThat(after, equalTo(before.withOut(modifiedGroup).withAdded(group)));
    }
}
