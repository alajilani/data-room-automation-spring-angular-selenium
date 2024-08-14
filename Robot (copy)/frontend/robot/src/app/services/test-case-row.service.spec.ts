/* tslint:disable:no-unused-variable */

import { TestBed, async, inject } from '@angular/core/testing';
import { TestCaseRowService } from './test-case-row.service';

describe('Service: TestCaseRow', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [TestCaseRowService]
    });
  });

  it('should ...', inject([TestCaseRowService], (service: TestCaseRowService) => {
    expect(service).toBeTruthy();
  }));
});
