package esame.organigramma.mvc.repositories;

import esame.organigramma.mvc.entities.Organigramma;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrganigrammaRepository extends JpaRepository<Organigramma,Integer> {
    Organigramma findById(int id);
}
