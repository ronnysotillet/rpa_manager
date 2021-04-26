/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.File;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import models.excel;
import models.registro;

/**
 *
 * @author 57311
 */
public class datos {
   
    private static excel ex = new excel();
    
    
    public static int[] importar(File archivo,JProgressBar jpb, JLabel lb,boolean nuevo){
        jpb.setIndeterminate(true);
        lb.setText("Leyendo Archivo");
        ArrayList<String> info = ex.importar(archivo);
        lb.setText("");
        jpb.setIndeterminate(false);
        int[] result= new int[2];
        int insertadas=0;
        for (int i = 0; i < info.size(); i++) {
            if(nuevo){
                if(registro.insertarPlaca(info.get(i))){
                    insertadas++;
                }
            }else{
                if(registro.actualizarPlaca(info.get(i))){
                    insertadas++;
                }
            }
            
            int porcent=getPorcent(info.size(), (i+1));
            jpb.setValue(porcent);
            lb.setText((i+1)+"/"+info.size()+"  - "+porcent+" % completado");
        }
        System.out.println(info);
        result[0]=insertadas;
        result[1]=info.size();
        return result;
    }
    
    public static int[] importarJulian(File archivo,JProgressBar jpb, JLabel lb){
        jpb.setIndeterminate(true);
        lb.setText("Leyendo Archivo");
        ArrayList<String> info = ex.importar(archivo);
        lb.setText("");
        jpb.setIndeterminate(false);
        int[] result= new int[2];
        int insertadas=0;
        for (int i = 0; i < info.size(); i++) {
            if(registro.insertarPlaca(info.get(i))){
                insertadas++;
            }
            int porcent=getPorcent(info.size(), (i+1));
            jpb.setValue(porcent);
            lb.setText((i+1)+"/"+info.size()+"  - "+porcent+" % completado");
        }
        System.out.println(info);
        result[0]=insertadas;
        result[1]=info.size();
        return result;
    }
    
    public static int getPorcent(int total, int variable){
        int result=(variable*100)/total;
        return result;
    }
    
    public static  int[] exportar(File archivo,JProgressBar jpb, JLabel lb,int level){
        int[] datos= new int[2];
        jpb.setIndeterminate(true);
        lb.setText("Extrayendo Datos");
        ArrayList<ArrayList<String>> info = registro.getRegistros(level);
        lb.setText("");
        jpb.setIndeterminate(false);
        ex.Exportar(archivo, info, jpb, lb);
        return datos;
        
    }
    
    
    
    
}
