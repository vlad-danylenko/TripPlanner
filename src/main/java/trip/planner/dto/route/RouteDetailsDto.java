package trip.planner.dto.route;

import lombok.Data;
import trip.planner.entity.Route;

import java.time.LocalDateTime;

@Data
public class RouteDetailsDto {
    private Long routeId;
    private Route route;
    private int routeStep;
    private double latitude;
    private double longitude;
    private LocalDateTime createdAt;
}
