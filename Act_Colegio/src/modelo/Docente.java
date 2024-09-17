/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;
import javax.swing.JOptionPane;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;
import java.sql.ResultSet;

/**
 *
 * @author Christian
 */
public class Docente extends Persona{
    private String codigo_docente, salario, fecha_ingreso_laborar, fecha_ingreso_registro;
    Conexion cn;

    public Docente(){}
    public Docente(String nit, String nombres, String apellidos, String direccion, String telefono, String fecha_nacimiento, String codigo_docente, String salario, String fecha_ingreso_laborar, String fecha_ingreso_registro) {
        super(nit, nombres, apellidos, direccion, telefono, fecha_nacimiento);
        this.codigo_docente = codigo_docente;
        this.salario = salario;
        this.fecha_ingreso_laborar = fecha_ingreso_laborar;
        this.fecha_ingreso_registro = fecha_ingreso_registro;
    }
    
    

    public String getCodigo_docente() {
        return codigo_docente;
    }

    public void setCodigo_docente(String codigo_docente) {
        this.codigo_docente = codigo_docente;
    }

    public String getSalario() {
        return salario;
    }

    public void setSalario(String salario) {
        this.salario = salario;
    }

    public String getFecha_ingreso_laborar() {
        return fecha_ingreso_laborar;
    }

    public void setFecha_ingreso_laborar(String fecha_ingreso_laborar) {
        this.fecha_ingreso_laborar = fecha_ingreso_laborar;
    }

    public String getFecha_ingreso_registro() {
        return fecha_ingreso_registro;
    }

    public void setFecha_ingreso_registro(String fecha_ingreso_registro) {
        this.fecha_ingreso_registro = fecha_ingreso_registro;
    }
    
    @Override
    public void crear(){
        try {
            PreparedStatement parametro;
            cn = new Conexion();
            cn.abrir_conexion();
            String query = "INSERT INTO clientes (nit, nombres, apellidos, direccion, telefono, fecha_nacimiento, codigo_docente, salario, fecha_ingreso_laborar, fecha_ingreso_registro) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
            parametro = (PreparedStatement) cn.conexionDB.prepareStatement(query);
            parametro.setString(1, getNit());
            parametro.setString(2, getNombres());
            parametro.setString(3, getApellidos());
            parametro.setString(4, getDireccion());
            parametro.setString(5, getTelefono());
            parametro.setString(6, getFecha_nacimiento());
	    parametro.setString(7, getCodigo_docente());
	    parametro.setString(8, getSalario());
	    parametro.setString(9, getFecha_ingreso_laborar());
	    parametro.setString(10, getFecha_ingreso_registro());
            int executar = parametro.executeUpdate();
            
             JOptionPane.showMessageDialog(null,"Ingreso Exitoso"  + Integer.toString(executar));
            cn.cerrar_conexion();
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null,"Ingreso Exitoso"  + ex.getMessage());
            //System.out.println("Error en crear: "+ ex.getMessage());
    
        }
    }
    
    @Override
    public void eliminar(){
    try {
            PreparedStatement parametro;
            cn = new Conexion();
            cn.abrir_conexion();
            String query = "DELETE FROM empleados WHERE id_persona = ?";
            parametro = (PreparedStatement) cn.conexionDB.prepareStatement(query);
            parametro.setString(1, getNit());
            
            int executar = parametro.executeUpdate();
            JOptionPane.showMessageDialog(null, "Registro Eliminado " + Integer.toString(executar));
            cn.cerrar_conexion();
        } catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Error al eliminar: " + ex.getMessage());
        }
    }
    
    @Override
    public void actualizar(){
    
    
    }
    
    @Override
    public DefaultTableModel leer(){
        DefaultTableModel tabla = new DefaultTableModel();
        try{
            cn = new Conexion();
            cn.abrir_conexion();
            String query = "SELECT id_persona, nit, nombres, apellidos, direccion, telefono, fecha_nacimiento, codigo_docente, salario, fecha_ingreso_laborar, fecha_ingreso_registro FROM db_escuela"; //datos de la tabla de la db 
            ResultSet consulta = cn.conexionDB.createStatement().executeQuery(query);
            
            String encabezado[] = {"id","Nit", "Nombres", "Apellidos", "Direccion", "Telefono", "Nacimiento", "Codigo Docente", "Salario", "Fecha Ingreso Laborar","Fecha Ingreso Registro"}; //parametros del encabezado del formulario 
            tabla.setColumnIdentifiers(encabezado);
            
            String datos[] = new String[10];  // Cambiado a 10 ya que hay parámetros nuevos
            while(consulta.next()){
		datos[0] = consulta.getString("id_persona");
                datos[1] = consulta.getString("nit");
                datos[2] = consulta.getString("nombres");
                datos[3] = consulta.getString("apellidos");
                datos[4] = consulta.getString("direccion");
                datos[5] = consulta.getString("telefono");
                datos[6] = consulta.getString("fecha_nacimiento");
		datos[7] = consulta.getString("codigo_docente");
		datos[8] = consulta.getString("salario");
		datos[9] = consulta.getString("fecha_ingreso_laborar");
		datos[10] = consulta.getString("fecha_ingreso_registro");
                tabla.addRow(datos);
            }
            
            cn.cerrar_conexion();
            
        } catch(SQLException ex) {
            cn.cerrar_conexion();
            System.out.println("Error: " + ex.getMessage());
        }
        return tabla;
    }
    
}
