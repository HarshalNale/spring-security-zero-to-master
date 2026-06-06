-- =============================================
-- Insert initial demo data (only if not exists)
-- =============================================

INSERT IGNORE INTO customers (email, pwd, role, enabled) VALUES
('admin@example.com', 'admin@123', 'ADMIN', TRUE),
('john.doe@example.com', '$2a$12$RnXdMeSEHEr0CiQt5e0UbuvSHXkQknaq8hJdHi8o5k9pX6eBXVKzK', 'ADMIN', TRUE);


-- Note:
-- 1. 'admin@example.com' has plaintext password ('admin@123')
--    → Login will FAIL because the application uses BCryptPasswordEncoder.
--    This is intentional to demonstrate a failure scenario.

-- 2. 'john.doe@example.com' has hashed password (john@123)
--    → Login will SUCCEED with password: john@123
--     This demonstrates the expected successful authentication flow with .