package trip.planner.utils;

import org.springframework.stereotype.Component;
import trip.planner.dto.RouteDTO;
import trip.planner.entity.Route;
@Component
public class RouteMapper {
    public RouteDTO toDTO (Route route) {
        RouteDTO dto = new RouteDTO();
        dto.setId(route.getId());
        dto.setRouteDesc(route.getRouteDesc());
        dto.setStartDt(route.getStartDt());
        dto.setFinishDt(route.getFinishDt());
        dto.setCreatedBy(route.getCreatedBy());
        dto.setCreatedAt(route.getCreatedAt());
        return dto;
    }

    public Route toEntity (RouteDTO routeDTO) {
        Route route = new Route();
        route.setId(routeDTO.getId());
        route.setRouteDesc(routeDTO.getRouteDesc());
        route.setStartDt(routeDTO.getStartDt());
        route.setFinishDt(routeDTO.getFinishDt());
        route.setCreatedBy(routeDTO.getCreatedBy());
        return route;
    }
}
