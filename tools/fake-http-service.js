const http = require("http");
const host = "localhost";
const port = 7070;

let incomingRequests = 0;

const requestListener = function(req, res) {
  console.log("request received: " + (++incomingRequests));

  setTimeout(() => {
    res.writeHead(200);
    res.end("Hello from Server !!!");
  }, 3_000);

}

const server = http.createServer(requestListener);
server.listen(port, host, () => {
  console.log(`Server is running on http://${host}:${port}`);
});