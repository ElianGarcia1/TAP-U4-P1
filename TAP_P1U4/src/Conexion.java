import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Conexion {
    Connection conexion;
    Statement transaccion;
    ResultSet cursor;
    
    public Conexion(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/inventario?zeroDateTimeBehavior=CONVERT_TO_NULL", "root", "root");
            transaccion = conexion.createStatement();
        }catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public boolean insertar(Productos productos){
        String SQL_Insert = "INSERT INTO productos VALUES(NULL,'%DES%','%PRE%','%EXIS%')";
        SQL_Insert = SQL_Insert.replaceAll("%DES%", productos.DESCRIPCION);
        SQL_Insert = SQL_Insert.replaceAll("%PRE%", productos.PRECIO+"");
        SQL_Insert = SQL_Insert.replaceAll("%EXIS%", productos.EXISTENCIA+"");
        try {
            transaccion.execute(SQL_Insert);
        } catch (SQLException ex) {
            return false;
        }
        return true;
    }
    public Productos consultarID(String ID){
        Productos ProductosResultado = new Productos();
        try{
            cursor = transaccion.executeQuery("SELECT * FROM PRODUCTOS");
            if (cursor.next()) {
                ProductosResultado.DESCRIPCION = cursor.getString(2);
                ProductosResultado.PRECIO = cursor.getInt(3);
                ProductosResultado.EXISTENCIA = cursor.getInt(4);
            }
        }catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ProductosResultado;
    }
    
        public boolean borrarArt(String ID){
        try{
            transaccion.execute("DELETE FROM PRODUCTOS WHERE IDPRODUCTOS="+ID);
        }catch (SQLException ex) {
            return false;
        }
        return true;
    }
        
    public boolean Actualizar(Productos productos){
        String update = "UPDATE PRODUCTOS SET DESCRIPCION ='%DES%', PRECIO = '%PRE%', EXISTENCIA = '%EXIS%' WHERE IDPRODUCTOS="+productos.ID;
        
        update = update.replaceAll("%DES%", productos.DESCRIPCION);
        update = update.replaceAll("%PRE%", productos.PRECIO+"");
        update = update.replaceAll("%EXIS%", productos.EXISTENCIA+"");
        try {
            transaccion.executeUpdate(update);
        } catch (SQLException ex) {
            return false;
        }
        return true;
    }
    public ArrayList<String[]>consultarTodos(){
        ArrayList<String[]> resultado = new ArrayList<>();
        try {
            cursor = transaccion.executeQuery("SELECT * FROM PRODUCTOS");
            if(cursor.next()){
                do{
                    String[] renglon = {cursor.getString(1),cursor.getString(2),cursor.getString(3), cursor.getString(4)};
                    resultado.add(renglon);
                }while(cursor.next());
            }
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resultado;
    }


}
