package esame.organigramma.mvc.repositories;

import esame.organigramma.mvc.entities.Dipendente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface DipendenteRepository extends JpaRepository<Dipendente,Integer> {

    Dipendente findById(int id);

    Dipendente findFirstByNomeIgnoreCaseAndCognomeIgnoreCase(String nome, String congome);

}
