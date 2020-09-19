package ru.stqa.course.mantis.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "mantis_user_table")
public class UserData {

    @Id
    @Column(name = "id")
    private int id = Integer.MAX_VALUE;

    @Column(name = "username")
    private String username;

    @Column(name = "realname")
    private String realname;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    public int getId() {
        return id;
    }

    public void withId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void withUsername(String username) {
        this.username = username;
    }

    public String getRealname() {
        return realname;
    }

    public void withRealname(String realname) {
        this.realname = realname;
    }

    public String getEmail() {
        return email;
    }

    public void withEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void withPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserData userData = (UserData) o;
        return Objects.equals(username, userData.username) &&
                Objects.equals(email, userData.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, email);
    }
}
