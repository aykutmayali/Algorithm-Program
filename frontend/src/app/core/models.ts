export interface Lab {
  id?: number;
  code: string;
  name: string;
  region: string;
}

export interface Tire {
  id?: number;
  code: string;
  brand: string;
}

export interface TestMetric {
  id?: number;
  name: string;
  value: number;
  unit: string;
}

export interface TestRun {
  id?: number;
  lab: Lab;
  tire: Tire;
  testDate: string;
  trackType: string;
  metrics: TestMetric[];
}

export interface CreateTestRunRequest {
  labId: number;
  tireId: number;
  testDate: string;
  trackType: string;
  metrics: { name: string; value: number; unit: string; }[];
}

export interface GripAverage {
  grouping: string;
  averageGrip: number | null;
}
