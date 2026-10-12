# Room API

A Spring Boot REST API for managing rooms using MySQL and Spring Data JPA.

## Requirements

- Java 17
- MySQL
- Gradle

## Database Setup

The application uses the MySQL database `roomdb`.

Create the database and application user in MySQL:

```sql
CREATE DATABASE roomdb;

CREATE USER 'roomapp'@'localhost'
IDENTIFIED BY 'roomapp1234';

GRANT ALL PRIVILEGES ON roomdb.* TO 'roomapp'@'localhost';
```

The application connects to MySQL using:

- Database: `roomdb`
- Username: `roomapp`
- Password: `roomapp1234`

The MySQL root password is not stored anywhere in the project.

## Reset the Database

The file `reset.sql` is located in the root of the repository.

It drops the existing `reservation` and `room` tables, creates them again, and inserts the initial sample data:

- 3 rooms
- 5 reservations

Before testing the API, run `reset.sql` so that the requests start from the expected database state.

From the command line:

```bash
mysql -u roomapp -p roomdb < reset.sql
```

When asked for the password, enter:

```text
roomapp1234
```

You can also open `reset.sql` in MySQL Workbench and execute it there.

## Run the Application

On Windows:

```bash
gradlew.bat bootRun
```

The application starts on:

```text
http://localhost:8080
```

## JPA

`Room` is a JPA entity and `RoomRepository` extends:

```java
JpaRepository<Room, Long>
```

Hibernate schema generation is disabled. The project uses:

```properties
spring.jpa.hibernate.ddl-auto=validate
```

This means Hibernate validates the existing MySQL schema instead of creating or modifying the tables.

## Search and Count

Room search is performed using Spring Data JPA query methods in `RoomRepository`.

The service does not use Java streams or filters to search rooms. Filtering is done by MySQL, so Hibernate generates SQL containing `WHERE` for the search requests.

The room count uses:

```java
roomRepository.count();
```

This produces a SQL `SELECT COUNT` query.

## 409 Conflict

The service layer converts the database constraint violation into `409 Conflict` because the service layer handles the business operation of deleting a room and can explain that a room with existing reservations cannot be deleted.

Example:

```http
DELETE /api/rooms/1
```

If room 1 has reservations, the response is:

```text
409 Conflict
```

instead of `500 Internal Server Error`.

## PUT Request 8

Request 8 updates an existing room.

Hibernate prints an SQL `UPDATE` statement when this request is executed.

Replace the SQL below with the exact SQL printed by Hibernate after running request 8:

```sql
-- REPLACE THIS WITH THE EXACT SQL FROM YOUR HIBERNATE CONSOLE
update room
set capacity=?, name=?
where id=?
```

This SQL updates the stored values of an existing room identified by its ID.

## API Tests

The file `api.http` contains requests 1 to 14 and the additional DELETE request used to test `409 Conflict`.

After running `reset.sql`, execute the requests from the beginning because later requests may change the database state.

The API uses the following HTTP status codes:

- `200 OK` — successful GET or PUT
- `201 Created` — successful POST
- `204 No Content` — successful DELETE
- `400 Bad Request` — invalid request data
- `404 Not Found` — requested room does not exist
- `409 Conflict` — room cannot be deleted because it has reservations

## Before Submission

Clone the repository into a new folder and test the project using only the instructions in this README.

Example:

```bash
git clone YOUR_REPOSITORY_URL
cd room-api1
```

Then:

1. Create the MySQL database and user.
2. Run `reset.sql`.
3. Start the Spring Boot application.
4. Run requests 1 to 14 in `api.http`.
5. Test the additional `409 Conflict` DELETE request.
6. Check that Hibernate prints `WHERE` for search requests 2, 3, and 4.
7. Check that Hibernate prints `SELECT COUNT` for request 14.

## AI Use

AI tools were used to help understand the assignment, review the implementation, and prepare documentation.
