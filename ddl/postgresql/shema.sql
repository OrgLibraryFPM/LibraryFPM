-- ��� �����

CREATE TABLE book_types
(
  id bigint NOT NULL,
  type character varying(255),
  CONSTRAINT book_types_pkey PRIMARY KEY (id)
);

ALTER TABLE book_types OWNER TO postgres;

CREATE SEQUENCE  book_types_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
	
ALTER SEQUENCE  book_types_id_seq OWNED BY  book_types.id;

ALTER TABLE ONLY book_types ALTER COLUMN id SET DEFAULT nextval('book_types_id_seq'::regclass);

-----------------------------------------------------------------------------------------------------------------------------------------------

-- �����������

CREATE TABLE publications
(
  id bigint NOT NULL,
  name character varying(255), 									-- ����� �����������
  city character varying(255), 									-- ����
  CONSTRAINT publications_pkey PRIMARY KEY (id)
);

ALTER TABLE publications OWNER TO postgres;

CREATE SEQUENCE  publications_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
	
ALTER SEQUENCE  publications_id_seq OWNED BY  publications.id;

ALTER TABLE ONLY publications ALTER COLUMN id SET DEFAULT nextval('publications_id_seq'::regclass);

-----------------------------------------------------------------------------------------------------------------------------------------------

-- �����

CREATE TABLE authors
(
  id bigint NOT NULL,
  last_name character varying(255), 							-- �������
  first_name character varying(255), 							-- ���
  middle_name character varying(255),							-- ��-�������
  CONSTRAINT authors_pkey PRIMARY KEY (id)
);

ALTER TABLE authors OWNER TO postgres;

CREATE SEQUENCE  authors_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
	
ALTER SEQUENCE  authors_id_seq OWNED BY  authors.id;

ALTER TABLE ONLY authors ALTER COLUMN id SET DEFAULT nextval('authors_id_seq'::regclass);

-----------------------------------------------------------------------------------------------------------------------------------------------

-- �����

CREATE TABLE books
(
  id bigint NOT NULL,
  name character varying(255), 								-- �����
  year smallint, 											-- �� �������
  isbn character varying(255),								-- ISBN
  note text,												-- ����
  book_type_id bigint,										-- ��� �������
  publication_id bigint,									-- ��� �������
  CONSTRAINT books_pkey PRIMARY KEY (id)
);

ALTER TABLE books OWNER TO postgres;

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