# Test application
Application allow `maxCount` request from one ip in `timeoutInSeconds`.
Return HTTP 200 or if limit exceeded HTTP 503.

## Build
1. `gradle build`
1. `docker-compose build`

## Run 

1. Set up `app.properties`
2. Run `docker-compose up`
3. Open [http://localhost:8080/hello](http://localhost:8080/hello)


 
 
 