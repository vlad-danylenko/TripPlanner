package trip.planner.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import trip.planner.entity.Route;

@Repository
public interface RouteRepository extends JpaRepository<Route,Long> {
}
