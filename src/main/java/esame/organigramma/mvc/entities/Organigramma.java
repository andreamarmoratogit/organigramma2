package esame.organigramma.mvc.entities;
import javax.persistence.*;
import java.util.Objects;

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

    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "Radice")
    private UnitaPadre unitaRadice;

    public Organigramma(){
        nome="null";
        unitaRadice=null;
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Organigramma)) return false;
        Organigramma that = (Organigramma) o;
        return getId() == that.getId() &&
                Objects.equals(getNome(), that.getNome());
    }
    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        return "Organigramma: " +
                "id= " + id +
                ", nome= " + nome + ' ' +
                ", unitaRadice= " + unitaRadice.toString();
    }
}
