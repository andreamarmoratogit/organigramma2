package esame.organigramma.mvc.repositories;

import esame.organigramma.mvc.entities.Dipendente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DipendenteRepository extends JpaRepository<Dipendente,Integer> {
}
