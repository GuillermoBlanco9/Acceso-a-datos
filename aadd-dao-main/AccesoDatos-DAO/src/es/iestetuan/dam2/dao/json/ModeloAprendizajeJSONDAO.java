package es.iestetuan.dam2.dao.json;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;

import com.google.gson.Gson;
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
import es.iestetuan.dam2.vo.Usuario;


public class ModeloAprendizajeJSONDAO implements IModeloAprendizajeDao {

	private static String RUTA_FICHERO_JSON=GestorConfiguracion.getInfoAtributoConfiguracion("rutaModeloAprendizajeXML");
	
	@Override
	public ModeloAprendizaje getModeloAprendizaje(int id) {
		RUTA_FICHERO_JSON="datos/perfiles_betts-neihart.json";
		ModeloAprendizaje modelo = null;
		File fichero = new File (RUTA_FICHERO_JSON);
		String jsonString= null;
		try {
			jsonString= new String(Files.readAllBytes(fichero.toPath()), Charset.forName("UTF-8"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Gson gson = new Gson();
		modelo= gson.fromJson(jsonString, ModeloAprendizaje.class);

		return modelo;
	}
	
}
