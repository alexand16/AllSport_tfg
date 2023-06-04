$(document).ready(function() {
    $('form').submit(function(e) {
        e.preventDefault();
        
        // Validar usuario
        var usuario = $('#usuario').val();
        if (usuario.trim() === '') {
            toastr.error('El campo de usuario es obligatorio.');
            return;
        }
        if (contieneCaracteresPeligrosos(usuario)) {
            toastr.error('El campo de usuario contiene caracteres peligrosos.');
            return;
        }
        
        // Validar contraseña
        var contrasena = $('#pwd').val();
        if (contrasena.trim() === '') {
            toastr.error('El campo de contrasena es obligatorio.');
            return;
        }
        if (contieneCaracteresPeligrosos(contrasena)) {
            toastr.error('El campo de contrasena contiene caracteres peligrosos.');
            return;
        }
        
        // Validar nombre
        var nombre = $('#nombre').val();
        if (nombre.trim() === '') {
            toastr.error('El campo de nombre es obligatorio.');
            return;
        }
        
        // Validar apellidos
        var apellidos = $('#apellidos').val();
        if (apellidos.trim() === '') {
            toastr.error('El campo de apellidos es obligatorio.');
            return;
        }
        
        // Validar teléfono
        var telefono = $('#telefono').val();
        if (telefono.trim() === '') {
            toastr.error('El campo de telefono es obligatorio.');
            return;
        }
        if (!validarTelefono(telefono)) {
            toastr.error('El campo de telefono tiene un formato invalido.');
            return;
        }
        
        // Validar email
        var email = $('#email').val();
        if (email.trim() === '') {
            toastr.error('El campo de email es obligatorio.');
            return;
        }
        if (!validarEmail(email)) {
            toastr.error('El campo de email tiene un formato inválido.');
            return;
        }
        
        // Validar fecha de nacimiento
        var fechaNacimiento = $('#fechaNacimiento').val();
        if (fechaNacimiento.trim() === '') {
            toastr.error('El campo de fecha de nacimiento es obligatorio.');
            return;
        }
        
        // Enviar el formulario si pasa todas las validaciones
        $('form').off('submit');
        $('form').submit();
    });
});

function contieneCaracteresPeligrosos(texto) {
    var caracteresPeligrosos = ['<', '>', '&', "'", '"', '/', '\\', ';'];
    for (var i = 0; i < caracteresPeligrosos.length; i++) {
        if (texto.includes(caracteresPeligrosos[i])) {
            return true;
        }
    }
    return false;
}

function validarTelefono(telefono) {
    var patronTelefono = /^\d{9}$/; // Asume un formato de 9 dígitos
    return patronTelefono.test(telefono);
}

function validarEmail(email) {
    var patronEmail = /^\w+([.-]?\w+)*@\w+([.-]?\w+)*(\.\w{2,3})+$/;
    return patronEmail.test(email);
}
