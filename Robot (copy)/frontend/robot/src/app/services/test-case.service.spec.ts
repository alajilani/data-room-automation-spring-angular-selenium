/* tslint:disable:no-unused-variable */

import { TestBed, async, inject } from '@angular/core/testing';
import { TestCaseService } from './test-case.service';

describe('Service: TestCase', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [TestCaseService]
    });
  });

  it('should ...', inject([TestCaseService], (service: TestCaseService) => {
    expect(service).toBeTruthy();
  }));
});
