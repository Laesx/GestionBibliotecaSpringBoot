package xanketes.gestionbibliotecaspringboot;

import org.json.JSONArray;
import xanketes.gestionbibliotecaspringboot.modelo.dao.LibroDAOImpl;
import xanketes.gestionbibliotecaspringboot.modelo.dao.PrestamoDAOImpl;
import xanketes.gestionbibliotecaspringboot.modelo.dao.UsuarioDAOImpl;
import xanketes.gestionbibliotecaspringboot.modelo.dao.helper.SolicitudesHTTP;

public class Test {
    public static void main(String[] args) {
        try {
            //Configuracion conf= Configuracion.getInstance();
            //conf.setPassword("admin1234");

            /*
            HibernateUtilJPA.getEntityManager();

            LibroDAOImpl lib = new LibroDAOImpl();
            List<Libro> libros = lib.leerAllLibros();
            System.out.println(libros);*/

            //Sql.importCSV(Libro.class,"Libro",',');

            //System.out.println(EncriptacionDesencriptacion.desencriptar("jL9QzuscGoBRMiEjVD0vHserm2Vm9F+3be04HP9Q7aZiYE1HRHXnKd8wMje+KXvX","asdf234fsdva%l9asdnklfa@f4f_adfafaAAaad;"));


            //JSONArray jsonArray = SolicitudesHTTP.getRequest("http://localhost:8080/api-rest/libros");
            //System.out.println(jsonArray.toString(6));



           // UsuarioDAOImpl usuarioDAO= new UsuarioDAOImpl();
           // System.out.println(usuarioDAO.modificar());

            LibroDAOImpl libroDAO= new LibroDAOImpl();
            System.out.println(libroDAO.leerAllLibros());


        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
