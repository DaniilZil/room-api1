

CREATE TABLE room (
                      id INT PRIMARY KEY,
                      name VARCHAR(100) NOT NULL,
                      capacity INT NOT NULL CHECK (capacity BETWEEN 1 AND 20)
);

CREATE TABLE reservation (
                             id INT PRIMARY KEY,
                             room_id INT NOT NULL,
                             reserved_by VARCHAR(100) NOT NULL,
                             reservation_date DATE NOT NULL,
                             start_time TIME NOT NULL,
                             end_time TIME NOT NULL,

                             CONSTRAINT fk_reservation_room
                                 FOREIGN KEY (room_id)
                                     REFERENCES room(id),

                             CONSTRAINT chk_reservation_time
                                 CHECK (end_time > start_time)
);