DROP SCHEMA IF EXISTS "hb-01-one-to-one-uni" CASCADE;

CREATE SCHEMA "hb-01-one-to-one-uni";

SET search_path TO "hb-01-one-to-one-uni";

CREATE TABLE instructor_detail (
                                   id serial PRIMARY KEY,
                                   youtube_channel varchar(128),
                                   hobby varchar(45)
);

CREATE TABLE instructor (
                            id serial PRIMARY KEY,
                            first_name varchar(45),
                            last_name varchar(45),
                            email varchar(45),
                            instructor_detail_id int,
                            CONSTRAINT fk_detail FOREIGN KEY (instructor_detail_id) REFERENCES instructor_detail (id) ON DELETE NO ACTION ON UPDATE NO ACTION
);
