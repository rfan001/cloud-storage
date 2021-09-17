package com.yowbuy.cloudstorage.model;

public class User {
/*    userid INT PRIMARY KEY auto_increment,
    username VARCHAR(20),
    salt VARCHAR(100),
    password VARCHAR(100),
    firstname VARCHAR(20),
    lastname VARCHAR(20)*/
    private Integer userid;
    private String username;
    private String salt;
    private String password;
    private String firstName;
    private String lastName;

    public User() {
    }

    public User(Integer userid, String username, String salt, String password, String firstName, String lastName) {
        this.userid = userid;
        this.username = username;
        this.salt = salt;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return "User{" +
                "userid=" + userid +
                ", username='" + username + '\'' +
                ", salt='" + salt + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
