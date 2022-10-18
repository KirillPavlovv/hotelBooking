package com.example.hotelBooking.roomtypes;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class RoomTypeController {

    @Resource
    RoomTypeService roomTypeService;

    @GetMapping("/room-types")
    public List<RoomType> getRoomTypes() {
        return roomTypeService.getRoomTypes();
    }

    @GetMapping("/available-rooms")
    public List<RoomType> getAvailableRoomsCount(@RequestParam String checkIn, @RequestParam String checkOut) {
        return roomTypeService.getAvailableRoomsCount(checkIn, checkOut);
    }

}

