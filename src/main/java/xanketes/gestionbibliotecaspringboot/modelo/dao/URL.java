package xanketes.gestionbibliotecaspringboot.modelo.dao;

public class URL {
    public static final String BASE_URL = "http://localhost:8080";
    public static final String ENDPOINT = "/api-rest";
    public static final String LIBROS = BASE_URL + ENDPOINT + "/libros";
    public static final String CATEGORIAS = BASE_URL + ENDPOINT + "/categorias";
    public static final String USUARIOS = BASE_URL + ENDPOINT + "/usuarios";
    public static final String PRESTAMOS = BASE_URL + ENDPOINT + "/prestamos";
    public static final String HISTORICO = BASE_URL + ENDPOINT + "/historico";
    public static final String LOGIN = BASE_URL + "/login";

    public static String user = "Grupo Umpa Lumpa";
}
