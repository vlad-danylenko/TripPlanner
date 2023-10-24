package trip.planner.service;

import jakarta.persistence.EntityNotFoundException;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import trip.planner.dto.route.RouteDTO;
import trip.planner.dto.route.RouteDetailsDto;
import trip.planner.entity.Route;
import trip.planner.entity.RouteDetails;
import trip.planner.entity.User;
import trip.planner.repository.RouteDetailsRepository;
import trip.planner.repository.RouteRepository;
import trip.planner.repository.UserRepository;
import trip.planner.utils.RouteDetailsMapper;
import trip.planner.utils.RouteMapper;

import java.util.List;
import java.util.stream.Collectors;

@Log4j2
@Service
public class RouteServiceImpl implements RouteService {
    private final RouteRepository routeRepository;
    private final RouteDetailsRepository routeDetailsRepository;
    private final UserRepository userRepository;
    private final RouteMapper routeMapper;
    private final RouteDetailsMapper routeDetailsMapper;


    public RouteServiceImpl(RouteRepository routeRepository, RouteDetailsRepository routeDetailsRepository, UserRepository userRepository, RouteMapper routeMapper, RouteDetailsMapper routeDetailsMapper) {
        this.routeRepository = routeRepository;
        this.routeDetailsRepository = routeDetailsRepository;
        this.userRepository = userRepository;
        this.routeMapper = routeMapper;
        this.routeDetailsMapper = routeDetailsMapper;
    }

    @Override
    public void createRoute(RouteDTO routeDTO) {
        User user = userRepository.findById(routeDTO.getCreatedBy().getId()).orElseThrow(() -> new EntityNotFoundException("User not found"));
        Route route = new Route();
        route.setId(routeDTO.getId());
        route.setRouteDesc(routeDTO.getRouteDesc());
        route.setStartDt(routeDTO.getStartDt());
        route.setFinishDt(routeDTO.getFinishDt());
        route.setCreatedBy(user);
        routeRepository.save(route);
    }

    @Override
    public String addToRoute(RouteDetailsDto routeDetailsDto) {
        Route route = routeRepository.findById(routeDetailsDto.getRoute().getId()).orElseThrow(() -> new EntityNotFoundException("Route not found"));
        log.info(route);

        RouteDetails routeDetails = new RouteDetails();
        routeDetails.setRoute(route);
        routeDetails.setRouteStep(routeDetailsDto.getRouteStep());
        routeDetails.setLatitude(routeDetailsDto.getLatitude());
        routeDetails.setLongitude(routeDetailsDto.getLongitude());
        if (routeDetailsRepository.findByRouteAndRouteStep(route,routeDetailsDto.getRouteStep()) == null) {
            routeDetailsRepository.save(routeDetails);
        } else {
            return "Step already created";
        }
        return "New step to route successfully added";
    }



    @Override
    public String removeRouteStep(long id, int routeStep) {
        Route route = routeRepository.findById(id).orElse(null);
        RouteDetails routeDetails = routeDetailsRepository.findByRouteAndRouteStep(route, routeStep);

        if (routeDetails != null) {
            routeDetailsRepository.delete(routeDetails);
            return "Step deleted";
        } else {
            return "Step not found"; //
        }
    }

    @Override
    public String removeRoute (long id) {
        Route route = routeRepository.findById(id).orElse(null);

        if (route != null) {
            routeRepository.delete(route);
            return "Route deleted";
        } else {
            return "Route not found";
        }
    }

    @Override
    public List<RouteDTO> getRoutesByUser(User user) {
        List<Route> routes = routeRepository.findByCreatedBy(user);

        if (routes == null || routes.isEmpty()) {
            return null;
        }

        return routes.stream()
                .map(routeMapper::toDTO)
                .collect(Collectors.toList());

    }

    @Override
    public List<RouteDetailsDto> getRouteDetails(Route route) {
        List<RouteDetails> routeDetails = routeDetailsRepository.findByRoute(route);
        return routeDetails.stream()
                .map(routeDetailsMapper::toDTO)
                .collect(Collectors.toList());
    }
}
