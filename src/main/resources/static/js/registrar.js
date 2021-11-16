async function registrarusuarios() {

    let datos = {};
    datos.nombre = document.getElementById("txtnombre").value;
    datos.apellido = document.getElementById("txtapellido").value;
    datos.pasword = document.getElementById("txtpass").value;
    datos.email = document.getElementById("txtemail").value;

    let contra2 = document.getElementById("txtrepetir").value;
    if(contra2 !== datos.pasword){
        alert("las contraseñas son diferentes");
        return;
    }

    const request = await fetch('api/usuarios', {
        method: 'POST',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(datos)
    });

    alert("la cuenta fue creada con exito");
    window.location.href = 'login.html';
}
