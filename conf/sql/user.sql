-- Sequence and defined type
CREATE SEQUENCE IF NOT EXISTS user_id_seq;

-- Table Definition
CREATE TABLE "public"."user" (
    "id" int4 NOT NULL DEFAULT nextval('user_id_seq'::regclass),
    "name" varchar NOT NULL,
    "age" int4,
    "city" varchar,
    "password" varchar,
    PRIMARY KEY ("id")
);