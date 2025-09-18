create sequence usuario_id_seq
    as integer;

alter sequence usuario_id_seq owner to postgres;

create sequence perfil_id_seq
    as integer;

alter sequence perfil_id_seq owner to postgres;

create sequence libros_id_seq
    as integer;

alter sequence libros_id_seq owner to postgres;

create sequence comentarios_id_seq
    as integer;

alter sequence comentarios_id_seq owner to postgres;

create sequence chat_id_seq
    as integer;

alter sequence chat_id_seq owner to postgres;

create sequence votos_id_seq
    as integer;

alter sequence votos_id_seq owner to postgres;

create sequence seguidores_id_seq
    as integer;

alter sequence seguidores_id_seq owner to postgres;

create sequence usuarios_baneados_id_seq
    as integer;

alter sequence usuarios_baneados_id_seq owner to postgres;

create table usuario
(
    id         bigint default nextval('usuario_id_seq'::regclass) not null
        primary key,
    nomusu     varchar(50)                                        not null
        unique,
    contrasena varchar(255)                                       not null,
    rol        varchar(10)                                        not null
        constraint usuario_rol_check
            check ((rol)::text = ANY ((ARRAY ['ADMIN'::character varying, 'USUARIO'::character varying])::text[]))
);

alter table usuario
    owner to postgres;

alter sequence usuario_id_seq owned by usuario.id;

create table perfil
(
    id          bigint default nextval('perfil_id_seq'::regclass) not null
        primary key,
    nombre      varchar(100)                                      not null,
    descripcion varchar(255),
    correo      varchar(100)                                      not null
        unique,
    imagen      varchar(255),
    generos     varchar(50)                                       not null
        constraint perfil_generos_check
            check ((generos)::text = ANY
                   ((ARRAY ['Aventura'::character varying, 'Ciencia Ficción'::character varying, 'Fantasía'::character varying, 'Romance'::character varying, 'Misterio'::character varying, 'Biografía'::character varying, 'Historia'::character varying, 'Autoayuda'::character varying])::text[])),
    usuario_id  bigint
        unique
        constraint fk_perfil_usuario
            references usuario
            on delete cascade
);

alter table perfil
    owner to postgres;

alter sequence perfil_id_seq owned by perfil.id;

create table libros
(
    id          bigint default nextval('libros_id_seq'::regclass) not null
        primary key,
    nombre      varchar(150)                                      not null,
    descripcion varchar(255),
    generos     varchar(50)                                       not null
        constraint libros_generos_check
            check ((generos)::text = ANY
                   ((ARRAY ['Aventura'::character varying, 'Ciencia Ficción'::character varying, 'Fantasía'::character varying, 'Romance'::character varying, 'Misterio'::character varying, 'Biografía'::character varying, 'Historia'::character varying, 'Autoayuda'::character varying])::text[])),
    contenido   varchar(255)                                      not null,
    autor       bigint                                            not null
        constraint fk_libros_autor
            references usuario
            on delete cascade,
    fecha       date                                              not null,
    imagen      varchar(255)
);

alter table libros
    owner to postgres;

alter sequence libros_id_seq owned by libros.id;

create table comentarios
(
    id        bigint default nextval('comentarios_id_seq'::regclass) not null
        primary key,
    contenido varchar(255)                                           not null,
    fecha     timestamp                                              not null,
    usuario   bigint                                                 not null
        constraint fk_comentarios_usuario
            references usuario
            on delete cascade,
    libro     bigint                                                 not null
        constraint fk_comentarios_libro
            references libros
            on delete cascade
);

alter table comentarios
    owner to postgres;

alter sequence comentarios_id_seq owned by comentarios.id;

create table chat
(
    id        bigint default nextval('chat_id_seq'::regclass) not null
        primary key,
    contenido varchar(255)                                    not null,
    fecha     timestamp                                       not null,
    usuario   bigint                                          not null
        constraint fk_chat_usuario
            references usuario
            on delete cascade,
    libro     bigint                                          not null
        constraint fk_chat_libro
            references libros
            on delete cascade
);

alter table chat
    owner to postgres;

alter sequence chat_id_seq owned by chat.id;

create table votos
(
    id        bigint default nextval('votos_id_seq'::regclass) not null
        primary key,
    usuario   bigint                                           not null
        constraint fk_votos_usuario
            references usuario
            on delete cascade,
    libro     bigint                                           not null
        constraint fk_votos_libro
            references libros
            on delete cascade,
    tipo_voto boolean                                          not null,
    fecha     timestamp                                        not null
);

alter table votos
    owner to postgres;

alter sequence votos_id_seq owned by votos.id;

create table seguidores
(
    id       bigint default nextval('seguidores_id_seq'::regclass) not null
        primary key,
    usuario1 bigint                                                not null
        constraint fk_seguidores_usuario1
            references usuario
            on delete cascade,
    usuario2 bigint                                                not null
        constraint fk_seguidores_usuario2
            references usuario
            on delete cascade
);

alter table seguidores
    owner to postgres;

alter sequence seguidores_id_seq owned by seguidores.id;

create table usuarios_baneados
(
    id         bigint default nextval('usuarios_baneados_id_seq'::regclass) not null
        primary key,
    id_usuario bigint                                                       not null
        constraint fk_usuarios_baneados
            references usuario
            on delete cascade,
    motivo     varchar(255)                                                 not null
);

alter table usuarios_baneados
    owner to postgres;

alter sequence usuarios_baneados_id_seq owned by usuarios_baneados.id;


