package xanketes.gestionbibliotecaspringboot.modelo.entidades;

public class Libro {
    private int id;
    private String nombre;
    private String autor;
    private String editorial;
    private int categoria;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getEditorial() {
        return editorial;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Libro libro = (Libro) o;

        if (id != libro.id) return false;
        if (nombre != null ? !nombre.equals(libro.nombre) : libro.nombre != null) return false;
        if (autor != null ? !autor.equals(libro.autor) : libro.autor != null) return false;
        if (editorial != null ? !editorial.equals(libro.editorial) : libro.editorial != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (nombre != null ? nombre.hashCode() : 0);
        result = 31 * result + (autor != null ? autor.hashCode() : 0);
        result = 31 * result + (editorial != null ? editorial.hashCode() : 0);
        return result;
    }

    public int getCategoria() {
        return categoria;
    }



    public void setCategoria(int categoria) {
        this.categoria = categoria;
    }

    @Override
    public String toString(){
        return String.format("%d. %s %s",id,nombre,editorial);
    }
}

