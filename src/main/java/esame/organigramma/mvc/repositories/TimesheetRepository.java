package esame.organigramma.mvc.repositories;

import esame.organigramma.mvc.entities.Timesheet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TimesheetRepository extends JpaRepository<Timesheet, Integer> {

}
