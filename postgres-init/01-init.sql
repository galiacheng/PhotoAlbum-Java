-- Migrated from Oracle to PostgreSQL according to SQL check item 1: Use lowercase for identifiers and uppercase for SQL keywords.
-- PostgreSQL initialization script for photoalbum application
-- This replaces the Oracle-specific oracle-init/01-create-user.sql

-- The photoalbum user and database are created by Docker environment variables.
-- Grant additional privileges if needed.
GRANT ALL PRIVILEGES ON DATABASE photoalbum TO photoalbum;
