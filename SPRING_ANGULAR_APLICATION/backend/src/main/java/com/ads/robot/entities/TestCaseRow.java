package com.ads.robot.entities;

import com.vladmihalcea.hibernate.type.json.JsonType;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.util.List;

@Entity
@TypeDef(name = "json", typeClass = JsonType.class)
public class TestCaseRow {

    @Id
    @GeneratedValue
    private Integer id;

    @ManyToOne
    private TestCase testCase;

    @ManyToOne
    private FunctionRobot function;


    @Type(type = "json")
    @Column(columnDefinition = "json")
    private List<String> parameterValues;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public TestCase getTestCase() {
        return testCase;
    }

    public void setTestCase(TestCase testCase) {
        this.testCase = testCase;
    }

    public List<String> getParameterValues() {
        return parameterValues;
    }

    public void setParameterValues(List<String> parameterValues) {
        this.parameterValues = parameterValues;
    }

    public FunctionRobot getFunction() {
        return function;
    }

    public void setFunction(FunctionRobot function) {
        this.function = function;
    }
}
