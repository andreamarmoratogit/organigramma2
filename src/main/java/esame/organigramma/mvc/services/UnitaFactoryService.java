package esame.organigramma.mvc.services;

import esame.organigramma.mvc.entities.*;
import esame.organigramma.mvc.repositories.DipendenteRepository;
import esame.organigramma.mvc.repositories.RuoloRepository;
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
    @Autowired
    RuoloRepository ruoloRepository;
    @Autowired
    DipendenteRepository dipendenteRepository;
    @Autowired
    DipendenteService dipendenteService;

    @Transactional
    public UnitaPadre createUnitaOrganizzativa(String nome, String tipo){
        UnitaPadre u;
        ArrayList<String> nomiR = new ArrayList<>();
        ArrayList<Ruolo> ruoli = new ArrayList<>();
        if(tipo.equals("unita")){
            u= new Unita();
            nomiR.add("Direttore");
            nomiR.add("Vice");
            nomiR.add("Avvocato");
            nomiR.add("Manager");
        }
        else if (tipo.equals("sottounita")){
            u= new Sottounita();
            nomiR.add("Segretario");
            nomiR.add("Manager Junior");
            nomiR.add("Programmatore");
            nomiR.add("Progettista");
        }
        else {
            u= new OrganiDiGestione();
            nomiR.add("Addetto Vendite");
            nomiR.add("Operaio");
            nomiR.add("Tecnico Informatico");
            nomiR.add("Contabile");
        }
        u.setNome(nome);
        for(String s:nomiR){
            Ruolo r = new Ruolo(s);
            ruoli.add(r);
            ruoloRepository.save(r);
        }
        u.setRuoli(ruoli);
        unitaPadreRepository.save(u);
        return u;
    }


    @Transactional
    public void addDip(int id, String nome, String cognome, Ruolo ruolo){
        UnitaPadre u=unitaPadreRepository.findById(id);
        List<Ruolo> r= u.getRuoli();
        r.remove(ruolo);
        u.setRuoli(r);
        Dipendente d=dipendenteService.createDip(nome,cognome,ruolo);
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
    public boolean addFiglio(int idPadre,UnitaPadre uFiglio){
        UnitaPadre up=unitaPadreRepository.findById(idPadre);
        List<UnitaPadre> figli=up.getFigli();
        if(figli==null)figli=new ArrayList<>();
        figli.add(uFiglio);
        up.setFigli(figli);
        uFiglio.setPadre(up);
        unitaPadreRepository.save(up);
        unitaPadreRepository.save(uFiglio);
        System.out.println(up.toString()+"\n"+uFiglio.toString());
        return true;
    }

    @Transactional
    public Ruolo containsRuolo(int id, String r){
        List<Ruolo> ruoli=unitaPadreRepository.findById(id).getRuoli();
        for(Ruolo i:ruoli){
            if(i.getNome().equals(r))return i;
        }
        return null;
    }

    @Transactional
    public boolean removeUnita(int id){
        //UnitaPadre u = unitaPadreRepository.findById(id);
        //if(u == null)return false;
        unitaPadreRepository.deleteById(id);
        return true;
    }

    @Transactional
    public boolean removeDip(int idU, int idD){
        UnitaPadre u=unitaPadreRepository.findById(idU);
        if(u==null)return false;
        List<Dipendente> listd=u.getListDip();
        Dipendente d=dipendenteRepository.findById(idD);
        if(d==null)return false;
        u.getRuoli().add(d.getRuolo());
        if(!listd.remove(d))return false;
        dipendenteRepository.delete(d);
        unitaPadreRepository.save(u);
        return  true;
    }

    @Transactional
    public boolean removeRuolo(int idU, String nome){
        UnitaPadre u = unitaPadreRepository.findById(idU);
        if(u==null)return false;
        List<Ruolo> rlist = u.getRuoli();
        for(Ruolo r : rlist){
            if(r.getNome().equals(nome)){
                ruoloRepository.delete(r);
                rlist.remove(r);
                unitaPadreRepository.save(u);
                return true;
            }
        }
        return false;
    }
}
