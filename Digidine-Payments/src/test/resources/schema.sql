-- schema.sql
CREATE TABLE IF NOT EXISTS payment (
                                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                       order_number BIGINT NOT NULL UNIQUE,
                                       amount DOUBLE NOT NULL,
                                       created_at TIMESTAMP NOT NULL,
                                       status VARCHAR(255)
    );