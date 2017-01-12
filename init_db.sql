CREATE TABLE person
(
  id integer NOT NULL DEFAULT nextval('serial'::regclass),
  age integer,
  content character varying(255),
  name character varying(255),
  CONSTRAINT person_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE person
  OWNER TO postgres;