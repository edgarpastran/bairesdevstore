\c bairesdevstore

/*==============================================================*/
/* INSERT DATA IN CATEGORIA	                              	*/
/*==============================================================*/
INSERT INTO categoria (id_categoria_padre, nombre, descripcion, nivel, orden, estatus) VALUES (null, 'Electronicos', 'Aparatos electronicos', 1, 1, 'A');
INSERT INTO categoria (id_categoria_padre, nombre, descripcion, nivel, orden, estatus) VALUES (1, 'Computadoras', 'Computadoras', 2, 1, 'A');
INSERT INTO categoria (id_categoria_padre, nombre, descripcion, nivel, orden, estatus) VALUES (2, 'Desktops', 'Computadoras de escritorio', 3, 1, 'A');
INSERT INTO categoria (id_categoria_padre, nombre, descripcion, nivel, orden, estatus) VALUES (2, 'Laptops', 'Computadoras portatiles', 3, 2, 'A');
INSERT INTO categoria (id_categoria_padre, nombre, descripcion, nivel, orden, estatus) VALUES (null, 'Ropa', 'Prendas para vestir', 1, 2, 'A');
INSERT INTO categoria (id_categoria_padre, nombre, descripcion, nivel, orden, estatus) VALUES (5, 'Camisas', 'Camisas', 2, 1, 'A');
INSERT INTO categoria (id_categoria_padre, nombre, descripcion, nivel, orden, estatus) VALUES (6, 'Manga larga', 'Camisas manga larga', 3, 1, 'A');
INSERT INTO categoria (id_categoria_padre, nombre, descripcion, nivel, orden, estatus) VALUES (6, 'Manga corta', 'Camisas manga corta', 3, 2, 'A');
INSERT INTO categoria (id_categoria_padre, nombre, descripcion, nivel, orden, estatus) VALUES (null, 'Juguetes', 'Cosas para entrenimiento', 1, 3, 'A');
INSERT INTO categoria (id_categoria_padre, nombre, descripcion, nivel, orden, estatus) VALUES (9, 'Pelotas', 'Pelotas', 2, 1, 'A');
INSERT INTO categoria (id_categoria_padre, nombre, descripcion, nivel, orden, estatus) VALUES (10, 'De futbol', 'Pelotas de futbol', 3, 1, 'A');
INSERT INTO categoria (id_categoria_padre, nombre, descripcion, nivel, orden, estatus) VALUES (10, 'De basketbol', 'Pelotas de basketbol', 3, 2, 'A');

/*==============================================================*/
/* INSERT DATA IN PRODUCTO	                              	*/
/*==============================================================*/
INSERT INTO producto (id_categoria, nombre, descripcion, cantidad, medida, precio, estatus) VALUES (3, 'PC Dell', 'PC Dell D-8', 100, 'U', 600, 'A');
INSERT INTO producto (id_categoria, nombre, descripcion, cantidad, medida, precio, estatus) VALUES (4, 'Laptop Gateway', 'Laptop Gateway G-9', 50, 'U', 700, 'A');
INSERT INTO producto (id_categoria, nombre, descripcion, cantidad, medida, precio, estatus) VALUES (7, 'Camisa Tommy Hilfiger', 'Camisa Tommy Hilfiger TH-10', 200, 'U', 60, 'A');
INSERT INTO producto (id_categoria, nombre, descripcion, cantidad, medida, precio, estatus) VALUES (8, 'Camisa Ralph Laurent', 'Camisa Ralph Laurent RL-12', 80, 'U', 80, 'A');
INSERT INTO producto (id_categoria, nombre, descripcion, cantidad, medida, precio, estatus) VALUES (11, 'Pelota Nike', 'Pelota Nike N-16', 150, 'U', 40, 'A');
INSERT INTO producto (id_categoria, nombre, descripcion, cantidad, medida, precio, estatus) VALUES (12, 'Pelota Adidas', 'Pelota Adidas A-18', 250, 'U', 50, 'A');