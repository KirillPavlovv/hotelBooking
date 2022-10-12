package com.example.hotelBooking.room;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class RoomController {

    @Resource
    RoomService roomService;

}
