create type generos as enum('Aventura', 'Ciencia Ficcion', 'Fantasía', 'Romance', 'Misterio', 'Biográfico', 'Historia', 'Autoayuda');
create type roles as enum('ADMIN', 'USUARIO');
create type tipo_baneo as enum('TEMPORAL', 'PERMANENTE');

CREATE TABLE usuario (
                         id BIGSERIAL PRIMARY KEY,
                         password VARCHAR(255) NOT NULL,
                         username VARCHAR(255) UNIQUE NOT NULL
);

CREATE TABLE perfil (
                        id serial primary key,
                        nombre varchar(50) not null,
                        descripcion varchar(255) not null,
                        email varchar(50) not null,
                        imagen varchar(255) not null,
                        generos generos not null,
                        usuario_id int not null,
                        foreign key (usuario_id) references usuario(id)
);

CREATE TABLE autores_favoritos (
                                   id BIGSERIAL PRIMARY KEY,
                                   usuario_id BIGINT REFERENCES usuario(id),
                                   autor_id BIGINT NOT NULL
);

CREATE TABLE seguidores (
                            id BIGSERIAL PRIMARY KEY,
                            usuario_id BIGINT REFERENCES usuario(id),
                            seguido_id BIGINT REFERENCES usuario(id)
);

CREATE TABLE libros (
                        id BIGSERIAL PRIMARY KEY,
                        nombre VARCHAR(255) NOT NULL,
                        descripcion TEXT,
                        fecha_publicacion DATE,
                        imagen VARCHAR(255),
                        autor_id BIGINT NOT NULL,
                        foreign key (autor_id) references usuario(id)

);

CREATE TABLE libros_favoritos (
                                  id BIGSERIAL PRIMARY KEY,
                                  usuario_id BIGINT REFERENCES usuario(id),
                                  libro_id BIGINT REFERENCES libros(id)
);

CREATE TABLE libros_leyendo (
                                id BIGSERIAL PRIMARY KEY,
                                usuario_id BIGINT REFERENCES usuario(id),
                                libro_id BIGINT REFERENCES libros(id)
);

CREATE TABLE usuarios_baneados (
                                   id BIGSERIAL PRIMARY KEY,
                                   usuario_id BIGINT REFERENCES usuario(id),
                                   fecha_baneo DATE,
                                   motivo_baneo VARCHAR(255)
);

CREATE TABLE comentarios (
                             id BIGSERIAL PRIMARY KEY,
                             usuario_id BIGINT REFERENCES usuario(id),
                             libro_id BIGINT REFERENCES libros(id),
                             fecha TIMESTAMP NOT NULL,
                             comentario TEXT
);

CREATE TABLE votos (
                       id BIGSERIAL PRIMARY KEY,
                       usuario_id BIGINT REFERENCES usuario(id),
                       libro_id BIGINT REFERENCES libros(id),
                       tipo_voto BOOLEAN NOT NULL
);

CREATE TABLE chat (
                      id BIGSERIAL PRIMARY KEY,
                      nombre VARCHAR(255),
                      descripcion TEXT,
                      imagen VARCHAR(255)
);

CREATE TABLE chat_usuarios (
                               id BIGSERIAL PRIMARY KEY,
                               usuario_id BIGINT REFERENCES usuario(id),
                               chat_id BIGINT REFERENCES chat(id)
);

CREATE TABLE chat_mensajes (
                               id BIGSERIAL PRIMARY KEY,
                               usuario_id BIGINT REFERENCES usuario(id),
                               chat_id BIGINT REFERENCES chat(id),
                               fecha TIMESTAMP NOT NULL,
                               mensaje VARCHAR(255)
);
