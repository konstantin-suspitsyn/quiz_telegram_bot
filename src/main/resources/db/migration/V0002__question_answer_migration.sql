-- ensure that the table with this name is removed before creating a new one.
DROP TABLE IF EXISTS question;

-- create new table
CREATE TABLE question (
     id INTEGER NOT NULL AUTO_INCREMENT
    ,question VARCHAR(1000)
    ,PRIMARY KEY (id)
);

-- ensure that the table with this name is removed before creating a new one.
DROP TABLE IF EXISTS answers;

-- create new table
CREATE TABLE answers (
     id INTEGER NOT NULL AUTO_INCREMENT
    ,question_id INTEGER
    ,answer VARCHAR(100)
    ,correct SMALLINT
    ,PRIMARY KEY (id)
);