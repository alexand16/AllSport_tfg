$(document).ready(function() {
    $('form').submit(function(e) {
        e.preventDefault();
        var nombre = $('#nombre').val();
        var apellidos = $('#apellidos').val();
        var email = $('#email').val();
        var telefono = $('#telefono').val();
        var fechaNacimiento = $('#fechaNacimiento').val();
        var puntos = $('#puntos').val();
        
        // Validar campos obligatorios
        if (nombre.trim() === '' || apellidos.trim() === '' || email.trim() === '' || telefono.trim() === '' || fechaNacimiento.trim() === '' || puntos.trim() === '') {
            toastr.error('Todos los campos obligatorios deben ser completados.');
            return;
        }
        
        // Validar formato de email
        var emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
        if (!emailRegex.test(email)) {
            toastr.error('El formato del email es invalido.');
            return;
        }
        
        // Validar formato de teléfono
        var telefonoRegex = /^\d+$/;
        if (!telefonoRegex.test(telefono)) {
            toastr.error('El formato del telefono es invalido.');
            return;
        }
        
        // Validar caracteres peligrosos en campos
        var regex = /[<>]/;
        var campos = [nombre, apellidos, email, telefono, fechaNacimiento, puntos];
        for (var i = 0; i < campos.length; i++) {
            if (regex.test(campos[i])) {
                toastr.error('No se permiten caracteres especiales en los campos.');
                return;
            }
        }
        
        // Enviar el formulario si pasa todas las validaciones
        $('form').off('submit');
        $('form').submit();
    });
});
