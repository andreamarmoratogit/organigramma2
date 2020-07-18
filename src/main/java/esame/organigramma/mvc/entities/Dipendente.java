package esame.organigramma.mvc.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "dipendente")
public class Dipendente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Basic
    @Column(name = "nome")
    private String nome;
    @Basic
    @Column(name = "cognome")
    private String cognome;
    @Basic
    @Column(name="ruolo")
    private String ruolo;

    public Dipendente(){
        nome="null";
        cognome="null";
        ruolo="null";
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getNome() {
        return nome;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getRuolo() { return ruolo; }

    public void setRuolo(String ruolo) { this.ruolo = ruolo; }

    public String toString(){
        return nome+" "+cognome+" "+ruolo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Dipendente)) return false;
        Dipendente that = (Dipendente) o;
        return getId() == that.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
