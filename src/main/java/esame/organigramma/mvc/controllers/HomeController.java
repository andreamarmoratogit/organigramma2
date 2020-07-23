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

    @PostMapping("/")
    @ResponseBody
    public ResponseEntity<Organigramma> nuovoOrganigramma(@RequestBody String[] nomi){
        System.out.println("nuovoOrganigramma "+nomi[0]+"  "+nomi[1]);
        return new ResponseEntity<>(organigrammaService.createOrganigramma(nomi[0],nomi[1]), HttpStatus.OK);
    }

    @GetMapping("/{nome}")
    @ResponseBody
    public ResponseEntity<Organigramma> getOrganigramma(@PathVariable String nome){
        System.out.println("get organigramma: "+nome);
        return new ResponseEntity<>(organigrammaService.getOrganigramma(nome), HttpStatus.OK);
    }


}
