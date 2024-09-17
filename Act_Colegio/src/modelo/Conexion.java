package modelo;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Conexion {
    private final String puerto = "3306";
    private final String db = "db_colegio";
    private final String urlConexion = "jdbc:mysql://localhost:3306/db_colegio";
      //"jdbc:mysql://localhost:"+puerto+"/ "+ db +";
   // = String.format("jdbc:mysql://localhost:%s/%s?serverTimezone=UTC",puerto,db);
    private final String usuario= "root";
    private final String contraseña= "Colegi@123";
    private final String jbdc =  "com.mysql.cj.jdbc.Driver";    
    
    public Connection conexionDB;
    public void abrir_conexion(){
        try{
            Class.forName(jbdc);
            conexionDB = DriverManager.getConnection(urlConexion,usuario,contraseña);
            System.out.println("Conexion Exitosa a la db");
        }catch(ClassNotFoundException | SQLException ex){
            System.out.println("Algo salio mal :("+ ex.getMessage());
        }
    
}
        public void cerrar_conexion(){
        try{
            conexionDB.close();
        }catch(SQLException ex){
            System.out.println("Algo salio mal :("+ ex.getMessage());
        }
    
}
}