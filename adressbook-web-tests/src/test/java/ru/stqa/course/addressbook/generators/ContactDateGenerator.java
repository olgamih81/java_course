package ru.stqa.course.addressbook.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.thoughtworks.xstream.XStream;
import ru.stqa.course.addressbook.model.ContactData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class ContactDateGenerator {

    @Parameter(names = "-c", description = "Contact count")
    public int count;
    @Parameter(names = "-f", description = "Target file")
    public String file;
    @Parameter(names = "-d", description = "Date format")
    public String format;

    public static void main(String[] args) throws IOException {
        ContactDateGenerator generator = new ContactDateGenerator();
        JCommander jCommander = new JCommander(generator);
        try {
            jCommander.parse(args);
        } catch (ParameterException ex) {
            jCommander.usage();
            return;
        }
        generator.run();
    }

    private void run() throws IOException {
        List<ContactData> contacts = generateContacts(count);
        if (format.equals("csv")) {
            saveAsCsv(contacts, new File(file));
        } else if (format.equals("xml")) {
            saveAsXml(contacts, new File(file));
        } else if (format.equals("json")) {
            saveAsJson(contacts, new File(file));
        } else {
            System.out.println("Unknown format: " + format);
        }
    }

    private void saveAsJson(List<ContactData> contacts, File file) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
        String json = gson.toJson(contacts);
        try(Writer writer = new FileWriter(file)) {
            writer.write(json);
        }
    }

    private void saveAsXml(List<ContactData> contacts, File file) throws IOException {
        XStream stream = new XStream();
        stream.processAnnotations(ContactData.class);
        String xml = stream.toXML(contacts);
        try(Writer writer = new FileWriter(file)) {
            writer.write(xml);
        }
    }

    private static void saveAsCsv(List<ContactData> contacts, File file) throws IOException {
        try(Writer writer = new FileWriter(file)) {
            for (ContactData contact : contacts) {
                writer.write(String.format("%s;%s;%s;%s \n", contact.getFirstname(), contact.getLastname(),
                        contact.getAddress(), contact.getMiddlename()));
            }
        }
    }

    private static List<ContactData> generateContacts(int count) {
        List<ContactData> contacts = new ArrayList<>();
        for (int i=0; i<count; i++) {
            contacts.add(new ContactData().withFirstname(String.format("testFirstname %s", i))
            .withLastname(String.format("testLastname %s", i))
            .withAddress(String.format("testAddress %s", i))
            .withMiddlename(String.format("testMiddlename %s", i))
            .withHomepage(String.format("testHomepage %s", i))
            .withNickname(String.format("testNickname %s", i))
            .withTittle(String.format("testTittle %s", i))
            .withHomePhone(String.format("111-11-1%s", i))
            .withMobilePhone(String.format("222 22 2%s", i))
            .withWorkPhone(String.format("333333%s", i))
            .withPhone2(String.format("444-44-4%s", i))
            //.withNewgroup(String.format("TestGroupName1"))
            .withEmail(String.format("test@Email %s", i))
            .withEmail2(String.format("test@Email2 %s", i))
            .withEmail3(String.format("test@Email3 %s", i))
            .withCompany(String.format("testCompany %s", i))
            .withFax(String.format("testFax %s", i))
            .withAddress2(String.format("test Address2 %s", i))
            .withNotes(String.format("test Notes %s", i))
            );
        }
        return contacts;
    }
}
