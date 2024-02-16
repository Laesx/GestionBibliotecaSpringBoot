package xanketes.gestionbibliotecaspringboot.modelo.entidades;

import xanketes.gestionbibliotecaspringboot.modelo.Entidad;
import xanketes.gestionbibliotecaspringboot.modelo.dao.helper.Entidades;
import java.time.LocalDateTime;

public class Prestamo extends Entidad {
    private int idPrestamo;
    private int idLibro;
    private int idUsuario;
    private LocalDateTime fechaPrestamo = LocalDateTime.now();

    public Prestamo(int idPrestamo, int idLibro, int idUsuario, LocalDateTime fechaPrestamo) {
        this.idPrestamo = idPrestamo;
        this.idLibro = idLibro;
        this.idUsuario = idUsuario;
        this.fechaPrestamo = fechaPrestamo;
    }

    public Prestamo() {
    }

    public int getIdPrestamo() {
        return idPrestamo;
    }

    public void setIdPrestamo(int idPrestamo) {
        this.idPrestamo = idPrestamo;
    }

    public int getIdLibro() {
        return idLibro;
    }

    public void setIdLibro(int idLibro) {
        this.idLibro = idLibro;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }
    //TODO revisar formateo de las fechas en los DAO
    public LocalDateTime getFechaPrestamo() {
        return fechaPrestamo;
    }

    public void setFechaPrestamo(LocalDateTime fechaPrestamo) {
        this.fechaPrestamo = fechaPrestamo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Prestamo prestamo = (Prestamo) o;

        if (idPrestamo != prestamo.idPrestamo) return false;
        if (fechaPrestamo != null ? !fechaPrestamo.equals(prestamo.fechaPrestamo) : prestamo.fechaPrestamo != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idPrestamo;
        result = 31 * result + (fechaPrestamo != null ? fechaPrestamo.hashCode() : 0);
        return result;
    }

    @Override
    public int getId() {
        return idPrestamo;
    }
    public String getFecha(){
        return fechaPrestamo.toString();
    }
    public Libro getObjLibro(){
        return Entidades.libro(idLibro);
    }

    public Usuario getObjUsuario(){
        return  Entidades.usuario(idUsuario);
    }

    @Override
    public String toString() {
        return "Prestamo{" +
                "idPrestamo=" + idPrestamo +
                ", idLibro=" + idLibro +
                ", idUsuario=" + idUsuario +
                ", fechaPrestamo=" + fechaPrestamo +
                '}';
    }
}
