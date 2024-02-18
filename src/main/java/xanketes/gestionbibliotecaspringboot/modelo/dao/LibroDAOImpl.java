package xanketes.gestionbibliotecaspringboot.modelo.dao;

import org.json.JSONArray;
import org.json.JSONObject;
import xanketes.gestionbibliotecaspringboot.excepciones.CampoVacioExcepcion;
import xanketes.gestionbibliotecaspringboot.modelo.dao.helper.LogFile;
import xanketes.gestionbibliotecaspringboot.modelo.dao.helper.SolicitudesHTTP;
import xanketes.gestionbibliotecaspringboot.modelo.entidades.Libro;
import xanketes.gestionbibliotecaspringboot.observer.Observer;
import xanketes.gestionbibliotecaspringboot.observer.Subject;
import java.util.ArrayList;
import java.util.List;

public class LibroDAOImpl implements LibroDAO, Subject {
    public LibroDAOImpl() {
    }

    /** Este metodo inserta un libro
     * @param libro objeto libro
     * @return true si se ha insertado el libro
     * @throws Exception cualquier error asociado a la consulta http, grabar en fichero...
     */
    @Override
    public boolean insertar(Libro libro) throws Exception {
        boolean insertado = SolicitudesHTTP.postRequest(URL.LIBROS,libro.toJSON());
        notifyObservers();
        return insertado;
    }

    /** Este metodo modifica un libro
     * @param libro objeto libro
     * @return true si se ha modificado el libro
     * @throws Exception cualquier error asociado a la consulta http, grabar en fichero...
     */
    @Override
    public boolean modificar(Libro libro) throws Exception {
        boolean actualizado = SolicitudesHTTP.putRequest(URL.LIBROS,libro.toJSON());
        notifyObservers();
        return actualizado;
    }

    /** Este metodo borra un libro a partir de su id
     * @param id clave primaria de la tabla libro
     * @return true si se ha borrado el libro
     * @throws Exception cualquier error asociado a la consulta http, grabar en fichero...
     */
    @Override
    public boolean borrar(int id) throws Exception {
        boolean borrado = SolicitudesHTTP.deleteRequest(URL.LIBROS + "/" + id);
        notifyObservers();
        return borrado;
    }

    /** Este metodo devuelve una lista de todos los libros
     * @return lista de todos los libros
     * @throws Exception cualquier error asociado a la consulta http, grabar en fichero...
     */
    @Override
    public List<Libro> leerAllLibros() throws Exception {
        List<Libro> libros=new ArrayList<>();
        JSONArray jsonArray= SolicitudesHTTP.getRequest(URL.LIBROS);
        for (int i = 0; i <jsonArray.length() ; i++) {
            JSONObject jsonLibro=jsonArray.getJSONObject(i);
            JSONObject jsonCategoria=jsonLibro.getJSONObject("categoria");
            libros.add(new Libro(jsonLibro.getInt("id"),jsonLibro.getString("nombre"),jsonLibro.getString("autor"),jsonLibro.getString("editorial"),jsonCategoria.getInt("id")));
        }
        return libros;
    }

    /** Este metodo busca libros que cumplan con los parametros de busqueda
     * @param id clave primaria de la tabla libro
     * @param nombre nombre del libro
     * @param autor autor del libro
     * @param editorial editorial del libro
     * @param categoria categoria del libro
     * @return lista de libros que cumplan con los parametros de busqueda
     * @throws Exception cualquier error asociado a la consulta http, grabar en fichero...
     */
    @Override
    public List<Libro> leerLibrosOR(int id, String nombre, String autor, String editorial, int categoria) throws Exception {

        JSONArray jsonArray= SolicitudesHTTP.getRequest(URL.LIBROS + "/busquedaOr" +
                "?id=" + id +
                "&nombre=" + nombre +
                "&autor=" + autor +
                "&editorial=" + editorial +
                "&categoria=" + categoria);
        List<Libro> lista = new ArrayList<>();

        for (int i = 0; i <jsonArray.length() ; i++) {
            JSONObject jsonLibro=jsonArray.getJSONObject(i);
            JSONObject jsonCategoria=jsonLibro.getJSONObject("categoria");
            lista.add(new Libro(jsonLibro.getInt("id"),jsonLibro.getString("nombre"),jsonLibro.getString("autor"),jsonLibro.getString("editorial"),jsonCategoria.getInt("id")));
        }
        return lista;
    }

    /**
     * para instanciar un objeto libro a partir de un id
     * @param id clave primaria de la tabla libro
     * @return el objeto libro asociado a una clave primaria
     * @throws Exception cualquier error asociado a la consulta http, grabar en fichero...
     */
    @Override
    public Libro getLibro(int id) throws Exception {
        JSONObject jsonLibro= SolicitudesHTTP.getRequestObject(URL.LIBROS + "/" +id);
        JSONObject jsonCategoria=jsonLibro.getJSONObject("categoria");

        return new Libro(
                jsonLibro.getInt("id"),
                jsonLibro.getString("nombre"),
                jsonLibro.getString("autor"),
                jsonLibro.getString("editorial"),
                jsonCategoria.getInt("id"));
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
