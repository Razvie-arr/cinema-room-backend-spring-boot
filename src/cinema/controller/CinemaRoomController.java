package cinema.controller;

import cinema.entity.CinemaRoom;
import cinema.service.CinemaRoomService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CinemaRoomController {

    private final CinemaRoomService cinemaRoomService;

    public CinemaRoomController(CinemaRoomService cinemaRoomService) {
        this.cinemaRoomService = cinemaRoomService;
    }

    @GetMapping("/seats")
    public CinemaRoom getSeats() {
        return cinemaRoomService.getCinemaRoomWithAvailableSeats();
    }

}
