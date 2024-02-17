package xanketes.gestionbibliotecaspringboot.modelo.dao;

import xanketes.gestionbibliotecaspringboot.modelo.dao.helper.SolicitudesHTTP;
import xanketes.gestionbibliotecaspringboot.modelo.entidades.Historico;
import xanketes.gestionbibliotecaspringboot.observer.Observer;
import xanketes.gestionbibliotecaspringboot.observer.Subject;

public class HistoricoDAOImpl implements HistoricoDAO, Subject {

    /** Este método inserta un historico en la BD, a través de Springboot
     * @param historico objeto historico a insertar
     * @return true si se ha insertado, false en caso contrario
     * @throws Exception cualquier error asociado a la consulta http, grabar en fichero...
     */
    @Override
    public boolean insertar(Historico historico) throws Exception {
        boolean insertado = SolicitudesHTTP.postRequest(URL.HISTORICO, historico.toJSON());
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