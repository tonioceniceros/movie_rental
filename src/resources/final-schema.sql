CREATE DATABASE movierental CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci;

-- Optional: create a dedicated user
CREATE USER 'user'@'%' IDENTIFIED BY 'jaceniceros';
GRANT ALL PRIVILEGES ON movierental.* TO 'user'@'%';
FLUSH PRIVILEGES;
