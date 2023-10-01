## Setup  
### Run both projects or one by one  
 
- imperative (Port 7071) 
- reactive (Port 7072)



### Run the fake nodejs service  
The main idea of the service is to provide delayed response, so I could simulate blocking IO operation.
```
node tools/fake-http-service.js  
```

### Config the client and and run it

- line 3: configure the for loop so you could simulate x number of parallel request to one of the two running backend servers.  
- line 5: configure the end point
```
http.request(.......)
```

change the port or the path, so you could play with the ditternt servers or different endpoints.  
change the applocation.yaml property server.tomcat.threads.max and test it  


### Run the client  
```
time node tools/fake-http-client.js 
```

### Use the visual vm to see the number of created threads  
https://visualvm.github.io/download.html  


## Summary:  

Imperative:  
- 10 000 requests
- server.tomcat.threads.max = 15500
- total elapsed time - real 0m12,020s


Reactive: 
- 10 000 requests
- web client max connections = 10005
- total elapsed time - real 0m11,729s


Local reqest which returns the response with 3 sec. delay  

Imperative:  
- 5 000 requests
- server.tomcat.threads.max = 7500
- total elapsed time - real 0m4,791s
----  
- 10 000 requests
- server.tomcat.threads.max = 15500
- total elapsed time - real 0m19,550s
----  
- 15 000 requests
- server.tomcat.threads.max = 18000
- total elapsed time - real 0m35,867s
  
----  
- 20 000 requests
- server.tomcat.threads.max = 25500
- total elapsed time - was not able to return responses to all 20 000 requests


Reactive:  
- 5 000 requests
- total elapsed time - real 0m4,387s
----  
- 10 000 requests
- total elapsed time - real 0m4,956s
----  
- 15 000 requests
- total elapsed time - real 0m7,534s
----  
- 20 000 requests
- total elapsed time - real 0m16,406s
----  
- 25 000 requests
- total elapsed time - real 0m30,535s
----  
- 30 000 requests
- total elapsed time - was not able to return responses to all 30 000 requests

    