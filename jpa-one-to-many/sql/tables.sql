DROP SCHEMA IF EXISTS "hb-03-one-to-many" CASCADE;

CREATE SCHEMA "hb-03-one-to-many";

SET search_path = "hb-03-one-to-many";

CREATE TABLE "instructor_detail" (
                                     "id" serial PRIMARY KEY,
                                     "youtube_channel" varchar(128),
                                     "hobby" varchar(45)
);

CREATE TABLE "instructor" (
                              "id" serial PRIMARY KEY,
                              "first_name" varchar(45),
                              "last_name" varchar(45),
                              "email" varchar(45),
                              "instructor_detail_id" int,
                              CONSTRAINT "FK_DETAIL" FOREIGN KEY ("instructor_detail_id")
                                  REFERENCES "instructor_detail" ("id")
                                  ON DELETE NO ACTION
                                  ON UPDATE NO ACTION
);

CREATE TABLE "course" (
                          "id" serial PRIMARY KEY,
                          "title" varchar(128) UNIQUE,
                          "instructor_id" int,
                          CONSTRAINT "FK_INSTRUCTOR" FOREIGN KEY ("instructor_id")
                              REFERENCES "instructor" ("id")
                              ON DELETE NO ACTION
                              ON UPDATE NO ACTION
);

CREATE TABLE "review" (
                        id serial PRIMARY KEY,
                        comment varchar(256),
                        course_id int,
                        CONSTRAINT FK_COURSE FOREIGN KEY (course_id)
                            REFERENCES "course" (id) ON DELETE NO ACTION ON UPDATE NO ACTION
);