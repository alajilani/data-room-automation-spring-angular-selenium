package com.ads.robot.entities;

import javax.persistence.*;
import java.util.List;

@Entity
public class TestSuite {

    @Id
    @GeneratedValue
    private int id;

    private String Name;

    @OneToMany(mappedBy = "testSuite", cascade = CascadeType.ALL)
    private List<TestCase> testCases;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public List<TestCase> getTestCases() {
        return testCases;
    }

    public void setTestCases(List<TestCase> testCases) {
        this.testCases = testCases;
    }
}
