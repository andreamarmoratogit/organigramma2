package esame.organigramma.mvc.services;

import esame.organigramma.mvc.entities.Dipendente;
import esame.organigramma.mvc.entities.Ruolo;
import esame.organigramma.mvc.repositories.DipendenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DipendenteService {
    @Autowired
    private DipendenteRepository dipendenteRepository;

    @Transactional
    public Dipendente CreateDip(String nome, String cognome, String r){
        Dipendente d=new Dipendente();
        d.setNome(nome);
        d.setCognome(cognome);
        d.setRuolo(r);
        dipendenteRepository.save(d);
        return d;
    }
    @Transactional(readOnly = true)
    public List<Dipendente> getDipententi(){
        return dipendenteRepository.findAll();

    }


}
