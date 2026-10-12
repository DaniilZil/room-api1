package com.example.roomapi1;

import org.hibernate.ObjectNotFoundException;
import org.hibernate.annotations.NotFound;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoomService {

    private final RoomRepository roomRepository;

    public RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }
    public List<Room> search(String name, Integer minCapacity) {

        if (name != null && name.isBlank()) {
            name = null;
        }



        // name + minCapacity
        if (name != null && minCapacity != null) {
            return roomRepository
                    .findByNameContainingIgnoreCaseAndCapacityGreaterThanEqual(
                            name,
                            minCapacity
                    );
        }

        // only name
        if (name != null) {
            return roomRepository.findByNameContainingIgnoreCase(name);
        }

        // only capacity
        if (minCapacity != null) {
            return roomRepository.findByCapacityGreaterThanEqual(minCapacity);
        }

        // no search parameters
        return roomRepository.findAll();
    }

    public Room findOne(Long id) {
        return roomRepository.findById(id).orElseThrow();
    }

    public Room create(Room room) {

        validateCapacity(room.getCapacity());

        return roomRepository.save(room);
    }

    public Room update(Long id, Room room) {

        validateCapacity(room.getCapacity());

        Room existingRoom = roomRepository.findById(id)
                .orElseThrow();

        existingRoom.setName(room.getName());
        existingRoom.setCapacity(room.getCapacity());

        return roomRepository.save(existingRoom);
    }

    public void delete(Long id) {
        Room room = roomRepository.findById(id)
                .orElseThrow();
        roomRepository.delete(room);
    }

    public long count() {
        return roomRepository.count();
    }

    private void validateCapacity(int capacity) {

        if (capacity < 1 || capacity > 20) {
            throw new IllegalArgumentException(
                    "Capacity must be between 1 and 20"
            );
        }
    }

}
