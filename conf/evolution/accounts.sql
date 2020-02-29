-- Sequence and defined type
CREATE SEQUENCE IF NOT EXISTS accounts_id_seq;

-- Table Definition
CREATE TABLE "public"."accounts" (
    "id" int4 NOT NULL DEFAULT nextval('accounts_id_seq'::regclass),
    "name" text NOT NULL,
    "email" text,
    "password" text NOT NULL,
    PRIMARY KEY ("id")
);