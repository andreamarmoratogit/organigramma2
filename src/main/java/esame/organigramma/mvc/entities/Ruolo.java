package esame.organigramma.mvc.entities;

import javax.persistence.*;

@Entity
@Table(name = "ruolo")
public class Ruolo {

    @Id
    String nome;



    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
