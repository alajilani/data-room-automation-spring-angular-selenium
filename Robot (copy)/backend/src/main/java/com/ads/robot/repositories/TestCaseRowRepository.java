package com.ads.robot.repositories;

import com.ads.robot.entities.TestCaseRow;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface TestCaseRowRepository extends JpaRepository<TestCaseRow, Integer> {

  public List<TestCaseRow> findBytestCaseId(int testCaseId);

  public List<TestCaseRow> findByfunctionId(int functionId);

  @Transactional
  @Modifying
  @Query("delete from TestCaseRow t where t.id not in ?1 and t.testCase.id = ?2")
  void deleteByIdNotInAndTestCaseIdEquals(List<Integer> testCaseRowOrder, int id);

  @Query("select t from TestCaseRow t where t.id not in ?1 and t.testCase.id = ?2")
  List<TestCaseRow> findByIdNotInAndTestCaseIdEquals(List<Integer> testCaseRowOrder, int id);

  @Transactional
  @Modifying
  @Query("delete from TestCaseRow t where t.id in ?1")
  void deleteAllById(List<Integer> ids);
}
