package xanketes.gestionbibliotecaspringboot.modelo.dao.helper;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

import static xanketes.gestionbibliotecaspringboot.modelo.dao.helper.LogFile.saveLOG;
import static xanketes.gestionbibliotecaspringboot.modelo.dao.helper.LogFile.saveLOGsinBD;

public class SolicitudesHTTP {
    private SolicitudesHTTP() {
    }

    /** Este método realiza una petición DELETE a la url que se le pasa como parámetro
     * @param pUrl url a la que se va a realizar la petición
     * @return true si se ha borrado correctamente
     * @throws Exception cualquier error asociado a la consulta http...
     */
    public static boolean deleteRequest(String pUrl) throws Exception {
        boolean borrado = false;
        HttpURLConnection conn = null;
        try {
            URL url = new URL(pUrl);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("DELETE");
            if (conn.getResponseCode() == 200)
                borrado = true;
            else throw new Exception(String.format("Fallo en la conexión: %s\n", pUrl));
        } finally {
            if (conn != null)
                conn.disconnect();
        }
        saveLOG("DELETE: " + pUrl);
        return borrado;
    }

    /** Este método realiza una petición PUT a la url que se le pasa como parámetro
     * @param pUrl url a la que se va a realizar la petición
     * @param json json que se va a enviar
     * @return true si se ha actualizado correctamente
     * @throws Exception cualquier error asociado a la consulta http...
     */
    public static boolean putRequest(String pUrl,String json) throws Exception {
        boolean actualizado = false;
        HttpURLConnection conn = null;
        try {
            URL url = new URL(pUrl);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("PUT");
            conn.setRequestProperty("Content-Type", "application/json; utf-8");
            conn.setRequestProperty("Accept", "application/json");
            conn.setDoOutput(true);
            try (OutputStream os = conn.getOutputStream()) {
                byte[] input = json.getBytes("utf-8");
                os.write(input, 0, input.length);
            }
            if (conn.getResponseCode() == 200)
                actualizado = true;
            else postError(conn,pUrl);
        } finally {
            if (conn != null )
                conn.disconnect();
        }
        saveLOG("PUT: " + pUrl + " " + json);
        return actualizado;
    }


    /** Este método realiza una petición GET a la url que se le pasa como parámetro
     * @param pUrl url a la que se va a realizar la petición
     * @return JSONArray con la respuesta
     * @throws Exception cualquier error asociado a la consulta http...
     */
    public static JSONArray getRequest(String pUrl) throws Exception {
        JSONArray jsonArray=null;
        HttpURLConnection conn
                = null;
        try {
            URL url
                    = new URL(pUrl);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestProperty("Accept", "application/json");
            if (conn.getResponseCode() == 200) {
                Scanner scanner
                        = new Scanner(conn.getInputStream());
                String response
                        = scanner.useDelimiter("\\Z").next();
                scanner.close();
                jsonArray = new JSONArray(response);
            } else throw new Exception(String.format("Fallo en la conexión: %s\n",pUrl));
        } finally {
            if (conn != null )
                conn.disconnect();
        }
        saveLOG("GET: " + pUrl);
        return jsonArray;
    }

    /** Este método realiza una petición GET a la url que se le pasa como parámetro
     * @param pUrl url a la que se va a realizar la petición
     * @return JSONObject con la respuesta
     * @throws Exception cualquier error asociado a la consulta http...
     */
    public static JSONObject getRequestObject(String pUrl) throws Exception {
        JSONObject jsonObject=null;
        HttpURLConnection conn = null;
        try {
            URL url = new URL(pUrl);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestProperty("Accept", "application/json");
            if (conn.getResponseCode() == 200) {
                Scanner scanner = new Scanner(conn.getInputStream());
                String response = scanner.useDelimiter("\\Z").next();
                scanner.close();
                jsonObject = new JSONObject(response);
            } else throw new Exception(String.format("Fallo en la conexión: %s\n", pUrl));
        } finally {
            if (conn != null )
                conn.disconnect();
        }
        saveLOG("GET: " + pUrl);
        return jsonObject;
    }


    /** Este método realiza una petición POST a la url que se le pasa como parámetro
     * @param pUrl url a la que se va a realizar la petición
     * @param json json que se va a enviar
     * @return true si se ha insertado correctamente
     * @throws Exception cualquier error asociado a la consulta http...
     */
    public static boolean postRequest(String pUrl,String json) throws Exception {
        boolean insertado=false;
        HttpURLConnection conn = null;
        try {
            URL url = new URL(pUrl);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json; utf-8");
            conn.setRequestProperty("Accept", "application/json");
            conn.setDoOutput(true);
            try (OutputStream os = conn.getOutputStream()) {
                byte[] input = json.getBytes("utf-8");
                os.write(input, 0, input.length);
            }
            if (conn.getResponseCode() == 200)
                insertado = true;
            else postError(conn,pUrl);
        } finally {
            if (conn != null )
                conn.disconnect();
        }
        // Comprobamos si la url es la de historico, para no guardar el log en la base de datos
        // (ya que si no empieza un bucle infinito)
        if (!pUrl.equals(xanketes.gestionbibliotecaspringboot.modelo.dao.URL.HISTORICO))
            saveLOG("POST: " + pUrl + " " + json);
        return insertado;
    }


    /** Este método devuelve el error que se ha producido en la petición
     * @param conn conexión
     * @param pUrl url
     * @throws Exception cualquier error asociado a la consulta http...
     */
    private static void postError(HttpURLConnection conn,String pUrl) throws Exception {
        Scanner scanner = new Scanner(conn.getErrorStream());
        String response = scanner.useDelimiter("\\Z").next();
        scanner.close();
        JSONObject jsonObject = new JSONObject(response);
        String linea1 = String.format("Fallo desde: %s", pUrl);
        String linea2 = String.format("Error: %s (%s)\n%s",jsonObject.get("error"),
                jsonObject.get("status"),jsonObject.get("message"));
        if (jsonObject.has("errors")) {
            JSONArray jsonArray = jsonObject.getJSONArray("errors");
            String linea3 = jsonArray.getJSONObject(0).get("defaultMessage").toString();
            throw new Exception(String.format("%s\n%s\n%s\n", linea1, linea2,linea3));
        } else throw new Exception(String.format("%s\n%s\n", linea1,linea2 ));
    }



}

