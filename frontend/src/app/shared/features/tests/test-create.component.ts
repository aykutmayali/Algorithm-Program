import { Component } from '@angular/core';
import { FormArray, FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Lab, Tire } from '../../../core/models';
import { LabService } from '../../../core/lab.service';
import { TestRunService } from '../../../core/test-run.service';

@Component({
  selector: 'app-test-create',
  templateUrl: './test-create.component.html'
})
export class TestCreateComponent {
  labs: Lab[] = [];
  tires: Tire[] = [];

  form = this.fb.group({
    labId: [null, Validators.required],
    tireId: [null, Validators.required],
    testDate: [new Date().toISOString().slice(0, 16), Validators.required],
    trackType: ['wet', Validators.required],
    metrics: this.fb.array([this.newMetric()])
  });

  constructor(private fb: FormBuilder, private labService: LabService, private testService: TestRunService, private router: Router) {
    this.labService.list().subscribe(labs => this.labs = labs);
    // In a real app this would be a dedicated tire service, reusing LabService for brevity
    this.tires = [
      { id: 1, code: 'GOODYEAR-XYZ-123', brand: 'Goodyear' },
      { id: 2, code: 'PIRELLI-ABC-999', brand: 'Pirelli' }
    ];
  }

  get metrics(): FormArray {
    return this.form.get('metrics') as FormArray;
  }

  addMetric(): void {
    this.metrics.push(this.newMetric());
  }

  removeMetric(index: number): void {
    if (this.metrics.length > 1) {
      this.metrics.removeAt(index);
    }
  }

  submit(): void {
    if (this.form.invalid) return;
    this.testService.create(this.form.value as any).subscribe(() => this.router.navigate(['/tests']));
  }

  private newMetric() {
    return this.fb.group({
      name: ['wetGrip', Validators.required],
      value: [80, Validators.required],
      unit: ['score', Validators.required]
    });
  }
}
