package trip.planner.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import trip.planner.dto.route.RouteDTO;
import trip.planner.dto.RouteDetailsDto;
import trip.planner.service.RouteService;
@Log4j2
@RestController
@RequestMapping("/api/routes")
public class RouteController {
    private final RouteService routeService;

    @Autowired
    public RouteController(RouteService routeService) {
        this.routeService = routeService;
    }
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @PostMapping("/create")
    public ResponseEntity<String> createRoute(@RequestBody RouteDTO routeDTO) {
        try {
            routeService.createRoute(routeDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body("Route created successfully");
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @PostMapping("/add")
    public ResponseEntity<String> addToRoute(@RequestBody RouteDetailsDto routeDetailsDto) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(routeService.addToRoute(routeDetailsDto));
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @DeleteMapping("/delete/{routeId}/{routeStep}")
    public ResponseEntity <String> removeRouteStep (@PathVariable long routeId, @PathVariable int routeStep) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(routeService.removeRouteStep(routeId,routeStep));
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{routeId}")
    public ResponseEntity <String> removeRoute (@PathVariable long routeId) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(routeService.removeRoute(routeId));
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}

