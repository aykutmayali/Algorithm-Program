import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { LabListComponent } from './lab-list.component';
import { RouterModule } from '@angular/router';

@NgModule({
  declarations: [LabListComponent],
  imports: [CommonModule, RouterModule],
  exports: [LabListComponent]
})
export class LabsModule {}
