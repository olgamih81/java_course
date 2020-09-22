package ru.stqa.course.rest.model;

import java.util.Objects;
/* [{"assignee_id":0,"category_id":0,"created":"2020-09-22T23:31:27+03:00","creator":{"id":"1","created":"2018-06-10T14:55:23+03:00","updated":"2020-08-15T16:43:07+03:00","firstname":"admin","lastname":"admin","name":"admin admin","email":"admin@demo.com","username":"admin","notifications":{"creator":true,"assignee":true,"following":true,"commented":true,"mychange":true,"mentioned":true,"allcreates":true},"groups":[],"settings":{"page_size":500,"language":"en_US","use_editor":"true","draft_issue":{"date":"2020-08-15T16:43:07+03:00","issue":{"project":"","category":"","priority":"1","assignee":"","milestone":"","subject":"111","description":"1"}}},"owner":true,"timezone":"Europe/Moscow","state":1,"api_key":"288f44776e7bec4bf44fdfeb1e646490"},"creator_id":1,"description":"RestTest description","id":294,"labels":[],"milestone_id":0,"percentage":0,"priority":"1","priority_name":"Normal","project_id":0,"related_issue_ids":[],"resolved":"1970-01-01T03:00:00+03:00","state":"0","state_name":"Open","subject":"RestTest issue","updated":"2020-09-22T23:31:28+03:00"}]
*/

public class Issue {


    public int state;
    public String state_name;
    private String subject;
    private int id;

    public int getId() {
        return id;
    }

    public Issue withId(int id) {
        this.id = id;
        return this;
    }

    public String getSubject() {
        return subject;
    }

    public Issue withSubject(String subject) {
        this.subject = subject;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Issue withDescription(String description) {
        this.description = description;
        return this;
    }

    private String description;


    public int getState() {
        return state;
    }

    public Issue withState(int state) {
        this.state = state;
        return this;
    }

    public String getState_name() {
        return state_name;
    }

    public Issue withState_name(String state_name) {
        this.state_name = state_name;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Issue issue = (Issue) o;
        return id == issue.id &&
                Objects.equals(subject, issue.subject) &&
                Objects.equals(description, issue.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(subject, id, description);
    }
}
