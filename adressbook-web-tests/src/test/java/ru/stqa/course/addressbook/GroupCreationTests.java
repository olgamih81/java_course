package ru.stqa.course.addressbook;

import org.testng.annotations.Test;

public class GroupCreationTests extends TestBase {

  @Test
  public void testGroupCreation() throws Exception {
    gotoGroupPage();
    initGroupCreation();
    fillGroupForm(new GroupData("TestGroupName1", "TestGroupHeader2", "TestGroupFooter3"));
    submitGroup();
    returnToGroupPage();
  }


}
