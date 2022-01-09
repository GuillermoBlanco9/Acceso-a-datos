package es.redmetro.dam2.utilidades;

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

import org.apache.commons.net.PrintCommandListener;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPSClient;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import es.redmetro.dam2.dao.orm.CocheraHibernateDAO;
import es.redmetro.dam2.dao.orm.EstacionHibernateDAO;
import es.redmetro.dam2.dao.orm.LineaHibernateDAO;
import es.redmetro.dam2.vo.*;

public class APPPruebaFTP {
	public static void parseo(List<Tren> trenes, List<Acceso> accesos, List<Estacion> estaciones){
	//pruebaFTPsClient();
	
    EstacionHibernateDAO operacionEstacion = new EstacionHibernateDAO();
	CocheraHibernateDAO operacionCochera = new CocheraHibernateDAO();
    String server = GestorConfiguracion.getInfoConfiguracion("server.filezilla");
    int port = Integer.parseInt(GestorConfiguracion.getInfoConfiguracion("port.filezilla"));
    String user = GestorConfiguracion.getInfoConfiguracion("user.filezilla");
    String pass = GestorConfiguracion.getInfoConfiguracion("pass.filezilla");

//    FTPClient ftpClient = new FTPClient();
    FTPSClient ftpClient = new FTPSClient("TLS", false);
    FTPFile[] ficherosRaiz=null;
    try {
		ftpClient.addProtocolCommandListener(new PrintCommandListener(new PrintWriter(System.out))); // outputs all conversation to the console
		
        ftpClient.connect(server, port);

        ftpClient.enterLocalPassiveMode();
        boolean success = ftpClient.login(user, pass);

        if (!success) {
            System.out.println("Could not login to the server");
            return;
        }
		// Set protection buffer size
		ftpClient.execPBSZ(0);
		// Set data channel protection to private
		ftpClient.execPROT("P");

//        FTPFile[] ftpFiles = ftpClient.listFiles();
        boolean cambio= ftpClient.changeToParentDirectory();
        if(cambio)
        		ficherosRaiz=ftpClient.listFiles();
        for (FTPFile ftpFile : ficherosRaiz) {
        	System.out.println(ftpFile.getName());
		}
        	
        	
        


     // configuration code for ftpclient port, server etc
        InputStream in = ftpClient.retrieveFileStream(GestorConfiguracion.getInfoConfiguracion("ruta.filezilla"));
        Document documento = readXml(in);
		// Se obtiene la lista de nodos relacionado con 'departamento'
		NodeList nListIni = documento.getElementsByTagName("tren");
		//System.out.println("Numero de trenes: " + nListIni.getLength());
		for(int temp = 0; temp < nListIni.getLength(); temp++) {
			Node nNode = nListIni.item(temp);
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
			    Element elemento = (Element) nNode;
		    	int codTren= Integer.parseInt(elemento.getAttribute("cod_tren"));
		    	
		    	NodeList contenidoModelo = elemento.getElementsByTagName("modelo");
		    	String modelo = contenidoModelo.item(0).getTextContent();
		    	
		    	NodeList contenidoEmpresa = elemento.getElementsByTagName("empresa_constructora");
		    	String empresa = contenidoEmpresa.item(0).getTextContent();
		    	
		    	NodeList contenidoFecha = elemento.getElementsByTagName("fecha_incorporacion");
		    	java.util.Date formatter = null;
				try {
					formatter = new SimpleDateFormat("dd-MM-yyyy",Locale.ITALY).parse(contenidoFecha.item(0).getTextContent());
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

		    	Cochera cochera = operacionCochera.consultarPorID(codCochera, Cochera.class);
		    	if(cochera==null) {
		    		cochera = new Cochera();
		    		cochera.setCodigoCochera(codCochera);
		    		operacionCochera.crear(cochera);
		    	}
		    	
		    	
		    	NodeList contenidoCodLinea = elemento.getElementsByTagName("cod_linea");
		    	int codLinea = Integer.parseInt(contenidoCodLinea.item(0).getTextContent());
		    	LineaHibernateDAO operacionLinea = new LineaHibernateDAO();
		    	Linea linea = operacionLinea.consultarPorID(codLinea, Linea.class);

		    	Tren tren = new Tren(codTren,modelo,fecha,empresa,linea,cochera);
		    	
		    	trenes.add(tren);
			}
		}
		
		nListIni = documento.getElementsByTagName("estacion");
		for(int temp = 0; temp < nListIni.getLength(); temp++) {
			Node nNode = nListIni.item(temp);
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				Element elemento = (Element) nNode;
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
		
		nListIni = documento.getElementsByTagName("acceso");
		for(int temp = 0; temp < nListIni.getLength(); temp++) {
			Node nNode = nListIni.item(temp);
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				Element elemento = (Element) nNode;
		    	int codAcceso= Integer.parseInt(elemento.getAttribute("cod_acceso"));
		    	
		    	NodeList contenidoNombre = elemento.getElementsByTagName("nombre");
		    	String nombre = contenidoNombre.item(0).getTextContent();
		    	
		    	NodeList contenidoAccesoDiscapacidad = elemento.getElementsByTagName("acceso_discapacidad");
		    	int accesoDiscapacidad = Integer.parseInt(contenidoAccesoDiscapacidad.item(0).getTextContent());
		    	
		    	NodeList contenidoCodEstacion = elemento.getElementsByTagName("cod_accesoestacion");
		    	int codEstacion = Integer.parseInt(contenidoCodEstacion.item(0).getTextContent());

		    	Estacion estacion = operacionEstacion.consultarPorID(codEstacion, Estacion.class);
		    	/*if(estacion==null) {
		    		estacion = new Estacion();
		    		estacion.setCodigoEstacion(codEstacion);
		    		operacionEstacion.crear(estacion);
		    	}*/
		    	
		    	Acceso acceso = new Acceso(codAcceso,nombre,accesoDiscapacidad,estacion);
		    	
		    	accesos.add(acceso);
			}
		}
		
		        
    } catch (IOException | ParserConfigurationException | SAXException ex) {
        System.out.println("Oops! Something wrong happened");
        ex.printStackTrace();
    } finally {
        try {
            if (ftpClient.isConnected()) {
                ftpClient.logout();
                ftpClient.disconnect();
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
     // dbf.setCoalescing(true);
     // dbf.setExpandEntityReferences(true);

     DocumentBuilder db = null;
     db = dbf.newDocumentBuilder();
     // db.setErrorHandler( new MyErrorHandler());

     return db.parse(is);
 }
}