package ru.stqa.course.addressbook;

import java.util.concurrent.TimeUnit;
import org.testng.annotations.*;
import static org.testng.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;

public class GroupDeletionTests extends TestBase{

  @Test
  public void testGroupDeletionTests() throws Exception {

    gotoGroupPage();
    selectGroup();
    deleteSelectedGroups();
    returnToGroupPage();
  }

}
