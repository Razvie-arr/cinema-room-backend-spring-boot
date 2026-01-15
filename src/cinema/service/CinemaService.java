package cinema.service;

import cinema.entity.Cinema;
import cinema.repository.CinemaRepository;
import org.springframework.stereotype.Service;

@Service
public class CinemaService {

    private final CinemaRepository cinemaRepository;

    public CinemaService(CinemaRepository cinemaRepository) {
        this.cinemaRepository = cinemaRepository;
    }

    public Cinema getMainCinema() {
        return cinemaRepository.getMainCinema();
    }

}
