$(document).ready(function() {
    $('form').submit(function(e) {
        e.preventDefault();
        var contenido = $('#contenido').val();
        
        // Validar campo obligatorio
        if (contenido.trim() === '') {
            toastr.error('El campo de contenido es obligatorio.');
            return;
        }
        
        // Validar caracteres peligrosos en el campo
        var regex = /[<>]/;
        if (regex.test(contenido)) {
            toastr.error('No se permiten caracteres peligrosos en el campo.');
            return;
        }
        
        // Enviar el formulario si pasa todas las validaciones
        $('form').off('submit');
        $('form').submit();
    });
});
