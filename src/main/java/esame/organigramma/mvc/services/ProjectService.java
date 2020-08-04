package esame.organigramma.mvc.services;

import esame.organigramma.mvc.entities.Dipendente;
import esame.organigramma.mvc.entities.Project;
import esame.organigramma.mvc.entities.Timesheet;
import esame.organigramma.mvc.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProjectService {

    @Autowired
    ProjectRepository projectRepository;

    @Transactional
    public Project[] getProjectbyDip(Dipendente d){
        return projectRepository.findAllByDip(d);
    }

    @Transactional
    public Project addProject(Dipendente d, String titolo, String desc){
        Project p = new Project();
        p.setDip(d);
        p.setTitolo(titolo);
        p.setDescrizione(desc);
        projectRepository.save(p);
        return p;
    }

    @Transactional
    public Project addTimesheet(int idP, String attivita){
        Project p= projectRepository.findById(idP);
        if(p==null)return null;
        Timesheet t=new Timesheet();
        t.setAttivita(attivita);
        p.getTimesheet().add(t);
        projectRepository.save(p);
        return p;
    }


}
