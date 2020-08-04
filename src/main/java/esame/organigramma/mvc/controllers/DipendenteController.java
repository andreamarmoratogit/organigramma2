package esame.organigramma.mvc.controllers;

import esame.organigramma.mvc.entities.Dipendente;
import esame.organigramma.mvc.entities.Project;
import esame.organigramma.mvc.services.DipendenteService;
import esame.organigramma.mvc.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/dipendente")
public class DipendenteController {
    @Autowired
    DipendenteService dipendenteService;
    @Autowired
    ProjectService projectService;

    @GetMapping("/{nome}/{cogn}")
    @ResponseBody
    public ResponseEntity<Dipendente> getDipendente(@PathVariable String nome, @PathVariable String cogn){
        Dipendente d = dipendenteService.findDip(nome,cogn);
        if(d == null)
            return new ResponseEntity("Dipendente non trovato", HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(d,HttpStatus.OK);
    }

    @GetMapping("/{idDip}")
    @ResponseBody
    public ResponseEntity<Project[]> getProjects(@PathVariable int idDip){
        Dipendente d = dipendenteService.findById(idDip);
        if(d == null)
            return new ResponseEntity("Dipendente non trovato", HttpStatus.BAD_REQUEST);
        Project[] p= projectService.getProjectbyDip(d);
        return new ResponseEntity<>(p,HttpStatus.OK);
    }

    @PostMapping("/{idDip}")
    @ResponseBody
    public ResponseEntity<Project> addProject(@PathVariable int idDip, @RequestBody String[] project){
        Dipendente d = dipendenteService.findById(idDip);
        if(d == null)
            return new ResponseEntity("Dipendente non trovato", HttpStatus.BAD_REQUEST);
        Project p = projectService.addProject(d, project[0] , project[1]);
        return new ResponseEntity<>(p,HttpStatus.OK);

    }

    @PostMapping("/timesheet/{idP}")
    @ResponseBody
    public ResponseEntity<Project> addTimesheet(@PathVariable int idP, @RequestBody String att){
        Project p=projectService.addTimesheet(idP,att);
        if(p==null)
            return new ResponseEntity("Progetto non trovato", HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(p, HttpStatus.OK);
    }


}
