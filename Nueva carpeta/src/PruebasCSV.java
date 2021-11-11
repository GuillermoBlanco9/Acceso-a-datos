

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

public class PruebasCSV {
	public List<Usuario> getUsuarios(){
		//https://docs.oracle.com/javase/tutorial/essential/exceptions/tryResourceClose.html
		// Información para uso del tryResourceClose
		// Permite trabajar con los recursos sin tener necesidad de cerrarlos dentro de un finally
		List<Usuario> listaUsuarios = new ArrayList<Usuario>();
		 try (CSVReader reader = new CSVReader(new FileReader("datos/alumnos-dam2-nuevos.txt", Charset.forName("UTF-8")))) {
			 reader.skip(1);
			 List<String[]> lineasCVS = reader.readAll();
		      //Se empieza en 1 para evitar la cabecera
			 for(String[] elementosLinea: lineasCVS) {
		    	  int id= Integer.parseInt(elementosLinea[0]);
		    	  String nombre =elementosLinea[1];
		    	  String apellido1=elementosLinea[2];
		    	  String apellido2=elementosLinea[3];
		    	  Usuario usuario = new Usuario();
		    	  usuario.setId(id);
		    	  usuario.setNombre(nombre);
		    	  usuario.setApellido1(apellido1);
		    	  usuario.setApellido2(apellido2);
		    	  listaUsuarios.add(usuario);
		    	  // Información de los elementos de cada fila
		    	  System.out.println(Arrays.toString(elementosLinea));  
		      }		      
		  } catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CsvException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 return listaUsuarios;		
	}
}
