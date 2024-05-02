CREATE TABLE visit (
    id BIGSERIAL PRIMARY KEY,
    file_name VARCHAR(255) NOT NULL,
    views INTEGER NOT NULL
);

CREATE INDEX idx_visit_file_name ON visit (file_name);