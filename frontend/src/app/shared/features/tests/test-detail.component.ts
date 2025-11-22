import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { TestRun } from '../../../core/models';
import { TestRunService } from '../../../core/test-run.service';

@Component({
  selector: 'app-test-detail',
  templateUrl: './test-detail.component.html'
})
export class TestDetailComponent implements OnInit {
  test$!: Observable<TestRun>;

  constructor(private route: ActivatedRoute, private testService: TestRunService) {}

  ngOnInit(): void {
    const id = Number(this.route.snapshot.paramMap.get('id'));
    this.test$ = this.testService.findById(id);
  }
}
