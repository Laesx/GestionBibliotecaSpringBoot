package xanketes.gestionbibliotecaspringboot.helper;


import org.json.JSONObject;
import xanketes.gestionbibliotecaspringboot.modelo.dao.helper.SolicitudesHTTP;

public class LogIn {
    public static boolean logIn(String user, String password) throws Exception {
        JSONObject json = new JSONObject().put("username", user).put("password", password);
        boolean result = SolicitudesHTTP.postRequest("http://localhost:8080/login", json.toString());
        if (!result){
            throw new Exception("Error en el login. Credenciales incorrectas.");
        }
        return result;

    }
}
