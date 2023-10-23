package trip.planner.dto.route;

import lombok.Data;
import trip.planner.entity.User;

import java.time.LocalDate;
import java.time.LocalDateTime;
@Data
public class RouteDTO {
    private long id;
    private String routeDesc;
    private LocalDate startDt;
    private LocalDate finishDt;
    private User createdBy;
    private LocalDateTime createdAt;
}
