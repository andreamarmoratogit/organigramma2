package esame.organigramma.mvc.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
@Entity
@Table(name = "unita_padre")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class UnitaPadre  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Basic
    @Column(name="nome")
    private String nome;

    @ManyToMany
    @JoinTable(name = "dip_unita",
            joinColumns = @JoinColumn(name = "id_unita"),
            inverseJoinColumns = @JoinColumn(name = "id_dip"))
    private List<Dipendente> listDip;

    @ManyToMany
    @JoinTable(name = "ruoli_unita",
            joinColumns = @JoinColumn(name = "id_unita"),
            inverseJoinColumns = @JoinColumn(name = "id_ruolo"))
    private List<Ruolo> ruoli;

    @OneToMany(mappedBy = "padre")
    private List<UnitaPadre> figli;

    @ManyToOne
    @JoinColumn(name = "padre")
    private UnitaPadre padre;

    public UnitaPadre() {
        this.id = -1;
        this.nome = "nome";
        this.listDip = new ArrayList<Dipendente>() ;
        this.ruoli = new ArrayList<Ruolo>();
        this.figli = null;
    }

    public void removeFiglio(UnitaPadre u){
        figli.remove(u);
    }

    public List<UnitaPadre> getFigli() {
        return figli;
    }

    public void setFigli(List<UnitaPadre> figli) {
        this.figli = figli;
    }

    //setter and getter
    public List<Ruolo> getRuoli() {
        return ruoli;
    }
    public void setRuoli(List<Ruolo> ruoli) {
        this.ruoli = ruoli;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getNome() { return nome; }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public List<Dipendente> getListDip() { return listDip; }
    public void setListDip(List<Dipendente> listDip) { this.listDip = listDip; }

    //adder
    public void addDip(Dipendente d) {
        listDip.add(d);
    }

    public void addRuolo(Ruolo r){
        if(!ruoli.contains(r))ruoli.add(r);
    }

    public boolean addFiglio(UnitaPadre u){
        if(figli.contains(u))return false;
        figli.add(u);
        return true;
    }
}
