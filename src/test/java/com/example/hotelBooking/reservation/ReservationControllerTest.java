package com.example.hotelBooking.reservation;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = ReservationController.class)
class ReservationControllerTest {
    @MockBean
    ReservationService reservationService;
    @MockBean
    ReservationRepository reservationRepository;
    @Autowired
    private MockMvc mockMvc;

    @Test
    void getReservations() throws Exception {
        when(reservationRepository.getReservations(LocalDate.now())).thenReturn(List.of());
        mockMvc.perform(get("/reservationsList").param("now", LocalDate.now().toString()))
                .andExpect(status().isOk())
                .andExpect(content().json("[]"));

    }

    @Test
    void saveReservation() {
    }

    @Test
    void deleteReservation() throws Exception {
        UUID reservationId = UUID.randomUUID();
        mockMvc.perform(delete("/deleteReservation").param("id", reservationId.toString()))
                .andExpect(status().isOk());

        verify(reservationRepository).deleteReservation(reservationId);
    }
}