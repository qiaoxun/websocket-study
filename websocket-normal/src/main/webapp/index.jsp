<html>
<body>

<button onclick="connect()">connect</button>
<button onclick="disconnect()">disconnect</button>

<table style="border: 1px;">
<tr>
<td id="price">--.--</td>
<td id="volume">--.--</td>
</tr>
</table>

</body>
</html>
<script>

var wsocket;

function onMessage(evt) {
    var arraypv = evt.data.split("/");
    console.info(arraypv)
    document.getElementById("price").innerHTML = arraypv[0];
    document.getElementById("volume").innerHTML = arraypv[1];
}

// connect to websocket server
function connect() {
    wsocket = new WebSocket("ws://localhost:8081/websocket-normal/dukeetf");
    wsocket.onmessage = onMessage;
}

// disconnect to websocket server
function disconnect() {
    wsocket.close();
}

</script>
