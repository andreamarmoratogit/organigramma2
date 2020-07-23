package esame.organigramma.mvc.entities;

import javax.persistence.*;

@Entity
@Table(name = "unita")
public class Unita extends UnitaPadre {
    public Unita(){
        super();
        this.tipo = "Unita";
    }

}
