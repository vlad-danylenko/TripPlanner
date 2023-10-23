package trip.planner.dto;

import lombok.Data;
import trip.planner.entity.Route;

import java.time.LocalDateTime;

@Data
public class RouteDetailsDto {
    private Route route;
    private int routeStep;
    private double latitude;
    private double longitude;
    private LocalDateTime createdAt;
}
