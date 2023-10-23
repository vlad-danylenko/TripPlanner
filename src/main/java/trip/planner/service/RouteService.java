package trip.planner.service;

import trip.planner.dto.route.RouteDTO;
import trip.planner.dto.RouteDetailsDto;

public interface RouteService {
    void createRoute(RouteDTO routeDTO);
    String addToRoute(RouteDetailsDto routeDetailsDto);
    String removeRouteStep (long id, int routeStep);
    String removeRoute (long id);
}
