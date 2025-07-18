drop table if exists categorias;
create table categorias(
	codigo_cat serial not null,
	nombre varchar (100)not null,
	categoria_padre int ,
	constraint categorias_pk primary key (codigo_cat) ,
	constraint categorias_fk foreign key (categoria_padre) references categorias (codigo_cat)
);

insert into categorias (nombre,categoria_padre)
values ('materia Prima',null);
insert into categorias (nombre,categoria_padre)
values ('Proteina',1);
insert into categorias (nombre,categoria_padre)
values ('Salsas',1);
insert into categorias (nombre,categoria_padre)
values ('punto de venta',null);
insert into categorias (nombre,categoria_padre)
values ('Bevidas',4);
insert into categorias (nombre,categoria_padre)
values ('Con alcohol',5);
insert into categorias (nombre,categoria_padre)
values ('Sin alcohol',5);

select * from categorias



drop table if exists Categorias_unidad_medidas;
create table Categorias_unidad_medidas(
codigo char(1)not null,
nombre varchar (100) not null,
constraint Categorias_unidad_medidas_pk primary key (codigo)
);
insert into Categorias_unidad_medidas (codigo,nombre)
values ('U','Unidades');
insert into Categorias_unidad_medidas (codigo,nombre)
values ('V','Volumen');
insert into Categorias_unidad_medidas (codigo,nombre)
values ('P','Peso');

select * from Categorias_unidad_medidas

drop table if exists Unidades_de_medida;
create table Unidades_de_medida(
codigo_cat serial not null ,
nombre varchar (100)not null,
descripcion varchar(100) not null,
Categoria_udm char(1) not null,
constraint Unidades_de_medida_pk primary key (codigo_cat),
constraint Unidades_de_medida_fk foreign key (Categoria_udm) references Categorias_unidad_medidas (codigo)
);
insert into Unidades_de_medida (nombre,descripcion,Categoria_udm)
values ('ml','mililitros','V');
insert into Unidades_de_medida (nombre,descripcion,Categoria_udm)
values ('m','litros','V');
insert into Unidades_de_medida (nombre,descripcion,Categoria_udm)
values ('u','unidad','U');
insert into Unidades_de_medida (nombre,descripcion,Categoria_udm)
values ('d','docena','U');
insert into Unidades_de_medida (nombre,descripcion,Categoria_udm)
values ('g','gramos','P');
insert into Unidades_de_medida (nombre,descripcion,Categoria_udm)
values ('kg','kilogramos','P');
insert into Unidades_de_medida (nombre,descripcion,Categoria_udm)
values ('lb','libras','P');

select * from  Unidades_de_medida

drop table if exists Productos;
create table Productos(
codigo_cat serial not null ,
nombre varchar (100) not null,
udm int  not null,
precio_venta money not null,
tiene_iva boolean  not null,
coste money not null,
categoria int not null,
strock int not null,
constraint Productos_pk primary key (codigo_cat),
constraint ProductosU_fk foreign key (udm) references Unidades_de_medida (codigo_cat),
constraint ProductosC_fk foreign key (categoria) references categorias (codigo_cat)
);
insert into Productos (nombre,udm,precio_venta,tiene_iva,coste,categoria,strock)
values ('coca cola pequeña','3',0.5804,true,0.3729,7,100);
insert into Productos (nombre,udm,precio_venta,tiene_iva,coste,categoria,strock)
values ('salsa de tomate','6',0.95,true,0.8736,3,0);
insert into Productos (nombre,udm,precio_venta,tiene_iva,coste,categoria,strock)
values ('mostaza','6',0.95,true,0.89,3,0);
insert into Productos (nombre,udm,precio_venta,tiene_iva,coste,categoria,strock)
values ('Fuze tea','3',0.8,true,0.7,7,50);

select * from  Productos

drop table if exists estados_pedido;
create table  estados_pedido (
codigo char (1) not null,
descripcion varchar (100),
constraint estados_pedido_pk primary key (codigo)
);
insert into estados_pedido (codigo,descripcion)
values ('S','Solicitutado');
insert into estados_pedido (codigo,descripcion)
values ('R','Recibido');

select * from  estados_pedido

drop table if exists tipo_documento;
create table tipo_documento (
codigo char (1) not null,
descripcion varchar  (50)not null,
constraint tipo_documento_pk primary key (codigo)
);
insert into tipo_documento (codigo,descripcion)
values ('C','cedula');
insert into tipo_documento (codigo,descripcion)
values ('R','Ruc');

select * from tipo_documento

drop table if exists proveedores;
create table proveedores(
identificador varchar (13) not null,
tipo_documento char (1)not null,
nombre varchar  (100)not null,
telefono char (10) not null,
correo varchar (100) not null,
direccion varchar (100)not null,
constraint proveedores_pk primary key (identificador),
constraint proveedores_fk foreign key (tipo_documento) references tipo_documento (codigo)	
);
insert into proveedores (identificador,tipo_documento,nombre,telefono,correo,direccion)
values ('1792285747','C','Mateo Muñoz','0979516266','munogweg00@gamil.com','Cumbayork');
insert into proveedores (identificador,tipo_documento,nombre,telefono,correo,direccion)
values ('1792285747001','C','Natalia Mosuqera','0979516298','nataliaer0g0er0@gamil.com','La Tola');

select * from proveedores

drop table if exists cabecera_pedido;
create table cabecera_pedido(
numero serial not null,
proveedores varchar (13)not null,
fecha date not null,
estado char (1)not null,
constraint  cabecera_pedido_pk primary key (numero),
constraint cabecera_pedidoP_fk foreign key (proveedores) references proveedores (identificador),
constraint cabecera_pedidoE_fk foreign key (estado) references estados_pedido (codigo)
);
insert into cabecera_pedido(proveedores,fecha,estado)
values ('1792285747','20/11/2023','R');
insert into cabecera_pedido(proveedores,fecha,estado)
values ('1792285747','20/11/2023','R');

select * from cabecera_pedido

drop table if exists detalle_pedido;
create table detalle_pedido(
codigo serial not null,
cabecera_pedido int not null,
producto int not null,
cantidad_solicitada int not null,
subtotal money not null,
cantidad_recibida int not null,
constraint detalle_pedido_pk primary key (codigo),
constraint detalle_pedidoC_fk foreign key (cabecera_pedido) references cabecera_pedido (numero),
constraint detalle_pedidoP_fk foreign key (producto) references Productos (codigo_cat)
);
insert into detalle_pedido (cabecera_pedido,producto,cantidad_solicitada,subtotal,cantidad_recibida)
values (1,1,100,37.29,100);
insert into detalle_pedido (cabecera_pedido,producto,cantidad_solicitada,subtotal,cantidad_recibida)
values (1,4,50,11.8,50);
insert into detalle_pedido (cabecera_pedido,producto,cantidad_solicitada,subtotal,cantidad_recibida)
values (1,1,100,3.73,10);


select * from detalle_pedido

drop table if exists historia_Stock;
create table historia_Stock(
codigo serial not  null,
fecha timestamp not null,
referecia varchar (100)not null,
producto int not null,
cantidad int not null,
constraint historia_Stock_pk primary key (codigo),
constraint historia_Stock_fk foreign key (producto) references Productos (codigo_cat)
);
insert into historia_Stock (fecha,referecia,producto,cantidad)
values ('20/11/2023 19:59','pedido 1',1,100);
insert into historia_Stock (fecha,referecia,producto,cantidad)
values ('20/11/2023 19:59','pedido 1',4,50);
insert into historia_Stock (fecha,referecia,producto,cantidad)
values ('20/11/2023 20:00','pedido 2',1,10);
insert into historia_Stock (fecha,referecia,producto,cantidad)	
values ('20/11/2023 20:00','venta 1',1,-5);
insert into historia_Stock (fecha,referecia,producto,cantidad)
values ('20/11/2023 20:00','venta 4	',1,1);

select * from historia_Stock

drop table if exists caberas_ventas;
create table caberas_ventas(
codigo serial not null,
fecha timestamp not null,
total_sin_iva money not null,
iva money not null,
total money not null,
constraint  caberas_ventas_pk primary key (codigo)
);
insert into caberas_ventas (fecha,total_sin_iva,iva,total)
values ('20/11/2023 20:00',3.26,0.29,3.65);

select * from caberas_ventas 

drop table if exists detalle_ventas;
create table detalle_ventas(
codigo serial not null,
cabecera_ventas int not null,
producto int not null,
cantidad int not null,
precio_venta money not null,
subtotal money not null,
subtotal_con_iva money  not null,
constraint detalle_ventas_pk primary key (codigo),
constraint  detalle_ventas_fk foreign key (producto) references Productos (codigo_cat)
);
insert into detalle_ventas(cabecera_ventas,producto,cantidad,precio_venta,subtotal,subtotal_con_iva)
values (1,1,5,0.58,2.9,3.25);
insert into detalle_ventas(cabecera_ventas,producto,cantidad,precio_venta,subtotal,subtotal_con_iva)
values (1,4,5,1,3.36,0.4);

select * from detalle_ventas