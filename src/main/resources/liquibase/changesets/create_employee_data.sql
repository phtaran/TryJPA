CREATE TABLE emp_data
(
  emp_id serial NOT NULL REFERENCES EMPLOYEE (id),
  mgr_id integer,
  year_of_serv integer,
  CONSTRAINT pk_emp_data PRIMARY KEY (emp_id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE emp_data
  OWNER TO postgres;