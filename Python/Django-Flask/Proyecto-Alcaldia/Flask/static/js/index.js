window.addEventListener('DOMContentLoaded', function() {
    // Agregar clase 'fade-in' a los elementos con la clase 'archivo'
    var archivos = document.getElementsByClassName('archivo');
    for (var i = 0; i < archivos.length; i++) {
        archivos[i].classList.add('fade-in');
    }
});