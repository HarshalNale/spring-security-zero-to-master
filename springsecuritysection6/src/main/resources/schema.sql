-- ========================================
-- data.sql - Table Creation + Initial Data
-- ========================================

-- Create table if it doesn't exist
CREATE TABLE IF NOT EXISTS customers (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    email VARCHAR(100) NOT NULL UNIQUE,
    pwd VARCHAR(500) NOT NULL,
    role VARCHAR(50) NOT NULL,
    enabled BOOLEAN DEFAULT TRUE
    );