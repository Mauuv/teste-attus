create table if not exists contato (
    id integer not null primary key,
    email varchar(255) not null,
    telefone varchar(20) not null
);

create table if not exists parte_envolvida (
    id integer not null primary key,
    nome varchar(255) not null,
    cpf_cnpj varchar(14) not null,
    tipo varchar(255) not null,
    contato_id integer constraint FK_parte_envolvida_contato references contato not null
);

create sequence if not exists parte_envolvida_seq increment by 50;
create sequence if not exists contato_seq increment by 50;