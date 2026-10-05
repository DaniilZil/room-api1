README

JDK version: [write your JDK version here]

Run command:
Windows:
mvnw.cmd spring-boot:run
Database

Table: room
- id - PRIMARY KEY
- name - room name
- capacity - number of people, from 1 to 20

Table: reservation
- id - PRIMARY KEY
- room_id - FOREIGN KEY referencing room(id)
- reserved_by - name of the person who made the reservation
- reservation_date - reservation date
- start_time - reservation start time
- end_time - reservation end time

Relationship:
One room can have many reservations.
Each reservation belongs to one room.

room (1) -------- (*) reservation


H2 Console

URL:
http://localhost:8080/h2-console

JDBC URL:
jdbc:h2:mem:testdb

User name:
sa

Password:
leave empty


ChatGPT was used to help understand SQL queries and database configuration.