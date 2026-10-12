package com.example.roomapi1;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoomRepository
    extends JpaRepository<Room, Long> {

    List<Room> findByNameContainingIgnoreCase(String name);

    List<Room> findByCapacityGreaterThanEqual(Integer minCapacity);

    List<Room> findByNameContainingIgnoreCaseAndCapacityGreaterThanEqual(
            String name,
            Integer minCapacity
    );
}

