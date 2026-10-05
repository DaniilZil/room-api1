INSERT INTO room (id, name, capacity) VALUES
                                          (1, 'Seminar A', 8),
                                          (2, 'Meeting B', 4),
                                          (3, 'Conference C', 12);

INSERT INTO reservation
(id, room_id, reserved_by, reservation_date, start_time, end_time)
VALUES
    (1, 1, 'Mina', '2026-10-06', '09:30:00', '10:45:00'),
    (2, 1, 'Alex', '2026-10-06', '10:45:00', '11:15:00'),
    (3, 1, 'Mina', '2026-10-06', '11:15:00', '12:00:00'),
    (4, 1, 'Jisoo', '2026-10-07', '13:00:00', '14:00:00'),
    (5, 2, 'Daniel', '2026-10-06', '15:00:00', '16:00:00');