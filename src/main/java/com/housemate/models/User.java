package com.housemate.models;

import com.housemate.validators.email.ValidEmail;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="username", nullable = false)
    private String username;

    @ValidEmail
    @Column(name="emailAddress", nullable = false)
    private String emailAddress;

    @Column(name= "auth")
    private String auth;

    @Column(name = "password")
    private String password;

    @OneToMany(fetch = FetchType.LAZY,
            mappedBy = "user",
            cascade = {CascadeType.PERSIST,CascadeType.MERGE,
                    CascadeType.PERSIST, CascadeType.REFRESH})
    private List<Expense> expenses;

    public User(){}

    public User(String emailAddress, String username) {
        this.username = username;
        this.emailAddress = emailAddress;
    }

    public User(String emailAddress, String username, String auth, String password) {
        this.username = username;
        this.emailAddress = emailAddress;
        this.auth = auth;
        this.password = password;
    }

    public User(String emailAddress, String username, String password) {
        this.username = username;
        this.emailAddress = emailAddress;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getAuth() {
        return auth;
    }

    public void setAuth(String auth) {
        this.auth = auth;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Expense> getExpenses() {
        return expenses;
    }

    public void setExpenses(List<Expense> expenses) {
        this.expenses = expenses;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", emailAddress='" + emailAddress + '\'' +
                '}';
    }
}
