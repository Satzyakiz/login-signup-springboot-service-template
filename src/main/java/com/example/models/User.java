package com.example.models;


import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;

import java.util.Optional;

@DynamoDbBean
public class User {
    private Integer id;
    private String username;
    private String firstname ;
    private String lastname;
    private String password;
    private String email;

    @DynamoDbPartitionKey
    public String getUsername() {
        return this.username;
    };
    public void setUsername(String s) {
        this.username = s;
    };

    public Integer getId() {
        return this.id;
    };

    public void setId(Integer i) {
        this.id = i;
    };

    public String getEmail() {
        return this.email;
    };
    public void setEmail(String s) {
        this.email = s;
    };

    public String getPassword() {
        return this.password;
    };
    public boolean checkPassword(String s) {
        return this.password.equals(s);
    };
    public void setPassword(String s) {
        this.password = s;
    };

    public String getFirstname() {
        return this.firstname;
    };
    public void setFirstname(String s) {
        this.firstname = s;
    };

    public String getLastname() {
        return this.lastname;
    };
    public void setLastname(String s) {
        this.lastname = s;
    };

}
