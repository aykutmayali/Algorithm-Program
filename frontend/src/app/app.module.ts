import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { ReactiveFormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';

import { AppComponent } from './app.component';
import { AppRoutingModule } from './app-routing.module';
import { LabsModule } from './shared/features/labs/labs.module';
import { TestsModule } from './shared/features/tests/tests.module';

@NgModule({
  declarations: [AppComponent],
  imports: [BrowserModule, HttpClientModule, ReactiveFormsModule, RouterModule, AppRoutingModule, LabsModule, TestsModule],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
