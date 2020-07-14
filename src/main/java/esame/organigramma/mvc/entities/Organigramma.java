package esame.organigramma.mvc.entities;

import javax.persistence.*;

@Entity
@Table( name = "organigramma")
public class Organigramma {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id",nullable = false)
    private int id;
    @Basic
    @Column(name="nome",nullable = false,length = 50)
    private String nome;
    @OneToOne
    @JoinColumn(name = "Radice")
    private UnitaPadre unitaRadice;

    public int getId() {
        return id;
    }

    public UnitaPadre getUnita() {
        return unitaRadice;
    }

    public void setUnita(UnitaPadre unita) {
        this.unitaRadice = unita;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
