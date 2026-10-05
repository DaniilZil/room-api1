package com.example.roomapi;

import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class InMemoryRoomRepository implements RoomRepository {

    private final Map<Long, Room> rooms = new LinkedHashMap<>();

    private long nextId = 1;

    public InMemoryRoomRepository()
    {
        save(new Room(null, "Computer lab", 20));
        save(new Room(null, "Meeting room", 15));
        save(new Room(null, "Library", 10));
    }

    @Override
    public List<Room> findAll() {
        return new ArrayList<>(rooms.values());
    }

    @Override
    public Room findById(Long id) {
        return rooms.get(id);
    }

    @Override
    public Room save(Room room) {
        room.setId(nextId);
        rooms.put(nextId,room);
        nextId++;
        return room;
    }

    @Override
    public Room update(Long id, Room room) {
        room.setId(id);
        rooms.put(id, room);
        return room;
    }

    @Override
    public boolean deleteById(Long id) {
        if(rooms.containsKey(id))
        {
            rooms.remove(id);
            return true;
        }
        return false;
    }
}
