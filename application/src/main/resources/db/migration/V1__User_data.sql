-- Sequence: public.hibernate_sequence

-- DROP SEQUENCE public.hibernate_sequence;

CREATE SEQUENCE public.hibernate_sequence
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 5
  CACHE 1;
ALTER TABLE public.hibernate_sequence
  OWNER TO postgres;

-- Table: public.department

-- DROP TABLE public.department;

CREATE TABLE public.department
(
  id bigint NOT NULL,
  company_id bigint,
  name character varying(255),
  CONSTRAINT department_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.department
  OWNER TO postgres;

INSERT INTO department VALUES (1, 1, 'Development');
INSERT INTO department VALUES (2, 1, 'Testing');
INSERT INTO department VALUES (3, 1, 'Human Resource');
INSERT INTO department VALUES (4, 1, 'Accountancy');
INSERT INTO department VALUES (5, 1, 'Quality Assurance');

-- Table: public.employee

-- DROP TABLE public.employee;



-- Table: public.location

-- DROP TABLE public.location;

CREATE TABLE public.location
(
  id bigint NOT NULL,
  city character varying(255),
  company_id bigint,
  country character varying(255),
  landmark character varying(255),
  state character varying(255),
  CONSTRAINT location_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.location
  OWNER TO postgres;
INSERT INTO location VALUES (1, 'Hyderabad', 1, 'India', 'Hyderabad', 'Telangana');
INSERT INTO location VALUES (2, 'Delhi', 1, 'India', 'Delhi', 'Delhi');
INSERT INTO location VALUES (3, 'Pune', 1, 'India', 'Pune', 'Maharastra');
INSERT INTO location VALUES (4, 'Banglore', 1, 'India', 'Banglore', 'Karnataka');

-- Table: public.project

-- DROP TABLE public.project;

CREATE TABLE public.project
(
  id bigint NOT NULL,
  company_id bigint NOT NULL,
  name character varying(255),
  CONSTRAINT project_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.project
  OWNER TO postgres;

INSERT INTO project VALUES (1, 1, 'TagCloud');
INSERT INTO project VALUES (2, 1, 'MindHive');
INSERT INTO project VALUES (3, 1, 'UAV');
INSERT INTO project VALUES (4, 1, 'zededup');
INSERT INTO project VALUES (5, 1, 'Review Project');

-- Table: public.role

-- DROP TABLE public.role;

CREATE TABLE public.role
(
  id bigint NOT NULL,
  company_id bigint,
  name character varying(255),
  CONSTRAINT role_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.role
  OWNER TO postgres;

INSERT INTO role VALUES (1, 1, 'Software Engineer');
INSERT INTO role VALUES (2, 1, 'Senior Software Engineer');
INSERT INTO role VALUES (3, 1, 'Analyst');
INSERT INTO role VALUES (4, 1, 'Technical Lead');
INSERT INTO role VALUES (5, 1, 'Product Manager');
INSERT INTO role VALUES (6, 1, 'Programe Manager');

-- Table: public.skill

-- DROP TABLE public.skill;

CREATE TABLE public.skill
(
  id bigint NOT NULL,
  company_id bigint,
  name character varying(255),
  CONSTRAINT skill_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.skill
  OWNER TO postgres;

INSERT INTO skill VALUES (1, 1, 'JAVA');
INSERT INTO skill VALUES (2, 1, 'SQL');
INSERT INTO skill VALUES (3, 1, 'HADOOP');
INSERT INTO skill VALUES (4, 1, 'C++');
INSERT INTO skill VALUES (5, 1, 'PYTHON');
INSERT INTO skill VALUES (6, 1, 'RUBY');
INSERT INTO skill VALUES (7, 1, 'EMBER');
INSERT INTO skill VALUES (8, 1, 'ANGULAR JS');

CREATE TABLE public.employee
(
  id bigint NOT NULL,
  created_tstamp timestamp without time zone,
  created_by_id bigint NOT NULL,
  deleted_tstamp timestamp without time zone,
  last_updated_tstamp timestamp without time zone,
  last_updated_by_id bigint NOT NULL,
  biodata character varying(255),
  company_id bigint,
  email character varying(255),
  employee_role character varying(255),
  first_name character varying(255),
  last_name character varying(255),
  middle_name character varying(255),
  mobile_no character varying(255),
  profile_pic character varying(255),
  reporting_employee_id bigint,
  reporting_employee_name character varying(255),
  start_date character varying(255),
  department_id bigint,
  location_id bigint,
  project_id bigint,
  CONSTRAINT employee_pkey PRIMARY KEY (id),
  CONSTRAINT fkbejtwvg9bxus2mffsm3swj3u9 FOREIGN KEY (department_id)
      REFERENCES public.department (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fknfotji9xluv8o3y9gogq2hxiw FOREIGN KEY (location_id)
      REFERENCES public.location (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fkt7hn4e5o1y2vv3gawqldhhsj8 FOREIGN KEY (project_id)
      REFERENCES public.project (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.employee
  OWNER TO postgres;

INSERT INTO employee VALUES (1, NULL, 1, NULL, '2018-02-07 20:30:28.89', 1, 'not available', 1, 'abhiraj@gmail.com', 'Senior Software Engineer', 'Abhiraj', 'Patel', 'singh', '9845232321', '123', NULL, '{name=null}', '2018-02-06', 1, 1, 1);
INSERT INTO employee VALUES (2, NULL, 1, NULL, '2018-02-07 20:31:39.961', 1, 'he work in microsoft', 1, 'prabhaj@gmail.com', 'Senior Software Engineer', 'prabhat', 'Singh', NULL, '20938230983', '1234', 1, 'Abhiraj singh Patel ', '2018-02-16', 1, 1, 1);
INSERT INTO employee VALUES (3, '2018-02-07 20:33:00.207', 1, NULL, '2018-02-07 20:33:00.207', 1, 'he work in apple', 1, 'sashi@gmail.com', 'Senior Software Engineer', 'Sashi', 'Singh', '', '29874987927', '12345', 2, 'prabhat Singh ', '2018-02-15', 2, 2, 2);
INSERT INTO employee VALUES (4, '2018-02-07 20:55:31.778', 1, NULL, '2018-02-07 21:07:10.597', 1, '', 1, 'ravikumar@gmail.com', 'Senior Software Engineer', 'Ravi', 'kumar', '', '0933090900', 'demo', NULL, 'Sashi  Singh ', '2018-02-06', 1, 1, 2);
INSERT INTO employee VALUES (6, '2018-02-07 21:01:17.098', 1, NULL, '2018-02-07 21:34:24.52', 1, '', 1, 'ravikumar@gmail.com', 'Senior Software Engineer', 'Ravi', 'kumar', '', '0933090900', 'walter', 50, NULL, '2018-02-06', 1, 1, 2);
INSERT INTO employee VALUES (7, '2018-02-07 20:41:01.348', 1, NULL, '2018-02-07 21:35:03.292', 1, 'he', 1, 'guruell@gmail.com', 'Software Engineer', 'Guru', 'well', '', '9373927398', 'aaron', NULL, NULL, '2018-02-08', 3, 1, 1);

-- Table: public.employee_skill

-- DROP TABLE public.employee_skill;

CREATE TABLE public.employee_skill
(
  employee_id bigint NOT NULL,
  skill_id bigint NOT NULL,
  CONSTRAINT employee_skill_pkey PRIMARY KEY (employee_id, skill_id),
  CONSTRAINT fkam2psf41jwoy33ge3uvxep8tl FOREIGN KEY (skill_id)
      REFERENCES public.skill (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fkkd8xx37dlmjryoas0d91hri6c FOREIGN KEY (employee_id)
      REFERENCES public.employee (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.employee_skill
  OWNER TO postgres;

INSERT INTO employee_skill VALUES (2, 7);
INSERT INTO employee_skill VALUES (2, 3);
INSERT INTO employee_skill VALUES (3, 5);
INSERT INTO employee_skill VALUES (3, 6);
INSERT INTO employee_skill VALUES (4, 5);
INSERT INTO employee_skill VALUES (4, 3);
