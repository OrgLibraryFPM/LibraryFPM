-- Тип книги

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

-- Видавництво

CREATE TABLE publications
(
  id bigint NOT NULL,
  name character varying(255), -- назва видавництва
  city character varying(255), -- місто
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