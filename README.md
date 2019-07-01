#ReactiveDemo

Sample spring boot webflux application to pass header implicitly to backend services.

#### Start the application
```bash
mvn spring-boot:run
```

BackendServer class will start default backend server in port 8181.


#### Test using CURL
```bash
curl -v -H "client_id: test" -H "request_id: 1234" http://localhost:8080/users/1234
```