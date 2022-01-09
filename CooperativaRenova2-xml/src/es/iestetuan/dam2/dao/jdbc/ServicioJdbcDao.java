package es.iestetuan.dam2.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import es.iestetuan.dam2.dao.InterfazBaseGeneralDao;
import es.iestetuan.dam2.exception.Renova2CoopException;
import es.iestetuan.dam2.utilidades.GestorConexion;
import es.iestetuan.dam2.vo.Servicio;

public class ServicioJdbcDao implements InterfazBaseGeneralDao<Servicio>{

	@Override
	public void crear(Servicio entidad) throws Renova2CoopException {
		// TODO Auto-generated method stub
		Connection conexion =null;
		PreparedStatement sentenciaPreparada =null;
		int resultado =0;
        String sql = "INSERT INTO T_SERVICIO (cod_servicio, descripcion, precio) VALUES (?, ?, ?)";
        try
        {
            conexion =GestorConexion.getConexion();
            if(conexion !=null) {	        
	            //Se crea una sentencia preparada
	            sentenciaPreparada = conexion.prepareStatement(sql);
	            

	            //Asignación de parámetros
	            sentenciaPreparada.setLong(1, entidad.getCodigoServicio());
	            sentenciaPreparada.setString(2, entidad.getDescripcion());
	            sentenciaPreparada.setFloat(3, entidad.getPrecio());
	            
	            // Ejecutar sentencia
	            resultado = sentenciaPreparada.executeUpdate();
				
	            // Si el alta no es correcta
				if (resultado ==1 ){
					System.out.println("Se ha insertado correctamente un SERVICIO "+ entidad.getDescripcion()); 
				}else{
					System.out.println("Error al insertar el SERVICIO " + entidad.getDescripcion());
				}
            }
        }catch(Exception e){
            System.out.println(e);
            throw new Renova2CoopException(Renova2CoopException.EXCEPCION_CREAR, e); // Error en alta de Servicio
        }finally {
        	try {
        		if (conexion!=null)
        			conexion.close();
        		if(sentenciaPreparada!=null)
        			sentenciaPreparada.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
	            throw new Renova2CoopException(Renova2CoopException.EXCEPCION_CREAR, e); // Error en alta de Servicio
			}
        }				
	}

	@Override
	public void borrar(Servicio entidad) throws Renova2CoopException {
		// TODO Auto-generated method stub
		Connection conexion =null;
		PreparedStatement sentenciaPreparada =null;
		int resultado =0;
        String sql = "DELETE FROM T_SERVICIO WHERE cod_servicio = ?";
        try
        {
            conexion =GestorConexion.getConexion();
            if(conexion !=null) {	        
	            //Se crea una sentencia preparada
	            sentenciaPreparada = conexion.prepareStatement(sql);
	            

	            //Asignación de parámetros
	            sentenciaPreparada.setLong(1, entidad.getCodigoServicio());
	            
	            // Ejecutar sentencia
	            resultado = sentenciaPreparada.executeUpdate();
				
	            // Si el alta no es correcta
				if (resultado ==1 ){
					System.out.println("Se ha borrado correctamente un SERVICIO "+ entidad.getDescripcion()); 
				}else{
					System.out.println("Error al borrar el SERVICIO " + entidad.getDescripcion());
				}
            }
        }catch(Exception e){
            System.out.println(e);
            throw new Renova2CoopException(Renova2CoopException.EXCEPCION_BORRAR, e); // Error en alta de Servicio
        }finally {
        	try {
        		if (conexion!=null)
        			conexion.close();
        		if(sentenciaPreparada!=null)
        			sentenciaPreparada.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
	            throw new Renova2CoopException(Renova2CoopException.EXCEPCION_BORRAR, e); // Error en alta de Servicio
			}
        }				
		
	}

	@Override
	public void actualizar(Servicio entidad) throws Renova2CoopException {
		// TODO Auto-generated method stub
		Connection conexion =null;
		PreparedStatement sentenciaPreparada =null;
		int resultado =0;
        String sql = "UPDATE T_SERVICIO SET descripcion=?, precio=? WHERE cod_servicio = ?";
        try
        {
            conexion =GestorConexion.getConexion();
            if(conexion !=null) {	        
	            //Se crea una sentencia preparada
	            sentenciaPreparada = conexion.prepareStatement(sql);
	            

	            //Asignación de parámetros
	            sentenciaPreparada.setString(1, entidad.getDescripcion());
	            sentenciaPreparada.setFloat(2, entidad.getPrecio());
	            sentenciaPreparada.setLong(3, entidad.getCodigoServicio());
	            
	            // Ejecutar sentencia
	            resultado = sentenciaPreparada.executeUpdate();
				
	            // Si el alta no es correcta
				if (resultado ==1 ){
					System.out.println("Se ha actualizado correctamente un SERVICIO "+ entidad.getDescripcion()); 
				}else{
					System.out.println("Error al actualizar el SERVICIO " + entidad.getDescripcion());
				}
            }
        }catch(Exception e){
            System.out.println(e);
            throw new Renova2CoopException(Renova2CoopException.EXCEPCION_ACTUALIZAR, e); // Error en alta de Servicio
        }finally {
        	try {
        		if (conexion!=null)
        			conexion.close();
        		if(sentenciaPreparada!=null)
        			sentenciaPreparada.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
	            throw new Renova2CoopException(Renova2CoopException.EXCEPCION_ACTUALIZAR, e); // Error en alta de Servicio
			}
        }						
	}

	@Override
	public Servicio getEntidadPorID(long idEntidad) throws Renova2CoopException {
		// TODO Auto-generated method stub
		// Ejemplo de carga de información de un Servicio.
		Servicio servicio=null;
		Connection conexion =null;
		PreparedStatement sentenciaPreparada=null;
		ResultSet resultado =null; 
	    try
	    {
	    	conexion =GestorConexion.getConexion();
	        if(conexion !=null) {	               	
	        	sentenciaPreparada = conexion.prepareStatement("SELECT * FROM T_SERVICIO WHERE cod_servicio = ?");

	        	// Asignación de parámetros
	            sentenciaPreparada.setLong(1, idEntidad);
	            resultado= sentenciaPreparada.executeQuery();
	            
				if(resultado.next()){
					servicio = new Servicio();
					long codigoServicio= resultado.getLong(1);
					servicio.setCodigoServicio(codigoServicio);
					String descripcion = resultado.getString(2);
					servicio.setDescripcion(descripcion);
					float precio= resultado.getFloat(3);
					servicio.setPrecio(precio);
				}
	        }
	    }catch(Exception e){
	        System.out.println(e);
            throw new Renova2CoopException(Renova2CoopException.EXCEPCION_CONSULTAR, e); // Error en alta de Servicio
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
	            throw new Renova2CoopException(Renova2CoopException.EXCEPCION_CONSULTAR, e); // Error en alta de Servicio
			}
	    }
		
		return servicio;		
	}

	@Override
	public List<Servicio> getListaEntidades() throws Renova2CoopException {
		// TODO Auto-generated method stub
		// Ejemplo de carga de todos los Servicios.
		List<Servicio> listaServicios= new ArrayList<Servicio>();
		Connection conexion =null;
		PreparedStatement sentenciaPreparada=null;
		ResultSet resultado =null; 
	    try
	    {
	    	conexion =GestorConexion.getConexion();
	        if(conexion !=null) {	               	
	        	sentenciaPreparada = conexion.prepareStatement("SELECT * FROM T_SERVICIO");
	
	            resultado= sentenciaPreparada.executeQuery();
				while (resultado.next()){
					Servicio servicio = new Servicio();
					long codigoServicio= resultado.getLong(1);
					servicio.setCodigoServicio(codigoServicio);
					String descripcion = resultado.getString(2);
					servicio.setDescripcion(descripcion);
					float precio= resultado.getFloat(3);
					servicio.setPrecio(precio);

					listaServicios.add(servicio);
				}
	        }
	    }catch(Exception e){
	        System.out.println(e);
            throw new Renova2CoopException(Renova2CoopException.EXCEPCION_CONSULTAR, e); // Error en alta de Servicio
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
	            throw new Renova2CoopException(Renova2CoopException.EXCEPCION_CONSULTAR, e); // Error en alta de Servicio
			}
	    }
		
		return listaServicios;		
	}
}
