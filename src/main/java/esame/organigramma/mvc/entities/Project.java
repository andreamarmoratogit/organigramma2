package esame.organigramma.mvc.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "project")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "titolo")
    private String titolo;

    @Lob
    @Column(name = "descrizione", columnDefinition = "CLOB")
    private String descrizione;

    @OneToMany( cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable( name = "proj_time",
    joinColumns = @JoinColumn(name = "idp"),
    inverseJoinColumns = @JoinColumn(name = "idt"))
    private List<Timesheet> timesheet;

    @OneToOne()
    @JoinColumn(name = "id_dip")
    private Dipendente dip;

    public Project(){
        this.titolo="";
        this.descrizione="";
        this.timesheet = new ArrayList<Timesheet>();
        this.dip=new Dipendente();
    }

    @Version
    int version;

    public int getId() { return id; }

    public void setId(int id) { this.id = id;}

    public List<Timesheet> getTimesheet() { return timesheet; }

    public void setTimesheet(List<Timesheet> timeSheet) { this.timesheet = timeSheet; }

    public Dipendente getDip() { return dip; }

    public void setDip(Dipendente dip) { this.dip = dip; }

    public String getTitolo() { return titolo; }

    public void setTitolo(String titolo) { this.titolo = titolo; }

    public String getDescrizione() { return descrizione; }

    public void setDescrizione(String descrizione) { this.descrizione = descrizione; }

    public int getVersion() { return version; }

    public void setVersion(int version) { this.version = version; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Project project = (Project) o;
        return getId() == project.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
