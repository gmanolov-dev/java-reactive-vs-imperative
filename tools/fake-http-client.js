const http = require('http');
let totalNumberOfResponses = 0;
for(var i = 0; i< 10_000; i ++) {
    const a = i;
    http.request("http://localhost:7071/delayed-response-from-remote", (res) => {
    // http.request("http://localhost:7071/delayed-response-from-local", (res) => {
    // http.request("http://localhost:7072/delayed-response-from-remote", (res) => {    
    // http.request("http://localhost:7072/delayed-response-from-local", (res) => {
        let data = ''
         
        res.on('data', (chunk) => {
            data += chunk;
        });
        
        // Ending the response 
        res.on('end', () => {
            // console.log(data);
           console.log(a + " - " + data);
           console.log("Total number of responses: " + (++ totalNumberOfResponses) );
        });
           
    }).on("error", (err) => {
        console.log("Error: ", err)
    }).end();
}