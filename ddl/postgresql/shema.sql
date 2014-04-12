-- Тип книги

CREATE TABLE book_types
(
  id bigint NOT NULL,
  type character varying(255),
  CONSTRAINT book_types_pkey PRIMARY KEY (id)
);

CREATE SEQUENCE  book_types_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
	
ALTER SEQUENCE  book_types_id_seq OWNED BY  book_types.id;

ALTER TABLE ONLY book_types ALTER COLUMN id SET DEFAULT nextval('book_types_id_seq'::regclass);

-----------------------------------------------------------------------------------------------------------------------------------------------

-- Видавництво

CREATE TABLE publications
(
  id bigint NOT NULL,
  name character varying(255), 									-- назва видавництва
  city character varying(255), 									-- місто
  CONSTRAINT publications_pkey PRIMARY KEY (id)
);

CREATE SEQUENCE  publications_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
	
ALTER SEQUENCE  publications_id_seq OWNED BY  publications.id;

ALTER TABLE ONLY publications ALTER COLUMN id SET DEFAULT nextval('publications_id_seq'::regclass);

-----------------------------------------------------------------------------------------------------------------------------------------------

-- Автор

CREATE TABLE authors
(
  id bigint NOT NULL,
  last_name character varying(255), 							-- прізвище
  first_name character varying(255), 							-- імя
  middle_name character varying(255),							-- по-батькові
  CONSTRAINT authors_pkey PRIMARY KEY (id)
);

CREATE SEQUENCE  authors_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
	
ALTER SEQUENCE  authors_id_seq OWNED BY  authors.id;

ALTER TABLE ONLY authors ALTER COLUMN id SET DEFAULT nextval('authors_id_seq'::regclass);

-----------------------------------------------------------------------------------------------------------------------------------------------

-- Книга

CREATE TABLE books
(
  id bigint NOT NULL,
  name character varying(255),						-- назва
  year smallint,                                    -- рік видання
  isbn character varying(255),                      -- ISBN
  note text,                                        -- опис
  book_type_id bigint,                              -- тип видання
  publication_id bigint,                            -- тип видання
  tome smallint,									-- том 
  number smallint,									-- номер
  part smallint,									-- частина
  series smallint,									-- серія
  page_count smallint,								-- к-сть сторінок						
  CONSTRAINT books_pkey PRIMARY KEY (id)
);

CREATE SEQUENCE  books_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
	
ALTER SEQUENCE  books_id_seq OWNED BY  books.id;

ALTER TABLE ONLY books ALTER COLUMN id SET DEFAULT nextval('books_id_seq'::regclass);

ALTER TABLE ONLY books
    ADD CONSTRAINT fk_book_type FOREIGN KEY (book_type_id) REFERENCES book_types(id) ON DELETE SET NULL;
    
ALTER TABLE ONLY books
    ADD CONSTRAINT fk_publication FOREIGN KEY (publication_id) REFERENCES publications(id) ON DELETE SET NULL;

-----------------------------------------------------------------------------------------------------------------------------------------------

CREATE TABLE author_book
(
  author_id bigint NOT NULL,
  book_id bigint NOT NULL
);

ALTER TABLE ONLY author_book
	ADD CONSTRAINT fk_book FOREIGN KEY (book_id) REFERENCES books (id) MATCH SIMPLE ON UPDATE CASCADE ON DELETE CASCADE;
 
ALTER TABLE ONLY author_book
	ADD CONSTRAINT fk_author FOREIGN KEY (author_id) REFERENCES authors (id) MATCH SIMPLE ON UPDATE CASCADE;
	
-----------------------------------------------------------------------------------------------------------------------------------------------

-- Читач

CREATE TABLE readers
(
  id bigint NOT NULL,
  last_name character varying(255), 							-- прізвище
  first_name character varying(255), 							-- імя
  middle_name character varying(255),							-- по-батькові
  CONSTRAINT readers_pkey PRIMARY KEY (id)
);

CREATE SEQUENCE  readers_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
	
ALTER SEQUENCE  readers_id_seq OWNED BY  readers.id;

ALTER TABLE ONLY readers ALTER COLUMN id SET DEFAULT nextval('readers_id_seq'::regclass);

-----------------------------------------------------------------------------------------------------------------------------------------------

-- Видача

CREATE TABLE issuanses
(
  id bigint NOT NULL,
  reader_id bigint NOT NULL, 							-- читач
  date_issuanse timestamp without time zone NOT NULL,	-- дата видачі
  date_return timestamp without time zone,				-- дата повернення
  CONSTRAINT issuanses_pkey PRIMARY KEY (id)
);

CREATE SEQUENCE  issuanses_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
	
ALTER SEQUENCE  issuanses_id_seq OWNED BY  issuanses.id;

ALTER TABLE ONLY issuanses ALTER COLUMN id SET DEFAULT nextval('issuanses_id_seq'::regclass);
    
ALTER TABLE ONLY issuanses
    ADD CONSTRAINT fk_reader FOREIGN KEY (reader_id) REFERENCES readers(id) ON UPDATE CASCADE;
    
-----------------------------------------------------------------------------------------------------------------------------------------------

CREATE TABLE book_issuanse
(
  issuanse_id bigint NOT NULL,
  book_id bigint NOT NULL
);

ALTER TABLE ONLY book_issuanse
	ADD CONSTRAINT fk_book FOREIGN KEY (book_id) REFERENCES books (id) MATCH SIMPLE ON UPDATE CASCADE;
 
ALTER TABLE ONLY book_issuanse
	ADD CONSTRAINT fk_issuanse FOREIGN KEY (issuanse_id) REFERENCES issuanses (id) MATCH SIMPLE ON UPDATE CASCADE ON DELETE CASCADE;