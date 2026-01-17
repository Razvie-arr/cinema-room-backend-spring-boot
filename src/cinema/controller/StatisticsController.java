package cinema.controller;

import cinema.dto.StatisticsResponse;
import cinema.service.StatisticsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StatisticsController {

    private final StatisticsService statisticsService;

    public StatisticsController(StatisticsService statisticsService) {
        this.statisticsService = statisticsService;
    }

    @GetMapping("/stats")
    public StatisticsResponse generateStatistics(@RequestParam(required = false) String password) {
        return statisticsService.generateStatistics(password);
    }

}
