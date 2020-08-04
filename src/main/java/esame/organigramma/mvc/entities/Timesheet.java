package esame.organigramma.mvc.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Timesheet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Lob
    private String attivita;


    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public String getAttivita() { return attivita; }

    public void setAttivita(String attivita) { this.attivita = attivita; }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Timesheet timesheet = (Timesheet) o;
        return getId() == timesheet.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
