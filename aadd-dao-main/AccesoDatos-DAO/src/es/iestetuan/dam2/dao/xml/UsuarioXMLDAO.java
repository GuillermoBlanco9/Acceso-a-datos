package es.iestetuan.dam2.dao.xml;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;

import com.thoughtworks.xstream.XStream;

import es.iestetuan.dam2.GestorConfiguracion;
import es.iestetuan.dam2.dao.IUsuarioDao;
import es.iestetuan.dam2.vo.Usuario;


public class UsuarioXMLDAO implements IUsuarioDao {

	private static String RUTA_FICHERO_XML=GestorConfiguracion.getInfoAtributoConfiguracion("rutaFicheroXML");
	private static String RUTA_FICHERO_USUARIOS_XML=GestorConfiguracion.getInfoAtributoConfiguracion("rutaFicheroUsuariosXML");
	@Override
	public Usuario getUsuario(int id) {
		Usuario usuario = null;
		File fichero = new File (RUTA_FICHERO_USUARIOS_XML);
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
		NodeList nListIni = documento.getElementsByTagName("usuario");
		System.out.println("Nº de bloques de usuario: " + nListIni.getLength());
		for(int temp = 0; temp < nListIni.getLength(); temp++) {
			Node nNode = nListIni.item(temp);
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
			    Element elemento = (Element) nNode;
			    String sId = elemento.getAttribute("id");
			    if(sId!=null || !"".equals(sId)) {
				    long idXML=Long.parseLong(sId);
				    if(idXML==id) {
				    	usuario=new Usuario();
				    	String nombre= elemento.getElementsByTagName("nombre").item(0).getTextContent();
				    	String apellido1= elemento.getElementsByTagName("apellido1").item(0).getTextContent();
				    	String apellido2= elemento.getElementsByTagName("apellido2").item(0).getTextContent();
				    	usuario.setId(id);
				    	usuario.setNombre(nombre);
				    	usuario.setApellido1(apellido1);
				    	usuario.setApellido2(apellido2);
				    	break;
				    }
			    }
			}
		}
		
		return usuario;
	}

	@Override
	public List<Usuario> getListaUsuarios() {
		List<Usuario> listaUsuarios= new ArrayList<Usuario>();
		Usuario usuario=null;
		File fichero = new File (RUTA_FICHERO_USUARIOS_XML);
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
		NodeList nListIni = documento.getElementsByTagName("usuario");
		System.out.println("Nº de bloques de usuario: " + nListIni.getLength());
		for(int temp = 0; temp < nListIni.getLength(); temp++) {
			Node nNode = nListIni.item(temp);
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
			    Element elemento = (Element) nNode;
			    String sId = elemento.getAttribute("id");
			    int id= Integer.parseInt(sId);
		    	String nombre= elemento.getElementsByTagName("nombre").item(0).getTextContent();
		    	String apellido1= elemento.getElementsByTagName("apellido1").item(0).getTextContent();
		    	String apellido2= elemento.getElementsByTagName("apellido2").item(0).getTextContent();
		    	usuario=new Usuario();
		    	usuario.setId(id);
		    	usuario.setNombre(nombre);
		    	usuario.setApellido1(apellido1);
		    	usuario.setApellido2(apellido2);
		    	listaUsuarios.add(usuario);
			}
		}
		
		return listaUsuarios;
	}

	@Override
	public void guardarUsuario(Usuario usuario) {
		List<Usuario> listaUsuarios = getListaUsuarios();
		}

	@Override
	public void guardarUsuarios(List<Usuario> listaUsuarios) {
		Document documento = null;
		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			DOMImplementation domImpl = dBuilder.getDOMImplementation();
			documento = domImpl.createDocument(null, "xml", null);
				
			// Se crear el nodo raíz
			Element raiz = documento.createElement("usuarios");
			documento.getDocumentElement().appendChild(raiz);

			// Información para nodos internos
			Element nodoUsuario = null , nodoDatos = null ;
			Text texto = null;
			Attr atributo=null;
			 
			for (Usuario usu: listaUsuarios) {
				// Se asigna el nodo/elemento usuario a elemento raíz 
				nodoUsuario = documento.createElement("usuario");
				raiz.appendChild(nodoUsuario);
				
				// Se carga información atributo id al usuario
				String sId = String.valueOf(usu.getId());
				atributo= documento.createAttribute("id");
				nodoUsuario.setAttributeNode(atributo);
				atributo.setTextContent(sId);
				
	
				// Se carga información nombre
				nodoDatos = documento.createElement("nombre");
				nodoUsuario.appendChild(nodoDatos);
				texto = documento.createTextNode(usu.getNombre());
				nodoDatos.appendChild(texto);
	
				// Se carga información apellido1
				nodoDatos = documento.createElement("apellido1");
				nodoUsuario.appendChild(nodoDatos);
				texto = documento.createTextNode(usu.getApellido1());
				nodoDatos.appendChild(texto);
	
				// Se carga información apellido2
				nodoDatos = documento.createElement("apellido2");
				nodoUsuario.appendChild(nodoDatos);
				texto = documento.createTextNode(usu.getApellido2());
				nodoDatos.appendChild(texto);
				// Se carga información email
				nodoDatos = documento.createElement("email");
				nodoUsuario.appendChild(nodoDatos);
				texto = documento.createTextNode(usu.getEmail());
				nodoDatos.appendChild(texto);
				
			}
			Transformer transformer = TransformerFactory.newInstance().newTransformer();
			Result output = new StreamResult(new File("datos/alumnosDOM.xml"));
			Source input = new DOMSource(documento);
			transformer.transform(input, output);
		} catch(Exception e) {
			  e.printStackTrace();
		}		
	}
	private void guardaFicheroXML(String rutaXML, String infoXML) {
		FileOutputStream fos = null;
		try {
		    fos = new FileOutputStream(rutaXML);
		    fos.write("<?xml version=\"1.0\"?>\n".getBytes("UTF-8")); //write XML header, as XStream doesn't do that for us
		    byte[] bytes = infoXML.getBytes("UTF-8");
		    fos.write(bytes);

		} catch(Exception e) {
		    e.printStackTrace(); // this obviously needs to be refined.
		} finally {
		    if(fos!=null) {
		        try{ 
		            fos.close();
		        } catch (IOException e) {
		            e.printStackTrace(); // this obviously needs to be refined.
		        }
		    }
		}		
		
	} 

}
