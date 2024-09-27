/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.UUID;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import vista.frmCarpintero;

/**
 *
 * @author Estudiante
 */
public class Carpintero {
   //Declaramos los parametros que teniamos en la base
    private String UUID_Carpintero; 
    private String Nombre_Carpintero;
    private int Edad_Carpinter;
    private int Peso_Carpintero;
    private String Correo_Carpintero;
    
    //GENERAMOS LOS GETTERS Y SETTERS 

    public String getUUID_Carpintero() {
        return UUID_Carpintero;
    }

    public void setUUID_Carpintero(String UUID_Carpintero) {
        this.UUID_Carpintero = UUID_Carpintero;
    }

    public String getNombre_Carpintero() {
        return Nombre_Carpintero;
    }

    public void setNombre_Carpintero(String Nombre_Carpintero) {
        this.Nombre_Carpintero = Nombre_Carpintero;
    }

    public int getEdad_Carpinter() {
        return Edad_Carpinter;
    }

    public void setEdad_Carpinter(int Edad_Carpinter) {
        this.Edad_Carpinter = Edad_Carpinter;
    }

    public int getPeso_Carpintero() {
        return Peso_Carpintero;
    }

    public void setPeso_Carpintero(int Peso_Carpintero) {
        this.Peso_Carpintero = Peso_Carpintero;
    }

    public String getCorreo_Carpintero() {
        return Correo_Carpintero;
    }

    public void setCorreo_Carpintero(String Correo_Carpintero) {
        this.Correo_Carpintero = Correo_Carpintero;
    }
    
     //3- Métodos (select, insert, update, delete)

    //Ingresar
    public void GuardarCarpintero() {
        //Creamos una variable igual a ejecutar el método de la clase de conexión
        Connection conexion = ClaseConexion.getConexion();
        try {
            //Creamos el PreparedStatement que ejecutará la Query
            PreparedStatement addCarpintero = conexion.prepareStatement("INSERT INTO tbCarpinteros(UUID_Carpintero, Nombre_Carpintero, Edad_Carpinter, Peso_Carpintero, Correo_Carpintero) VALUES (?, ?, ?, ?, ?)");
            //Establecer valores de la consulta SQL
            addCarpintero.setString(1, UUID.randomUUID().toString());
            addCarpintero.setString(2, getNombre_Carpintero().toString());
            addCarpintero.setInt(3, getEdad_Carpinter());
            addCarpintero.setInt(4, getPeso_Carpintero());
            addCarpintero.setString(5, getCorreo_Carpintero().toString());
            
            
 
            //Ejecutar la consulta
            addCarpintero.executeUpdate();
 
        } catch (SQLException ex) {
            System.out.println("este es el error en el modelo:metodo guardar " + ex);
        }
    }
    
    public void MostrarCarpintero(JTable tabla) {
        //Creamos una variable de la clase de conexion
        Connection conexion = ClaseConexion.getConexion();
        //Definimos el modelo de la tabla
        DefaultTableModel modeloCarpintero = new DefaultTableModel();
        modeloCarpintero.setColumnIdentifiers(new Object[]{"UUID_Carpintero", "Nombre_Carpintero", "Edad_Carpinter", "Peso_Carpintero", "Correo_Carpintero"});
        try {
         
            //Creamos un Statement
            Statement statement = conexion.createStatement();
            //Ejecutamos el Statement con la consulta y lo asignamos a una variable de tipo ResultSet
            ResultSet rs = statement.executeQuery("select * from tbCarpinteros");
            
            //Recorremos el ResultSet
            while (rs.next()) {
                //Llenamos el modelo por cada vez que recorremos el resultSet
                modeloCarpintero.addRow(new Object[]{rs.getString("UUID_Carpintero"), 
                    rs.getString("Nombre_Carpintero"), 
                    rs.getInt("Edad_Carpinter"), 
                    rs.getInt("Peso_Carpintero"), 
                    rs.getString("Correo_Carpintero")});
            }
            //Asignamos el nuevo modelo lleno a la tabla
            tabla.setModel(modeloCarpintero);
        } catch (Exception e) {
            System.out.println("Este es el error en el modelo, metodo mostrar " + e);
        }
    }
    
    public void EliminarCarpintero(JTable tabla) {
        //Creamos una variable igual a ejecutar el método de la clase de conexión
        Connection conexion = ClaseConexion.getConexion();

        //obtenemos que fila seleccionó el usuario
        int filaSeleccionada = tabla.getSelectedRow();
        //Obtenemos el id de la fila seleccionada

        String miId = tabla.getValueAt(filaSeleccionada, 0).toString();
        //borramos 
        try {
            String sql = "delete from tbCarpinteros where UUID_Carpintero = ?";
            PreparedStatement deleteCarpintero = conexion.prepareStatement(sql);
            deleteCarpintero.setString(1, miId);
            deleteCarpintero.executeUpdate();
        } catch (Exception e) {
            System.out.println("este es el error metodo de eliminar" + e);
        }
    }
    
    public void ActualizarCarpintero(JTable tabla) {
        //Creamos una variable igual a ejecutar el método de la clase de conexión
        Connection conexion = ClaseConexion.getConexion();

        //obtenemos que fila seleccionó el usuario
        int filaSeleccionada = tabla.getSelectedRow();

        if (filaSeleccionada != -1) {
            //Obtenemos el id de la fila seleccionada
            String miUUId = tabla.getValueAt(filaSeleccionada, 0).toString();

            try {
                //Ejecutamos la Query
                String sql = "update tbCarpinteros set Nombre_Carpintero = ?, Edad_Carpinter = ?, Peso_Carpintero = ?, Correo_Carpintero = ? where UUID_Carpintero = ?";
                PreparedStatement updateCarpintero = conexion.prepareStatement(sql);

                updateCarpintero.setString(1, UUID.randomUUID().toString());
            updateCarpintero.setString(2, getNombre_Carpintero().toString());
            updateCarpintero.setInt(3, getEdad_Carpinter());
            updateCarpintero.setInt(4, getPeso_Carpintero());
            updateCarpintero.setString(5, getCorreo_Carpintero().toString());
            updateCarpintero.executeUpdate();

            } catch (Exception e) {
                System.out.println("este es el error en el metodo de actualizar" + e);
            }
        } else {
            System.out.println("no");
        }
    }
    
    public void LimpiarCarpintero (frmCarpintero vistaCarpintero ){
        vistaCarpintero.txtNombreCarpintero.setText("");
        vistaCarpintero.txtEdad.setText("");
        vistaCarpintero.txtPeso.setText("");
        vistaCarpintero.txtCorreo.setText("");
        
    }
    
    public void cargarDatosTabla(frmCarpintero vistaCarpintero) {
        // Obtén la fila seleccionada 
        int filaSeleccionada = vistaCarpintero.jtbCarpintero.getSelectedRow();

        // Debemos asegurarnos que haya una fila seleccionada antes de acceder a sus valores
        if (filaSeleccionada != -1) {
            String UUIDDeTb = vistaCarpintero.jtbCarpintero.getValueAt(filaSeleccionada, 0).toString();
            String Nombre_Carpintero = vistaCarpintero.jtbCarpintero.getValueAt(filaSeleccionada, 1).toString();
            String Edad_Carpinter = vistaCarpintero.jtbCarpintero.getValueAt(filaSeleccionada, 2).toString();
            String Peso_Carpintero = vistaCarpintero.jtbCarpintero.getValueAt(filaSeleccionada, 3).toString();
            String Correo_Carpintero = vistaCarpintero.jtbCarpintero.getValueAt(filaSeleccionada, 4).toString();

            // Establece los valores en los campos de texto
            vistaCarpintero.txtNombreCarpintero.setText(Nombre_Carpintero);
            vistaCarpintero.txtEdad.setText(Edad_Carpinter);
            vistaCarpintero.txtPeso.setText(Peso_Carpintero);
            vistaCarpintero.txtCorreo.setText(Correo_Carpintero);
           
        }
    }
    
    
    
    
    
}
