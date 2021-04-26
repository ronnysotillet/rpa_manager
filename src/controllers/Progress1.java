/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.File;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.SwingWorker;

/**
 *
 * @author 57311
 */
public class Progress1 extends SwingWorker<Integer, String>{
    
    JLabel label;
    JProgressBar jpbar;
    File archivo;
    int level;

    public Progress1(JLabel label, JProgressBar jpbar,File archivo,int level) {
        this.label = label;
        this.jpbar = jpbar;        
        this.archivo = archivo;
        this.level=level;
    }


    public JLabel getLabel() {
        return label;
    }

    public void setLabel(JLabel label) {
        this.label = label;
    }

    public JProgressBar getJpbar() {
        return jpbar;
    }

    public void setJpbar(JProgressBar jpbar) {
        this.jpbar = jpbar;
    }

    public File getArchivo() {
        return archivo;
    }

    public void setArchivo(File archivo) {
        this.archivo = archivo;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    @Override
    protected Integer doInBackground() throws Exception {
        getLabel().setVisible(true);
        getJpbar().setVisible(true);
        getJpbar().setValue(0);
           //Poceso aqui
           int[] info = datos.exportar(getArchivo(),getJpbar(),getLabel(),getLevel());
           getLabel().setText(info[1]+" Procesados - Exportados Correctamente: "+info[0]+"/"+info[1]);
        getJpbar().setVisible(false);
        return 0;
    }
    
}
