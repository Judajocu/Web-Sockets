(function (window, document, JSON) {
    'use strict';

    var url="ws://" + location.hostname + ":" + location.port +"/data/websocket",
        ws =new WebSocket(url),
        mensajes=document.getElementById('concersacion'),
        boton=document.getElementById('envio'),
        nombre=document.getElementById('usuario'),
        mensaje=document.getElementById('mensaje');

    ws.onmessage=onMenssage;
    ws.onopen= onOpen;
    ws.onclose= onClose;
    boton.addEventListener('click',enviar);

    function onMenssage(evt) {
        var obj=JSON.parse(evt.data),
            msg='Nombre: '+obj.nombre+' dice: '+obj.mensaje;
        mensajes.innerHTML +='<br/>'+msg;
    }

    function onOpen() {
        console.log('conectado...');
    }

    function onClose() {
        console.log('Desconecct...');
    }

    function enviar() {
        var msg={
            nombre: nombre.value,
            mensaje:mensaje.value
        };

        ws.send(JSON.stringify(msg));
    }

})(window, document, JSON);