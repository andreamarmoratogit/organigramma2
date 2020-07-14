package esame.organigramma.mvc.controllers;

import esame.organigramma.mvc.entities.Dipendente;
import esame.organigramma.mvc.entities.Ruolo;
import esame.organigramma.mvc.services.DipendenteService;
import esame.organigramma.mvc.services.RuoloService;
import esame.organigramma.mvc.services.UnitaFactoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/organigramma")
public class UnitaController {
    @Autowired
    UnitaFactoryService unitaFactoryService;
    @Autowired
    DipendenteService dipendenteService;
    @Autowired
    RuoloService ruoloService;


    @PostMapping("/unita")
    public ResponseEntity creaUnita(@RequestBody String unita){
        unitaFactoryService.createUnita(unita);
        return new ResponseEntity("Unita creata", HttpStatus.OK);
    }

    @PostMapping("/sottounita")
    public ResponseEntity creaSottounita(@RequestBody String sottounita){
        unitaFactoryService.createSottounita(sottounita);
        return new ResponseEntity("Sottounita creata",HttpStatus.OK);
    }

    @PostMapping("/organi")
    public ResponseEntity creaOrgani(@RequestBody String organi ){
        unitaFactoryService.createOrgani(organi);
        return new ResponseEntity("Organi Di Gestione creati",HttpStatus.OK);
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
