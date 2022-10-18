package com.example.hotelBooking.roomtypes;

import com.example.hotelBooking.reservation.ReservationRepository;
import com.example.hotelBooking.room.RoomRepository;
import com.example.hotelBooking.validation.ValidationService;
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
    @Resource
    ValidationService validationService;

    public List<RoomType> getAvailableRoomsCount(String checkInStr, String checkOutStr) {
        LocalDate checkIn = LocalDate.parse(checkInStr);
        LocalDate checkOut = LocalDate.parse(checkOutStr);
        validationService.isStartDateBeforeCurrentDate(checkIn);
        validationService.isStartDateIsBeforeEndDate(checkIn, checkOut);
        List<RoomType> roomTypes = roomTypeRepository.getRoomTypes();
        List<BookedRoomsCount> bookedNumbers = reservationRepository.getBookedNumbersCounts(checkIn, checkOut);
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
