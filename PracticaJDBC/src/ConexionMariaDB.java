import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;



public class ConexionMariaDB {
    private static final String DRIVER = "org.mariadb.jdbc.Driver";
    private static final String URL_CONEXION = "jdbc:mariadb://localhost:3306/empleadodpt";
   // private static final String DRIVER = "org.postgresql.Driver";
   //private static final String URL_CONEXION = "postgreSQL://dam2.actividad.cf:5432/aadd-dam2";
    public static void main(String args[]) throws SQLException {
        final String usuario = "root";
        final String password = "";
        Connection dbConnection = null;
        Statement statement = null;
        Statement statement2 = null;
        try {
            Class.forName(DRIVER);
            Connection conn = DriverManager.getConnection(URL_CONEXION, usuario, password);
            
           
            String selectTableSQL = "INSERT INTO departamentos VALUES (50,'INFORMATICA','MADRID')";
            statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(selectTableSQL);

            String selectTableSQL2 = "INSERT INTO departamentos VALUES (60,'COMUNICACIONES','MADRID')";
            statement2 = conn.createStatement();
            ResultSet rs2 = statement2.executeQuery(selectTableSQL2);
             
            
           
               
               
            	 
                   PreparedStatement preparedStmt = conn.prepareStatement("update departamentos set dnombre = ? where dept_no = ?");
                   preparedStmt.setString(1, "Informatica y Comunicaciones");
                   preparedStmt.setString(2, "60");
                   preparedStmt.executeUpdate();
               
                   PreparedStatement preparedStmt2 = conn.prepareStatement("update departamentos set dnombre = ? where dept_no = ?");
                   preparedStmt2.setString(1, "TIC");
                   preparedStmt2.setString(2, "50");
                   preparedStmt2.executeUpdate();
               
                   PreparedStatement st = conn.prepareStatement("DELETE FROM departamentos WHERE dept_no = 60;");
                   st.executeUpdate();
                   
                   
                   
                   PreparedStatement st3 = conn.prepareStatement("INSERT INTO empleados VALUES (8001,'Valery','Programador java',7782,'1990-12-17',1570,NULL,50);");
                   st3.executeUpdate();
                   
                   PreparedStatement preparedStmt3 = conn.prepareStatement("update empleados set emp_no = ?, salario = ?, dept_no=? where apellido regexp \"^j|J.\" and salario > 1500;");
                   preparedStmt3.setString(1, "7668");
                   preparedStmt3.setString(2, "1500");
                   preparedStmt3.setString(3, "50");
                   preparedStmt3.executeUpdate();
                   
                   PreparedStatement st4 = conn.prepareStatement("DELETE FROM empleados WHERE emp_no = 7499;");
                   st4.executeUpdate();
               
                   
                   
            
            conn.close();
            
        } catch (SQLException e) {
            System.out.println("1"+e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("2"+e.getMessage());
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (dbConnection != null) {
                dbConnection.close();
            }
        }
    }   
}