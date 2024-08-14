package com.ads.robot.entities;


import com.vladmihalcea.hibernate.type.json.JsonType;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.util.List;

@Entity
@TypeDef(name = "json", typeClass = JsonType.class)
public class FunctionRobot {
    @Id
    @GeneratedValue
    private int id;

    private String name;

    @Type(type = "json")
    @Column(columnDefinition = "json")
    private List<String> parameters;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "function",fetch = FetchType.LAZY)
    private List<TestCaseRow> testCaseRows;

    @ManyToOne
    private Library library;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getParameters() {
        return parameters;
    }

    public void setParameters(List<String> parameters) {
        this.parameters = parameters;
    }

    public List<TestCaseRow> getTestCaseRows() {
        return testCaseRows;
    }

    public void setTestCaseRows(List<TestCaseRow> testCaseRows) {
        this.testCaseRows = testCaseRows;
    }

    public Library getLibrary() {
        return library;
    }

    public void setLibrary(Library library) {
        this.library = library;
    }

    @Override
    public boolean equals(Object o){
        FunctionRobot func = (FunctionRobot) o;
        return this.name.equals(func.getName());
    }


}
