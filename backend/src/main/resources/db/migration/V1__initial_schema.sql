CREATE TABLE labs (
    id BIGSERIAL PRIMARY KEY,
    code VARCHAR(64) NOT NULL UNIQUE,
    name VARCHAR(128) NOT NULL,
    region VARCHAR(32) NOT NULL
);

CREATE TABLE tires (
    id BIGSERIAL PRIMARY KEY,
    code VARCHAR(128) NOT NULL UNIQUE,
    brand VARCHAR(128) NOT NULL
);

CREATE TABLE test_runs (
    id BIGSERIAL PRIMARY KEY,
    lab_id BIGINT NOT NULL REFERENCES labs(id),
    tire_id BIGINT NOT NULL REFERENCES tires(id),
    test_date TIMESTAMP NOT NULL,
    track_type VARCHAR(32) NOT NULL
);

CREATE TABLE test_metrics (
    id BIGSERIAL PRIMARY KEY,
    test_run_id BIGINT NOT NULL REFERENCES test_runs(id) ON DELETE CASCADE,
    name VARCHAR(64) NOT NULL,
    value DOUBLE PRECISION NOT NULL,
    unit VARCHAR(32) NOT NULL
);

CREATE INDEX idx_test_runs_lab_date ON test_runs(lab_id, test_date);
