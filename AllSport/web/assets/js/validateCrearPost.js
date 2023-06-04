$(document).ready(function() {
    $('form').submit(function(e) {
        e.preventDefault();
        var titulo = $('#titulo').val();
        var contenido = $('#contenido').val();
        
        // Validar campos obligatorios
        if (titulo.trim() === '' || contenido.trim() === '') {
            toastr.error('Todos los campos obligatorios deben ser completados.');
            return;
        }
        
        // Validar caracteres peligrosos en campos
        var regex = /[<>]/;
        var campos = [titulo, contenido];
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
