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
    public Organigramma nuovoOrganigramma(@RequestBody String nomi){
        System.out.println("nuovoOrganigramma "+nomi);
        int x=0;
        return organigrammaService.createOrganigramma(nomi);

    }


}
