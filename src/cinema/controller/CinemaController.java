package cinema.controller;

import cinema.dto.SeatPurchaseRequest;
import cinema.entity.Cinema;
import cinema.entity.Seat;
import cinema.service.CinemaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CinemaController {

    private final CinemaService cinemaService;

    public CinemaController(CinemaService cinemaService) {
        this.cinemaService = cinemaService;
    }

    @GetMapping("/seats")
    public Cinema getSeats() {
        return cinemaService.getCinemaWithAvailableSeats();
    }

    @PostMapping("/purchase")
    public ResponseEntity<Seat> purchaseSeat(@RequestBody SeatPurchaseRequest seatPurchaseRequest) {
        Seat purchasedSeat = cinemaService.purchaseSeat(seatPurchaseRequest);
        return ResponseEntity.ok().body(purchasedSeat);
    }

}
