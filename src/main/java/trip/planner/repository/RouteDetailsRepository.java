package trip.planner.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import trip.planner.entity.Route;
import trip.planner.entity.RouteDetails;

import java.util.List;

@Repository
public interface RouteDetailsRepository extends JpaRepository<RouteDetails, Long> {
    RouteDetails findByRouteAndRouteStep(Route id, int routeStep);
    List<RouteDetails> findByRoute (Route route);

}
