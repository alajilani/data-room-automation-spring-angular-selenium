package com.ads.robot.services;

import com.ads.robot.entities.TestCaseRow;
import com.ads.robot.repositories.TestCaseRowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TestCaseRowService {



    @Autowired
    private TestCaseRowRepository testCaseRowRepository;

    public List<TestCaseRow> getAllTestCaseRows() {
        return testCaseRowRepository.findAll();
    }

    public TestCaseRow getTestCaseRow(int id){
        return testCaseRowRepository.findById(id).get();
    }

    public void addTestCaseRow(TestCaseRow testCaseRow) {
        testCaseRowRepository.save(testCaseRow);
    }

    public void updateTestCaseRow(TestCaseRow testCaseRow) {
        testCaseRowRepository.save(testCaseRow);
    }

    public void deleteTestCaseRow(int id) {
        testCaseRowRepository.deleteById(id);
    }

}
