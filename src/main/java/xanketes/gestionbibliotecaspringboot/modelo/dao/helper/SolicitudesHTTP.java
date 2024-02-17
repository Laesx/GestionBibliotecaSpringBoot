package xanketes.gestionbibliotecaspringboot.modelo.dao.helper;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class SolicitudesHTTP {
    private SolicitudesHTTP() {
    }
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
        return borrado;
    }

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
        return actualizado;
    }


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
        return jsonArray;
    }

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
        return jsonObject;
    }


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
        return insertado;
    }

    //TODO Revisar esta función un poco, porque da más errores de los que soluciona
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

