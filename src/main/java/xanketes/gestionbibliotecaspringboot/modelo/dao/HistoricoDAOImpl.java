package xanketes.gestionbibliotecaspringboot.modelo.dao;

import xanketes.gestionbibliotecaspringboot.modelo.dao.helper.LogFile;
import xanketes.gestionbibliotecaspringboot.modelo.dao.helper.SolicitudesHTTP;
import xanketes.gestionbibliotecaspringboot.modelo.entidades.Historico;
import xanketes.gestionbibliotecaspringboot.observer.Observer;
import xanketes.gestionbibliotecaspringboot.observer.Subject;
import xanketes.gestionbibliotecaspringboot.singleton.Configuracion;

import java.io.IOException;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;

/**
 * Aquí implementaremos las reglas de negocio definidas
 * en la interfaz para trabajar con historico y
 * base de datos en MySQL
 * @author AGE
 * @version 2
 */
public class HistoricoDAOImpl implements HistoricoDAO, Subject {

    @Override
    public boolean insertar(Historico historico) throws Exception {
        boolean insertado = SolicitudesHTTP.postRequest(URl.HISTORICO+historico.toJSON());
        notifyObservers();
       return insertado;
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