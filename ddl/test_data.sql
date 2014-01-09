INSERT INTO  book_types (id, type) VALUES (1, 'book');
INSERT INTO  book_types (id, type) VALUES (2, 'newspaper');

INSERT INTO  publications (id, name, city) VALUES (1, 'simvol-plus', 'peterburg');
INSERT INTO  publications (id, name, city) VALUES (2, 'piter', 'peterburg');

INSERT INTO  authors (id, last_name, first_name, middle_name) VALUES (1, 'Pupkin', 'Vasa','Vasyljvych');
INSERT INTO  authors (id, last_name, first_name, middle_name) VALUES (2, 'Ivanov', 'Ivan','Ivanovych');

-----------------------------RESTART SEQUENCE-----------------------------------------------
ALTER SEQUENCE book_types_id_seq RESTART WITH 100;
ALTER SEQUENCE publications_id_seq RESTART WITH 100;
ALTER SEQUENCE authors_id_seq RESTART WITH 100;

