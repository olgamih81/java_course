package ru.stqa.course.rest.tests;

import com.google.gson.JsonObject;
import org.testng.annotations.Test;
import ru.stqa.course.rest.appmanager.RestHelper;
import ru.stqa.course.rest.model.Issue;

import java.io.IOException;
import java.util.Set;

import static org.testng.Assert.*;

public class RestTests extends TestBase{

    int issueId = 300;

    @Test //(enabled = false)
    public void testCreateIssue() throws IOException {
        skipIfNotFixed(issueId);

        Set<Issue> oldIssues = RestHelper.getIssues();
        Issue newIssue = new Issue().withSubject("RestTest issue").withDescription("RestTest description");
        int issueId = RestHelper.createIssue(newIssue);
        Set<Issue> newIssues = RestHelper.getIssues();
        oldIssues.add(newIssue.withId(issueId));
        assertEquals(newIssues, oldIssues);
    }

    @Test
    public void testGetIssueStatus() throws IOException {

        skipIfNotFixed(issueId);

        JsonObject asJsonObject = RestHelper.getIssueStatus(issueId).getAsJsonObject();

        String subject = asJsonObject.get("subject").getAsString();
        String description= asJsonObject.get("description").getAsString();
        String status_name = asJsonObject.get("state_name").getAsString();
        String status = asJsonObject.get("state").getAsString();
        System.out.println(subject);
        System.out.println(description);
        System.out.println(status_name);

    }
}
