import {Component, OnInit} from '@angular/core';
import {TestCase} from '../entities/test-case';
import {TestSuite} from '../entities/test-suite';
import {TestSuiteService} from '../services/test-suite.service';
import {SelectedTestCase} from "../entities/selected-test-case";
import {TestCaseCloneDto, TestCaseService} from "../services/test-case.service";


@Component({
    selector: 'app-test-list',
    templateUrl: './test-list.component.html',
    styleUrls: ['./test-list.component.css']
})
export class TestListComponent implements OnInit {

    constructor(private testSuiteService: TestSuiteService, private testCaseService: TestCaseService) {
    }

    itemList = [];
    selectedTestCase = null;
    displayModal = false;
    displayTestcase = false;
    testCaseName = null;
    testSuiteName = null;
    displayTestSuiteModal = false;
    testSuiteList: TestSuite[] = null;
    selectedTestSuite: TestSuite = null;
    testSuiteUpdate: TestSuite = null;

    ngOnInit() {
        this.testSuiteService.getTestSuites().subscribe(
            (testSuiteList) => {
                this.testSuiteList = testSuiteList;
                this.initTestSuiteMenu();
            },
            (error) => console.log(error)
        )
    }

    initTestSuiteMenu() {
        this.testSuiteList.forEach(
            (testSuite) => {
                this.itemList.push({
                    testSuite: testSuite,
                    enabled: false
                })
            }
        )
    }

    showTestCaseSaveDialog(testSuite: TestSuite) {
        this.displayModal = true;
        this.selectedTestSuite = testSuite;
        this.testCaseName = null;
    }

    showTestCaseCloneSaveDialog(testSuite: TestSuite) {
        this.displayTestcase = true;
        this.selectedTestSuite = testSuite;
        this.testCaseName = null;
    }

    saveTestCase() {
        if (this.selectedTestSuite == null || !this.validTestCaseName())
            return;
        let testCase: TestCase = {
            id: null,
            name: this.testCaseName,
            testCaseRowOrder: [],
        }
        let testSuite = this.selectedTestSuite;
        this.testSuiteService.saveTestCase(testSuite.id, testCase).subscribe(
            testCase => {
                testSuite.testCases.push(testCase);

            },
            error => console.log(error)
        );
        this.displayModal = false
    }

    saveTestCaseClone() {
        if (!this.selectedTestSuite || !this.testCaseName) {
            return;
        }
        const testCaseCloneDto: TestCaseCloneDto = {
            testSuiteId: this.selectedTestSuite.id,
            testCaseName: this.testCaseName,
            testCaseId: this.selectedTestCase.id,
        };
        this.testCaseService.cloneTestCase(testCaseCloneDto).subscribe(
            (clonedTestCase: TestCase) => {
                this.selectedTestSuite.testCases.push(clonedTestCase);
                console.log('Nouveau cas de test cloné : ', clonedTestCase);
            },
            (error) => {
                console.error('Erreur lors du clonage du cas de test : ', error);
            }
        );

        this.displayTestcase = false;
    }

    validTestCaseName() {
        return this.testCaseName != "" && this.testCaseName != null;
    }

    deleteTestCase(testcase: TestCase) {
        let index;
        let i;
        for (i = 0; i < this.testSuiteList.length; i++) {
            index = this.testSuiteList[i].testCases.indexOf(testcase);
            if (index > 0)
                break;
        }
        this.testSuiteList[i].testCases.splice(index, 1);
        this.itemList[i].testSuite.testCases.splice(index, 1);
    }

    selectTestCase(testCase: TestCase, testSuite: TestSuite) {
        this.selectedTestCase = testCase;
        this.selectedTestSuite = testSuite;

    }

    validTestSuiteName() {
        return this.testSuiteName != "" && this.testSuiteName != null;
    }

    saveTestSuite() {
        let testSuite = {
            id: null,
            name: this.testSuiteName,
            testCases: []
        }
        this.testSuiteService.saveTestSuite(testSuite).subscribe(
            testSuite => {
                this.testSuiteList.push(testSuite);
                this.itemList.push({
                    testSuite: testSuite,
                    enabled: false
                })
            },
            error => console.log(error)
        )
        this.displayTestSuiteModal = false;
    }

    updateTestSuite(testSuite: TestSuite) {
        let oldName = testSuite.name;
        testSuite.name = this.testSuiteName;
        this.testSuiteService.saveTestSuite(testSuite).subscribe(
            testSuite => {
            },
            error => {
                console.log(error)
                testSuite.name = oldName
            }
        )


    }

    saveOrUpdateTestSuite() {
        if (this.testSuiteUpdate != null) {
            this.updateTestSuite(this.testSuiteUpdate);
        } else {
            this.saveTestSuite();
        }
    }

    showTestSuiteSaveDialog(testSuite?: TestSuite) {
        this.displayTestSuiteModal = true;
        if (typeof testSuite !== "undefined") {
            this.testSuiteUpdate = testSuite;
            this.testSuiteName = testSuite.name;
        } else {
            this.testSuiteUpdate = null;
            this.testSuiteName = "";
        }
    }

    deleteTestSuite(testSuiteId: number) {
        if (confirm('are you sure to delete this test suite ?'))
            this.testSuiteService.deleteTestSuite(testSuiteId).subscribe(
                reponse => {
                    this.testSuiteList = this.testSuiteList.filter(v => v.id !== testSuiteId);
                    this.itemList = this.itemList.filter(v => v.testSuite.id !== testSuiteId);
                },
                error => console.log(error)
            )
    }


}
