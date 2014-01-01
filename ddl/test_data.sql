INSERT INTO  book_types (id, type) VALUES (1, 'book');
INSERT INTO  book_types (id, type) VALUES (2, 'newspaper');

INSERT INTO  publications (id, name, city) VALUES (1, 'simvol-plus', 'peterburg');
INSERT INTO  publications (id, name, city) VALUES (2, 'piter', 'peterburg');

-----------------------------RESTART SEQUENCE-----------------------------------------------
ALTER SEQUENCE book_types_id_seq RESTART WITH 100;
ALTER SEQUENCE publications_id_seq RESTART WITH 100;

