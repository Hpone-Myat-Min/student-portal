# Student Portal Microservice

A Java SpringBoot-based microservice that provides student operations/ information such as course enrollment, student information and graduation eligibility. This portal is developed according to the microservice architecture pattern via RESTful APIs, it integrates with two other microservices - Finance and Library.

## Features
- View all available courses
- Register as student and enroll into courses
- View student information and enrolled courses
- Check graduation eligibility based on financial status
- Dockerized with .env and yaml file support for configuration

## Microservice Architecture
This microservice communicates with:
- Finance Service: Issues invoices for course enrollment or overdue books and accept payments
- Library Service: Manages book borrowing and tracks overdue books

## Tech Stack
- Java 17
- Spring Boot
- Spring Data JPA
- Docker & Docker Compose
- RESTful APIs
- MariaDB

## Getting Started
1. Clone the repository.
``git clone https://github.com/Hpone-Myat-Min/student-portal.git
cd student-portal``
2. Create .env File
Create a .env file at the project root:
```angular2html
DB_Name=
DB_APPLICATION_USER=
DB_APPLICATION_PASSWORD=
DB_ROOT_PASSWORD=
```
``Adjust these variable values to match your environment.``

3. Build the Project
``./mvnw clean install``

4. Run with Docker Compose
``docker-compose up --build``





