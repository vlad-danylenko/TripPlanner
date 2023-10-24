package trip.planner.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import trip.planner.entity.Route;
import trip.planner.entity.User;

import java.util.List;

@Repository
public interface RouteRepository extends JpaRepository<Route,Long> {
    List<Route> findByCreatedBy (User user);
}
