function eliminar(id){
    swal({
        title: "¿Esta seguro?",
        text: "¿Realmente desea eliminar este registro?",
        icon: "warning",
        buttons: true,
        dangerMode: true,
    })
    .then((willDelete) => {
        if (willDelete) {
            location.href="/eliminarEmpleado/"+id;   
        } else {
            // Código para manejar la cancelación
        }
    })
    .catch(() => {
        window.location.href = "/error-eliminar-empleado";
    });
}
function eliminarTarea(id){
	swal({
	  title: "¿Esta seguro?",
	  text: "¿Realmente desea eliminar este registro?",
	  icon: "warning",
	  buttons: true,
	  dangerMode: true,
	})
	.then((willDelete) => {
	  if (willDelete) {
	    location.href="/eliminarTarea/"+id;   
	  } else {
	    
	  }
	});
}
