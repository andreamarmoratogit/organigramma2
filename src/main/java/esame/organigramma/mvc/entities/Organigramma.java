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

    @Version
    private int version;

    public Organigramma(){
        nome="null";
        unitaRadice=null;
    }

    public UnitaPadre getUnitaRadice() { return unitaRadice; }

    public void setUnitaRadice(UnitaPadre unitaRadice) { this.unitaRadice = unitaRadice; }

    public int getVersion() { return version; }

    public void setVersion(int version) { this.version = version; }

    public int getId() {
        return id;
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
