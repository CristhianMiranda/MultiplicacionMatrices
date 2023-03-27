package proyecto.multiplicacionmatrices.clases;

import proyecto.multiplicacionmatrices.algoritmosimplementacion.ExcelServicioImpl;
import proyecto.multiplicacionmatrices.servicios.ExcelServicio;

public class Excel {
    public static void escribirExcel(int id,int caso,String tamano,String tiempoRespuesta)
    {
        ExcelServicio excelServicio = new ExcelServicioImpl();
        excelServicio.escribirEnHoja(id,caso,tamano,tiempoRespuesta);
    }

    public static void escribirEnHojaEspecifica(String valor,int j)
    {
        ExcelServicio excelServicio = new ExcelServicioImpl();
        excelServicio.escribirEnHojaEspecifica(valor,j);
    }


}
