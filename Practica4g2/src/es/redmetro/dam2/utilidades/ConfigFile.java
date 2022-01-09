package es.redmetro.dam2.utilidades;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigFile {
	private static Properties properties=null;
	public static String getInfoConfiguracion(String clave) {
		String valor = null;
		
		if(properties ==null) {
			InputStream inputStream=null;
			properties = new Properties();
			try {
				inputStream = new FileInputStream("config/red-metro.properties");
				properties.load(inputStream);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		// Se devuelve un valor.
		valor=properties.getProperty(clave);
		
		return valor;
	}

}
