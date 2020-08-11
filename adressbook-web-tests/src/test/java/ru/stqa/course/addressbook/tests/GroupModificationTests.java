package ru.stqa.course.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.course.addressbook.model.GroupData;

public class GroupModificationTests extends TestBase {
    @Test
    public void testGroupModifacation() {
        app.getNavigationHelper().gotoGroupPage();
        if (! app.getGroupHelper().existenceOfGroup()) {
            app.getGroupHelper().createGroup(new GroupData(
                    "TestGr1",
                    "TestGr2",
                    "TestGr3"));
        }
        app.getGroupHelper().selectGroup();
        app.getGroupHelper().editGroup();
        app.getGroupHelper().fillGroupForm(new GroupData(
                "TestGroupName111",
                "TestGroupHeader222",
                "TestGroupFooter333"));
        app.getGroupHelper().updateGroup();
        app.getGroupHelper().returnToGroupPage();
    }
}
