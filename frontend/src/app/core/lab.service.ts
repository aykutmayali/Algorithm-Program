import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Lab } from './models';

@Injectable({ providedIn: 'root' })
export class LabService {
  private apiUrl = '/api/labs';

  constructor(private http: HttpClient) {}

  list(): Observable<Lab[]> {
    return this.http.get<Lab[]>(this.apiUrl);
  }

  create(lab: Lab): Observable<Lab> {
    return this.http.post<Lab>(this.apiUrl, lab);
  }
}
