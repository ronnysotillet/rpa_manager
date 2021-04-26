/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import config.connection;
import controllers.datos;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.JTable;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author 57311
 */
public class excel {
    Workbook wb;
    
    public ArrayList<String> importar(File archivo){
        ArrayList<String>  datos= new ArrayList<>();
        
        try{
            wb= WorkbookFactory.create(new FileInputStream(archivo));
            Sheet hoja = wb.getSheetAt(0);
            Iterator filaIterator = hoja.rowIterator();
            int indicefila=-1;
            while(filaIterator.hasNext()){
                indicefila++;
                Row fila = (Row) filaIterator.next();
                Iterator columnaIterator = fila.cellIterator();
                Object[] listaColumna = new Object[5];
                int indiceColumna=-1;
                while(columnaIterator.hasNext()){
                    indiceColumna++;
                    Cell celda = (Cell) columnaIterator.next();
                    if(indicefila!=0 && indiceColumna==0){
                        if(celda!=null){
                            datos.add(celda.getStringCellValue());//aqui debo colocar para meter en la base de datos
                        }
                    }
                }
            }
            
        }catch(IOException | EncryptedDocumentException | InvalidFormatException e){
            
        }
        return datos;
    }

    public String Exportar(File archivo,ArrayList<ArrayList<String>> datos1,JProgressBar jpb, JLabel lb){
        String Respuesta = "No se realizo la exportacion con exito";
        int numFila=datos1.size();//establecer el numero de filas del array
        int numColumna=18;//establecer el numero de columnas
        if(archivo.getName().endsWith("xls")){
            wb = new HSSFWorkbook();
        }else{
            wb = new XSSFWorkbook();
        }
        datos.getPorcent(numFila, 0);
        Sheet hoja = wb.createSheet("Pruebita2");
        try{
            for(int i = 0;i<numFila-1;i++){
                
                Row fila= hoja.createRow(i);
                for (int j = 0; j < numColumna; j++) {
                    Cell celda = fila.createCell(j);
                    celda.setCellValue(datos1.get(i).get(j));
                }
                int porcent = datos.getPorcent(numFila, i);
                jpb.setValue(porcent);
                lb.setText((i+1)+"/"+numFila+"  - "+porcent+" % completado");
            }
            wb.write(new FileOutputStream(archivo));
            Respuesta = "Exportacion realizada con exito";
        }catch(IOException e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return Respuesta;
    }
}
