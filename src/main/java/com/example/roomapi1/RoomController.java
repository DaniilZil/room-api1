package com.example.roomapi1;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/rooms")
public class RoomController {

    private final RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @GetMapping
    public List<Room> getAllRooms(
            @RequestParam(required = false) Integer minCapacity,
            @RequestParam(required = false) String keyword) {

        return roomService.search(keyword, minCapacity);
    }



    @GetMapping("/{id}")
    public ResponseEntity<Room> getRoomById(@PathVariable Long id) {

        Room room = roomService.findOne(id);

        if (room == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(room);
    }



    @PostMapping
    public ResponseEntity<Room> createRoom(@RequestBody Room room) {

        try {
            Room createdRoom = roomService.create(room);

            URI location = URI.create(
                    "/api/rooms/" + createdRoom.getId()
            );

            return ResponseEntity
                    .created(location)
                    .body(createdRoom);

        } catch (IllegalArgumentException e) {

            return ResponseEntity
                    .badRequest()
                    .build();
        }
    }


    @PutMapping("/{id}")
    public ResponseEntity<Room> updateRoom(
            @PathVariable Long id,
            @RequestBody Room updateRoom) {

        try {

            Room room = roomService.update(id, updateRoom);

            if (room == null) {
                return ResponseEntity.notFound().build();
            }

            return ResponseEntity.ok(room);

        } catch (IllegalArgumentException e) {

            return ResponseEntity
                    .badRequest()
                    .build();
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable Long id
    ) {

        roomService.delete(id);

        return ResponseEntity.noContent().build();
    }
}
