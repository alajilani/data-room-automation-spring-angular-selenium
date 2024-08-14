package com.ads.robot.entities;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity
public class Loginpage {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String username;
    String password;
    String url;
    String success_page_name;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String userName) {
        this.username = userName;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSuccess_page_name() {
        return success_page_name;
    }

    public void setSuccess_page_name(String success_page_name) {
        this.success_page_name = success_page_name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url ) {
        this.url = url;
    }

}
