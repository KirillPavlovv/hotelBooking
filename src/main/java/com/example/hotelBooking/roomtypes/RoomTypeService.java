package com.example.hotelBooking.roomtypes;

import com.example.hotelBooking.reservation.ReservationRepository;
import com.example.hotelBooking.room.RoomRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.util.List;

@Service
public class RoomTypeService {

    @Resource
    RoomTypeRepository roomTypeRepository;
    @Resource
    RoomRepository roomRepository;
    @Resource
    ReservationRepository reservationRepository;


    public List<RoomType> getRoomTypes() {
        List<RoomType> roomTypes = roomTypeRepository.getRoomTypes();
        for (RoomType roomType : roomTypes) {
            roomType.setRoomsCount(roomRepository.getRoomsCount(roomType.getId()));
        }
        return roomTypes;
    }

    public List<RoomType> getAvailableRoomsCount(String checkInStr, String checkOutStr) {
        LocalDate checkIn = LocalDate.parse(checkInStr);
        LocalDate checkOut = LocalDate.parse(checkOutStr);
        List<RoomType> roomTypes = roomTypeRepository.getRoomTypes();
        List<BookedRoomsCount> bookedNumbers = reservationRepository.getBookedNumbers(checkIn, checkOut);
        for (RoomType roomType : roomTypes) {
            for (BookedRoomsCount bookedNumber : bookedNumbers) {
                if (bookedNumber.getBeds() == roomType.getId()) {
                    roomType.setRoomsCount(roomRepository.getRoomsCount(roomType.getId()) - bookedNumber.getBookedRooms());
                    break;
                }
                roomType.setRoomsCount(roomRepository.getRoomsCount(roomType.getId()));
            }

        }
        return roomTypes;
    }
}
