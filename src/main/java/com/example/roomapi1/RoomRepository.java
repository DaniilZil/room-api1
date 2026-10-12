package com.example.roomapi1;

import java.util.List;

public interface RoomRepository {
    List<Room> findAll();
    Room findById(Long id);
    Room save(Room room);
    Room update(Long id, Room room);
    boolean deleteById(Long id);
}
