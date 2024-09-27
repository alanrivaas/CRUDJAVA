
package controlador;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JOptionPane;
import modelo.Carpintero;
import vista.frmCarpintero;

public class ctlrCarpintero implements MouseListener, KeyListener{
    
    //////////////////////////2- Parametros
    private Carpintero modeloCarpintero;
    
    private frmCarpintero vistaCarpintero; 
    
    //////////////////////////3- Constructor de la clase
    public ctlrCarpintero(Carpintero modeloCarpintero, frmCarpintero vistaCarpintero) {
        
        this.modeloCarpintero = modeloCarpintero;
        
        this.vistaCarpintero = vistaCarpintero;
        
        //Siempre hay que poner los botones que vamos a utilizar
        
        this.vistaCarpintero.btnGuardar.addMouseListener(this);
        this.vistaCarpintero.btnActualizar.addMouseListener(this);
        this.vistaCarpintero.btnEliminar.addMouseListener(this);
        this.vistaCarpintero.btnLimpiar.addMouseListener(this);
        this.vistaCarpintero.jtbCarpintero.addMouseListener(this);
        
        
        
        modeloCarpintero.MostrarCarpintero(vistaCarpintero.jtbCarpintero);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == vistaCarpintero.btnGuardar) {
            if (vistaCarpintero.txtNombreCarpintero.getText().isEmpty()|| vistaCarpintero.txtEdad.getText().isEmpty() || vistaCarpintero.txtPeso.getText().isEmpty() || vistaCarpintero.txtCorreo.getText().isEmpty() )  {
                JOptionPane.showMessageDialog(vistaCarpintero, "Debes llenar todos los campos", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                try {
                    //Asignar lo de la vista al modelo
                    modeloCarpintero.setNombre_Carpintero(vistaCarpintero.txtNombreCarpintero.getText());
                    modeloCarpintero.setEdad_Carpinter(Integer.parseInt(vistaCarpintero.txtEdad.getText()));
                    modeloCarpintero.setPeso_Carpintero(Integer.parseInt(vistaCarpintero.txtPeso.getText()));
                    modeloCarpintero.setCorreo_Carpintero(vistaCarpintero.txtCorreo.getText());
                    //Ejecutar el metodo 
                    modeloCarpintero.GuardarCarpintero();
                    modeloCarpintero.MostrarCarpintero(vistaCarpintero.jtbCarpintero);
                   // modeloNoticias.limpiar(Panel);
                } catch (Exception ex) {
                    System.out.println("este es el error al guardar" + e);
                }
            }
        }
        
        if (e.getSource() == vistaCarpintero.btnEliminar) {
            if (vistaCarpintero.txtNombreCarpintero.getText().isEmpty() || vistaCarpintero.txtEdad.getText().isEmpty() || vistaCarpintero.txtPeso.getText().isEmpty() || vistaCarpintero.txtCorreo.getText().isEmpty() ) {
                JOptionPane.showMessageDialog(vistaCarpintero, "Debes seleccionar un registro para eliminar", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                try {
                modeloCarpintero.EliminarCarpintero(vistaCarpintero.jtbCarpintero);
                modeloCarpintero.MostrarCarpintero(vistaCarpintero.jtbCarpintero);
               // modeloNoticias.limpiar(Panel);
                } catch (Exception ex) {
                    System.out.println("este es el error al guardar" + e);
                    }
                }
            }
        
        if (e.getSource() == vistaCarpintero.btnActualizar) {
            if (vistaCarpintero.txtNombreCarpintero.getText().isEmpty() || vistaCarpintero.txtEdad.getText().isEmpty() || vistaCarpintero.txtPeso.getText().isEmpty() || vistaCarpintero.txtCorreo.getText().isEmpty() ) {
                JOptionPane.showMessageDialog(vistaCarpintero, "Debes seleccionar un registro para actualizar", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                try {
                    //Asignar lo de la vista al modelo al momento de darle clic a actualizar
                    modeloCarpintero.setNombre_Carpintero(vistaCarpintero.txtNombreCarpintero.getText());
                    modeloCarpintero.setEdad_Carpinter(Integer.parseInt(vistaCarpintero.txtEdad.getText()));
                    modeloCarpintero.setPeso_Carpintero(Integer.parseInt(vistaCarpintero.txtPeso.getText()));
                    modeloCarpintero.setCorreo_Carpintero(vistaCarpintero.txtCorreo.getText());
                    

                    //Ejecutar el método    
                    modeloCarpintero.ActualizarCarpintero(vistaCarpintero.jtbCarpintero);
                    modeloCarpintero.MostrarCarpintero(vistaCarpintero.jtbCarpintero);
                   // modeloNoticias.limpiar(Panel);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(vistaCarpintero, "La edad debe ser un número", "Error", JOptionPane.WARNING_MESSAGE);
                }
            }
        }
        
        if (e.getSource() == vistaCarpintero.btnLimpiar) {
          modeloCarpintero.LimpiarCarpintero(vistaCarpintero);
        }
        
        if (e.getSource() == vistaCarpintero.jtbCarpintero) {
            modeloCarpintero.cargarDatosTabla(vistaCarpintero);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}




