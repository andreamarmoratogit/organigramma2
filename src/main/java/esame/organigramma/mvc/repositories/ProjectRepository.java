package esame.organigramma.mvc.repositories;


import esame.organigramma.mvc.entities.Dipendente;
import esame.organigramma.mvc.entities.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Integer> {

    Project[] findAllByDip(Dipendente d);
    Project findById(int id);
}
