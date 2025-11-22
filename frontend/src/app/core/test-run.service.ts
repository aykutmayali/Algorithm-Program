import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { CreateTestRunRequest, GripAverage, TestRun } from './models';

@Injectable({ providedIn: 'root' })
export class TestRunService {
  private apiUrl = '/api/tests';

  constructor(private http: HttpClient) {}

  list(filters?: { labId?: number; from?: string; to?: string }): Observable<TestRun[]> {
    let params = new HttpParams();
    if (filters?.labId) params = params.set('labId', filters.labId);
    if (filters?.from) params = params.set('from', filters.from);
    if (filters?.to) params = params.set('to', filters.to);
    return this.http.get<TestRun[]>(this.apiUrl, { params });
  }

  findById(id: number): Observable<TestRun> {
    return this.http.get<TestRun>(`${this.apiUrl}/${id}`);
  }

  create(payload: CreateTestRunRequest): Observable<TestRun> {
    return this.http.post<TestRun>(this.apiUrl, payload);
  }

  averageGrip(): Observable<GripAverage[]> {
    return this.http.get<GripAverage[]>(`${this.apiUrl}/stats/average-grip`);
  }
}
