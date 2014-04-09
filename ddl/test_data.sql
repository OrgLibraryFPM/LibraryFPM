INSERT INTO  book_types (id, type) VALUES (1, 'book');
INSERT INTO  book_types (id, type) VALUES (2, 'newspaper');

INSERT INTO  publications (id, name, city) VALUES (1, 'simvol-plus', 'peterburg');
INSERT INTO  publications (id, name, city) VALUES (2, 'piter', 'peterburg');

INSERT INTO  authors (id, last_name, first_name, middle_name) VALUES (1, 'Pupkin', 'Vasa','Vasyljvych');
INSERT INTO  authors (id, last_name, first_name, middle_name) VALUES (2, 'Ivanov', 'Ivan','Ivanovych');
INSERT INTO  authors (id, last_name, first_name, middle_name) VALUES (3, 'Zuzykin', 'Peta','Petrowych');

INSERT INTO  books (id, name, year, isbn, note, book_type_id, publication_id) VALUES (1, 'Effective Java: Programming Language Guide', 2014,'125-365-459P','',1,1);
INSERT INTO  books (id, name, year, isbn, note, book_type_id, publication_id) VALUES (2, 'CHIP', 2014,'9874-9658-K','',2,2);
INSERT INTO  books (id, name, year, isbn, note, book_type_id, publication_id) VALUES (3, 'Code Complete editions', 2005,'125-365-459P','',1,1);
INSERT INTO  books (id, name, year, isbn, note, book_type_id, publication_id) VALUES (4, 'Professional Java JDK 6 Edition', 2007,'213548785','',1,1);
INSERT INTO  books (id, name, year, isbn, note, book_type_id, publication_id) VALUES (5, 'OpenGL. Программирование компьютерной графики', 2006,'125-365-459P','',1,1);

INSERT INTO  author_book (author_id, book_id) VALUES (1,1);
INSERT INTO  author_book (author_id, book_id) VALUES (1,3);
INSERT INTO  author_book (author_id, book_id) VALUES (2,3);
INSERT INTO  author_book (author_id, book_id) VALUES (3,2);

INSERT INTO  readers (id, last_name, first_name, middle_name) VALUES (1, 'Shutka', 'Anriy','Vasyljvych');
INSERT INTO  readers (id, last_name, first_name, middle_name) VALUES (2, 'Kowtun', 'Roman','Ivanovych');
INSERT INTO  readers (id, last_name, first_name, middle_name) VALUES (3, 'Sofer', 'Wowa','Petrowych');

INSERT INTO  issuanses (id, reader_id, date_issuanse, date_return) VALUES (1, 3, '2013-03-15','2013-03-25');
INSERT INTO  issuanses (id, reader_id, date_issuanse, date_return) VALUES (2, 2, '2013-04-25',null);

INSERT INTO book_issuanse(book_id, issuanse_id) VALUES (3,1);
INSERT INTO book_issuanse(book_id, issuanse_id) VALUES (4,2);
INSERT INTO book_issuanse(book_id, issuanse_id) VALUES (5,2);

-----------------------------RESTART SEQUENCE-----------------------------------------------
ALTER SEQUENCE book_types_id_seq RESTART WITH 100;
ALTER SEQUENCE publications_id_seq RESTART WITH 100;
ALTER SEQUENCE authors_id_seq RESTART WITH 100;
ALTER SEQUENCE books_id_seq RESTART WITH 100;
ALTER SEQUENCE readers_id_seq RESTART WITH 100;
ALTER SEQUENCE issuanses_id_seq RESTART WITH 100;

