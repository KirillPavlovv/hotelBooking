package com.example.hotelBooking.roomtypes;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class RoomTypeController {

    @Resource
    RoomTypeRepository roomTypeRepository;

    @GetMapping("/room-types")
    public List<RoomType> getRoomTypes() {
        return roomTypeRepository.getRoomTypes();
    }

}
