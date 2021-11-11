package es.iestetuan.dam2;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Iterator;
import java.util.Properties;
import java.util.Scanner;
import java.util.Set;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

/**
 *  @descrition
 *	@author ineva
 *  @date 25 sept 2021
 *  @version 1.0
 *  @license GPLv3
 */

public class GestorConfiguracion {
	private static Properties propiedadesConfiguracion;

	
	public static Properties getPropiedadesConfiguracion() {
		// Por defecto carga la configuración de tipo properties
		if (propiedadesConfiguracion==null) {
			propiedadesConfiguracion=cargarConfiguracion();
		}
		return propiedadesConfiguracion;
	}


	public static void setPropiedadesConfiguracion(Properties propiedadesConfiguracion) {
		GestorConfiguracion.propiedadesConfiguracion = propiedadesConfiguracion;
	}

	public static String getInfoAtributoConfiguracion(String clave) {
		return getPropiedadesConfiguracion().getProperty(clave);
	} 
	private static Properties cargarConfiguracion() {
		Properties infoConfig = new Properties();
		
		try {
			infoConfig.load(new FileInputStream("config/dam2-aadd.properties"));
		} catch (FileNotFoundException fnfe ) { 
		  fnfe.printStackTrace();
		} catch (IOException ioe) { 
		  ioe.printStackTrace();
		}
		
		return infoConfig;
	}
	
	
	private static Properties cargarConfiguracionJSON() {
		Properties propiedades= new Properties();
		File fichero = new File ("config/dam2-aadd.json");
		String jsonString= null;
		JsonObject jsonObject =null;
		//JsonElement jsonElement =null;
		try {
			jsonString= new String(Files.readAllBytes(fichero.toPath()));
			//jsonString is of type java.lang.String
			 jsonObject = JsonParser.parseString(jsonString).getAsJsonObject();

/*
			//reader is of type java.io.Reader
			JsonObject jsonObject = JsonParser.parseReader​(reader).getAsJsonObject();

			//jsonReader is of type com.google.gson.stream.JsonReader
			JsonObject jsonObject = JsonParser.parseReader​(jsonReader).getAsJsonObject();
			*/
			 Set<String> claves = jsonObject.keySet();
			 for( Iterator<String> it = claves.iterator(); it.hasNext();) { 
				    String clave = it.next();
				    String valor = jsonObject.get(clave).getAsString();
				    propiedades.setProperty(clave, valor);
				}
		} catch (JsonSyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return propiedades;
	}
	

	private static Properties cargarConfiguracionJSON2() {
		Properties propiedades= new Properties();
		File fichero = new File ("config/dam2-aadd.json");
		String jsonString= null;
		try {
			jsonString= new String(Files.readAllBytes(fichero.toPath()));
			JSONObject jsonObject = new JSONObject(jsonString);
			System.out.println("jsonStr:"+jsonObject.toString());
			 Set<String> claves = jsonObject.keySet();
			 for( Iterator<String> it = claves.iterator(); it.hasNext();) { 
			    String clave = it.next();
			    String valor = jsonObject.getString(clave);
			    propiedades.setProperty(clave, valor);
			}
	    } catch (Exception e) {
	        System.out.println("Excepcion leyendo fichero de configuracion " + e);
	    }
		return propiedades;
	}
	
	private static Properties cargarConfiguracionXML() {
		Properties propiedades= new Properties();
		File fichero = new File ("config/dam2-aadd.xml");
		Document documento = null;
		try {
			  DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			  DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			  documento = dBuilder.parse(fichero);
			  documento.getDocumentElement().normalize();
			} catch(Exception e) {
			  e.printStackTrace();
		}
		// almacenamos los nodos para luego mostrar la
		// cantidad de ellos con el método getLength()
		NodeList nListIni = documento.getElementsByTagName("configuracion");
		System.out.println("Bloques de configuración: " + nListIni.getLength());
		// Se obtienen los nodos asociados a la entrada "configuraion"
		NodeList nList= nListIni.item(0).getChildNodes();		
		for(int temp = 0; temp < nList.getLength(); temp++) {
			  Node nNode = nList.item(temp);

			  if(nNode.getNodeType() == Node.ELEMENT_NODE) {
			    Element eElement = (Element) nNode;
			    String clave = eElement.getNodeName();
			    Node valor = eElement.getChildNodes().item(0);
			    propiedades.setProperty(clave, valor.getNodeValue());
			  }
		}
		
		return propiedades;
	}
	
	/*	
	public static InfoConfiguracion getConfiguracionXML() {
		if(configuracionXML==null) {
			configuracionXML= cargarConfiguracionXML();
		}
		return configuracionXML;
	}
	
	
	
	private static InfoConfiguracion cargarConfiguracionXML() {
		InfoConfiguracion configuracion =null;
		Path ruta = null;
		XStream xstream = new XStream();
		try {
			//File ficheroXML = new File ("dam2-aadd.xml");
			ruta = Paths.get("dam2-aadd.xml");
	        String xmlConfiguracion= new String ( Files.readAllBytes(ruta) );
	        System.out.println(xmlConfiguracion);
			xstream.addPermission(AnyTypePermission.ANY);
			xstream.alias("configuracion", GestorConfiguracion.class);
	        configuracion = (InfoConfiguracion)xstream.fromXML(xmlConfiguracion);
		} catch (JsonSyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return configuracion;
	}	
	*/
	public static void main(String[] args) {
		// Acceso a informacion del sistema
		// https://docs.oracle.com/javase/tutorial/essential/environment/sysprop.html
		//System.out.println("La carpeta de mi usuario es " + System.getProperty("user.home"));
		System.out.println("Selecciona la forma de cargar el fichero de configuración: 1 -> PROPERTIES, 2 -> JSON, 3 -> XML.");
		Scanner nombreObjeto = new Scanner(System.in);
		int valor = nombreObjeto.nextInt();
		nombreObjeto.close();
		switch (valor) {
		case 1: {
			setPropiedadesConfiguracion(cargarConfiguracion());
			break;
		}
		case 2: {
			setPropiedadesConfiguracion(cargarConfiguracionJSON2());
			break;
		}
		case 3: {
			setPropiedadesConfiguracion(cargarConfiguracionXML());
			break;
		}

		default:
			throw new IllegalArgumentException("Valor inesperado para la gestión de la configuración: " + valor);
		}
		
		// Acceso a información de un fichero de configuración: Devuelve Properties
		System.out.println(getPropiedadesConfiguracion());
	}
}


