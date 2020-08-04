package esame.organigramma.mvc.services;
import esame.organigramma.mvc.entities.Organigramma;
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
        UnitaPadre u=unitaFactoryService.createUnitaOrganizzativa(nomeU, "unita");
        o.setNome(nomeO);
        o.setUnitaRadice(u);
        organigrammaRepository.save(o);
        return o;
    }

    @Transactional(readOnly = true)
    public Organigramma getOrganigramma(String nome){
        Organigramma o= organigrammaRepository.findByNomeIgnoreCase(nome);
        return o;
    }

    @Transactional
    public void setNome(String nome,int id){
        Organigramma o=organigrammaRepository.findById(id);
        organigrammaRepository.save(o);
    }

    @Transactional
    public Organigramma getById(int id){
        return organigrammaRepository.findById(id);
    }

    @Transactional
    public void deleteOrg(int idOrg){
        organigrammaRepository.deleteById(idOrg);
    }

}
