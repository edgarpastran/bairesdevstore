<html>
<head>
	<title>Simple Store</title>
	<link href="resources/css/bootstrap.css" rel="stylesheet">
	<link href="resources/css/dataTables.bootstrap.min.css" rel="stylesheet">
	<link href="resources/css/screen.css" rel="stylesheet">
	<script src="resources/js/jquery.js"></script>
	<script src="resources/js/jquery.validate.min.js"></script>
	<script src="resources/js/jquery.numeric.min.js"></script>
	<script src="resources/js/pages/productos.js"></script>
</head>

<body class="col-md-10 col-md-offset-1">
	<!-- NAVBAR START -->
	<nav class="navbar navbar-inverse navbar-fixed-top">
		<div class="container-fluid">
			<!-- Brand and toggle get grouped for better mobile display -->
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"
					aria-expanded="false">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="#">Simple Store</a>
			</div>

			<!-- Collect the nav links, forms, and other content for toggling -->
			<div class="collapse navbar-collapse"
				id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav">
					<li><a href="index.html">Inicio</a></li>
					<li><a href="categorias.html">Categorias</a></li>
					<li class="active"><a href="productos.html">Productos<span
							class="sr-only">(current)</span></a></li>
					<li><a href="contacto.html">Contacto</a></li>
				</ul>
			</div>
			<!-- /.navbar-collapse -->
		</div>
		<!-- /.container-fluid -->
	</nav>
	<!-- NAVBAR END -->
	
	<!-- HEADER START -->
	<div id="header">
		<H2>Simple Store</H2>
	</div>
	<!-- HEADER END -->
	
	<!-- CONTENT START -->
	<div id="content">

		<!-- VENTANA START -->
		<div class="modal fade" id="ventana" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">&times;</button>
						<h4>Producto</h4>
					</div>
					<div class="modal-body">
						<div id="mensajesError"></div>
						<form id="formProducto" name="producto">
							<div class="form-group">
								<input type="hidden" id="idString" name="idString" />								
								</br>
								<label for="nombre">Nombre</label>
								<input type="text" class="form-control" id="nombre" name="nombre" placeholder="Nombre" maxlength="80" required/>
								</br>
								<label for="descripcion" placeholder="Descripcion" >Descripcion</label>
								<textarea class="form-control" rows="2" id="descripcion" name="descripcion" maxlength="200" ></textarea>
								</br>
								<label for="idCategoria">Categoria</label>																									
								<select id="idCategoria" name="idCategoria" class="form-control" >
									<option value="-1">Seleccione</option> 
									<#list model["categoriaList"] as category>
										<option value="${category.idString}">${category}</option> 
									</#list>
								</select>
								</br>
								<label for="cantidad">Cantidad</label>
								<input type="text" class="form-control" id="cantidad" name="cantidad" placeholder="Cantidad" maxlength="8" required/>
								</br>										
								<label for="medida">Medida</label>
								<select id="medida" name="medida" class="form-control" >
									<option value="-1">Seleccione</option> 
									<#list model["medidaList"] as medida>
										<option value="${medida.id}">${medida}</option> 
									</#list>
								</select>
								</br>
								<label for="precio">Precio</label>
								<input type="text" class="form-control" id="precio" name="precio" placeholder="Precio" maxlength="10" required/>
							</div>
						</form>
					</div>
					<div class="modal-footer">
						<button type="button" id="btnGuardar" class="btn btn-primary"
							onclick="onGuardar();"><i class="glyphicon glyphicon-ok"></i>&nbsp;Guardar</button>
						<button type="button" class="btn btn-danger" data-dismiss="modal"><i class="glyphicon glyphicon-remove"></i>&nbsp;Cerrar</button>
						</form>
					</div>
				</div>
			</div>
		</div>
		<!-- VENTANA END -->		
		
		<!-- BARRA DE HERRAMIENTAS START -->
		<div class="navbar navbar-default">
			<div class="container">
				<table class="col-md-10">
					<tr>
						<td>
							<a class="navbar-btn btn-info btn" data-toggle="modal"
							data-target="#ventana" onclick="onIncluir();"><i
							class="glyphicon glyphicon-plus"></i>&nbsp;Incluir</a></td>
						<td>
							<div id="mensajes" class="col-md-offset-1"></div>
						</td>
					</tr>
				</table>
			</div>			
		</div>
		<!-- BARRA DE HERRAMIENTAS END -->
		
		<!-- TABLA START -->
		<div>
			<table id="tabla" class="table table-striped table-bordered table-hover table-condensed">
				<thead>
					<tr>
						<th>Nombre</th>
						<th>Descripcion</th>
						<th>Categoria</th>
						<th>Cantidad</th>
						<th>Medida</th>
						<th>Precio</th>
						<th>Consultar</th>
						<th>Modificar</th>
						<th>Eliminar</th>
					</tr>
				</thead>
				<tbody>
					<#list model["productoList"] as producto>
					<tr>						
						<td><span id="nombre${producto.idString}">${producto.nombre}</span></td>
						<td><span id="descripcion${producto.idString}">${producto.descripcion}</span></td>
						<td><span id="categoria${producto.idString}">${producto.categoria.nombre}</span>
						<input type="hidden"
							id="idCategoria${producto.idString}"
							value="${producto.idCategoria}" />
						</td>
						<td><span id="cantidad${producto.idString}">${producto.cantidad}</span></td>
						<td><span id="medidaString${producto.idString}">${producto.medidaString}</span>
						<input type="hidden"
							id="medida${producto.idString}"
							value="${producto.medida}" />
						</td>
						<td><span id="precio${producto.idString}">${producto.precio}</span></td>
						<td><a class="btn-success btn" data-toggle="modal"
							data-target="#ventana" onclick="onConsultar('${producto.idString}');"><i
								class="glyphicon glyphicon-eye-open"></i></a></td>
						<td><a class="btn-warning btn" data-toggle="modal"
							data-target="#ventana" onclick="onModificar('${producto.idString}');"><i
								class="glyphicon glyphicon-edit"></i></a></td>
						<td><a id="btnEliminar${producto.idString}" class="btn-danger btn" data-toggle="confirmation" data-title="Estas seguro?" data-singleton="true" data-popout="true"
						data-href="javascript:onEliminar('${producto.idString}');"						
						data-btn-ok-label="Si" data-btn-ok-icon="glyphicon glyphicon-share-alt" data-btn-ok-class="btn-success" 
						data-btn-cancel-label="No" data-btn-cancel-icon="glyphicon glyphicon-ban-circle" data-btn-cancel-class="btn-danger">
						<i class="glyphicon glyphicon-trash"></i></a></td>						
					</tr>
					</#list>
				</tbody>
			</table>
		</div>
		<!-- TABLA END -->
	</div>
	<!-- CONTENT END -->
	
	
	<!-- JAVASCRIPT START -->
	<script src="resources/js/bootstrap.js"></script>
	<script src="resources/js/jquery.dataTables.min.js"></script>
	<script src="resources/js/dataTables.bootstrap.min.js"></script>
	<script src="resources/js/bootstrap-confirmation.js"></script>	
	<script type="text/javascript" class="init">		
		$('[data-toggle="confirmation"]').confirmation('hide');
	</script>
	<!-- JAVASCRIPT END -->

</body>
</html>
