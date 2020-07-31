package ru.stqa.course.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.course.addressbook.model.GroupData;

public class GroupCreationTests extends TestBase {

  @Test
  public void testGroupCreation() throws Exception {
    app.getNavigationHelper().gotoGroupPage();
    app.getGroupHelper().initGroupCreation();
    app.getGroupHelper().fillGroupForm(new GroupData(
            "TestGroupName1",
            "TestGroupHeader2",
            "TestGroupFooter3"));
    app.getGroupHelper().submitGroup();
    app.getGroupHelper().returnToGroupPage();
  }
}
