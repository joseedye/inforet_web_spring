// Call the dataTables jQuery plugin
$(document).ready(function () {
    cargarusuarios();

    $('#cargausuarios').DataTable();
actualizarnombre();
});

function actualizarnombre(){
    document.getElementById('nombre-usuario').outerHTML = localStorage.email;
}


async function cargarusuarios() {

    const request = await fetch('api/usuarios', {
        method: 'GET',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
            'autorizacion':localStorage.token
        }
    });

    const usuariossalida = await request.json();
    let total = "";


    for (const usuariossalidaElement of usuariossalida) {
        let botoneliminar = '<a href="#" onclick="borrar('+ usuariossalidaElement.id  +')" class="btn btn-danger btn-circle btn-sm"> <i class="fas fa-trash"></i> </a>  </td>';
        let insert = ' <tr>' +
            '        <td>' + usuariossalidaElement.id + '</td>' +
            '        <td>' + usuariossalidaElement.nombre + ' ' + usuariossalidaElement.apellido + '</td>' +
            '        <td>' + usuariossalidaElement.email + '</td>' +
            '        <td>' + usuariossalidaElement.telefono + '</td>' +
            '        <td>' +  botoneliminar +'</tr>';

        total += insert;
    }
    document.querySelector('#cargausuarios tbody').outerHTML = total;
    console.log(usuariossalida);

}

async function borrar(id){

    if(!confirm("¿desea eliminar este usuario?")){
        return;
    }
    const request = await fetch('api/usuarios/'+id , {
        method: 'DELETE',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
            'autorizacion':localStorage.token
        }
    });
location.reload();
}