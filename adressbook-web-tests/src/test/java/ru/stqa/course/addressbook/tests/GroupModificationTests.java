package ru.stqa.course.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.course.addressbook.model.GroupData;
import ru.stqa.course.addressbook.model.Groups;

import java.util.Set;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;
import static org.testng.Assert.assertEquals;

public class GroupModificationTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().groupPage();
        if (app.group().all().size() == 0) {
            app.group().create(new GroupData().withName("TestGroupName1"));
        }
    }

    @Test
    public void testGroupModifacation() {
        Groups before = app.group().all();
        GroupData modifiedGroup = (GroupData) before.iterator().next();
        GroupData group = new GroupData().
                withId(modifiedGroup.getId()).withName("TestGroupName2").withHeader("TestGroupHead2").withFooter("TestGroupFoot2");
        app.group().modify(group);
        assertThat(app.group().count(), equalTo(before.size()));
        Groups after = app.group().all();

        //assertThat(after.size(), equalTo(before.size()));
        assertThat(after, equalTo(before.withOut(modifiedGroup).withAdded(group)));

        //assertEquals(after.size(), before.size());
        //before.remove(modifiedGroup);
        //before.add(group);
        //Assert.assertEquals(before, after);
    }

 //   @NotNull

}
