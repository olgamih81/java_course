package ru.stqa.course.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.course.addressbook.model.GroupData;

public class GroupCreationTests extends TestBase {

  @Test
  public void testGroupCreation() throws Exception {
    app.gotoGroupPage();
    app.initGroupCreation();
    app.fillGroupForm(new GroupData("TestGroupName1", "TestGroupHeader2", "TestGroupFooter3"));
    app.submitGroup();
    app.returnToGroupPage();
  }
}
