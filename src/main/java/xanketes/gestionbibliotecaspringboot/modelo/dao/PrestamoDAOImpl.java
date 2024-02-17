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

public class PrestamoDAOImpl implements PrestamoDAO, Subject {
    public PrestamoDAOImpl() {
    }

    /** Este método inserta un préstamo en la BD, a través de Springboot
     * @param prestamo objeto prestamo a insertar
     * @return true si se ha insertado, false en caso contrario
     * @throws Exception cualquier error asociado a la consulta http, grabar en fichero...
     */
    @Override
    public boolean insertar(Prestamo prestamo) throws Exception {
        boolean insertado = SolicitudesHTTP.postRequest(URL.PRESTAMOS,prestamo.toJSON());
        notifyObservers();
        return insertado;
    }

    /** Este método modifica un préstamo en la BD, a través de Springboot
     * @param prestamo objeto prestamo a modificar
     * @return true si se ha modificado, false en caso contrario
     * @throws Exception cualquier error asociado a la consulta http, grabar en fichero...
     */
    @Override
    public boolean modificar(Prestamo prestamo) throws Exception {
        boolean actualizado = SolicitudesHTTP.putRequest(URL.PRESTAMOS,prestamo.toJSON());
        notifyObservers();
        return actualizado;
    }

    /** Este método borra un préstamo en la BD, a través de Springboot
     * @param id clave primaria de la tabla préstamos
     * @return true si se ha borrado, false en caso contrario
     * @throws Exception cualquier error asociado a la consulta http, grabar en fichero...
     */
    @Override
    public boolean borrar(int id) throws Exception {
        boolean borrado=SolicitudesHTTP.deleteRequest(URL.PRESTAMOS + "/" + id);
        notifyObservers();
        return borrado;
    }

    /**
     * Este método devuelve todos los prestamos de la BD, a través de Springboot
     * @return un arraylist con todos los prestamos de la BD
     * @throws Exception cualquier error asociado a la consulta http, grabar en fichero...
     */
    @Override
    public List<Prestamo> leerAllPrestamos() throws Exception {
        List<Prestamo> prestamos= new ArrayList<>();
        JSONArray jsonArray = SolicitudesHTTP.getRequest(URL.PRESTAMOS);

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


    /** Este método devuelve todos los préstamos de un usuario de la BD, a través de Springboot
     * @param id clave primaria de la tabla préstamos
     * @return el objeto préstamo asociado a una clave primaria
     * @throws Exception cualquier error asociado a la consulta http, grabar en fichero...
     */
    @Override
    public Prestamo getPrestamo(int id) throws Exception {
        JSONObject jsonObject = SolicitudesHTTP.getRequestObject(URL.PRESTAMOS + "/" + id);
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