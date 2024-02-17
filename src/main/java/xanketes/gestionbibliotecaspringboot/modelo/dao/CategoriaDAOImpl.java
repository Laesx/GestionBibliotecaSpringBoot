package xanketes.gestionbibliotecaspringboot.modelo.dao;

import org.json.JSONArray;
import org.json.JSONObject;
import xanketes.gestionbibliotecaspringboot.excepciones.CampoVacioExcepcion;
import xanketes.gestionbibliotecaspringboot.modelo.dao.helper.LogFile;
import xanketes.gestionbibliotecaspringboot.modelo.dao.helper.SolicitudesHTTP;
import xanketes.gestionbibliotecaspringboot.modelo.entidades.Categoria;
import xanketes.gestionbibliotecaspringboot.observer.Observer;
import xanketes.gestionbibliotecaspringboot.observer.Subject;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoriaDAOImpl implements CategoriaDAO, Subject {
    public CategoriaDAOImpl() {
    }

    /** Este método inserta una categoría en la BD, a través de Springboot
     * @param categoria objeto de la clase Categoria
     * @return true si la categoría se ha insertado correctamente
     * @throws Exception cualquier error asociado a la consulta http, grabar en fichero...
     */
    @Override
    public boolean inserta(Categoria categoria) throws Exception {
        boolean insertado = SolicitudesHTTP.postRequest(URL.CATEGORIAS, categoria.toJSON());
        notifyObservers();
        return insertado;
    }

    /** Este método modifica una categoría en la BD, a través de Springboot
     * @param categoria objeto de la clase Categoria
     * @return true si la categoría se ha modificado correctamente
     * @throws Exception cualquier error asociado a la consulta http, grabar en fichero...
     */
    @Override
    public boolean modificar(Categoria categoria) throws Exception {
        boolean modificado = SolicitudesHTTP.putRequest(URL.CATEGORIAS,categoria.toJSON());
        notifyObservers();
        return modificado;
    }

    /** Este método devuelve una categoría de la BD, a través de Springboot
     * @param id clave primaria de la tabla categorías
     * @return el objeto categoría asociado a una clave primaria
     * @throws Exception cualquier error asociado a la consulta http, grabar en fichero...
     */
    public Categoria getCategoriaById(int id) throws Exception {
     JSONObject jsonCategoria=SolicitudesHTTP.getRequestObject(URL.CATEGORIAS + "/"+id);
     return new Categoria(jsonCategoria.getInt("id"),jsonCategoria.getString("categoria"));
    }


    /** Este método devuelve todas las categorías de la BD, a través de Springboot
     * @param id clave primaria de la tabla categorías
     * @return true si la categoría se ha borrado correctamente
     * @throws Exception cualquier error asociado a la consulta http, grabar en fichero...
     */
    @Override
    public boolean borrar(int id) throws Exception {
        boolean borrado = SolicitudesHTTP.deleteRequest(URL.CATEGORIAS + "/"+id);
        notifyObservers();
        return borrado;
    }



    /** Este método devuelve una categoría de la BD, a través de Springboot
     * @param id clave primaria de la tabla categorías
     * @return el objeto categoría asociado a una clave primaria
     * @throws Exception cualquier error asociado a la consulta http, grabar en fichero...
     */
    @Override
    public Categoria categoria(int id) throws Exception {
        JSONObject jsonCategoria= SolicitudesHTTP.getRequestObject(URL.CATEGORIAS + "/" + id);
        return new Categoria(jsonCategoria.getInt("id"), jsonCategoria.getString("categoria"));
    }


    /** Este método devuelve todas las categorías de la BD, a través de Springboot
     * @return una lista con todas las categorías de la BD
     * @throws Exception cualquier error asociado a la consulta http, grabar en fichero...
     */
    public List<Categoria> leerAllCategorias() throws Exception {
       List<Categoria> categorias=new ArrayList<>();
        JSONArray jsonArray= SolicitudesHTTP.getRequest(URL.CATEGORIAS);
        for (int i = 0; i <jsonArray.length() ; i++) {
            JSONObject jsonCategoria= jsonArray.getJSONObject(i);
            categorias.add(new Categoria(jsonCategoria.getInt("id"),jsonCategoria.getString("categoria")));
        }
        return categorias;
    }


    private Observer observer;

    @Override
    public void register(Observer obj) {
        if (obj == null) throw new NullPointerException("Null Observer");
        observer = obj;
    }

    @Override
    public void unregister(Observer obj) {
        observer = null;
    }

    @Override
    public void notifyObservers() throws Exception {
        if (observer != null) {
            observer.update(this);
        }
    }


}
