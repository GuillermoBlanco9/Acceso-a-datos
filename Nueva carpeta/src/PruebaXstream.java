import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;

public class PruebaXstream {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PruebasCSV csv= new PruebasCSV();
		List<Usuario> listaUsuarios = csv.getUsuarios();
		
		PruebaXstream pxstream = new PruebaXstream();
		pxstream.crearXML(listaUsuarios);
		pxstream.obtenerUsuariosFromXML();
	}
	public void crearXML(List<Usuario> listaUsuarios) {
		XStream xstream = new XStream(new StaxDriver());		
		//xstream.allowTypes(new Class[] {Usuario.class, PerfilAprendizaje.class,InfoGeneral.class});
		xstream.alias("usuarios",List.class);
		xstream.alias("usuario",Usuario.class);
		xstream.aliasAttribute(Usuario.class, "id", "id");
		String xml =xstream.toXML(listaUsuarios);
		System.out.println(xml);
		try {
			Files.writeString(Paths.get("datos/salida.xml"), xml);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
	}
	
	public void obtenerUsuariosFromXML() {
		XStream xstream = new XStream(new StaxDriver());		
		xstream.allowTypes(new Class[] {Usuario.class});
		xstream.alias("usuarios",List.class);
		xstream.alias("usuario",Usuario.class);
		xstream.aliasAttribute(Usuario.class, "id", "id");

		File ficheroXML= new File("datos/salida.xml");
		
		// Al incluirlo en el try() evito tener que cerrar el BufferedInputStream
		try (FileReader fr= new FileReader(ficheroXML, StandardCharsets.UTF_8)) {
			List<Usuario> listaUsuarios = (ArrayList<Usuario>)xstream.fromXML(fr);
			System.out.println(listaUsuarios.size());
		} catch (RuntimeException | Error | IOException e) {
			System.out.println(e.fillInStackTrace());
		}
	}
}
