package esame.organigramma.mvc.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

    @ManyToOne()
    @JoinColumn( name = "padre")
    @JsonBackReference
    private UnitaPadre padre;

    @Basic
    @JoinColumn(name = "tipo")
    protected String tipo;

    public UnitaPadre() {
        this.nome = "nome";
        this.listDip = new ArrayList<>() ;
        this.ruoli = new ArrayList<>();
        this.figli = new ArrayList<>();
        this.padre = null;
    }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }
    public List<UnitaPadre> getFigli() {
        return figli;
    }
    public void setFigli(List<UnitaPadre> figli) {
        this.figli = figli;
    }
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
    public void addDip(Dipendente d) {
        listDip.add(d);
    }
    public void addRuolo(Ruolo r){
        if(!ruoli.contains(r))ruoli.add(r);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UnitaPadre)) return false;
        UnitaPadre that = (UnitaPadre) o;
        return getId() == that.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
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
