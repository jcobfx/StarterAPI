CREATE TABLE IF NOT EXISTS users
(
    id       SERIAL PRIMARY KEY,
    username VARCHAR(50)         NOT NULL,
    email    VARCHAR(100) UNIQUE NOT NULL,
    password VARCHAR(255)        NOT NULL,
    role     VARCHAR(20)         NOT NULL
);

CREATE TABLE IF NOT EXISTS tasks
(
    id          SERIAL PRIMARY KEY,
    title       VARCHAR(100)  NOT NULL,
    description VARCHAR(255) NOT NULL,
    dueDate     TIMESTAMP    NOT NULL,
    user_id     BIGINT      NOT NULL REFERENCES users (id)
);

-- Dodanie przykładowego użytkownika
-- Hasło: test123 (zahashowane przez BCrypt)
INSERT INTO users (username, email, password, role)
VALUES ('testuser', 'test@example.com', '$2a$10$DbP4qIK/gvX1L04Tb5uZPebN39w6q1C1kUGp3LqQx4vjLlk7874K2', 'USER');

-- Dodanie przykładowego zadania
INSERT INTO tasks (title, description, duedate, user_id)
VALUES ('Test Task', 'This is a test task description.', '2023-10-01 12:00:00', 1);