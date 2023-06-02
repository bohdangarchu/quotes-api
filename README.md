## Usage
run the app with
```bash
./mvnw mn:run
```
run tests
```bash
./mvnw test
```

## Endpoints

- `GET /quotes?n={number}`: returns {number} of random quotes from Breaking Bad, Lucifer or GOT.


## Issues

- could not inject HttpClient using @Inject and @Client annotations

- testing the http endpoint doesn't work 
