package es.redmetro.dam2.utilidades;

import org.apache.commons.net.PrintCommandListener;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPSClient;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import es.redmetro.dam2.dao.orm.CocheraHibernate;
import es.redmetro.dam2.dao.orm.EstacionHibernate;
import es.redmetro.dam2.dao.orm.LineaHibernate;
import es.redmetro.dam2.vo.*;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;



public class ConexionFTP {
	public static void parseo(List<Tren> trenes, List<Acceso> accesos, List<Estacion> estaciones){

	
    EstacionHibernate opEstacion = new EstacionHibernate();
	CocheraHibernate opCochera = new CocheraHibernate();
    String server = ConfigFile.getInfoConfiguracion("server.filezilla");
    int port = Integer.parseInt(ConfigFile.getInfoConfiguracion("port.filezilla"));
    String user = ConfigFile.getInfoConfiguracion("user.filezilla");
    String pass = ConfigFile.getInfoConfiguracion("pass.filezilla");


    FTPSClient clienteFTP = new FTPSClient("TLS", false);
    FTPFile[] ficherosRaiz=null;
    try {
		clienteFTP.addProtocolCommandListener(new PrintCommandListener(new PrintWriter(System.out))); // outputs all conversation to the console
		
        clienteFTP.connect(server, port);

        clienteFTP.enterLocalPassiveMode();
        boolean success = clienteFTP.login(user, pass);

        if (!success) {
            System.out.println("Could not login to the server");
            return;
        }
		// Set protection buffer size
		clienteFTP.execPBSZ(0);
		// Set data channel protection to private
		clienteFTP.execPROT("P");


        boolean cambio= clienteFTP.changeToParentDirectory();
        if(cambio)
        		ficherosRaiz=clienteFTP.listFiles();
        for (FTPFile ftpFile : ficherosRaiz) {
        	System.out.println(ftpFile.getName());
		}
        	
        	
        


     
        InputStream in = clienteFTP.retrieveFileStream(ConfigFile.getInfoConfiguracion("ruta.filezilla"));
        Document archivoXML = readXml(in);
		// Se obtiene la lista de nodos relacionado con 'departamento'
		NodeList nListIni = archivoXML.getElementsByTagName("tren");
		for(int temp = 0; temp < nListIni.getLength(); temp++) {
			Node nodos = nListIni.item(temp);
			if (nodos.getNodeType() == Node.ELEMENT_NODE) {
			    Element elemento = (Element) nodos;
		    	int codTren= Integer.parseInt(elemento.getAttribute("cod_tren"));
		    	
		    	NodeList stringModelo = elemento.getElementsByTagName("modelo");
		    	String modelo = stringModelo.item(0).getTextContent();
		    	
		    	NodeList stringEmpresa = elemento.getElementsByTagName("empresa_constructora");
		    	String empresa = stringEmpresa.item(0).getTextContent();
		    	
		    	NodeList dateFecha = elemento.getElementsByTagName("fecha");
		    	java.util.Date formatter = null;
				try {
					formatter = new SimpleDateFormat("dd-MM-yyyy",Locale.FRANCE).parse(dateFecha.item(0).getTextContent());
				} catch (DOMException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    	java.sql.Date fecha = new java.sql.Date(formatter.getTime());
		    	
		    	NodeList contenidoCodCochera = elemento.getElementsByTagName("cod_cochera");
		    	int codCochera = Integer.parseInt(contenidoCodCochera.item(0).getTextContent());

		    	Cochera cochera = opCochera.consultarPorID(codCochera, Cochera.class);
		    	if(cochera==null) {
		    		cochera = new Cochera();
		    		cochera.setCodigoCochera(codCochera);
		    		opCochera.crear(cochera);
		    	}
		    	
		    	
		    	NodeList contenidoCodLinea = elemento.getElementsByTagName("cod_linea");
		    	int codLinea = Integer.parseInt(contenidoCodLinea.item(0).getTextContent());
		    	LineaHibernate operacionLinea = new LineaHibernate();
		    	Linea linea = operacionLinea.consultarPorID(codLinea, Linea.class);

		    	Tren tren = new Tren(codTren,modelo,fecha,empresa,linea,cochera);
		    	
		    	trenes.add(tren);
			}
		}
		
		nListIni = archivoXML.getElementsByTagName("estacion");
		for(int temp = 0; temp < nListIni.getLength(); temp++) {
			Node nodos = nListIni.item(temp);
			if (nodos.getNodeType() == Node.ELEMENT_NODE) {
				Element elemento = (Element) nodos;
		    	int codEstacion= Integer.parseInt(elemento.getAttribute("cod_estacion"));
		    	
		    	NodeList contenidoNombre = elemento.getElementsByTagName("nombre");
		    	String nombre = contenidoNombre.item(0).getTextContent();
		    	
		    	NodeList contenidoDireccion = elemento.getElementsByTagName("direccion");
		    	String direccion = contenidoDireccion.item(0).getTextContent();
		    	
		    	Estacion estacion = new Estacion();
		    	estacion.setCodigoEstacion(codEstacion);
		    	estacion.setNombre(nombre);
		    	estacion.setDireccion(direccion);
		    	
		    	estaciones.add(estacion);
			}
		}
		
		nListIni = archivoXML.getElementsByTagName("acceso");
		for(int temp = 0; temp < nListIni.getLength(); temp++) {
			Node nodos = nListIni.item(temp);
			if (nodos.getNodeType() == Node.ELEMENT_NODE) {
				Element elemento = (Element) nodos;
		    	int codAcceso= Integer.parseInt(elemento.getAttribute("cod_acceso"));
		    	
		    	NodeList contenidoNombre = elemento.getElementsByTagName("nombre");
		    	String nombre = contenidoNombre.item(0).getTextContent();
		    	
		    	NodeList contenidoAccesoDiscapacidad = elemento.getElementsByTagName("acceso_discapacidad");
		    	int accesoDiscapacidad = Integer.parseInt(contenidoAccesoDiscapacidad.item(0).getTextContent());
		    	
		    	NodeList contenidoCodEstacion = elemento.getElementsByTagName("cod_estacion");
		    	int codEstacion = Integer.parseInt(contenidoCodEstacion.item(0).getTextContent());

		    	Estacion estacion = opEstacion.consultarPorID(codEstacion, Estacion.class);
		    	
		    	Acceso acceso = new Acceso(codAcceso,nombre,accesoDiscapacidad,estacion);
		    	
		    	accesos.add(acceso);
			}
		}
		
		        
    } catch (IOException | ParserConfigurationException | SAXException ex) {        
        ex.printStackTrace();
    } finally {
        try {
            if (clienteFTP.isConnected()) {
                clienteFTP.logout();
                clienteFTP.disconnect();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}

	 public static Document readXml(InputStream is) throws ParserConfigurationException, SAXException, IOException  {
     DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

     dbf.setValidating(false);
     dbf.setIgnoringComments(false);
     dbf.setIgnoringElementContentWhitespace(true);
     dbf.setNamespaceAware(true);
     

     DocumentBuilder db = null;
     db = dbf.newDocumentBuilder();
     

     return db.parse(is);
 }
}