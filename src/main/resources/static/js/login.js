async function entrar() {

    let datos = {};

    datos.pasword = document.getElementById("txtpass").value;
    datos.email = document.getElementById("txtemail").value;

    const request = await fetch('api/login', {
        method: 'POST',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(datos)
    });
    const respuesta = await request.text();

    if(respuesta!='no'){
        localStorage.token = respuesta;
        localStorage.email = datos.email;
        window.location.href = 'usuarios.html';
    }else{
        alert('las credenciales son incorrectas');
    }

}
