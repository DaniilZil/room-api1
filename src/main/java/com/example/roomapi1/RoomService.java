package com.example.roomapi;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoomService {

    private final RoomRepository roomRepository;

    public RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }
    public List<Room> findAll(Integer minCapacity, String keyword) {

        List<Room> rooms = roomRepository.findAll();
        List<Room> result = new ArrayList<>();

        for (Room room : rooms) {

            if (minCapacity != null && room.getCapacity() < minCapacity) {
                continue;
            }

            if (keyword != null && !keyword.isBlank()) {
                if (!room.getName().toLowerCase().contains(keyword.toLowerCase())) {
                    continue;
                }
            }

            result.add(room);
        }

        return result;
    }

    public Room findOne(Long id) {
        return roomRepository.findById(id);
    }

    public Room create(Room room) {

        validateCapacity(room.getCapacity());

        return roomRepository.save(room);
    }

    public Room update(Long id, Room room) {

        validateCapacity(room.getCapacity());

        return roomRepository.update(id, room);
    }

    public boolean delete(Long id) {
        return roomRepository.deleteById(id);
    }

    private void validateCapacity(int capacity) {

        if (capacity < 1 || capacity > 20) {
            throw new IllegalArgumentException(
                    "Capacity must be between 1 and 20"
            );
        }
    }

}
