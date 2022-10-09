package com.example.hotelBooking.roomtypes;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.context.annotation.Configuration;


@Data
@Accessors(chain = true)
@Configuration
public class RoomType {

    int id;
    String description;
    String pictureName;
    double price;
}
