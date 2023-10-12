package org.example.app.entity.user;

import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class User {

    private Integer id;
    private String userName;
    private String name;
    private String email;

    public User() {
    }

    public User(String userName, String name, String email) {
        this.userName = userName;
        this.name = name;
        this.email = email;
    }

    public User(Integer id, String userName, String name, String email) {
        this.id = id;
        this.userName = userName;
        this.name = name;
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (!Objects.equals(id, user.id)) return false;
        if (!Objects.equals(userName, user.userName)) return false;
        if (!Objects.equals(name, user.name)) return false;
        return Objects.equals(email, user.email);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (userName != null ? userName.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        return result;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "id - " + this.getId() +
                ", userName: " + this.getUserName() +
                ", name: " + this.getName() +
                ", email: " + this.getEmail() + "\n";
    }
}
