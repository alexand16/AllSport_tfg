$(document).ready(function() {
    $('form').submit(function(e) {
        e.preventDefault();
        
        var nombre = $('#nombre').val();
        var apellidos = $('#apellidos').val();
        var telefono = $('#telefono').val();
        var email = $('#email').val();
        var fechaNacimiento = $('#fechaNacimiento').val();
        var observaciones = $('#observaciones').val();
        
        // Expresiones regulares para validar el formato del teléfono y el correo electrónico
        var telefonoPattern = /^[0-9]{9}$/;
        var emailPattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
        
        // Validar los campos aquí
        if (nombre.trim() === '') {
            toastr.error('El campo nombre es obligatorio');
            return;
        }
        
        if (apellidos.trim() === '') {
            toastr.error('El campo apellidos es obligatorio');
            return;
        }
        
        if (telefono.trim() === '') {
            toastr.error('El campo telefono es obligatorio');
            return;
        } else if (!telefonoPattern.test(telefono)) {
            toastr.error('El campo telefono tiene un formato invalido');
            return;
        }
        
        if (email.trim() === '') {
            toastr.error('El campo email es obligatorio');
            return;
        } else if (!emailPattern.test(email)) {
            toastr.error('El campo email tiene un formato invalido');
            return;
        }
        
        if (fechaNacimiento.trim() === '') {
            toastr.error('El campo fecha de nacimiento es obligatorio');
            return;
        }
        
        // Si todos los campos son válidos, enviar el formulario
        $('form')[0].submit();
    });
});
