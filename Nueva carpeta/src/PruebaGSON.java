import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;

public class PruebaGSON {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PruebasCSV csv= new PruebasCSV();
		List<Usuario> listaUsuarios = csv.getUsuarios();
		
		PruebaGSON pxstream = new PruebaGSON();
		pxstream.crearJSON(listaUsuarios);
		pxstream.obtenerUsuariosFromJSON();
	}
	public void crearJSON(List<Usuario> listaUsuarios) {
		Gson gson= new Gson();		
		String json =gson.toJson(listaUsuarios);
		System.out.println(json);
		try {
			Files.writeString(Paths.get("datos/salida.json"), json);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
	}
	
	public void obtenerUsuariosFromJSON() {
		Gson gson= new Gson();		
		Type tipoJsonEsperado = new TypeToken<ArrayList<Usuario>>() {}.getType();
		File ficheroJSON= new File("datos/salida.json");
		
		// Al incluirlo en el try() evito tener que cerrar el BufferedInputStream
		try (FileReader fr= new FileReader(ficheroJSON, StandardCharsets.UTF_8)) {
			
			List<Usuario> listaUsuarios = gson.fromJson(fr, tipoJsonEsperado);
			System.out.println(listaUsuarios.size());
		} catch (RuntimeException | Error | IOException e) {
			System.out.println(e.fillInStackTrace());
		}
	}
}
