import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { Observable } from 'rxjs';
import { Lab, TestRun } from '../../../core/models';
import { LabService } from '../../../core/lab.service';
import { TestRunService } from '../../../core/test-run.service';

@Component({
  selector: 'app-test-list',
  templateUrl: './test-list.component.html'
})
export class TestListComponent implements OnInit {
  tests$!: Observable<TestRun[]>;
  labs$!: Observable<Lab[]>;

  filterForm = new FormGroup({
    labId: new FormControl<number | null>(null),
    from: new FormControl<string | null>(null),
    to: new FormControl<string | null>(null)
  });

  constructor(private testService: TestRunService, private labService: LabService) {}

  ngOnInit(): void {
    this.labs$ = this.labService.list();
    this.tests$ = this.testService.list();
  }

  applyFilter(): void {
    const value = this.filterForm.value;
    this.tests$ = this.testService.list({
      labId: value.labId ?? undefined,
      from: value.from ?? undefined,
      to: value.to ?? undefined
    });
  }
}
