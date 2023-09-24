DROP SCHEMA IF EXISTS "hb-05-many-to-many" CASCADE;

CREATE SCHEMA "hb-05-many-to-many";

SET search_path = "hb-05-many-to-many";

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
                          "id" serial PRIMARY KEY,
                          "comment" varchar(256),
                          "course_id" int,
                          CONSTRAINT "FK_COURSE" FOREIGN KEY ("course_id")
                              REFERENCES "course" ("id")
                              ON DELETE NO ACTION
                              ON UPDATE NO ACTION
);

CREATE TABLE "student" (
                           "id" serial PRIMARY KEY,
                           "first_name" varchar(45),
                           "last_name" varchar(45),
                           "email" varchar(45)
);

CREATE TABLE "course_student" (
                                  "course_id" int NOT NULL,
                                  "student_id" int NOT NULL,
                                  PRIMARY KEY ("course_id", "student_id"),
                                  CONSTRAINT "FK_COURSE_05" FOREIGN KEY ("course_id")
                                      REFERENCES "course" ("id")
                                      ON DELETE NO ACTION
                                      ON UPDATE NO ACTION,
                                  CONSTRAINT "FK_STUDENT" FOREIGN KEY ("student_id")
                                      REFERENCES "student" ("id")
                                      ON DELETE NO ACTION
                                      ON UPDATE NO ACTION
);
