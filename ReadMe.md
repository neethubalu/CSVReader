# CSV READER

This application is the backend service for a mortgage platform which supports CRUD operations using RESTful
webservices. The application is developed using Java, Spring Boot and in-memory H2 database. Following are the major
features of the application.

* fetch all data
* upload data
* fetch by code
* delete all data

### Customer

| Method | Path                    | Description                 |
|--------|-------------------------|-----------------------------|
| POST   | csv/reader/upload       | user can upload a csv file  |
| GET    | csv/reader/fetch/all    | Retrieves full data         |
| GET    | csv/reader/fetch/{code} | Retrieves data using `code` |
| DELETE | csv/reader/delete/      | Deletes all data            |

## Steps to install

1. Pull the repository into your local drive.
2. Project is built on `JDK 17` and `Spring boot 3.2.3`
3. `application.yaml` is kept along with the project
4. This project uses `In-memory database - H2 `
5. Run the application using mvn `spring-boot:run` or from your IDE.

## Postman Collection

A postman collection `CSV_Reader_Postman_Collection.json` is included in the projects folder which could be used to test
and validate once the API is up and running.

