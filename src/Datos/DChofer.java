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
import java.util.logging.Level;
import java.util.logging.Logger;
//import org.sqlite.SQLiteException;


/**
 *
 * @author brian
 */
public class DChofer {
    private int idChofer;
    private String nombreApellido;
    private int dni;
    private String fechaNacimiento;
    private String numTelefono;
    private String direccion;
    private String estadoCivil;
    private int vehiculo;
    private String textoBuscar;
    private String imagen;

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

   

    public int getIdChofer() {
        return idChofer;
    }
    public String getNombreApellido() {
        return nombreApellido;
    }
    public void setNombreApellido(String nombreApellido) {
        this.nombreApellido = nombreApellido;
    }
    public int getDni() {
        return dni;
    }
    public void setDni(int dni) {
        this.dni = dni;
    }
    public String getFechaNacimiento() {
        return fechaNacimiento;
    }
    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }
    public String getNumTelefono() {
        return numTelefono;
    }
    public void setNumTelefono(String numTelefono) {
        this.numTelefono = numTelefono;
    }
    public String getDireccion() {
        return direccion;
    }
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    public String getEstadoCivil() {
        return estadoCivil;
    }
    public void setEstadoCivil(String estadoCivil) {
        this.estadoCivil = estadoCivil;
    }
    public int getVehiculo() {
        return vehiculo;
    }
    public void setVehiculo(int vehiculo) {
        this.vehiculo = vehiculo;
    }
    public String getTextoBuscar() {
        return textoBuscar;
    }
    public void setTextoBuscar(String textoBuscar) {
        this.textoBuscar = textoBuscar;
    }

    public DChofer() {
    }

    public DChofer( int idchofer,String nombreApellido, int dni,String fechanacimiento, 
                   String numTelefono, String direccion, String estadoCivil, int vehiculo, String textobuscar, String imagen) {
        this.idChofer = idchofer;
        this.nombreApellido = nombreApellido;
        this.dni = dni;
        this.fechaNacimiento = fechanacimiento;
        this.numTelefono = numTelefono;
        this.direccion = direccion;
        this.estadoCivil = estadoCivil;
        this.vehiculo = vehiculo;

        this.imagen = imagen;
        
    }
    
   // metodo insertar
    public void Insertar(String nombreapellido, int dni,String fechanacimiento, 
                   String numTelefono, String direccion, String estadocivil, int vehiculo, String imagen){
        
        Conexion con = new Conexion();
        try {
            String sql = ( "insert into Chofer (NombreApellido, Dni, FechaNacimiento,NroTelefono,Direccion,EstadoCivil,Vehiculo,Imagen)  "
                    + "values (?,?,?,?,?,?,?,?)");
            

            con.connect();
            PreparedStatement st = con.connect().prepareStatement(sql);
            st.setString(1, nombreapellido);
            st.setInt(2, dni);
            st.setString(3, fechanacimiento);
            st.setString(4, numTelefono);
            st.setString(5, direccion);
            st.setString(6, estadocivil);
            st.setInt(7, vehiculo);
            st.setBytes(8, readFile(imagen));
            st.executeUpdate(); 
            System.out.println("Registro Cargado");
            con.close();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            

        }
        con.close(); 
         
    }
    public void Editar(int idchofer, String nombreapellido, int dni,String fechanacimiento, 
                   String numTelefono, String direccion, String estadocivil, int vehiculo, String imagen){
        Conexion con = new Conexion();
        try {
            String sql = ( "update  Chofer "
                    + "set NombreApellido = ?,Dni = ?, FechaNacimiento = ?,NroTelefono = ?,Direccion = ?,EstadoCivil = ?,Vehiculo = ?, Imagen = ?"
                    + "where idChofer = ?");
           
            con.connect();
            PreparedStatement st = con.connect().prepareStatement(sql);
           
            st.setString(1,nombreapellido);
            st.setInt(2, dni);
            st.setString(3, fechanacimiento);
            st.setString(4, numTelefono);
            st.setString(5, direccion);
            st.setString(6, estadocivil);
            st.setInt(7, vehiculo);
            st.setInt(9, idchofer);
            st.setBytes(8, readFile(imagen));
           
            st.executeUpdate(); 
            System.out.println("Registro Editado");
            con.close();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            con.close(); 
        } 
    }
    public void Eliminar(int id){
         Conexion con = new Conexion();
        try {
            String sql = ( "delete from  Chofer where idChofer = ?");
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
        String sql = "Select idChofer,NombreApellido,Dni,FechaNacimiento,NroTelefono,Direccion,EstadoCivil,Vehiculo"
                   + " from Chofer"; 
        Statement st = con.connect().createStatement();
        ResultSet rs = st.executeQuery(sql);
            
        
        while(rs.next())
        {
         System.out.println(rs.getInt("idChofer")+"\t"+rs.getString("NombreApellido")+"\t"+rs.getString("Dni")+
                 "\t"+rs.getString("NroTelefono")+"\t"+rs.getString("Direccion")+"\t"+rs.getString("EstadoCivil")
                 +"\t"+rs.getInt("Vehiculo"));
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
                   + " from Chofer"
                   + " where NombreApellido LIKE ? ";
              
        PreparedStatement st1 = con.connect().prepareStatement(sql);
        st1.setString(1,"%"+tb+"%");
        ResultSet rs = st1.executeQuery();
            
        
        while(rs.next())
        {
          System.out.println(rs.getInt("idChofer")+"\t"+rs.getString("NombreApellido")+"\t"+rs.getInt("Dni")+
                 "\t"+rs.getString("NroTelefono")+"\t"+rs.getString("Direccion")+"\t"+rs.getString("EstadoCivil")
                 +"\t"+rs.getInt("Vehiculo")+"\t"+rs.getString("Imagen"));
        }
        con.close();
        
        } catch (SQLException ex) {
             ex.printStackTrace();
        }
        con.close();
        
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
     
     public void updatePiture(int id, String filename){
         Conexion con = new Conexion();
         
         String updateSQL = "INSERT INTO imagen ( img1) values (?)";
         try {
            con.connect(); 
            
             PreparedStatement st = con.connect().prepareStatement(updateSQL);
//             st.setInt(1, id);
             st.setBytes(1, readFile(filename));
             
             
             st.executeUpdate();
             con.close();  
             System.out.println("Datos Cargados");
             
            
         } catch (SQLException ex) {
             System.err.println(ex.getMessage());
         }
     }
    
    //idChofer,NombreApellido,Dni,FechaNacimiento,NroTelefono,Direccion,EstadoCivil,Vehiculo,Imagen
    
    
    
    

}
