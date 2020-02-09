package testinggame.utils;

import java.util.List;
import testinggame.Modelos.Usuario;
import testinggame.Modelos.Categoria;

public class Context {
    private static Usuario usuario = null;
    private static Integer ronda = null;
    private static Integer dificultad = null;
    private static Float puntaje = null;
    private static String[] categorias;
    
    public static Usuario getUsuario() {
        return usuario;
    }

    public static void setUsuario(Usuario usuario) {
        Context.usuario = usuario;
    }

    public static Integer getRonda() {
        return ronda;
    }

    public static void setRonda(Integer ronda) {
        Context.ronda = ronda;
    }

    public static Integer getDificultad() {
        return dificultad;
    }

    public static void setDificultad(Integer dificultad) {
        Context.dificultad = dificultad;
    }

    public static String[] getCategorias() {
        return categorias;
    }

    public static void setCategorias(String[] categorias) {
        Context.categorias = categorias;
    }
    
    public static void clear() {
        Context.usuario = null;
    }
    
}
