$(document).ready(function() {
    $('form').submit(function(e) {
        e.preventDefault();
        
        // Validar usuario
        var usuario = $('input[name="usuario"]').val();
        if (usuario.trim() === '') {
            toastr.error('El campo de usuario es obligatorio.');
            return;
        }
        if (contieneCaracteresPeligrosos(usuario)) {
            toastr.error('El campo de usuario contiene caracteres peligrosos.');
            return;
        }
        
        // Validar contraseña
        var contrasena = $('input[name="contrasena"]').val();
        if (contrasena.trim() === '') {
            toastr.error('El campo de contraseña es obligatorio.');
            return;
        }
        if (contieneCaracteresPeligrosos(contrasena)) {
            toastr.error('El campo de contraseña contiene caracteres peligrosos.');
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
