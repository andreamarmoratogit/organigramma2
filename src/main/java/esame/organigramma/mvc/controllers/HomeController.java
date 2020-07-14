package esame.organigramma.mvc.controllers;

import esame.organigramma.mvc.entities.Organigramma;
import esame.organigramma.mvc.services.OrganigrammaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
public class HomeController {
    @Autowired
    OrganigrammaService organigrammaService;
    @PostMapping("/")
    @ResponseBody
    public int nuovoOrganigramma(@RequestBody String[] nomi){
        System.out.println("nuovoOrganigramma "+nomi[0]+"  "+nomi[1]);
        return organigrammaService.createOrganigramma(nomi[0],nomi[1]).getId();

    }


}
