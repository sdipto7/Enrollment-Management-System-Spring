CREATE TABLE course(
    id INT NOT NULL AUTO_INCREMENT,
    course_title VARCHAR(100) NOT NULL,
    course_code VARCHAR(7) NOT NULL,
    created DATETIME NULL,
    updated DATETIME NULL,
    PRIMARY KEY("id")
);

CREATE TABLE user(
    id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    role VARCHAR(10) NOT NULL
    created DATETIME NULL,
    updated DATETIME NULL,
    PRIMARY KEY("id")
);

CREATE TABLE enrollment(
    id INT NOT NULL AUTO_INCREMENT,
    user_id INT NOT NULL,
    course_id INT NOT NULL,
    created DATETIME NULL,
    updated DATETIME NULL,
    PRIMARY KEY("id")
    CONSTRAINT FK_course_id FOREIGN KEY (course_id) REFERENCES course (id),
    CONSTRAINT FK_user_id FOREIGN KEY (user_id) REFERENCES user (id)
);

CREATE TABLE credential (
    id INT NOT NULL AUTO_INCREMENT,
    user_name VARCHAR(100) NOT NULL,
    password VARCHAR(45) NOT NULL,
    user_id INT NOT NULL,
    created DATETIME NULL,
    updated DATETIME NULL,
    PRIMARY KEY ("id"),
    CONSTRAINT uk_user_id UNIQUE (user_id),
    CONSTRAINT  uk_user_name UNIQUE (user_name)
);
