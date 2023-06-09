package proyecto.multiplicacionmatrices.algoritmosimplementacion;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import proyecto.multiplicacionmatrices.servicios.ExcelServicio;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
public class ExcelServicioImpl implements ExcelServicio {

    /**
     Escribe datos en una hoja de cálculo de Excel existente.
     @param id el ID del registro que se desea modificar.
     @param caso el número del caso que se desea modificar.
     @param tamano el nuevo tamaño que se desea asignar al caso.
     @param tiempoRespuesta el nuevo tiempo de respuesta que se desea asignar al caso.
     */
    @Override
    public void escribirEnHoja(int id,int caso,String tamano,String tiempoRespuesta) {

        // Cargar el archivo existente de Excel
        String nombreArchivo = "datos.xlsx";
        try (FileInputStream archivoEntrada = new FileInputStream(nombreArchivo)) {
            Workbook libro = new XSSFWorkbook(archivoEntrada);
            //Sheet hoja = libro.createSheet("Javas");
            Sheet hoja = libro.getSheetAt(0);

            // Modificar los datos de la hoja existente
            int filaActual = hoja.getLastRowNum() + 1;

            Object[] nuevosDatos = {
                    tamano, tiempoRespuesta
            };

            for (Row fila : hoja) {
                if (fila.getRowNum() == 0) {
                    // Ignorar la primera fila (encabezados)
                    continue;
                }
                int idActual = (int) fila.getCell(0).getNumericCellValue();
                if (idActual == id) {
                    // Se encontró el ID buscado en la hoja
                    // Se encontró el ID buscado en la hoja
                    Cell celdaValor1 = fila.createCell(caso*2);
                    celdaValor1.setCellValue(tamano);
                    Cell celdaValor2 = fila.createCell(caso*2+1);
                    celdaValor2.setCellValue(tiempoRespuesta);
                }
            }


            // Guardar los cambios en el archivo
            try (FileOutputStream archivoSalida = new FileOutputStream(nombreArchivo)) {
                libro.write(archivoSalida);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void escribirEnHojaEspecifica(String valor, int j) {
        try (Workbook workbook = WorkbookFactory.create(new FileInputStream("datos.xlsx"))) {
            Sheet sheet = workbook.getSheet("CasosAnalisis");
            Row row = sheet.getRow(5+j);
            if (row == null) {
                row = sheet.createRow(5+j);
            }
            Cell cell = row.createCell(26);
            cell.setCellValue(valor);

            FileOutputStream outputStream = new FileOutputStream("datos.xlsx");
            workbook.write(outputStream);
            outputStream.close();
        } catch (IOException | InvalidFormatException e) {
            e.printStackTrace();
        }
    }



}
