package trip.planner.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import trip.planner.dto.route.RouteDTO;
import trip.planner.dto.route.RouteDetailsDto;
import trip.planner.entity.Route;
import trip.planner.entity.User;
import trip.planner.repository.RouteRepository;
import trip.planner.repository.UserRepository;
import trip.planner.service.RouteService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Log4j2
@RestController
@RequestMapping("/api/routes")
public class RouteController {
    private final RouteService routeService;
    private final UserRepository userRepository;
    private final RouteRepository routeRepository;

    @Autowired
    public RouteController(RouteService routeService, UserRepository userRepository, RouteRepository routeRepository) {
        this.routeService = routeService;
        this.userRepository = userRepository;
        this.routeRepository = routeRepository;
    }

    @PostMapping("/create")
    public ResponseEntity<String> createRoute(@RequestBody RouteDTO routeDTO) {
        try {
            routeService.createRoute(routeDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body("Route created successfully");
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/add")
    public ResponseEntity<String> addToRoute(@RequestBody RouteDetailsDto routeDetailsDto) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(routeService.addToRoute(routeDetailsDto));
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete/{routeId}/{routeStep}")
    public ResponseEntity <String> removeRouteStep (@PathVariable long routeId, @PathVariable int routeStep) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(routeService.removeRouteStep(routeId,routeStep));
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete/{routeId}")
    public ResponseEntity <String> removeRoute (@PathVariable long routeId) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(routeService.removeRoute(routeId));
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<RouteDTO>> getRoutesByUser (@PathVariable("userId") Long userId) {
        User user = userRepository.findById(userId).orElse(null);
        if (user != null) {
            List<RouteDTO> routes = routeService.getRoutesByUser(user);
            log.info(routes);
            if (routes != null && !routes.isEmpty()) {
                return ResponseEntity.status(HttpStatus.OK).body(routes);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping("/detailed/{routeId}")
    public ResponseEntity<Map<String, List<RouteDetailsDto>>> getRouteDetails(@PathVariable("routeId") Long routeId) {
        Route route = routeRepository.findById(routeId).orElse(null);
        if (route != null) {
            List<RouteDetailsDto> routeDetails = routeService.getRouteDetails(route);
            List<RouteDetailsDto> routeDetailsResponse = new ArrayList<>();

            if (routeDetails != null && !routeDetails.isEmpty()) {
                for (RouteDetailsDto detail : routeDetails) {
                    RouteDetailsDto responseDetail = new RouteDetailsDto();
                    Long routeIdLong = detail.getRoute().getId();
                    responseDetail.setRouteId(routeIdLong);
                    responseDetail.setRouteStep(detail.getRouteStep());
                    responseDetail.setLatitude(detail.getLatitude());
                    responseDetail.setLongitude(detail.getLongitude());
                    responseDetail.setCreatedAt(detail.getCreatedAt());
                    routeDetailsResponse.add(responseDetail);
                }
                Map<String, List<RouteDetailsDto>> response = new HashMap<>();
                response.put("routeDetails", routeDetailsResponse);

                return ResponseEntity.status(HttpStatus.OK).body(response);
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);

    }
}

