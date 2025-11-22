import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { Lab } from '../../../core/models';
import { LabService } from '../../../core/lab.service';

@Component({
  selector: 'app-lab-list',
  templateUrl: './lab-list.component.html'
})
export class LabListComponent implements OnInit {
  labs$!: Observable<Lab[]>;

  constructor(private labService: LabService) {}

  ngOnInit(): void {
    this.labs$ = this.labService.list();
  }
}
