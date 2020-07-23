package esame.organigramma.mvc.repositories;

import esame.organigramma.mvc.entities.Dipendente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface DipendenteRepository extends JpaRepository<Dipendente,Integer> {
   // @Query("select du.id from dip_unita du where id = ?1")
   // int[] DipUnita(int id);
    Dipendente findById(int id);

}
