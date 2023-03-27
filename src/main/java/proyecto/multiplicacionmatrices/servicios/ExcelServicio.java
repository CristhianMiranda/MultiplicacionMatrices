package proyecto.multiplicacionmatrices.servicios;

/**
 Interfaz pública que define los métodos para escribir en una hoja de Excel.
 */
public interface ExcelServicio {


    /**
     Método para escribir en una hoja de Excel.
     @param id identificador del registro.
     @param caso caso de prueba ejecutado.
     @param tamano tamaño de la muestra utilizada.
     @param tiempoRespuesta tiempo de respuesta obtenido en la prueba.
     */
    void escribirEnHoja(int id,int caso,String tamano,String tiempoRespuesta);

    void escribirEnHojaEspecifica(String valor,int j);
}
