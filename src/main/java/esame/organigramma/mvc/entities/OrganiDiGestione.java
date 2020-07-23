package esame.organigramma.mvc.entities;

import javax.persistence.*;
@Entity
@Table(name = "organi_di_gestione")
public class OrganiDiGestione extends UnitaPadre {

    public OrganiDiGestione(){
        super();
        this.tipo="Organi Di Gestione";
    }


}
