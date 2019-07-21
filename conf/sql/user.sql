CREATE TABLE public."user"
(
    id bigint NOT NULL DEFAULT nextval('user_id_seq'::regclass),
    name character varying COLLATE pg_catalog."default" NOT NULL,
    age integer,
    city character varying COLLATE pg_catalog."default",
    CONSTRAINT user_pkey PRIMARY KEY (id)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public."user"
    OWNER to apps;