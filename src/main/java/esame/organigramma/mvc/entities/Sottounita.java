package esame.organigramma.mvc.entities;

import javax.persistence.*;
import java.util.List;
@Entity
@Table(name = "sottounita")
public class Sottounita extends UnitaPadre {

    public Sottounita(){
        super();
        this.tipo="Sottounita";
    }
}
