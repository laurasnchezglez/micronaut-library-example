# micronaut-library-example
Example of api-rest in Micronaut

This project uses the following technologies:
1. Java 1.8
2. Micronaut 1.3.3
3. Micronaut-data 1.0.2
4. Lombok 1.16
5. Junit 5
6. Database H2
7. Micronaut-security for basicAuthentication
8. Gradle


To **run the application** with gradle:
1. Clone the project
2. Inside the project folder, execute ./gradlew run

This is a very simple project: just two domain classes: books and genres. The repositories of these domains extend from the CrudRepository (Micronaut-data, very similar to spring boot data). This repository is very useful for simple domains. It can be improved with other queries (@query for include hsql code). 
For security, it has an authprovider, which authenticates just with login/password. Authentication must be activated in application.yml. 
For inserting initial data, and ApplicationEventListener were included with some inserts.

The controllers offers (authenticated with user/pwd admin/admin):

1. all books: GET http://localhost:8080/books
2. some book: GET http://localhost:8080/books/{id}
3. save book: PUT http://localhost:8080/books - request body {"name": "Little women", "isbn": "123"}
4. get book from genre name: GET http://localhost:8080/books/genre/{genrename}
5. all genres: GET http://localhost:8080/genres
6. some genre: GET http://localhost:8080/genres/{id}
