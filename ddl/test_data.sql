INSERT INTO  book_types (id, type) VALUES (1, 'book');
INSERT INTO  book_types (id, type) VALUES (2, 'newspaper');

INSERT INTO  publications (id, name, city) VALUES (1, 'simvol-plus', 'peterburg');
INSERT INTO  publications (id, name, city) VALUES (2, 'piter', 'peterburg');

INSERT INTO  authors (id, last_name, first_name, middle_name) VALUES (1, 'Pupkin', 'Vasa','Vasyljvych');
INSERT INTO  authors (id, last_name, first_name, middle_name) VALUES (2, 'Ivanov', 'Ivan','Ivanovych');

INSERT INTO  books (id, name, year, isbn, note, book_type_id, publication_id) VALUES (1, 'Effective Java: Programming Language Guide', 2014,'125-365-459P','',1,1);
INSERT INTO  books (id, name, year, isbn, note, book_type_id, publication_id) VALUES (2, 'CHIP', 2014,'9874-9658-K','',2,2);

-----------------------------RESTART SEQUENCE-----------------------------------------------
ALTER SEQUENCE book_types_id_seq RESTART WITH 100;
ALTER SEQUENCE publications_id_seq RESTART WITH 100;
ALTER SEQUENCE authors_id_seq RESTART WITH 100;
ALTER SEQUENCE books_id_seq RESTART WITH 100;

