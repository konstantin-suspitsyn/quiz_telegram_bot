-- Insert three questions into DB

INSERT INTO question (id, question) VALUES
(3, 'Какой патронус принадлежит Луне Лавгуд?');

INSERT INTO answers (question_id, answer, correct)
VALUES (3, 'Лань', 0);
INSERT INTO answers (question_id, answer, correct)
VALUES (3, 'Кролик', 1);
INSERT INTO answers (question_id, answer, correct)
VALUES (3, 'Собака', 0);
INSERT INTO answers (question_id, answer, correct)
VALUES (3, 'Лошадь', 0);


INSERT INTO question (id, question) VALUES
(4, 'Как звали домашнего эльфа семьи Блэк?');

INSERT INTO answers (question_id, answer, correct)
VALUES (4, 'Добби', 0);
INSERT INTO answers (question_id, answer, correct)
VALUES (4, 'Винки', 0);
INSERT INTO answers (question_id, answer, correct)
VALUES (4, 'Кикимер', 1);
INSERT INTO answers (question_id, answer, correct)
VALUES (4, 'Хоккей', 0);


INSERT INTO question (id, question) VALUES
(5, 'Что такое фестрал?');

INSERT INTO answers (question_id, answer, correct)
VALUES (5, 'Полугигант', 0);
INSERT INTO answers (question_id, answer, correct)
VALUES (5, 'Невидимый крылатый конь', 1);
INSERT INTO answers (question_id, answer, correct)
VALUES (5, 'Сморщенная голова', 0);
INSERT INTO answers (question_id, answer, correct)
VALUES (5, 'Пикси', 0);


INSERT INTO question (id, question) VALUES
(6, 'Слезы какого животного являются единственным известным противоядием от яда василиска?');

INSERT INTO answers (question_id, answer, correct)
VALUES (6, 'Феникс', 1);
INSERT INTO answers (question_id, answer, correct)
VALUES (6, 'Билливиг', 0);
INSERT INTO answers (question_id, answer, correct)
VALUES (6, 'Гиппогриф', 0);
INSERT INTO answers (question_id, answer, correct)
VALUES (6, 'Ддомовой', 0);