$(document).ready(function() {
    $('form').submit(function(e) {
        e.preventDefault();
        
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
        
        // Validar email
        var email = $('#email').val();
        if (email.trim() === '') {
            toastr.error('El campo de email es obligatorio.');
            return;
        }
        var emailPattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
        if (!emailPattern.test(email)) {
            toastr.error('El campo de email no tiene un formato válido.');
            return;
        }
        
        // Validar fecha de nacimiento
        var fechaNacimiento = $('#fechaNacimiento').val();
        if (fechaNacimiento.trim() === '') {
            toastr.error('El campo de fecha de nacimiento es obligatorio.');
            return;
        }
        
        // Validar teléfono
        var telefono = $('#telefono').val();
        if (telefono.trim() === '') {
            toastr.error('El campo de teléfono es obligatorio.');
            return;
        }
        var telefonoPattern = /^\d{9}$/;
        if (!telefonoPattern.test(telefono)) {
            toastr.error('El campo de teléfono no tiene un formato válido. Debe contener 9 dígitos.');
            return;
        }
        
        // Validar formato de imagen
        var file = $('#file').val();
        if (file !== '') {
            var ext = file.split('.').pop().toLowerCase();
            if (['png', 'jpg', 'jpeg', 'gif'].indexOf(ext) === -1) {
                toastr.error('La imagen solo admite los formatos PNG, JPG, JPEG y GIF.');
                return;
            }
        }
        
        // Enviar el formulario si pasa todas las validaciones
        $('form').off('submit');
        $('form').submit();
    });
});
