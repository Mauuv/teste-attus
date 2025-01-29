create table if not exists contrato (
    id integer not null primary key,
    numero integer not null unique,
    data_criacao timestamp not null default CURRENT_TIMESTAMP,
    descricao text,
    status_contrato varchar(50)
);

create table if not exists evento (
    id integer not null primary key,
    tipo varchar(50) not null,
    data_registro timestamp not null default CURRENT_TIMESTAMP,
    descricao text,
    contrato_id integer not null,
    constraint FK_evento_contrato foreign key (contrato_id) references contrato (id) on delete cascade
);

create table if not exists parte_envolvida_contrato (
    id integer not null primary key,
    tipo_parte_envolvida varchar(50) not null,
    parte_envolvida_id integer not null,
    contrato_id integer not null,
    constraint FK_parte_contrato foreign key (contrato_id) references contrato(id)
);


create sequence if not exists evento_seq increment by 50;
create sequence if not exists contrato_seq increment by 50;
create sequence if not exists parte_envolvida_contrato_seq increment by 50;

create index if not exists idx_status_contrato on contrato (status_contrato);
create index if not exists idx_data_criacao on contrato (data_criacao);