-- do it by hand

-- CREATE USER blog WITH PASSWORD 'blog';
-- GRANT USAGE ON SCHEMA public TO blog;
-- GRANT CREATE ON SCHEMA public TO blog;
-- GRANT ALL PRIVILEGES ON ALL TABLES IN SCHEMA public TO blog;
-- ALTER DEFAULT PRIVILEGES IN SCHEMA public GRANT ALL PRIVILEGES ON TABLES TO blog;

-- CREATE TABLE log (
--    id BIGSERIAL PRIMARY KEY,
--    page VARCHAR(255),
--    type VARCHAR(255),
--    level VARCHAR(255),
--    time TEXT,
--    phase TEXT,
--    message TEXT
--);

--CREATE INDEX idx_log_page ON log(page);