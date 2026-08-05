package pe.cibertec.util;

import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class FormatoUtil {
    // Funcion de utilitario para forzar la primera letra en mayuscula y lo demas en minuscula
    public String capitalizar(String texto){
        if(texto == null || texto.isEmpty()) return texto;
        return texto.substring(0, 1).toUpperCase() + texto.substring(1).toLowerCase();
    }
}
