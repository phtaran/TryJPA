-- Table: customer

-- DROP TABLE customer;

CREATE TABLE EMPLOYEE
(
  id serial NOT NULL,
  f_name character varying,
  l_name character varying,
  salary integer,
  address_id integer,
  CONSTRAINT pk_employee PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE EMPLOYEE
  OWNER TO postgres;
