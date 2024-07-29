--Creating Trail Table
CREATE TABLE nkievit.trails (
	"name" varchar NOT NULL,
	"location" varchar NULL,
	difficulty varchar NULL,
	distance float4 NULL,
	elevation int4 NULL,
	CONSTRAINT trail_check CHECK ((((difficulty)::text = 'Easy'::text) OR ((difficulty)::text = 'Intermediate'::text) OR ((difficulty)::text = 'Difficult'::text))),
	CONSTRAINT trail_pk PRIMARY KEY (name)
);

CREATE TABLE nkievit.users (
	"name" varchar NOT NULL,
	birthday varchar NULL,
	CONSTRAINT user_pk PRIMARY KEY (name)
);

--Creating Hike table
CREATE TABLE nkievit.records (
	trail_name varchar NOT NULL,
	user_name varchar NOT NULL,
	hike_date varchar NOT NULL,
	start_time varchar NULL,
	end_time varchar NULL,
	CONSTRAINT hikes_pk PRIMARY KEY (trail_name, user_name, hike_date)
);

ALTER TABLE nkievit.records ADD CONSTRAINT records_trails_fk FOREIGN KEY (trail_name) REFERENCES nkievit.trails("name") ON DELETE CASCADE ON UPDATE CASCADE;
ALTER TABLE nkievit.records ADD CONSTRAINT records_users_fk FOREIGN KEY (user_name) REFERENCES nkievit.users("name") ON DELETE CASCADE ON UPDATE CASCADE;