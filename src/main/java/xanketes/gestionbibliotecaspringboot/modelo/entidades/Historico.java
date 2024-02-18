package xanketes.gestionbibliotecaspringboot.modelo.entidades;

import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class Historico {
    private int idHistorico;
    private String user;
    private LocalDateTime fecha = LocalDateTime.now();
    private String info;

    public Historico(int idHistorico, String user, LocalDateTime fecha, String info) {
        this.idHistorico = idHistorico;
        this.user = user;
        this.fecha = fecha;
        this.info = info;
    }

    public Historico() {
    }

    public int getIdHistorico() {
        return idHistorico;
    }

    public void setIdHistorico(int idHistorico) {
        this.idHistorico = idHistorico;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Historico that = (Historico) o;

        if (idHistorico != that.idHistorico) return false;
        if (user != null ? !user.equals(that.user) : that.user != null) return false;
        if (fecha != null ? !fecha.equals(that.fecha) : that.fecha != null) return false;
        if (info != null ? !info.equals(that.info) : that.info != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idHistorico;
        result = 31 * result + (user != null ? user.hashCode() : 0);
        result = 31 * result + (fecha != null ? fecha.hashCode() : 0);
        result = 31 * result + (info != null ? info.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Historico{" +
                "idHistorico=" + idHistorico +
                ", user='" + user + '\'' +
                ", fecha=" + fecha +
                ", info='" + info + '\'' +
                '}';
    }

    public String toJSON() throws JSONException {
        return toJSONObject().toString();
    }

    public JSONObject toJSONObject() throws JSONException {
        return new JSONObject()
                .put("id", idHistorico)
                .put("user", user)
                .put("fecha", fecha.toString())
                .put("info", info);
    }
}
