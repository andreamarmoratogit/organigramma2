package esame.organigramma.mvc.controllers;

import esame.organigramma.mvc.entities.Dipendente;
import esame.organigramma.mvc.entities.Ruolo;
import esame.organigramma.mvc.entities.UnitaPadre;
import esame.organigramma.mvc.services.DipendenteService;
import esame.organigramma.mvc.services.RuoloService;
import esame.organigramma.mvc.services.UnitaFactoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/organigramma")
public class UnitaController {
    @Autowired
    UnitaFactoryService unitaFactoryService;
    @Autowired
    DipendenteService dipendenteService;
    @Autowired
    RuoloService ruoloService;


    @PostMapping("/unita")
    public ResponseEntity creaUnita( @RequestBody String[] unita){
        UnitaPadre u;
        if(unita[0].equals("unita"))u=unitaFactoryService.createUnita(unita[1]);
        else if (unita[0].equals("sottounita"))u=unitaFactoryService.createSottounita(unita[1]);
        else u=unitaFactoryService.createOrgani(unita[1]);
        if(u==null)return new ResponseEntity("impossibile aggiungere unita",HttpStatus.BAD_REQUEST);
        return new ResponseEntity(u, HttpStatus.OK);
    }
    @PostMapping("/unita/{id}")
    public ResponseEntity addUnita(@PathVariable int id, @RequestBody String[] unita){
        UnitaPadre u;
        System.out.println(id+" "+unita[0]+" "+unita[1]);
        if(unita[0].equals("unita"))u=unitaFactoryService.createUnita(unita[1]);
        else if (unita[0].equals("sottounita"))u=unitaFactoryService.createSottounita(unita[1]);
        else u=unitaFactoryService.createOrgani(unita[1]);
        System.out.println(u.toString());
        if(u!=null && !unitaFactoryService.addFiglio(id,u))return new ResponseEntity("impossibile aggiungere unita",HttpStatus.BAD_REQUEST);
        return new ResponseEntity(u, HttpStatus.OK);
    }


    @PostMapping("/dipendente/{id}")
    public ResponseEntity addDipendente(@PathVariable int id,@RequestBody String[] dipendente){
        if(unitaFactoryService.controllaRuolo(id,dipendente[3])) {
            Dipendente d =dipendenteService.CreateDip(dipendente[0], dipendente[1], dipendente[2]);
            unitaFactoryService.addDip(id,d);
            return new ResponseEntity("Dipendente aggiunto",HttpStatus.OK);
        }
        return new ResponseEntity("impossibile aggiungere dipendente",HttpStatus.BAD_REQUEST);
    }
    @PostMapping("/ruolo/{id}")
    public ResponseEntity addRuolo(@PathVariable int id,@RequestBody String r){
        Ruolo ruolo=ruoloService.createRuolo(r);
        unitaFactoryService.addRuolo(ruolo,id);
        return new ResponseEntity("Ruolo aggiunto",HttpStatus.OK);
    }



}
