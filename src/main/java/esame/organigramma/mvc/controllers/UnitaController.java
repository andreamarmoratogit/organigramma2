package esame.organigramma.mvc.controllers;


import esame.organigramma.mvc.entities.Organigramma;
import esame.organigramma.mvc.entities.Ruolo;
import esame.organigramma.mvc.entities.UnitaPadre;
import esame.organigramma.mvc.services.DipendenteService;
import esame.organigramma.mvc.services.OrganigrammaService;
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
    @Autowired
    OrganigrammaService organigrammaService;

    //metodi che ricevono le richieste http dal front-end per la gestione dell'organigramma

    @PostMapping("/unita/{orgId}")
    @ResponseBody
    public ResponseEntity<Organigramma> addUnita(@PathVariable int orgId, @RequestBody String[] unita){
        UnitaPadre u = unitaFactoryService.createUnitaOrganizzativa(unita[2], unita[1]);
        System.out.println(unita[0]+" "+unita[1]+" "+unita[2]);
        if(u!=null && !unitaFactoryService.addFiglio(Integer.parseInt(unita[0]) ,u))
            return new ResponseEntity("impossibile aggiungere unita",HttpStatus.BAD_REQUEST);
        Organigramma o=organigrammaService.getById(orgId);
        System.out.println(o.toString());
        return new ResponseEntity<>(o, HttpStatus.OK);
    }


    @PostMapping("/dipendente/{orgId}")
    @ResponseBody
    public ResponseEntity<Organigramma> addDipendente(@PathVariable int orgId,@RequestBody String[] dip){
        int idU = Integer.parseInt(dip[0]);
        Ruolo r=unitaFactoryService.containsRuolo(idU,dip[3]);
        if(r!=null) {
            unitaFactoryService.addDip(idU,dip[1], dip[2],r);

            return new ResponseEntity<>(organigrammaService.getById(orgId),HttpStatus.OK);
        }
        return new ResponseEntity("impossibile aggiungere dipendente",HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/ruolo/{orgId}")
    @ResponseBody
    public ResponseEntity<Organigramma> addRuolo(@PathVariable int orgId, @RequestBody String[] r){
        Ruolo ruolo=ruoloService.createRuolo(r[1]);
        unitaFactoryService.addRuolo(ruolo,Integer.parseInt(r[0]));
        return new ResponseEntity<>(organigrammaService.getById(orgId),HttpStatus.OK);
    }

    @DeleteMapping("/unita/{orgId}/{idU}")
    @ResponseBody
    public ResponseEntity<Organigramma> delUnita(@PathVariable int orgId, @PathVariable int idU){
        if(!unitaFactoryService.removeUnita(idU)){
            return new ResponseEntity("impossibile rimuovere unita",HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(organigrammaService.getById(orgId),HttpStatus.OK);
    }

    @DeleteMapping("dipendente/{orgId}/{idU}/{idD}")
    @ResponseBody
    public ResponseEntity<Organigramma> delDipendente(@PathVariable int orgId,@PathVariable int idU,@PathVariable int idD){

        if(!unitaFactoryService.removeDip(idU,idD))
            return new ResponseEntity("dipendente non trovato",HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(organigrammaService.getById(orgId),HttpStatus.OK);
    }

    @DeleteMapping("ruolo/{orgId}/{idU}/{nome}")
    @ResponseBody
    public ResponseEntity<Organigramma> delRuolo(@PathVariable int orgId,@PathVariable int idU,@PathVariable String nome){
        if(unitaFactoryService.removeRuolo(idU, nome))
            return new ResponseEntity<>(organigrammaService.getById(orgId),HttpStatus.OK);
        return new ResponseEntity("ruolo non trovato",HttpStatus.BAD_REQUEST);
    }
}
