# Crypto Price Tracking and Portfolio App

# Project Overview

In this project, users can:

- Register on the system (users will receive a welcome email upon registration).
- Add any cryptocurrency to their watchlist.
- Create a portfolio by entering their trading transactions for cryptocurrencies.
- View their trading history.

## Role Management

- **Admin**
- **User**

### Admin Features:
- View all users.
- Update user roles.
- Delete all users.
- Delete specific users.
- and all user features.

### User Features:
- Add any cryptocurrency to their watchlist (must be listed on Binance exchange).
- Create a portfolio by adding cryptocurrencies they trade.
- View their trading history.
- Update their own information.

## Features

- Sending mails
- JWT based authentication and authorization.
- RESTful APIs with Spring Boot Web and Security.
- Exception handling for robust error management.
- AOP logging for method entry, exit and exception tracking.
- Database integration with Spring Data JPA and PostgreSQL.
- ModelMapper for object mapping.
- OpenAPI specification for standardized API interaction.

## Technologies
* Java 17
* Spring Boot 3.0.5
    * Mail
    * JPA Repository
    * Lombok
    * Model Mapper
    * AOP
* JWT
* Logging
* PostgreSQL
* Open API

## Getting Started
To get started with this project, you will need to have the following installed on your local machine:

* JDK 14+
* Maven 3+

To build and run the project, follow these steps:

* Clone the repository: `https://github.com/azizCan10/crypto-price-tracking-and-portfolio-app.git`
* FE repository: [crypto-price-tracking-and-portfolio-app-fe](https://github.com/azizCan10/crypto-price-tracking-and-portfolio-app-fe)
* Navigate to the project directory
* Open a db in PostgreSQL called crypto-price-tracking
* in application.yml file, set your own values

* Build the project: `mvn clean install`
* Run the project: `mvn spring-boot:run`

-> The application will be available at http://localhost:8080

-> Swagger will be available at http://localhost:8080/swagger-ui/index.html