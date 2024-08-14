import {
  Component,
  Input,
  OnChanges,
  OnInit,
  SimpleChanges,
} from "@angular/core";
import { TestCase } from "../entities/test-case";
import { TestCaseRow } from "../entities/test-case-row";
import { TestCaseRowDto } from "../entities/test-case-row-dto";
import { TestCaseUpdate } from "../entities/test-case-update";
import { TestFunction } from "../entities/test-function";
import { TestCaseService } from "../services/test-case.service";
import { Output, EventEmitter } from "@angular/core";
import { Library } from "../entities/library";
import { LibraryService } from "../services/library.service";
import { AlertService } from "../services/alert.service";
import {TestSuite} from "../entities/test-suite";



@Component({
  selector: "app-test-case",
  templateUrl: "./test-case.component.html",
  styleUrls: ["./test-case.component.css"],
})
export class TestCaseComponent implements OnInit, OnChanges {
  hideMessagesAfterDelay(): void {
    setTimeout(() => {
      this.successMessage = "";
      this.errorMessage = "";
    }, 1000); // 1000 ms = 1 seconde
  }
  successMessage: string = "";
  errorMessage: string = "";

  showSuccessMessage(message: string) {
    this.successMessage = message;
    this.hideMessagesAfterDelay(); // Appel pour masquer après une seconde
  }



  @Input()
  testCase: TestCase;
  @Input()
  testSuite:TestSuite;

  @Output() deleteTestCaseEvent = new EventEmitter<TestCase>();
  reportHtml: string = "";
  rolenameList: { label: string , value:string}[];
  permission_name: { label: string , value:string}[];
  USER_FirstName: { label: string , value:string}[];
  USER_LastName: { label: string , value:string}[];


  constructor(
    private libraryService: LibraryService,
    private testCaseService: TestCaseService,
    private alertService: AlertService) {
    this.rolenameList=[
      { label: 'ADMIN', value: 'ADMIN' },
      { label: 'Investor', value: 'Investor' },
      { label: 'SUPER_ADMIN', value: 'SUPER_ADMIN' },
      { label: 'DEG Impact', value: 'DEG Impact' },
      { label: 'DD team', value: 'DD team' },
      { label: 'new role', value: 'new role' },
      { label: 'AYAGED', value: 'AYAGED' }
    ],
        this.permission_name=[
          { label: 'Creation Date', value: 'Creation Date' },
          { label: 'Created By', value: 'Created By' },
          { label: 'Owner', value: 'owner' },
          { label: 'Privacy', value: 'Privacy' },
          { label: 'Subject', value: 'Subject' },
          { label: 'Parent Folder', value: 'Parent Folder' },
          { label: 'Description', value: 'Description' },
          { label: 'Modification Date', value: 'Modification Date' },
          { label: 'Access Date', value: 'Access Date' },
          { label: 'Category', value: 'Category' },
          { label: 'Comment', value: 'Comment' },
          { label: 'Last Version', value: 'Last Version' },
          { label: 'Key Words/Tags', value: 'Key Words/Tags' },
          { label: 'Type', value: 'Type' },
          { label: 'Size', value: 'Size' }
        ],
        this.USER_FirstName=[
          { label: 'Mejri', value: 'Mejri' },
          { label: 'asma', value: 'asma' },
          { label: 'slime', value: 'slime' },
          { label: 'julia', value: 'julia' },
          { label: 'lily', value: 'lily' },
          { label: 'zz', value: 'zz' }
        ],
        this.USER_LastName=[
          { label: 'Houssem', value: 'Houssem' },
          { label: 'quest', value: 'quest' },
          { label: 'lamine', value: 'lamine' },
          { label: 'robertson', value: 'robertson' },
          { label: 'groumy', value: 'groumy' },
          { label: 'zz', value: 'zz' }
        ]


  }




  libraries: Library[] = [];

  draggedFunction: TestFunction = null;
  draggedRow: TestCaseRowDto = null;

  updatedTestCase: TestCaseUpdate = {
    deletedTestCaseRows: [],
    testCase: null,
    updatedTestCaseRows: [],
  };
  testCaseName = "";
  testSuiteName="";
  orginalValues: Map<number, string[]> = new Map<number, string[]>();
  copiedtext:String;


  ngOnChanges(changes: SimpleChanges): void {
    this.updatedTestCase.testCase = this.testCase;
    this.testCaseName = this.testCase.name;
    this.updatedTestCase.deletedTestCaseRows = [];
    this.updatedTestCase.updatedTestCaseRows = [];
    this.testSuiteName=this.testSuite.name;
    if (this.testCase == null) return;
    this.testCaseService
      .getTestCaseRowsByTestCaseId(this.testCase.id)
      .subscribe(
        (testCaseRows) => this.initTestCaseRows(testCaseRows),
        (error) => console.log(error)
      );
    this.copiedtext = "quest-automated-tests/test/"+this.testSuiteName+".robot#"+this.testCaseName;
  }

  ngOnInit() {
    this.libraryService.getLibraries().subscribe(
      (libraryList) => (this.libraries = libraryList),
      (error) => console.log(error)
    );
  }
  // Fonction pour définir la liste d'options en fonction du label
  getOptionsList(label: string): { label: string, value: string }[] {
    if (label === 'role_name') {
      return this.rolenameList;
    } else if (label === 'permission_name') {
      return this.permission_name;
    } else if (label === 'USER_FirstName'){
      return this.USER_FirstName;
    }else if (label === 'USER_LastName'){
      return this.USER_LastName;}



    else{ return [];}
  }

  dragStart(func: TestFunction) {
    this.draggedFunction = func;
    console.log("start");
  }

  drop(event, index) {
    console.log(event);
    console.log("drop");
    console.log(index);

    if (this.draggedFunction != null) {
      this.addTestCaseRow(this.draggedFunction, index);
      this.draggedFunction = null;
      console.log(this.updatedTestCase);
    }
    if (this.draggedRow != null) {
      console.log("dragging row");
      let originalindex = this.updatedTestCase.updatedTestCaseRows.indexOf(
        this.draggedRow
      );
      console.log("original index >>>", originalindex);
      this.updatedTestCase.updatedTestCaseRows.splice(originalindex, 1);
      if (index < 0) {
        this.updatedTestCase.updatedTestCaseRows.push(this.draggedRow);
        this.draggedRow = null;
        return;
      }
      console.log("index >>>", index);
      console.log("original index >>>", originalindex);
      this.updatedTestCase.updatedTestCaseRows.splice(
        index,
        0,
        this.draggedRow
      );
      this.draggedRow = null;
    }
  }

  dragEnd() {
    console.log("end");
    this.draggedFunction = null;
  }
  dragStartRow(row) {
    this.draggedRow = row;
  }
  dragEndRow() {
    console.log("end");
    this.draggedRow = null;
  }

  initTestCaseRows(testCaseRows: TestCaseRow[]) {
    testCaseRows.forEach((testCaseRow) => {
      let testCaseRowDto: TestCaseRowDto = {
        testCaseRow: testCaseRow,
        updated: false,
      };
      this.updatedTestCase.updatedTestCaseRows.push(testCaseRowDto);
    });
    testCaseRows.forEach((testCaseRow) =>
      this.orginalValues.set(
        testCaseRow.id,
        Object.assign([], testCaseRow.parameterValues)
      )
    );
  }

  addTestCaseRow(func: TestFunction, index: number) {
    let parameters = new Array<string>(func.parameters.length);
    let testCaseRow: TestCaseRow = {
      function: func,
      id: null,
      parameterValues: parameters,
    };
    let testCaseRowDto: TestCaseRowDto = {
      testCaseRow: testCaseRow,
      updated: true,
    };
    if (index < 0)
      this.updatedTestCase.updatedTestCaseRows.push(testCaseRowDto);
    else
      this.updatedTestCase.updatedTestCaseRows.splice(index, 0, testCaseRowDto);
  }
  saveTestCase() {
    this.updatedTestCase.testCase.name = this.testCaseName;
    this.testCaseService.updateTestCase(this.updatedTestCase).subscribe(
      (response) => {
        this.successMessage = "Test Case Saved Successfully";
        console.log(response);
      },
      (error) => {
        console.log(error)
        this.showSuccessMessage("Test Case Saved Successfully" )
      }
    );
  }


  updateTestCaseRow(testCaseRowDto: TestCaseRowDto) {
    let id = testCaseRowDto.testCaseRow.id;
    let orginalValues = this.orginalValues.get(id);
    let notUpdated =
      testCaseRowDto.testCaseRow.parameterValues.length == orginalValues.length;

    for (let i = 0; i < orginalValues.length && notUpdated; i++) {
      notUpdated =
        orginalValues[i] === testCaseRowDto.testCaseRow.parameterValues[i];
    }

    testCaseRowDto.updated = !notUpdated;

  }
  deleteTestCaseRow(row: TestCaseRowDto) {
    let index = this.updatedTestCase.updatedTestCaseRows.indexOf(row);
    if (confirm("are you sure to delete this test case row ?")) {
      if (index < 0) return;
      this.updatedTestCase.updatedTestCaseRows.splice(index, 1);
      this.updatedTestCase.deletedTestCaseRows.push(row);
    }
  }
  deleteTestCase() {
    if (confirm("are you sure to delete this test case ?"))
      this.testCaseService.deleteTestCase(this.testCase.id).subscribe(
        (response) => {
          this.deleteTestCaseEvent.emit(this.testCase);
          this.testCase = null;
        },
        (error) => console.log(error)
      );
  }
  executeTestCase() {
    this.testCaseService.executeTestCase(this.testCase.id).subscribe(
      (value) =>
        value.text().then((txt) => {
          let iframeElement: HTMLIFrameElement = <HTMLIFrameElement>(
            document.getElementById("report")
          );
          iframeElement.setAttribute("srcdoc", txt);
          this.alertService.sucess ("Test Case Execution Complete");
        }),
      (error) => {
        console.log(error);
        this.alertService.error("Test Case Failed");
      }
    );
  }
  ClipBoardCopy(input){
    input.select();
    document.execCommand('copy');
    input.setSelectionRange(0,0);
  }
}