package xanketes.gestionbibliotecaspringboot.modelo.entidades;

import org.json.JSONException;
import org.json.JSONObject;

public class Categoria {
    private int id;
    private String categoria;


    public Categoria(int id, String categoria) {
        this.id = id;
        this.categoria = categoria;
    }

    public Categoria() {
    }

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

        Categoria that = (Categoria) o;

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

    @Override
    public String toString() {
        return "Categoria{" +
                "id=" + id +
                ", categoria='" + categoria + '\'' +
                '}';
    }

    public String toJSON() throws JSONException {
        return toJSONObject().toString();
    }


    public JSONObject toJSONObject() throws JSONException {
        return new JSONObject()
                .put("id", id)
                .put("categoria", categoria);
    }


}
