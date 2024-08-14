/* tslint:disable:no-unused-variable */

import { TestBed, async, inject } from '@angular/core/testing';
import { TestSuiteService } from './test-suite.service';

describe('Service: TestSuite', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [TestSuiteService]
    });
  });

  it('should ...', inject([TestSuiteService], (service: TestSuiteService) => {
    expect(service).toBeTruthy();
  }));
});
