-- Insert three questions into DB

INSERT INTO question (id, question) VALUES
(1, 'Какое животное на первом собрании Дуэльного клуба Драко Малфой вызвал заклинанием Serpensortia?');

INSERT INTO answers (question_id, answer, correct)
VALUES (1, 'Лягушка', 0);
INSERT INTO answers (question_id, answer, correct)
VALUES (1, 'Змея', 1);
INSERT INTO answers (question_id, answer, correct)
VALUES (1, 'Дракон', 0);
INSERT INTO answers (question_id, answer, correct)
VALUES (1, 'Медведь', 0);

INSERT INTO question (id, question) VALUES
(2, 'Безмерное остроумие — величайшее сокровище человека — вот девиз какого дома?');

INSERT INTO answers (question_id, answer, correct)
VALUES (2, 'Гриффиндор', 0);
INSERT INTO answers (question_id, answer, correct)
VALUES (2, 'Пуффендуй', 0);
INSERT INTO answers (question_id, answer, correct)
VALUES (2, 'Когтевран', 1);
INSERT INTO answers (question_id, answer, correct)
VALUES (2, 'Слизерин', 0);
