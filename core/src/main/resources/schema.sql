CREATE TABLE IF NOT EXISTS users (
    uid VARCHAR(550) NOT NULL,
    username VARCHAR(100) NOT NULL,
    email VARCHAR(50) NOT NULL,
    password VARCHAR(50) NOT NULL,
    photo LONGBLOB,
    phone VARCHAR(20) NOT NULL,
    address VARCHAR(50) NOT NULL,
    city VARCHAR(50) NOT NULL,
    role VARCHAR(50) NOT NULL,
    enabled TINYINT(4) NOT NULL DEFAULT 1,
    time_stamp VARCHAR(255),
    PRIMARY KEY (uid)
);