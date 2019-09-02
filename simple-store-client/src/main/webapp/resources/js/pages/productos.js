var tabla;
$(document).ready(function() {
	tabla = 
	$('#tabla').DataTable({
		"language": {
            "lengthMenu": "Mostrar _MENU_ registros por pagina",
            "zeroRecords": "No se encontraron registros",
            "info": "Mostrando registros del _START_ al _END_ de un total de _TOTAL_ registros",
            "infoEmpty": "No hay registros disponibles",
            "infoFiltered": "(filtrado de _MAX_ registros totales)",
            "search": "Buscar:",
            "paginate": {
        		"first":    "Primero",
        		"last":     "Ultimo",
        		"next":     "Siguiente",
        		"previous": "Anterior"
        	}
        }
	});
	$('#formProducto').validate();
	$('#cantidad').numeric();
	$('#precio').numeric('.');
});

function onIncluir() {
	disableInputs(false);
	$('#idString').val("");	
	$('#nombre').val("");
	$('#descripcion').val("");
	$('#idCategoria').val("-1");
	$('#cantidad').val("0");
	$('#medida').val("-1");
	$('#precio').val("0.00");
}

function onModificar(id) {
	disableInputs(false);
	$('#idString').val(id);	
	$('#nombre').val($("#nombre"+id).text());
	$('#descripcion').val($("#descripcion"+id).text());
	$('#idCategoria').val($("#idCategoria"+id).val());	
	$('#cantidad').val(quitarPuntosMiles($("#cantidad"+id).text()));
	$('#medida').val($("#medida"+id).val());
	$('#precio').val(quitarPuntosMilesYCambiarPorPuntoDecimal($("#precio"+id).text()));
}

function onConsultar(id) {
	onModificar(id);
	disableInputs(true);
}

function onGuardar() {
	if (validar()) {
		if ($('#idString').val().length > 0 ) {
			onEditar();
		}
		else {
			onAgregar();
		}
	}
}

function validar() {
	mensaje = "";
	inputs = ["nombre", "descripcion", "idCategoria", "cantidad", "medida", "precio"];
	mensajes = ["el Nombre", "la Descripcion", "la Categoria", "la Cantidad", "la Medida", "el Precio"];
	for (i=0; i<inputs.length; i++) {
		if (!$('#'+inputs[i]).val() || parseInt($('#'+inputs[i]).val()) <= 0) {
			mensaje += (mensaje.length>0?", ":"")+mensajes[i];
		}
	}
	if (mensaje.length > 0) {
		$('#mensajesError').
		append('<div class="alert alert-danger fade in"><a href="#" class="close" data-dismiss="alert">&times;</a><strong>Debe indicar:&nbsp;</strong>'+mensaje+'</div>');
		return false;
	}
	return true;
}

function onAgregar() {
	$.ajax({
		beforeSend: function() {
			
		},
		url: "productos/incluir.html",
		type: "POST",
		data: $("#formProducto").serialize(),
		success: function(response) {
			if (response.indexOf("error:") != 0) {
				var producto = JSON.parse(response);
				agregarFila(producto);
				mostrarMensajeExito("Item incluido satisfactoriamente!!!");
			}
			else {
				mostrarMensajeError(response.substring(6));
 			}
		},
		error: function(jqXHR, estado, error) {
			mostrarMensajeError(error);
		},
		complete: function(jqXHR, estado) {
			$('#ventana').modal('hide');
		},
		timeout: 10000
	});
}

function onEditar() {
	$.ajax({
		beforeSend: function() {
			
		},
		url: "productos/modificar.html",
		type: "POST",
		data: $("#formProducto").serialize(),
		success: function(response) {
			if (response.indexOf("error:") != 0) {
				var producto = JSON.parse(response);
				eliminarFila(producto.id);
				agregarFila(producto);
				mostrarMensajeExito("Item modificado satisfactoriamente!!!");
			}
			else {
				mostrarMensajeError(response.substring(6));
 			}
		},
		error: function(jqXHR, estado, error) {
			mostrarMensajeError(error);
		},
		complete: function(jqXHR, estado) {
			$('#ventana').modal('hide');
		},
		timeout: 10000
	});
}

function onEliminar(id) {	
	$.ajax({
		beforeSend: function() {
			
		},
		url: "productos/eliminar/"+id+".html",
		type: "DELETE",
		data: "id="+id,
		success: function(response) {
			if (response == "ok") {
				eliminarFila(id);
				mostrarMensajeExito('Item eliminado satisfactoriamente!!!');
			}
			else {
				mostrarMensajeError(response);
			}
		},
		error: function(jqXHR, estado, error) {
			mostrarMensajeError(error);
		},
		timeout: 10000
	});
	
}

function agregarFila(producto) {
	tabla.row.add( [
        '<td><span id="nombre'+producto.idString+'">'+producto.nombre+'</span></td>',
        '<td><span id="descripcion'+producto.idString+'">'+producto.descripcion+'</span></td>',
        '<td><span id="categoria'+producto.idString+'">'+producto.categoria.nombre+'</span><input type="hidden" id="idCategoria'+producto.idString+'" value="'+producto.idCategoria+'" /></td>',
        '<td><span id="cantidad'+producto.idString+'">'+colocarPuntosMiles(producto.cantidad)+'</span></td>',
        '<td><span id="medidaString'+producto.idString+'">'+producto.medidaString+'</span><input type="hidden" id="medida'+producto.idString+'" value="'+producto.medida+'" /></td>',
        '<td><span id="precio'+producto.idString+'">'+colocarPuntosMilesYComaDecimal(producto.precio)+'</span></td>',
        '<td><a class="btn-success btn" data-toggle="modal" data-target="#ventana" onclick="onConsultar(\''+producto.idString+'\');"><i class="glyphicon glyphicon-eye-open"></i></a></td>',
        '<td><a class="btn-warning btn" data-toggle="modal" data-target="#ventana" onclick="onModificar(\''+producto.idString+'\');"><i class="glyphicon glyphicon-edit"></i></a></td>',
        '<td><a id="btnEliminar'+producto.idString+'" class="btn-danger btn" data-toggle="confirmation" data-title="Estas seguro?" data-singleton="true" data-popout="true" data-href="javascript:onEliminar(\''+producto.idString+'\');" data-btn-ok-label="Si" data-btn-ok-icon="glyphicon glyphicon-share-alt" data-btn-ok-class="btn-success" data-btn-cancel-label="No" data-btn-cancel-icon="glyphicon glyphicon-ban-circle" data-btn-cancel-class="btn-danger"> <i class="glyphicon glyphicon-trash"></i></a></td>'
    ] ).draw();
	$('[data-toggle="confirmation"]').confirmation('hide');
}

function colocarPuntosMiles(numero) {
	numero = numero.toString().split('').reverse().join('').replace(/(?=\d*\.?)(\d{3})/g,'$1.');
	numero = numero.split('').reverse().join('').replace(/^[\.]/,'');
	return numero;
}

function quitarPuntosMiles(numero) {
	while (numero.toString().indexOf(".") >= 0) {
		numero = numero.toString().replace('.', '');
	}
	return numero;
}

function colocarPuntosMilesYComaDecimal(numero) {
	num = numero.toString().replace('.', ',');
	index = num.indexOf(',');
	entera = colocarPuntosMiles(num.substr(0, index));
	decimal = num.substring(index);
	return entera+decimal;
}

function quitarPuntosMilesYCambiarPorPuntoDecimal(numero) {
	numero = quitarPuntosMiles(numero);
	numero = numero.replace(',', '.');
	return numero;
}

function eliminarFila(id) {
	fila = $('#btnEliminar'+id).closest("tr")[0];
	$(fila).addClass('selected');
	tabla.row('.selected').remove().draw( false );
}

function disableInputs(value) {	
	$('#nombre').prop('disabled', value);
	$('#descripcion').prop('disabled', value);
	$('#idCategoria').prop('disabled', value);
	$('#cantidad').prop('disabled', value);
	$('#medida').prop('disabled', value);
	$('#precio').prop('disabled', value);
	$('#btnGuardar').prop('disabled', value);
}

function mostrarMensajeExito(mensaje) {
	$('#mensajes').
	append('<div class="navbar-btn alert alert-success fade in"><a href="#" class="close" data-dismiss="alert">&times;</a><strong>Exito:&nbsp;</strong>'+mensaje+'</div>');
}

function mostrarMensajeError(mensaje) {
	$('#mensajes').
	append('<div class="navbar-btn alert alert-danger fade in"><a href="#" class="close" data-dismiss="alert">&times;</a><strong>Error:&nbsp;</strong>'+mensaje+'</div>');
}