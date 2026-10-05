-- Q1. Rooms for 6 or more people, largest first.
SELECT *
FROM room
WHERE capacity >= 6
ORDER BY capacity DESC;


-- Q2. Every reservation made by Mina.
SELECT *
FROM reservation
WHERE reserved_by = 'Mina';


-- Q3. Every reservation with the name of its room.
SELECT
    reservation.id,
    reservation.reserved_by,
    reservation.reservation_date,
    reservation.start_time,
    reservation.end_time,
    room.name AS room_name
FROM reservation
         JOIN room
              ON reservation.room_id = room.id;


-- Q4. Reservations for Seminar A on 6 October 2026.
SELECT
    reservation.id,
    reservation.reserved_by,
    reservation.reservation_date,
    reservation.start_time,
    reservation.end_time,
    room.name AS room_name
FROM reservation
         JOIN room
              ON reservation.room_id = room.id
WHERE room.name = 'Seminar A'
  AND reservation.reservation_date = '2026-10-06';

-- Q5. Number of reservations per room (JOIN).
SELECT room.name,
       COUNT (reservation.id) AS reservation_count
FROM room
         JOIN reservation
              ON room.id = reservation.room_id
GROUP BY room.id, room.name;

-- Q6. Same as Q5, but show 0 for rooms with no reservations.
SELECT room.name,
       COUNT (reservation.id) AS reservation_count
FROM room
         LEFT JOIN reservation
                   ON room.id = reservation.room_id
GROUP BY room.id, room.name;

--Q7. Rooms that have never been reserved.
SELECT
    room.id,
    room.name,
    room.capacity
FROM room
         LEFT JOIN reservation
                   ON room.id = reservation.room_id
WHERE reservation.id IS NULL;

--Q8. Rooms with more than two reservations.
SELECT
    room.name,
    COUNT(reservation.id) AS reservation_count
FROM room
         JOIN reservation
              ON room.id = reservation.room_id
GROUP BY room.id, room.name
HAVING COUNT(reservation.id) > 2;

-- Challenge. Which reservations in room 1 overlap 10:30–11:30 on 6 October 2026?
SELECT *
FROM reservation
WHERE room_id = 1
  AND reservation_date = '2026-10-06'
  AND start_time < '11:30:00'
  AND end_time > '10:30:00';