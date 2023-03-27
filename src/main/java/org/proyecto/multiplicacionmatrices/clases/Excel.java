package org.proyecto.multiplicacionmatrices.clases;

import org.proyecto.multiplicacionmatrices.algoritmosimplementacion.ExcelServicioImpl;
import org.proyecto.multiplicacionmatrices.servicios.ExcelServicio;

public class Excel {
    public static void escribirExcel(int id,int caso,String tamano,String tiempoRespuesta)
    {
        ExcelServicio excelServicio = new ExcelServicioImpl();
        excelServicio.escribirEnHoja(id,caso,tamano,tiempoRespuesta);
    }
}
