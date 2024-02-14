package xanketes.gestionbibliotecaspringboot.modelo.dao;

import xanketes.gestionbibliotecaspringboot.excepciones.CampoVacioExcepcion;
import xanketes.gestionbibliotecaspringboot.modelo.dao.helper.LogFile;
import xanketes.gestionbibliotecaspringboot.modelo.entidades.Categoria;
import xanketes.gestionbibliotecaspringboot.observer.Observer;
import xanketes.gestionbibliotecaspringboot.observer.Subject;

import java.sql.SQLException;
import java.util.List;
/**
 * Aquí implementaremos las reglas de negocio definidas
 * en la interfaz para trabajar con categorías y
 * base de datos en MySQL
 * @author AGE
 * @version 2
 */
public class CategoriaDAOImpl implements CategoriaDAO, Subject {
    private static final String sqlINSERT="INSERT INTO categoria (categoria) VALUES (?)";
    private static final String sqlUPDATE="UPDATE categoria SET categoria=? WHERE id=?";

    private static final String sqlDELETE="DELETE FROM categoria WHERE id=?";

    public CategoriaDAOImpl(){
    }

    @Override
    public boolean inserta(Categoria categoria) throws Exception {
        boolean insertado = false;


        LogFile.saveLOG(sqlINSERT.replace("?",categoria.getCategoria()));
        notifyObservers();
        return insertado;
    }

    @Override
    public boolean modificar(Categoria categoria) throws Exception {
        boolean modificado = false;


        grabaEnLogUpd(categoria,sqlUPDATE);
        notifyObservers();
        return modificado;
    }


    private void grabaEnLogUpd(Categoria categoria, String sql) throws Exception {
            sql=sql.replaceFirst("\\?",categoria.getCategoria());
            sql=sql.replaceFirst("\\?",String.valueOf(categoria.getId()));
            LogFile.saveLOG(sql);
    }


    @Override
    public boolean borrar(int id) throws Exception {
        boolean borrado = false;


        grabaEnLogDel(id,sqlDELETE);
        notifyObservers();
        return borrado;
    }

    private void grabaEnLogDel(int id, String sql) throws Exception {
        sql=sql.replaceFirst("\\?",String.valueOf(id));
        LogFile.saveLOG(sql);
    }

    /**
     * el valor máximo del campo id de la tabla categorías
     * @return valor máximo del campo id
     * @throws Exception cualquier error asociado a la consulta sql
     */
    public static int maximaId() throws Exception {
        int maximo = 0;

        return maximo;
    }

    /**
     * el valor mínimo del campo id de la tabla categorías
     * @return valor mínimo del campo id
     * @throws Exception cualquier error asociado a la consulta sql
     */
    public static int minimaId() throws Exception {
        int minimo = 0;

        return minimo;
    }

    /**
     * para instanciar un objeto categoria a partir de un id
     * @param id clave primaria de la tabla categoria
     * @return el objeto categoría asociado a una clave primaria
     * @throws Exception cualquier error asociado a la consulta sql
     */
    @Override
    public Categoria categoria(int id) throws Exception {
        return null;
    }


    /**
     * Este método estático devuelve todos las categorías de la BD,
     * este método tendremos en un futuro reimplmentarlo por rangos de x,
     * para que el rendimiento no decaiga cuando la tabla crezca
     * @return un arraylist con todos las categorias de la BD
     * @throws SQLException cualquier error asociado a la consulta sql
     * @throws CampoVacioExcepcion en el caso que contenga una categoria con categoria a null
     */
    public List<Categoria> leerAllCategorias() throws Exception {
        return null;
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
