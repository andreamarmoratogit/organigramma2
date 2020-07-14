package esame.organigramma.mvc.repositories;

import esame.organigramma.mvc.entities.UnitaPadre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UnitaPadreRepository extends JpaRepository<UnitaPadre,Integer> {
    UnitaPadre findById(int id);
}
