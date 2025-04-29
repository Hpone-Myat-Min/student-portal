CREATE TABLE IF NOT EXISTS students (
                                        id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                        student_id VARCHAR(255) UNIQUE NOT NULL,
    first_name VARCHAR(255),
    last_name VARCHAR(255),
    qualification VARCHAR(255),
    university VARCHAR(255)
    );

-- 👤 Users Table
CREATE TABLE IF NOT EXISTS user (
                                    id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                    username VARCHAR(255) UNIQUE,
    email VARCHAR(255),
    password VARCHAR(255),
    role VARCHAR(255) DEFAULT 'normal',
    account_id BIGINT,
    CONSTRAINT fk_user_student FOREIGN KEY (account_id) REFERENCES students (id) ON DELETE CASCADE
    );

-- 📚 Courses Table
CREATE TABLE IF NOT EXISTS course (
                                      id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                      title VARCHAR(255),
    description TEXT,
    fee DOUBLE
    );

CREATE TABLE IF NOT EXISTS student_course (
                                              student_id BIGINT,
                                              course_id BIGINT,
                                              PRIMARY KEY (student_id, course_id),
    CONSTRAINT fk_student_course_student FOREIGN KEY (student_id) REFERENCES students (id) ON DELETE CASCADE,
    CONSTRAINT fk_student_course_course FOREIGN KEY (course_id) REFERENCES course (id) ON DELETE CASCADE
    );

INSERT INTO user (username, email, password, role) VALUES
    ('neymar', 'neymar@gmail.com', 'password', 'student');

INSERT INTO students (student_id, first_name, last_name, qualification, university) VALUES
    ('c0000011', 'Neymar', 'Junior', 'BSc Computer Science', 'Leeds Beckett University');


UPDATE user
SET account_id = (SELECT id FROM students WHERE student_id = 'c0000011')
WHERE username = 'neymar';

-- 📚 Insert 3 Courses
INSERT INTO course (title, description, fee) VALUES ('Cloud Computing', 'Trending Cloud Computing technologies.', 150);
INSERT INTO course (title, description, fee) VALUES ('Smart Systems', 'Learning the IoT Systems and trending tools.', 150);
INSERT INTO course (title, description, fee) VALUES ('Research Practice', 'Training how to conduct proper research at Masters level.', 150);