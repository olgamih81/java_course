package ru.stqa.course.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.course.addressbook.model.GroupData;

public class GroupDeletionTests extends TestBase{

  @Test
  public void testGroupDeletionTests() throws Exception {
    app.getNavigationHelper().gotoGroupPage();
    if (! app.getGroupHelper().existenceOfGroup()) {
      app.getGroupHelper().createGroup(new GroupData(
              "TestGr1",
              "TestGr2",
              "TestGr3"));
    }
    app.getGroupHelper().selectGroup();
    app.getGroupHelper().deleteSelectedGroups();
    app.getGroupHelper().returnToGroupPage();
  }
}
