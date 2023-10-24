package trip.planner.service;

import trip.planner.dto.route.RouteDTO;
import trip.planner.dto.route.RouteDetailsDto;
import trip.planner.entity.Route;
import trip.planner.entity.User;

import java.util.List;

public interface RouteService {
    void createRoute(RouteDTO routeDTO);
    String addToRoute(RouteDetailsDto routeDetailsDto);
    String removeRouteStep (long id, int routeStep);
    String removeRoute (long id);
    List<RouteDTO> getRoutesByUser(User user);
    List<RouteDetailsDto> getRouteDetails (Route route);
}
