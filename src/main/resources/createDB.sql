CREATE DATABASE university_platform;

CREATE USER admin1 WITH PASSWORD'ad321';

GRANT ALL PRIVILEGES ON DATABASE "university_platform" to admin1;

CREATE SCHEMA Students_platform;

GRANT ALL PRIVILEGES ON SCHEMA Students_platform to admin1;
GRANT ALL PRIVILEGES ON ALL TABLES IN SCHEMA Students_platform TO admin1;
GRANT ALL PRIVILEGES ON ALL SEQUENCES IN SCHEMA Students_platform TO admin1;