package esame.organigramma.mvc.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "ruolo")
public class Ruolo {

    @Id
    private String nome;

    public Ruolo(){
        nome="null";
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "Ruolo: " +
                "nome='" + nome ;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Ruolo)) return false;
        Ruolo ruolo = (Ruolo) o;
        return Objects.equals(getNome(), ruolo.getNome());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNome());
    }
}
