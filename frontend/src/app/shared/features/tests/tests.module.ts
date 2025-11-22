import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { TestListComponent } from './test-list.component';
import { TestDetailComponent } from './test-detail.component';
import { TestCreateComponent } from './test-create.component';

@NgModule({
  declarations: [TestListComponent, TestDetailComponent, TestCreateComponent],
  imports: [CommonModule, ReactiveFormsModule, RouterModule],
  exports: [TestListComponent, TestDetailComponent, TestCreateComponent]
})
export class TestsModule {}
