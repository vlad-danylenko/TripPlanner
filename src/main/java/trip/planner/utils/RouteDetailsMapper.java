package trip.planner.utils;

import org.springframework.stereotype.Component;
import trip.planner.dto.route.RouteDetailsDto;
import trip.planner.dto.route.RouteDTO;
import trip.planner.entity.Route;
import trip.planner.entity.RouteDetails;

@Component
public class RouteDetailsMapper {
    public RouteDetailsDto toDTO (RouteDetails routeDetails) {
        RouteDetailsDto dto = new RouteDetailsDto();
        dto.setRoute(routeDetails.getRoute());
        dto.setRouteStep(routeDetails.getRouteStep());
        dto.setLatitude(routeDetails.getLatitude());
        dto.setLongitude(routeDetails.getLongitude());
        dto.setCreatedAt(routeDetails.getCreatedAt());
        return dto;
    }

    public Route toEntity (RouteDTO routeDTO) {
       return null;
    }
}
