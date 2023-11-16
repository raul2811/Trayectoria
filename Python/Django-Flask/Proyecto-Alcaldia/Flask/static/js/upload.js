$(document).ready(function() {
    $('form').on('submit', function(event) {
        event.preventDefault();
        var form = $(this);
        var progressBar = $('.progress-bar');
        var uploadMessage = $('#upload-message');
        var formData = new FormData(form[0]);

        $.ajax({
            xhr: function() {
                var xhr = new window.XMLHttpRequest();
                xhr.upload.addEventListener('progress', function(e) {
                    if (e.lengthComputable) {
                        var percent = Math.round((e.loaded / e.total) * 100);
                        progressBar.width(percent + '%').text(percent + '%');
                    }
                });
                return xhr;
            },
            type: 'POST',
            url: form.attr('action'),
            data: formData,
            processData: false,
            contentType: false,
            success: function(response) {
                progressBar.width('0%').text('0%');
                uploadMessage.text('Archivo subido exitosamente.');
                // Manejar la respuesta del servidor después de que se haya completado la carga
            },
            error: function(xhr, status, error) {
                progressBar.width('0%').text('0%');
                // Manejar el error en caso de que ocurra durante la carga
            }
        });
    });
});