package esame.organigramma.mvc.controllers;

import esame.organigramma.mvc.entities.Dipendente;
import esame.organigramma.mvc.entities.Organigramma;
import esame.organigramma.mvc.entities.Ruolo;
import esame.organigramma.mvc.entities.UnitaPadre;
import esame.organigramma.mvc.repositories.UnitaPadreRepository;
import esame.organigramma.mvc.services.DipendenteService;
import esame.organigramma.mvc.services.OrganigrammaService;
import esame.organigramma.mvc.services.RuoloService;
import esame.organigramma.mvc.services.UnitaFactoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;

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


    @PostMapping("/unita")
    @ResponseBody
    public ResponseEntity<UnitaPadre> creaUnita( @RequestBody String[] unita){
        UnitaPadre u;
        if(unita[0].equals("unita"))u=unitaFactoryService.createUnita(unita[1]);
        else if (unita[0].equals("sottounita"))u=unitaFactoryService.createSottounita(unita[1]);
        else u=unitaFactoryService.createOrgani(unita[1]);
        if(u==null)return new ResponseEntity("impossibile aggiungere unita",HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(u, HttpStatus.OK);
    }
    @PostMapping("/unita/{orgId}")
    @ResponseBody
    public ResponseEntity<Organigramma> addUnita(@PathVariable int orgId, @RequestBody String[] unita){
        UnitaPadre u;
        System.out.println(unita[0]+" "+unita[1]+" "+unita[2]);
        if(unita[1].equals("unita"))u=unitaFactoryService.createUnita(unita[2]);
        else if (unita[1].equals("sottounita"))u=unitaFactoryService.createSottounita(unita[2]);
        else u=unitaFactoryService.createOrgani(unita[1]);
        if(u!=null && !unitaFactoryService.addFiglio(Integer.parseInt(unita[0]) ,u))
            return new ResponseEntity("impossibile aggiungere unita",HttpStatus.BAD_REQUEST);
        Organigramma o=organigrammaService.getById(orgId);
        System.out.println(o.toString());
        return new ResponseEntity<>(o, HttpStatus.OK);
    }


    @PostMapping("/dipendente/{orgId}")
    @ResponseBody
    public ResponseEntity addDipendente(@PathVariable int orgId,@RequestBody String[] dip){
        int idU = Integer.parseInt(dip[0]);
        if(unitaFactoryService.containsRuolo(idU,dip[3])) {
            Dipendente d = dipendenteService.CreateDip(dip[1], dip[2], dip[3]);
            unitaFactoryService.addDip(idU,d);
            return new ResponseEntity(organigrammaService.getById(orgId),HttpStatus.OK);
        }
        return new ResponseEntity("impossibile aggiungere dipendente",HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/ruolo/{orgId}")
    @ResponseBody
    public ResponseEntity addRuolo(@PathVariable int orgId, @RequestBody String[] r){
        Ruolo ruolo=ruoloService.createRuolo(r[1]);
        unitaFactoryService.addRuolo(ruolo,Integer.parseInt(r[0]));
        return new ResponseEntity(organigrammaService.getById(orgId),HttpStatus.OK);
    }



}
