package com.ads.robot.entities;

import com.vladmihalcea.hibernate.type.json.JsonType;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

@Entity
@TypeDef(name = "json", typeClass = JsonType.class)
public class TestCase {

  @Id
  @GeneratedValue
  private int id;

  private String name;

  @Type(type = "json")
  @Column(columnDefinition = "json")
  private List<Integer> TestCaseRowOrder;

  @OneToMany(mappedBy = "testCase", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  private List<TestCaseRow> testCaseRows;

  @ManyToOne
  private TestSuite testSuite;


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

  public List<Integer> getTestCaseRowOrder() {
    return TestCaseRowOrder;
  }

  public void setTestCaseRowOrder(List<Integer> testCaseRowOrder) {
    TestCaseRowOrder = testCaseRowOrder;
  }

  public List<TestCaseRow> getTestCaseRows() {
    return testCaseRows;
  }

  public void setTestCaseRows(List<TestCaseRow> testCaseRows) {
    this.testCaseRows = testCaseRows;
  }

  public TestSuite getTestSuite() {
    return testSuite;
  }

  public void setTestSuite(TestSuite testSuite) {
    this.testSuite = testSuite;
  }
}
