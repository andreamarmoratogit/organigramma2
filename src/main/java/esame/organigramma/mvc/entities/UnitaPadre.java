package esame.organigramma.mvc.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
@Entity
@Table(name = "unita_padre")
@Inheritance(strategy = InheritanceType.JOINED)
public class UnitaPadre  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Basic
    @Column(name="nome")
    private String nome;

    @ManyToMany(cascade=CascadeType.ALL)
    @JoinTable(name = "dip_unita",
            joinColumns = @JoinColumn(name = "id_unita"),
            inverseJoinColumns = @JoinColumn(name = "id_dip"))
    private List<Dipendente> listDip;

    @ManyToMany(cascade=CascadeType.ALL)
    @JoinTable(name = "ruoli_unita",
            joinColumns = @JoinColumn(name = "id_unita"),
            inverseJoinColumns = @JoinColumn(name = "id_ruolo"))
    private List<Ruolo> ruoli;

    @OneToMany(cascade=CascadeType.ALL, mappedBy = "padre")
    @JsonManagedReference
    private List<UnitaPadre> figli;

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn( name = "padre")
    @JsonBackReference
    private UnitaPadre padre;

    public UnitaPadre() {
        this.nome = "nome";
        this.listDip = new ArrayList<Dipendente>() ;
        this.ruoli = new ArrayList<Ruolo>();
        this.figli = null;
        this.padre = null;
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
    public UnitaPadre getPadre() { return padre; }
    public void setPadre(UnitaPadre padre) { this.padre = padre; }

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

    @Override
    public String toString() {
        String ret=id+", "+nome+", "+listDip+", "+ruoli+", "+padre;
        if(figli!=null){
            for(UnitaPadre u:figli)
                ret+=", "+u.nome;
        }
        return ret;
    }
}
