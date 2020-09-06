package ru.stqa.course.addressbook.tests;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.course.addressbook.model.ContactData;
import ru.stqa.course.addressbook.model.Contacts;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTest extends TestBase {

  @DataProvider
  public Iterator<Object[]> validContactJson() throws IOException {
    BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.json")));
    String json = "";
    String line = reader.readLine();
    while (line != null) {
      json += line;
      line = reader.readLine();
    }
    Gson gson = new Gson();
    List<ContactData> contacts = gson.fromJson(json, new TypeToken<List<ContactData>>(){}.getType());
    return contacts.stream().map((c) -> new Object[] {c}).collect(Collectors.toList()).iterator();
  }

  @Test (dataProvider = "validContactJson")
  public void testContactCreation(ContactData contact) throws Exception {
    app.contact().home();
    Contacts before = app.contact().all();

    /*File photo = new File("src/test/resources/rabbit.jpg");
    System.out.println(photo.getAbsolutePath());

    ContactData contact = new ContactData().
            withFirstname("test_name").withMiddlename("test_middle").withLastname("test_lastname").withNickname("test_nickname").
            withTittle("tes_ttitle").withCompany("test_company").withAddress("test_address").
            //withHome("test_home").withMobile("test_mobile").withWork("test_work").withPhone2("test_phone2").
            withFax("test_fax").withEmail("test_email").withEmail2("test_email2").withEmail3("test_email3").
            withHomepage("test_homepage").withAddress2("test_address2").
            withNewgroup("TestGroupName1").withNotes("test_notes")
            .withPhoto(photo);*/

    app.contact().create(contact);
    assertThat(app.contact().count(), equalTo(before.size() + 1));
    Contacts after = app.contact().all();
    assertThat(after, equalTo(
            before.withAdded(contact.withId(after.stream().mapToInt((c)->c.getId()).max().getAsInt()))));

    //assertThat(after.size(), equalTo(before.size() + 1));
    //Assert.assertEquals(after.size(), before.size() + 1);
    //contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt());
    //before.add(contact);
    //Assert.assertEquals(before, after);
  }
}



