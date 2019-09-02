<!DOCTYPE html>
<!-- saved from url=(0052)http://getbootstrap.com/2.3.2/examples/carousel.html -->
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta charset="utf-8">
<title>Simple Store</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">

<!-- Le styles -->
<link href="resources/css/bootstrap.css" rel="stylesheet">
<link href="resources/css/bootstrap-responsive.css" rel="stylesheet">
<link href="resources/css/index.css" rel="stylesheet">
</head>

<body>
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
					<li class="active"><a href="index.html">Inicio<span
							class="sr-only">(current)</span></a></li>
					<li><a href="categorias.html">Categorias</a></li>
					<li><a href="productos.html">Productos</a></li>
					<li><a href="contacto.html">Contacto</a></li>
				</ul>
			</div>
			<!-- /.navbar-collapse -->
		</div>
		<!-- /.container-fluid -->
	</nav>

	<!-- Carousel
    ================================================== -->
	<div id="myCarousel" class="carousel slide">
		<div class="carousel-inner">
			<div class="item">
				<img src="resources/img/slide-01.jpg" alt="">
				<div class="container">
					<div class="carousel-caption">
						<h1>Categorias.</h1>
						<p class="lead">Incluye, Consulta, Modifica y Elimina las
							Categorias de tu Almacen en linea de forma rapida.</p>
						<a class="btn btn-large btn-primary" href="categorias.html">Ver
							Categorias</a>
					</div>
				</div>
			</div>
			<div class="item active">
				<img src="resources/img/slide-02.jpg" alt="">
				<div class="container">
					<div class="carousel-caption">
						<h1>Productos.</h1>
						<p class="lead">Incluye, Consulta, Modifica y Elimina los
							Productos de tu Almacen en linea de forma rapida.</p>
						<a class="btn btn-large btn-primary" href="productos.html">Ver
							Productos</a>
					</div>
				</div>
			</div>
			<div class="item">
				<img src="resources/img/slide-03.jpg" alt="">
				<div class="container">
					<div class="carousel-caption">
						<h1>Contacto.</h1>
						<p class="lead">Ponte en contacto con nosotros para recibir
							mas informacion acerca de nuestro software.</p>
						<a class="btn btn-large btn-primary" href="contacto.html">Hablemos</a>
					</div>
				</div>
			</div>
		</div>
		<a class="left carousel-control" href="index.html#myCarousel"
			data-slide="prev"><</a> <a class="right carousel-control"
			href="index.html#myCarousel" data-slide="next">></a>
	</div>
	<!-- /.carousel -->

	<!-- Marketing messaging and featurettes
    ================================================== -->
	<!-- Wrap the rest of the page in another container to center all the content. -->
	<div class="container marketing">
		<!-- Three columns of text below the carousel -->
		<div class="row">
			<div class="col-md-4" align="center">
				<img class="img-circle" data-src="holder.js/140x140" alt="140x140"
					src="resources/img/categorias.png"
					style="width: 140px; height: 140px;">
				<h2>Categorias</h2>
				<p>Incluye, Modifica, Consulta y Elimina las Categorias de tu
					Almacen en linea de forma rapida.</p>
				<p>
					<a class="btn" href="categorias.html">Ver Categorias</a>
				</p>
			</div>
			<div class="col-md-4" align="center">
				<img class="img-circle" data-src="holder.js/140x140" alt="140x140"
					src="resources/img/productos.png"
					style="width: 140px; height: 140px;">
				<h2>Productos</h2>
				<p>Incluye, Modifica, Consulta y Elimina los Productos de tu
					Almacen en linea de forma rapida.</p>
				<p>
					<a class="btn" href="productos.html">Ver Productos</a>
				</p>
			</div>
			<div class="col-md-4" align="center">
				<img class="img-circle" data-src="holder.js/140x140" alt="140x140"
					src="resources/img/contacto.png"
					style="width: 140px; height: 140px;">
				<h2>Contacto</h2>
				<p>Ponte en contacto con nosotros para recibir mas informacion
					acerca de nuestro software.</p>
				<p>
					<a class="btn" href="contacto.html">Hablemos</a>
				</p>
			</div>
		</div>
		<!-- /.row -->

		<!-- FOOTER -->
		<div class="navbar navbar-default navbar-fixed-bottom">
			<div class="container">
				<p class="pull-right">
					<a href="index.html#">Ir arriba</a>
				</p>
				<p class="muted credit">
					Software promovido por <a href="https://www.linkedin.com/in/edgarpastran/?locale=en_US">Edgar Pastran Cordero</a>
					- 2015
				</p>
			</div>
		</div>
		
	</div>
	<!-- /.container -->

	<!-- Le javascript
    ================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->
	<script src="resources/js/jquery.js"></script>
	<script src="resources/js/bootstrap-transition.js"></script>
	<script src="resources/js/bootstrap-alert.js"></script>
	<script src="resources/js/bootstrap-modal.js"></script>
	<script src="resources/js/bootstrap-dropdown.js"></script>
	<script src="resources/js/bootstrap-scrollspy.js"></script>
	<script src="resources/js/bootstrap-tab.js"></script>
	<script src="resources/js/bootstrap-tooltip.js"></script>
	<script src="resources/js/bootstrap-popover.js"></script>
	<script src="resources/js/bootstrap-button.js"></script>
	<script src="resources/js/bootstrap-collapse.js"></script>
	<script src="resources/js/bootstrap-carousel.js"></script>
	<script src="resources/js/bootstrap-typeahead.js"></script>
	<script>
      !function ($) {
        $(function(){
          // carousel demo
          $('#myCarousel').carousel()
        })
      }(window.jQuery)
    </script>
	<script src="resources/js/holder.js"></script>

</body>
</html>