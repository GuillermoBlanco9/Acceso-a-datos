package es.iestetuan.dam2.dao.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import es.iestetuan.dam2.dao.IDepartamento;
import es.iestetuan.dam2.vo.Departamento;

public class DepartamentoJdbcDao implements IDepartamento {
	
	//Conexión con JDBC a MariaDB	
	private Connection getConexion(){
		Connection conexion = null;
        try{
			String driver="org.mariadb.jdbc.Driver";
		    Class.forName(driver);

		    String sgbd="mariadb";
			String servidor="192.168.1.99";
			String puerto="3306"; // Ojo, el puerto puede cambiar
			String nombreBBDD="bd_aadd";
			String usuarioBBDD="18dam2";
			String clave="dam2123";
			
			String urlConexionBBDD="jdbc:" + sgbd + "://"+ servidor + ":" + puerto + "/" + nombreBBDD;
			// Acceso a información de un fichero de configuración: Devuelve Properties
			System.out.println(urlConexionBBDD);
		    
			//Connection cn = DriverManager.getConnection("jdbc:mysql://servidor_bd:puerto/nombre_bd", "usuario", "contraseña");
			conexion = DriverManager.getConnection(urlConexionBBDD, usuarioBBDD, clave);

			if (conexion != null) {            
                System.out.println("Se ha obtenido conexión a la base de datos.");
				//imprimirInfoBaseDatos();
			}
            else          
                System.out.println("No se ha obtenido conexión a la base de datos.");         
        }
        catch(Exception e){
            System.out.println(e);
        }
        return conexion;
		
	}	
	
	// Conexión con JDBC a PostgreSQL	
	private Connection getConexionPostgresl() {
		Connection conexion = null;
        try{
			String driver="org.postgresql.Driver";
		    Class.forName(driver);

		    String sgbd="postgresql";
			String servidor="dam2.actividad.cf";
			String puerto="5432";
			String nombreBBDD="aadd-dam2";
			String usuarioBBDD="aadd";
			String clave="d1m2p0sgr3sql";
			
			String urlConexionBBDD="jdbc:" + sgbd + "://"+ servidor + ":" + puerto + "/" + nombreBBDD;
			// Acceso a información de un fichero de configuración: Devuelve Properties
			System.out.println(urlConexionBBDD);
		    
			//Connection cn = DriverManager.getConnection("jdbc:mysql://servidor_bd:puerto/nombre_bd", "usuario", "contraseña");
			conexion = DriverManager.getConnection(urlConexionBBDD, usuarioBBDD, clave);
        	/*
        	Class.forName("org.mariadb.jdbc.Driver");
            conexion = DriverManager.getConnection("jdbc:mariadb://localhost:3306/prueba-jdbc", "aadd-dam2", "inmdam2");
            */
            if (conexion != null)            
                System.out.println("Connected");           
            else          
                System.out.println("Not Connected");         
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        return conexion;
		
	}	
	
	

	@Override
	public Departamento consultarDepartamento(int idDepartamento) {
		Departamento departamento = null;
		Connection conexion =null;
		PreparedStatement sentenciaPreparada =null;
		ResultSet resultado =null; 
        String sql = "select dept_no, dnombre, loc from departamentos where dept_no = ?";
        try
        {
            conexion =getConexion();
            sentenciaPreparada = conexion.prepareStatement(sql);
            sentenciaPreparada.setInt(1, idDepartamento);
            resultado = sentenciaPreparada.executeQuery();
			// Solo ha de devolver un registro ya que va por clave primaria
			if (resultado.next()){
				departamento = new Departamento();
				int numeroDpto= resultado.getInt(1);
				departamento.setNumeroDepartamento(numeroDpto);
				String nombreDpto = resultado.getString(2);
				departamento.setNombreDepartamento(nombreDpto);
				String localidadDpto = resultado.getString(3);
				departamento.setLocalidad(localidadDpto);
			}else{
				System.out.println("No existe un departamento con el identificador " + idDepartamento);
			}    
        }catch(Exception e){
            System.out.println(e);
        }finally {
        	try {
        		if (conexion!=null)
        			conexion.close();
        		if(sentenciaPreparada!=null)
        			sentenciaPreparada.close();
        		if(resultado!=null)
        			resultado.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
        return departamento;
	}

	@Override
	public void crearDepartamento(Departamento departamento) {
		Connection conexion =null;
		PreparedStatement sentenciaPreparada =null;
		int resultado =0;
        String sql = "INSERT INTO departamentos VALUES (?, ?, ?)";
        try
        {
            conexion =getConexion();
            //Se crea una sentencia preparada
            sentenciaPreparada = conexion.prepareStatement(sql);
            
            //Asignación de parámetros
            sentenciaPreparada.setInt(1, departamento.getNumeroDepartamento());
            sentenciaPreparada.setString(2, departamento.getNombreDepartamento());
            sentenciaPreparada.setString(3, departamento.getLocalidad());
            
            // Ejecutar sentencia
            resultado = sentenciaPreparada.executeUpdate();
			
            // Si el alta no es correcta
			if (resultado ==1 ){
				System.out.println("SE insertado correctamente el departamento "+ departamento.getNombreDepartamento()); 
			}else{
				System.out.println("Error al insertar el departamento " + departamento.getNombreDepartamento());
			}    
        }catch(Exception e){
            System.out.println(e);
        }finally {
        	try {
        		if (conexion!=null)
        			conexion.close();
        		if(sentenciaPreparada!=null)
        			sentenciaPreparada.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
	}

	@Override
	public void modificarDepartamento(Departamento departamento) {
		// TODO Auto-generated method stub
		Connection conexion =null;
		PreparedStatement sentenciaPreparada =null;
		int resultado =0;
        String sql = "UPDATE departamentos SET dnombre=?, loc=? WHERE dept_no = ?";
        try
        {
            conexion =getConexion();
            //Se crea una sentencia preparada
            sentenciaPreparada = conexion.prepareStatement(sql);
            
            //Asignación de parámetros
            sentenciaPreparada.setString(1, departamento.getNombreDepartamento());
            sentenciaPreparada.setString(2, departamento.getLocalidad());
            sentenciaPreparada.setInt(3, departamento.getNumeroDepartamento());

            
            // Ejecutar sentencia
            resultado = sentenciaPreparada.executeUpdate();
			
            // Si el alta no es correcta
			if (resultado ==1 ){
				System.out.println("Se ha modificado correctamente el departamento "+ departamento.getNombreDepartamento()); 
			}else{
				System.out.println("Error al modificar el departamento " + departamento.getNombreDepartamento());
			}    
        }catch(Exception e){
            System.out.println(e);
        }finally {
        	try {
        		if (conexion!=null)
        			conexion.close();
        		if(sentenciaPreparada!=null)
        			sentenciaPreparada.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }

	}

	@Override
	public void borrarDepartamento(int idDepartamento) {
		Connection conexion =null;
		PreparedStatement sentenciaPreparada =null;
		int resultado =0;
        String sql = "DELETE FROM departamentos WHERE dept_no = ?";
        try
        {
            conexion =getConexion();
            //Se crea una sentencia preparada
            sentenciaPreparada = conexion.prepareStatement(sql);
            
            //Asignación de parámetros
            sentenciaPreparada.setInt(1, idDepartamento);
            
            // Ejecutar sentencia
            resultado = sentenciaPreparada.executeUpdate();
			
            // Si el alta no es correcta
			if (resultado ==1 ){
				System.out.println("Se ha borrado correctamente el departamento "+ idDepartamento); 
			}else{
				System.out.println("Error al borrar el departamento " + idDepartamento);
			}    
        }catch(Exception e){
            System.out.println(e);
        }finally {
        	try {
        		if (conexion!=null)
        			conexion.close();
        		if(sentenciaPreparada!=null)
        			sentenciaPreparada.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }

	}

	@Override
	public List<Departamento> consultarDepartamentosNombre(String nombre) {
		// Ejemplo de carga de información mediante un procedimiento almacenado.
		List<Departamento> listaDepartamentos= new ArrayList<Departamento>();
		Connection conexion =null;
		CallableStatement sentenciaLlamable =null;
		ResultSet resultado =null; 
        try
        {
        	conexion =getConexion();
        	sentenciaLlamable = conexion.prepareCall("CALL consultarDepartamentosNombre(?)");

            sentenciaLlamable.setString(1, nombre);     //Marcador de parámetro 1, parámetro de procedimiento almacenado 1
            
            resultado= sentenciaLlamable.executeQuery();
			while (resultado.next()){
				Departamento dpto = new Departamento();
				int numeroDpto= resultado.getInt(1);
				dpto.setNumeroDepartamento(numeroDpto);
				String nombreDpto = resultado.getString(2);
				dpto.setNombreDepartamento(nombreDpto);
				String localidadDpto = resultado.getString(3);
				dpto.setLocalidad(localidadDpto);
				
				listaDepartamentos.add(dpto);
			}
        }catch(Exception e){
            System.out.println(e);
        }finally {
        	try {
        		if (conexion!=null)
        			conexion.close();
        		if(sentenciaLlamable!=null)
        			sentenciaLlamable.close();
        		if(resultado!=null)
        			resultado.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
		
		return listaDepartamentos;
	}
	
	private void imprimirInfoBaseDatos(){
		// TODO Auto-generated method stub
		Connection conexion= getConexion();
		try {
			DatabaseMetaData metadatosBD =conexion.getMetaData();
			ResultSet rs = metadatosBD.getTables(null, null, "%", null);
			while (rs.next()) {
				System.out.println("\n\n###################################");
				String nombreTabla=rs.getString(3).toUpperCase();
				System.out.println(nombreTabla);
				System.out.println("------------------------------------------");		  
				ResultSet columns = metadatosBD.getColumns(null,null, nombreTabla, null);
				while(columns.next()) {
					String columnName = columns.getString("COLUMN_NAME");
					String columnSize = columns.getString("COLUMN_SIZE");
					String datatype = columns.getString("DATA_TYPE");
					String isNullable = columns.getString("IS_NULLABLE");
					String isAutoIncrement = columns.getString("IS_AUTOINCREMENT");
					  
					System.out.println(columnName + " " + datatype + " isNullable: " + isNullable+ " columnSize: " + columnSize+ " isAutoIncrement: " + isAutoIncrement);
				}

			}
		}catch(Exception e){
	        System.out.println(e);
	    }
	}

}
