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
public class Progress2 extends SwingWorker<Integer, String>{
    
    JLabel label;
    JProgressBar jpbar;
    File archivo;

    public Progress2(JLabel label, JProgressBar jpbar,File archivo) {
        this.label = label;
        this.jpbar = jpbar;        
        this.archivo = archivo;

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

    @Override
    protected Integer doInBackground() throws Exception {
        getLabel().setVisible(true);
        getJpbar().setVisible(true);
        getJpbar().setValue(0);
           //Poceso aqui
           int[] info = datos.importar(archivo,getJpbar(),getLabel(),false);
           getLabel().setText(info[1]+" Procesados - Actualizados Correctamente: "+info[0]+"/"+info[1]);
        getJpbar().setVisible(false);
        return 0;
    }
    
}
