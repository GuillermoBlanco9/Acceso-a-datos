package es.iestetuan.dam2.dao.xml;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.collections.CollectionConverter;
import com.thoughtworks.xstream.converters.extended.NamedArrayConverter;
import com.thoughtworks.xstream.io.xml.StaxDriver;

import es.iestetuan.dam2.GestorConfiguracion;
import es.iestetuan.dam2.dao.IModeloAprendizajeDao;
import es.iestetuan.dam2.vo.InfoGeneral;
import es.iestetuan.dam2.vo.ModeloAprendizaje;
import es.iestetuan.dam2.vo.PerfilAprendizaje;


public class ModeloAprendizajeXMLDAO implements IModeloAprendizajeDao {

	private static String RUTA_FICHERO_XML=GestorConfiguracion.getInfoAtributoConfiguracion("rutaModeloAprendizajeXML");
	
	@Override
	public ModeloAprendizaje getModeloAprendizaje(int id) {
		RUTA_FICHERO_XML="datos/perfil1.xml";
		ModeloAprendizaje modelo=null;
		PerfilAprendizaje perfilAprendizaje=null;
		File ficheroXML= new File(RUTA_FICHERO_XML);
		
		// Al incluirlo en el try() evito tener que cerrar el BufferedInputStream
		try (FileReader fr= new FileReader(ficheroXML, Charset.forName("UTF-8"))) {
			XStream xstream = new XStream(new StaxDriver());		
			xstream.allowTypes(new Class[] {ModeloAprendizaje.class, PerfilAprendizaje.class,InfoGeneral.class});			
			// Alias Modelo Aprendizaje
			xstream.alias("modelo-aprendizaje", ModeloAprendizaje.class);
			xstream.aliasAttribute(ModeloAprendizaje.class, "id", "id");
			xstream.aliasAttribute(ModeloAprendizaje.class, "clave", "clave");
			xstream.addImplicitArray(ModeloAprendizaje.class, "perfiles");

			// Alias Perfil Aprendizaje
			xstream.alias("perfil", PerfilAprendizaje.class);
			xstream.aliasAttribute(PerfilAprendizaje.class, "id", "id");
			xstream.aliasAttribute(PerfilAprendizaje.class, "orden", "orden");	
			xstream.aliasAttribute(PerfilAprendizaje.class, "tipo", "tipo");			

			
			// Alias InfoGeneral
			xstream.alias("general", InfoGeneral.class);
			xstream.aliasAttribute(InfoGeneral.class, "estilo", "estilo");
			xstream.addImplicitCollection(InfoGeneral.class, "info", "info", String.class);
			/*			
			xstream.alias("sentimientos", InfoSentimiento.class);
			xstream.alias("comportamientos", InfoComportamiento.class);
			xstream.alias("necesidades", InfoNecesidad.class);
			xstream.alias("reconocimiento", InfoReconocimiento.class);
			xstream.alias("identificacion", InfoIdentificacion.class);

			xstream.aliasField("ayudaInterna", PerfilAprendizaje.class, "ayuda-interna");
			xstream.aliasField("ayudaExterna", PerfilAprendizaje.class, "ayuda-externa");
			 */
 

		 
			 
			perfilAprendizaje= (PerfilAprendizaje)xstream.fromXML(fr);
		} catch (RuntimeException | Error | IOException e) {
			System.out.println(e.fillInStackTrace());
		}
		
		return modelo;
	}
	
	public void crearXML() {
		ModeloAprendizaje modelo=new ModeloAprendizaje();
		modelo.setId(0);
		modelo.setClave("1");
		modelo.setDescripcion("Desc1");
		modelo.setDetalle("Detalle desc1");

		PerfilAprendizaje perfilAprendizaje=new PerfilAprendizaje();
		/*
		perfilAprendizaje.setAyudaExterna("AyudaExterna1");
		perfilAprendizaje.setAyudaInterna("AyudaInterna1");
		perfilAprendizaje.setComportamientos("Comportamientos1");
		perfilAprendizaje.setDescripcion("Descripcion1");
		InfoGeneral info1= new InfoGeneral();
		info1.setEstilo("html");
		info1.addInfo("info1");
		info1.addInfo("info2");
		perfilAprendizaje.setGeneral(info1);
	
		InfoGeneral info2= new InfoGeneral();
		info2.setOrden(2);
		info2.setValor("info2");
		perfilAprendizaje.setGeneral(info2);
		
		perfilAprendizaje.setId("0001");
		perfilAprendizaje.setIdentificacion("Identificacion1");
		perfilAprendizaje.setNecesidades("Necesidades1");
		perfilAprendizaje.setOrden(1);
		perfilAprendizaje.setReconocimiento("Reconocimiento1");
		perfilAprendizaje.setSentimientos("Sentimientos1");
		perfilAprendizaje.setTipo("Tipo1");
		modelo.addPerfilAprendizaje(perfilAprendizaje);
		
		perfilAprendizaje=new PerfilAprendizaje();
		perfilAprendizaje.setAyudaExterna("AyudaExterna1");
		perfilAprendizaje.setAyudaInterna("AyudaInterna1");
		perfilAprendizaje.setComportamientos("Comportamientos1");
		perfilAprendizaje.setDescripcion("Descripcion1");
		InfoGeneral info2= new InfoGeneral();
		info2.setEstilo("html");
		info2.addInfo("info1");
		info2.addInfo("info2");
		perfilAprendizaje.setGeneral(info2);
		
		InfoGeneral info4= new InfoGeneral();
		info4.setOrden(2);
		info4.setValor("info4");
		perfilAprendizaje.setGeneral(info4);
		
		perfilAprendizaje.setId("0001");
		perfilAprendizaje.setIdentificacion("Identificacion1");
		perfilAprendizaje.setNecesidades("Necesidades1");
		perfilAprendizaje.setOrden(1);
		perfilAprendizaje.setReconocimiento("Reconocimiento1");
		perfilAprendizaje.setSentimientos("Sentimientos1");
		perfilAprendizaje.setTipo("Tipo1");
	
		modelo.setPerfiles(perfilAprendizaje);
		*/	
		RUTA_FICHERO_XML="datos/perfiles_betts-neihart.xml";
		File ficheroXML= new File(RUTA_FICHERO_XML);
		
		// Al incluirlo en el try() evito tener que cerrar el BufferedInputStream
		try (FileReader fr= new FileReader(ficheroXML, Charset.forName("UTF-8"))) {
			XStream xstream = new XStream(new StaxDriver());		
			xstream.allowTypes(new Class[] {ModeloAprendizaje.class, PerfilAprendizaje.class,InfoGeneral.class});			
			// Alias Modelo Aprendizaje
			xstream.alias("modelo-aprendizaje", ModeloAprendizaje.class);
			xstream.aliasAttribute(ModeloAprendizaje.class, "id", "id");
			xstream.aliasAttribute(ModeloAprendizaje.class, "clave", "clave");
			xstream.addImplicitArray(ModeloAprendizaje.class, "perfiles");

			// Alias Perfil Aprendizaje
			xstream.alias("perfil", PerfilAprendizaje.class);
			xstream.aliasAttribute(PerfilAprendizaje.class, "id", "id");
			xstream.aliasAttribute(PerfilAprendizaje.class, "orden", "orden");	
			xstream.aliasAttribute(PerfilAprendizaje.class, "tipo", "tipo");			

			
			// Alias InfoGeneral
			xstream.alias("general", InfoGeneral.class);
			xstream.aliasAttribute(InfoGeneral.class, "estilo", "estilo");
			xstream.addImplicitCollection(InfoGeneral.class, "info", "info", String.class);


//			xstream.addImplicitArray(PerfilAprendizaje.class, "general");

			/*			
			xstream.alias("sentimientos", InfoSentimiento.class);
			xstream.alias("comportamientos", InfoComportamiento.class);
			xstream.alias("necesidades", InfoNecesidad.class);
			xstream.alias("reconocimiento", InfoReconocimiento.class);
			xstream.alias("identificacion", InfoIdentificacion.class);

			xstream.aliasField("ayudaInterna", PerfilAprendizaje.class, "ayuda-interna");
			xstream.aliasField("ayudaExterna", PerfilAprendizaje.class, "ayuda-externa");
*/			 

		 
			 
			String xml =xstream.toXML(modelo);
			System.out.println(xml);
		} catch (RuntimeException | Error | IOException e) {
			System.out.println(e.fillInStackTrace());
		}
				
	}
}
