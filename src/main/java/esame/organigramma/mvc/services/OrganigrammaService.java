package esame.organigramma.mvc.services;
import esame.organigramma.mvc.entities.Organigramma;
import esame.organigramma.mvc.entities.Unita;
import esame.organigramma.mvc.entities.UnitaPadre;
import esame.organigramma.mvc.repositories.OrganigrammaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrganigrammaService {
    @Autowired
    OrganigrammaRepository organigrammaRepository;
    @Autowired
    UnitaFactoryService unitaFactoryService;

    @Transactional
    public Organigramma createOrganigramma(String nomeO, String nomeU){
        Organigramma o=new Organigramma();
        UnitaPadre u=unitaFactoryService.createUnita(nomeU);
        o.setNome(nomeO);
        o.setUnita(u);
        organigrammaRepository.save(o);
        return o;
    }
    @Transactional
    public Organigramma getOrganigramma(String nome){
        return organigrammaRepository.findByNome(nome);

    }

    @Transactional
    public void setNome(String nome,int id){
        Organigramma o=organigrammaRepository.findById(id);
        organigrammaRepository.save(o);
    }

    @Transactional
    public void setRadice(int id,String nome){
        Unita u =unitaFactoryService.createUnita(nome);
        Organigramma o=organigrammaRepository.findById(id);
        o.setUnita(u);

    }

}
