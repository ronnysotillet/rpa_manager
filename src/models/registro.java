/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import com.mysql.jdbc.Connection;
import config.connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author 57311
 */
public class registro {
    
    public static Connection con = connection.conector();
    
    registro(){
        
    }
    
    
    public static boolean insertarPlaca(String placa){
        String SQL ="INSERT INTO registro (placa) values (?)";
        try{
            PreparedStatement pst = con.prepareStatement(SQL);
            pst.setString(1, placa);
            pst.execute();
            return true;
        }catch(SQLException e){
            return false;
        }
    }
    
    public static boolean actualizarPlaca(String placa){
        String SQL ="UPDATE registro SET level=9 WHERE placa=?";
        try{
            PreparedStatement pst = con.prepareStatement(SQL);
            pst.setString(1, placa);
            pst.execute();
            return true;
        }catch(SQLException e){
            return false;
        }
    }
    
    
        public static ArrayList<ArrayList<String>> getRegistros(int level){
        String SQL ="SELECT * FROM registro WHERE level="+level;
        ArrayList<ArrayList<String>> datos = new ArrayList<>();
        try{
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(SQL);
            int count = 0;
            datos.add(new ArrayList<>());
            datos.get(count).add("Placa");             
            datos.get(count).add("Nombre"); 
            datos.get(count).add("Tipo Documento"); 
            datos.get(count).add("Documento"); 
            datos.get(count).add("Teléfono"); 
            datos.get(count).add("Email"); 
            datos.get(count).add("Direccion"); 
            datos.get(count).add("Aseguradora"); 
            datos.get(count).add("Fecha De Inicio Vigencia"); 
            datos.get(count).add("Fecha De Fin Vigencia"); 
            datos.get(count).add("Código"); 
            datos.get(count).add("Clase"); 
            datos.get(count).add("Motor"); 
            datos.get(count).add("Chasis"); 
            datos.get(count).add("Tipo"); 
            datos.get(count).add("Modelo"); 
            datos.get(count).add("Servicio");             
            datos.get(count).add("Marca"); 

            while(rs.next()){
                count++;
                datos.add(new ArrayList<>());
                datos.get(count).add(rs.getString("placa"));                 
                datos.get(count).add(rs.getString("name"));  
                datos.get(count).add(rs.getString("documentType"));  
                datos.get(count).add(rs.getString("document"));  
                datos.get(count).add(rs.getString("phone"));  
                datos.get(count).add(rs.getString("email"));  
                datos.get(count).add(rs.getString("direction"));  
                datos.get(count).add(rs.getString("insurence"));  
                datos.get(count).add(rs.getString("initDate"));  
                datos.get(count).add(rs.getString("finishDate"));  
                datos.get(count).add(rs.getString("code"));  
                datos.get(count).add(rs.getString("class"));  
                datos.get(count).add(rs.getString("motor"));  
                datos.get(count).add(rs.getString("chassis"));  
                datos.get(count).add(rs.getString("type"));  
                datos.get(count).add(rs.getString("model"));  
                datos.get(count).add(rs.getString("service"));  
                datos.get(count).add(rs.getString("mark"));  
            }
        }catch(SQLException e){
            System.out.println(e);
        }
        return datos;
    }
}
