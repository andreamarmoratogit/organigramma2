package esame.organigramma.mvc.controllers;

import esame.organigramma.mvc.entities.Organigramma;
import esame.organigramma.mvc.services.OrganigrammaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
public class HomeController {
    @Autowired
    OrganigrammaService organigrammaService;

    //metodo che riceve la richiesta http data dalla funzione nuovo Organigramma
    @PostMapping("/")
    @ResponseBody
    public ResponseEntity<Organigramma> nuovoOrganigramma(@RequestBody String[] nomi){
        System.out.println("nuovoOrganigramma "+nomi[0]+"  "+nomi[1]);
        return new ResponseEntity<>(organigrammaService.createOrganigramma(nomi[0],nomi[1]), HttpStatus.OK);
    }
    //metodo che riceve la richiesta http data dalla funzione Cerca Organigramma
    @GetMapping("/{nome}")
    @ResponseBody
    public ResponseEntity<Organigramma> getOrganigramma(@PathVariable String nome){
        System.out.println("get organigramma: "+nome);
        Organigramma o = organigrammaService.getOrganigramma(nome);
        if(o==null)return new ResponseEntity("Organigramma non trovato",HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(o, HttpStatus.OK);
    }

    @DeleteMapping("/{idOrg}")
    @ResponseBody
    public ResponseEntity deleteOrg(@PathVariable int idOrg){
        organigrammaService.deleteOrg(idOrg);
        return new ResponseEntity( HttpStatus.OK);
    }


}
