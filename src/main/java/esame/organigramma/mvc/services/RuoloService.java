package esame.organigramma.mvc.services;

import esame.organigramma.mvc.entities.Ruolo;
import esame.organigramma.mvc.repositories.RuoloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RuoloService {
    @Autowired
    RuoloRepository ruoloRepository;

    @Transactional
    public Ruolo createRuolo(String nome){
        Ruolo r=new Ruolo();
        r.setNome(nome);
        ruoloRepository.save(r);
        return r;
    }
    @Transactional
    public List<Ruolo> getRuoli(){
        return ruoloRepository.findAll();
    }
}
