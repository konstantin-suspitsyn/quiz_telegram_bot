-- ensure that the table with this name is removed before creating a new one.
DROP TABLE IF EXISTS question;

-- create new table
CREATE TABLE question (
     id SERIAL PRIMARY KEY
    ,question VARCHAR(1000)
);

-- ensure that the table with this name is removed before creating a new one.
DROP TABLE IF EXISTS answers;

-- create new table
CREATE TABLE answers (
     id SERIAL PRIMARY KEY
    ,question_id INTEGER
    ,answer VARCHAR(100)
    ,correct SMALLINT
);