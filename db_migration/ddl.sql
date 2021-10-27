CREATE TABLE course(
    id INT AUTO_INCREMENT,
    course_title VARCHAR(100) NOT NULL,
    course_code VARCHAR(7) NOT NULL,
    created DATETIME NOT NULL,
    updated DATETIME NULL,
    PRIMARY KEY("id"));

CREATE TABLE user(
    id INT AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    role VARCHAR(10) NOT NULL,
    user_name VARCHAR(100) NOT NULL,
    password VARCHAR(45) NOT NULL,
    created DATETIME NOT NULL,
    updated DATETIME NULL,
    PRIMARY KEY("id"));

CREATE TABLE enrollment(
    id INT AUTO_INCREMENT,
    user_id INT NOT NULL,
    course_id INT NOT NULL,
    created DATETIME NOT NULL,
    updated DATETIME NULL,
    PRIMARY KEY("id"),
    CONSTRAINT FK_course_id FOREIGN KEY (course_id) REFERENCES course (id),
    CONSTRAINT FK_user_id FOREIGN KEY (user_id) REFERENCES user (id));
