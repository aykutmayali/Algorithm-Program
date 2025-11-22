import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LabListComponent } from './shared/features/labs/lab-list.component';
import { TestListComponent } from './shared/features/tests/test-list.component';
import { TestDetailComponent } from './shared/features/tests/test-detail.component';
import { TestCreateComponent } from './shared/features/tests/test-create.component';

const routes: Routes = [
  { path: '', redirectTo: 'labs', pathMatch: 'full' },
  { path: 'labs', component: LabListComponent },
  { path: 'tests', component: TestListComponent },
  { path: 'tests/create', component: TestCreateComponent },
  { path: 'tests/:id', component: TestDetailComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
