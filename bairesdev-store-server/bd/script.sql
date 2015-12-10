/*==============================================================*/
/* DBMS name:      PostgreSQL 8                                 */
/* Created on:     05/12/2015 08:38:43 a.m.                     */
/*==============================================================*/

CREATE DATABASE bairesdevstore;
\c bairesdevstore

drop table if exists producto;

drop table if exists categoria;


/*==============================================================*/
/* Table: categoria                                             */
/*==============================================================*/
create table categoria (
   id                   SERIAL               not null,
   id_categoria_padre   INT4                 null,
   nombre               VARCHAR(100)         not null,
   descripcion          VARCHAR(250)         null,
   nivel                INT4                 not null,
   orden                INT4                 not null,
   estatus              CHAR(1)              not null,
   constraint PK_CATEGORIA primary key (id)
);

;
/*==============================================================*/
/* Table: producto                                              */
/*==============================================================*/
create table producto (
   id                   SERIAL               not null,
   id_categoria         INT4                 not null,
   nombre               VARCHAR(100)         not null,
   descripcion          VARCHAR(250)         null,
   cantidad             INT4                 not null,
   medida               CHAR(1)              not null,
   precio               FLOAT4               not null,
   estatus              CHAR(1)              not null,
   constraint PK_PRODUCTO primary key (id)
);

;
alter table categoria
   add constraint FK_CATEGORI_REFERENCE_CATEGORI foreign key (id_categoria_padre)
      references categoria (id)
      on delete restrict on update restrict;

alter table producto
   add constraint FK_PRODUCTO_REFERENCE_CATEGORI foreign key (id_categoria)
      references categoria (id)
      on delete restrict on update restrict;
