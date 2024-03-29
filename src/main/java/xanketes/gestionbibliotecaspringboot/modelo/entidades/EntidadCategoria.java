package xanketes.gestionbibliotecaspringboot.modelo.entidades;

import jakarta.persistence.*;

@Entity
@Table(name = "categoria", schema = "BIBLIOTECA")
public class EntidadCategoria {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;
    @Basic
    @Column(name = "categoria", nullable = true, length = -1)
    private String categoria;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EntidadCategoria that = (EntidadCategoria) o;

        if (id != that.id) return false;
        if (categoria != null ? !categoria.equals(that.categoria) : that.categoria != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (categoria != null ? categoria.hashCode() : 0);
        return result;
    }
}
