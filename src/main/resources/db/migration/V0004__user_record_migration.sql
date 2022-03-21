-- ensure that the table with this name is removed before creating a new one.
DROP TABLE IF EXISTS user_record;

-- create new table
CREATE TABLE user_record (
     id SERIAL PRIMARY KEY
    ,user_id VARCHAR(100)
    ,question_id INTEGER
    ,correct SMALLINT
);