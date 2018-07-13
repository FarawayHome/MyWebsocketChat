/**
 * Created by Soyal on 2015/10/25.
 */
var ws = new Socket("ws://localhost:8080/MyWebSocketChat/webSocketServer",function() {
    console.log("success");
})