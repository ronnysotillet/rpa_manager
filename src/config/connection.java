/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package config;

import com.mysql.jdbc.Connection;
import forms.MainForm;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author 57311
 */
public class connection {
    public static Connection con;
    
    private static final String DRIVER="com.mysql.jdbc.Driver";
    private static final String URL="jdbc:mysql://localhost:3306/rpa-ams";
    
    public static Connection conector(){
        con = null;
        try{
            Class.forName(DRIVER);
            con = (Connection) DriverManager.getConnection(URL,MainForm.txtUserdb.getText() ,MainForm.txtPassdb.getText());
            
            if(con!=null){
                System.out.println("Conexion establecida");
            }
        }catch(ClassNotFoundException | SQLException e){
            JOptionPane.showMessageDialog(null,"No se pudo establecer conexion con la base de datos: "+e);
        }
        return con;
    }
    
    
}
