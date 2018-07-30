/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datos;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author brian
 */
public class DVehiculo {
    private int idVehiculo;
    private String MarcaModelo;
    private String Anio;
    private String Patente;
    private String NroChasis;
    private String NroMotor;
    private int Chofer;

    public int getIdVehiculo() {
        return idVehiculo;
    }

    public void setIdVehiculo(int idVehiculo) {
        this.idVehiculo = idVehiculo;
    }

    public String getMarcaModelo() {
        return MarcaModelo;
    }

    public void setMarcaModelo(String MarcaModelo) {
        this.MarcaModelo = MarcaModelo;
    }

    public String getAnio() {
        return Anio;
    }

    public void setAnio(String Anio) {
        this.Anio = Anio;
    }

    public String getPatente() {
        return Patente;
    }

    public void setPatente(String Patente) {
        this.Patente = Patente;
    }

    public String getNroChasis() {
        return NroChasis;
    }

    public void setNroChasis(String NroChasis) {
        this.NroChasis = NroChasis;
    }

    public String getNroMotor() {
        return NroMotor;
    }

    public void setNroMotor(String NroMotor) {
        this.NroMotor = NroMotor;
    }

    public int getChofer() {
        return Chofer;
    }

    public void setChofer(int Chofer) {
        this.Chofer = Chofer;
    }

    public DVehiculo() {
    }

    public DVehiculo(int idVehiculo, String MarcaModelo, String Anio, String Patente, String NroChasis, String NroMotor, int Chofer) {
        this.idVehiculo = idVehiculo;
        this.MarcaModelo = MarcaModelo;
        this.Anio = Anio;
        this.Patente = Patente;
        this.NroChasis = NroChasis;
        this.NroMotor = NroMotor;
        this.Chofer = Chofer;
    }
    
    //Metodo Insertar
    public void Insertar(String marcamodelo, String anio,String patente, 
                   String nrochasis, String nromotor, int chofer, String imagen1, String imagen2, String imagen3, 
                   String imagen4, String imagen5, String imagen6, String imagen7, String imagen8, String imagen9, String imagen10){
        
        Conexion con = new Conexion();
        try {
            String sql = ( "insert into Vehiculo (MarcaModelo, Anio, Patente,NroChasis,NroMotor,Chofer,Img1,Img2,Img3,Img4,Img5,Img6,Img7,Img8,Img9,Img10)  "
                    + "values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
            

            con.connect();
            PreparedStatement st = con.connect().prepareStatement(sql);
            st.setString(1, marcamodelo);
            st.setString(2, anio);
            st.setString(3, patente);
            st.setString(4, nrochasis);
            st.setString(5, nromotor);
            st.setInt(6, chofer);
            st.setBytes(7, readFile(imagen1));
            st.setBytes(8, readFile(imagen2));
            st.setBytes(9, readFile(imagen3));
            st.setBytes(10, readFile(imagen4));
            st.setBytes(11, readFile(imagen5));
            st.setBytes(12, readFile(imagen6));
            st.setBytes(13, readFile(imagen7));
            st.setBytes(14, readFile(imagen8));
            st.setBytes(15, readFile(imagen9));
            st.setBytes(16, readFile(imagen10));
            st.executeUpdate(); 
            System.out.println("Registro Cargado");
            con.close();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            

        }
        con.close(); 
         
    }
    public void Editar(int idvehiculo, String marcamodelo, String anio,String patente, 
                   String nrochasis, String nromotor, int chofer, String imagen1, String imagen2, String imagen3, 
                   String imagen4, String imagen5, String imagen6, String imagen7, String imagen8, String imagen9, String imagen10){
        Conexion con = new Conexion();
        try {
            String sql = ( "update  Vehiculo "
                    + "set (MarcaModelo = ?, Anio = ?, Patente = ?,NroChasis = ?,NroMotor = ?,Chofer = ?,Img1 = ?,Img2 = ?,Img3 = ?,Img4 = ?,Img5 = ?,Img6 = ?,Img7 = ?,Img8 = ?,Img9 = ?,Img10 = ?)  "
                  
                    + "where idVehiculo = ?");
           
            con.connect();
            PreparedStatement st = con.connect().prepareStatement(sql);
           
           
            st.setString(1, marcamodelo);
            st.setString(2, anio);
            st.setString(3, patente);
            st.setString(4, nrochasis);
            st.setString(5, nromotor);
            st.setInt(6, chofer);
            st.setBytes(7, readFile(imagen1));
            st.setBytes(8, readFile(imagen2));
            st.setBytes(9, readFile(imagen3));
            st.setBytes(10, readFile(imagen4));
            st.setBytes(11, readFile(imagen5));
            st.setBytes(12, readFile(imagen6));
            st.setBytes(13, readFile(imagen7));
            st.setBytes(14, readFile(imagen8));
            st.setBytes(15, readFile(imagen9));
            st.setBytes(16, readFile(imagen10));
            st.setInt(17, idvehiculo);
           
            st.executeUpdate(); 
            System.out.println("Registro Editado");
            con.close();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            con.close(); 
        } 
    }
    private byte[] readFile(String file)
     {
         ByteArrayOutputStream bos = null;
         try {
             
             File f = new File(file);
             FileInputStream fis = new FileInputStream(f);
             byte[] buffer = new byte[1024];
             bos = new ByteArrayOutputStream();
             for (int len; (len = fis.read(buffer)) != -1;) {
                 bos.write(buffer,0,len);
             }
             
         } catch (FileNotFoundException e) 
         {
             System.err.println(e.getMessage());  
         } catch(IOException e2){
             System.err.println(e2.getMessage());  
         }
         return bos != null ? bos.toByteArray() : null;
     }
    
    
    public void Eliminar(int id){
         Conexion con = new Conexion();
        try {
            String sql = ( "delete from  Vehiculo where idVehiculo = ?");
            con.connect();
            PreparedStatement st = con.connect().prepareStatement(sql);
     
            st.setInt(1, id);
           
            st.executeUpdate(); 
            System.out.println("Registro Eliminado");
            con.close();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            con.close(); 
        }
        
    }
    
    public void Mostrar(){
        Conexion con = new Conexion();
        try {
        con.connect();
        String sql = "Select idVehiculo, MarcaModelo, Anio, Patente,NroChasis,NroMotor,Chofer,Img1,Img2,Img3,Img4,Img5,Img6,Img7,Img8,Img9,Img10"
                   + " from Vehiculo"; 
        Statement st = con.connect().createStatement();
        ResultSet rs = st.executeQuery(sql);
            
        
        while(rs.next())
        {
         System.out.println(rs.getInt("idVehiculo")+"\t"+rs.getString("MarcaModelo")+"\t"+rs.getString("Anio")+
                 "\t"+rs.getString("NroChasis")+"\t"+rs.getString("NroMotor")+"\t"+rs.getInt("Chofer")+"\t"+rs.getString("Img1")+
                 "\t"+rs.getString("Img2")+"\t"+rs.getString("Img3")+"\t"+rs.getString("Img4")+"\t"+rs.getString("Img5")+"\t"+
                 rs.getString("Img6")+"\t"+rs.getString("Img7")+"\t"+rs.getString("Img8")+"\t"+rs.getString("Img9")+"\t"+
                 rs.getString("Img10")+"\t");
        }
        con.close();
        
        } catch (SQLException ex) {
             ex.printStackTrace();
        }
        con.close();
        
    } 
     public void Buscar(String tb){
        Conexion con = new Conexion();
        try {
        con.connect();
        String sql = "Select *"
                   + " from Vehiculo"
                   + " where MarcaModelo LIKE ? ";
              
        PreparedStatement st1 = con.connect().prepareStatement(sql);
        st1.setString(1,"%"+tb+"%");
        ResultSet rs = st1.executeQuery();
            
        
        while(rs.next())
        {
         System.out.println(rs.getInt("idVehiculo")+"\t"+rs.getString("MarcaModelo")+"\t"+rs.getString("Anio")+
                 "\t"+rs.getString("NroChasis")+"\t"+rs.getString("NroMotor")+"\t"+rs.getInt("Chofer")+"\t"+rs.getString("Img1")
//                 +"\t"+rs.getString("Img2")+"\t"+rs.getString("Img3")+"\t"+rs.getString("Img4")+"\t"+rs.getString("Img5")+"\t"+
//                 rs.getString("Img6")+"\t"+rs.getString("Img7")+"\t"+rs.getString("Img8")+"\t"+rs.getString("Img9")+"\t"+
//                 rs.getString("Img10")+"\t");
         );
        }
        con.close();
        
        } catch (SQLException ex) {
             ex.printStackTrace();
        }
        con.close();
        
    } 
      public void BuscarPorPatente(String tb){
        Conexion con = new Conexion();
        try {
        con.connect();
        String sql = "Select *"
                   + " from Vehiculo"
                   + " where Patente LIKE ? ";
              
        PreparedStatement st1 = con.connect().prepareStatement(sql);
        st1.setString(1,"%"+tb+"%");
        ResultSet rs = st1.executeQuery();
            
        
        while(rs.next())
        {
         System.out.println(rs.getInt("idVehiculo")+"\t"+rs.getString("MarcaModelo")+"\t"+rs.getString("Anio")+
                 "\t"+rs.getString("NroChasis")+"\t"+rs.getString("NroMotor")+"\t"+rs.getInt("Chofer")+"\t"+rs.getString("Img1")
//                 +"\t"+rs.getString("Img2")+"\t"+rs.getString("Img3")+"\t"+rs.getString("Img4")+"\t"+rs.getString("Img5")+"\t"+
//                 rs.getString("Img6")+"\t"+rs.getString("Img7")+"\t"+rs.getString("Img8")+"\t"+rs.getString("Img9")+"\t"+
//                 rs.getString("Img10")+"\t");
         );
        }
        con.close();
        
        } catch (SQLException ex) {
             ex.printStackTrace();
        }
        con.close();
        
    } 
    
    
    
}
