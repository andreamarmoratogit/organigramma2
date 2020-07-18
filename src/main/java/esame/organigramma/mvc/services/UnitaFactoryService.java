package esame.organigramma.mvc.services;

import esame.organigramma.mvc.entities.*;
import esame.organigramma.mvc.repositories.UnitaPadreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class UnitaFactoryService {
    @Autowired
    UnitaPadreRepository unitaPadreRepository;

    @Transactional
    public Unita createUnita(String nome){
        Unita u=new Unita();
        u.setNome(nome);
        unitaPadreRepository.save(u);
        return u;
    }

    @Transactional
    public Sottounita createSottounita(String nome){
        Sottounita u=new Sottounita();
        u.setNome(nome);
        unitaPadreRepository.save(u);
        return u;
    }

    @Transactional
    public OrganiDiGestione createOrgani(String nome){
        OrganiDiGestione u=new OrganiDiGestione();
        u.setNome(nome);
        unitaPadreRepository.save(u);
        return u;
    }

    @Transactional
    public void addDip(int id, Dipendente d){
        UnitaPadre u=unitaPadreRepository.findById(id);
        u.addDip(d);
        unitaPadreRepository.save(u);

    }
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void addRuolo(Ruolo r, int id ){
        UnitaPadre u=unitaPadreRepository.findById(id);
        u.addRuolo(r);
        unitaPadreRepository.save(u);
    }

    @Transactional
    public boolean controllaRuolo(int id,String ruolo){
        UnitaPadre u=unitaPadreRepository.findById(id);
        for(Ruolo r:u.getRuoli()){
            if(r.getNome().equals(ruolo))
                for(Dipendente d:u.getListDip()){
                    if(d.getRuolo().equals(ruolo))return false;
                }
        }
        return true;
    }

    @Transactional
    public boolean addFiglio(int idPadre,UnitaPadre uFiglio){
        UnitaPadre up=unitaPadreRepository.findById(idPadre);
        List<UnitaPadre> figli=up.getFigli();
        if(figli==null)figli=new ArrayList<UnitaPadre>();
        figli.add(uFiglio);
        up.setFigli(figli);
        uFiglio.setPadre(up);
        unitaPadreRepository.save(up);
        unitaPadreRepository.save(uFiglio);
        System.out.println(up.toString()+"\n"+uFiglio.toString());
        return true;
    }
    @Transactional
    public void removeFiglio(UnitaPadre u, UnitaPadre ufiglio){
        u.removeFiglio(ufiglio);
        unitaPadreRepository.save(u);
    }
}
