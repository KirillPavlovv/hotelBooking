package com.example.hotelBooking.roomtypes;

import com.example.hotelBooking.room.RoomRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class RoomTypeControllerTest {

    @MockBean
    RoomTypeService roomTypeService;
    @MockBean
    RoomTypeRepository roomTypeRepository;
    @MockBean
    RoomRepository roomRepository;
    @Autowired
    private MockMvc mockMvc;

    RoomType roomType = new RoomType()
            .setId(1)
            .setRoomsCount(2)
            .setDescription("Desc")
            .setPrice(100.00);

    @Test
    void getRoomTypes() throws Exception {
        when(roomTypeService.getRoomTypes()).thenReturn(List.of(roomType));
        mockMvc.perform(get("/room-types"))
                .andExpect(status().isOk())
                .andExpect(content().json("[]"));
        verify(roomTypeService).getRoomTypes();
    }

    @Test
    void getAvailableRoomsCount() {
    }
}