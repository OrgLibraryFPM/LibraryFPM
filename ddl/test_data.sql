INSERT INTO  book_types (id, type) VALUES (1, 'book');
INSERT INTO  book_types (id, type) VALUES (2, 'newspaper');

-----------------------------RESTART SEQUENCE-----------------------------------------------
ALTER SEQUENCE book_types_id_seq RESTART WITH 100;

