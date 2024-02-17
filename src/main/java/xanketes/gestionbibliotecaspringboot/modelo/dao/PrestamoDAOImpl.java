package xanketes.gestionbibliotecaspringboot.modelo.dao;

import org.json.JSONArray;
import org.json.JSONObject;
import xanketes.gestionbibliotecaspringboot.modelo.dao.helper.LogFile;
import xanketes.gestionbibliotecaspringboot.modelo.dao.helper.SolicitudesHTTP;
import xanketes.gestionbibliotecaspringboot.modelo.entidades.Prestamo;
import xanketes.gestionbibliotecaspringboot.observer.Observer;
import xanketes.gestionbibliotecaspringboot.observer.Subject;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Aquí implementaremos las reglas de negocio definidas
 * en la interfaz para trabajar con préstamos y
 * base de datos en MySQL
 * @author AGE
 * @version 2
 */
public class PrestamoDAOImpl implements PrestamoDAO, Subject {

    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private static final String sqlINSERT="INSERT INTO prestamos (idLibro,idUsuario,fechaPrestamo) VALUES (?,?,?)";
    private static final String sqlUPDATE="UPDATE prestamos SET idLibro=?, idUsuario=?, fechaPrestamo=? WHERE idPrestamo = ?";
    private static final String sqlDELETE="DELETE FROM prestamos WHERE idPrestamo = ?";
    public PrestamoDAOImpl() {
    }
    @Override
    public boolean insertar(Prestamo prestamo) throws Exception {
        boolean insertado = SolicitudesHTTP.postRequest("http://localhost:8080/api-rest/prestamos",prestamo.toJSON());
        grabaEnLogIns(prestamo,sqlINSERT);
        notifyObservers();
        return insertado;
    }
    private void grabaEnLogIns(Prestamo prestamo, String sql) throws Exception {
        /*
        sql = sql.replaceFirst("\\?",String.valueOf(prestamo.getIdLibro()));
        sql = sql.replaceFirst("\\?",String.valueOf(prestamo.getIdUsuario()));
        sql = sql.replaceFirst("\\?", prestamo.getFechaPrestamo().format(formatter));
         */
        LogFile.saveLOG(sql);
    }

    @Override
    public boolean modificar(Prestamo prestamo) throws Exception {
        boolean actualizado = SolicitudesHTTP.putRequest("http://localhost:8080/api-rest/prestamos",prestamo.toJSON());

        grabaEnLogUpd(prestamo,sqlUPDATE);
        notifyObservers();
        return actualizado;
    }
    private void grabaEnLogUpd(Prestamo prestamo, String sql) throws Exception {
        /*
        sql = sql.replaceFirst("\\?",String.valueOf(prestamo.getIdLibro()));
        sql = sql.replaceFirst("\\?",String.valueOf(prestamo.getIdUsuario()));
        sql = sql.replaceFirst("\\?", prestamo.getFechaPrestamo().format(formatter));
        sql = sql.replaceFirst("\\?",String.valueOf(prestamo.getIdPrestamo()));
         */
        LogFile.saveLOG(sql);
    }

    @Override
    public boolean borrar(int id) throws Exception {
        boolean borrado=SolicitudesHTTP.deleteRequest("http://localhost:8080/api-rest/prestamos/"+id);
        grabaEnLog(id,sqlDELETE);
        notifyObservers();
        return borrado;
    }
    private void grabaEnLog(int id,String sql) throws Exception {
        sql = sql.replaceFirst("\\?",String.valueOf(id));
        LogFile.saveLOG(sql);
    }

    /**
     * Este método devuelve todos los prestamos de la BD, a través de Springboot
     * @return un arraylist con todos los prestamos de la BD
     * @throws Exception cualquier error asociado a la consulta http, grabar en fichero...
     */
    @Override
    public List<Prestamo> leerAllPrestamos() throws Exception {
        List<Prestamo> prestamos= new ArrayList<>();
        JSONArray jsonArray = SolicitudesHTTP.getRequest("http://localhost:8080/api-rest/prestamos");

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);

            JSONObject jsonLibro = jsonObject.getJSONObject("libro");
            JSONObject jsonUsuario = jsonObject.getJSONObject("usuario");

            prestamos.add(new Prestamo(jsonObject.getInt("idPrestamo"),
                    jsonLibro.getInt("id"),
                    jsonUsuario.getInt("id"),
                    LocalDateTime.parse(jsonObject.getString("fechaPrestamo"), DateTimeFormatter.ISO_OFFSET_DATE_TIME)));
        }

        return prestamos;
    }

    /**
     * Para instanciar un objeto préstamos a partir de un id
     * @param id clave primaria de la tabla préstamos
     * @return el objeto prestamos asociado a una clave primaria
     * @throws Exception cualquier error asociado a la consulta sql, grabar en fichero, ...
     */
    @Override
    public Prestamo getPrestamo(int id) throws Exception {
        JSONObject jsonObject = SolicitudesHTTP.getRequestObject("http://localhost:8080/api-rest/prestamos/"+id);
        JSONObject jsonLibro = jsonObject.getJSONObject("libro");
        JSONObject jsonUsuario = jsonObject.getJSONObject("usuario");

        return new Prestamo(jsonObject.getInt("idPrestamo"),
                jsonLibro.getInt("id"),
                jsonUsuario.getInt("id"),
                LocalDateTime.parse(jsonObject.getString("fechaPrestamo"), DateTimeFormatter.ISO_OFFSET_DATE_TIME));
    }


    private Observer observer;

    @Override
    public void register(Observer obj){
        if (obj == null) throw new NullPointerException("Null Observer");
        observer=obj;
    }

    @Override
    public void unregister(Observer obj) {
        observer=null;
    }

    @Override
    public void notifyObservers() throws Exception {
        if (observer!=null){
            observer.update(this);
        }
    }

}