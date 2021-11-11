package es.iestetuan.dam2.dao.xml;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import es.iestetuan.dam2.GestorConfiguracion;
import es.iestetuan.dam2.dao.IUsuarioDao;
import es.iestetuan.dam2.vo.Usuario;
import es.iestetuan.dam2.vo.Usuarios;


public class UsuarioJAXBDAO implements IUsuarioDao {

	private static String RUTA_FICHERO_XML=GestorConfiguracion.getInfoAtributoConfiguracion("rutaFicheroXML");
	private static String RUTA_FICHERO_USUARIOS_XML=GestorConfiguracion.getInfoAtributoConfiguracion("rutaFicheroUsuariosXML");
	@Override
	public Usuario getUsuario(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Usuario> getListaUsuarios() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void guardarUsuario(Usuario usuario) {
		JAXBContext jaxbContext;
		Marshaller jaxbMarshaller =null;
		String rutaXML=RUTA_FICHERO_XML.replaceFirst("\\[NIA]", String.valueOf(usuario.getId()));
		try {
			jaxbContext = JAXBContext.newInstance( Usuario.class);
			jaxbMarshaller   = jaxbContext.createMarshaller();
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			jaxbMarshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
			OutputStream os = new FileOutputStream(rutaXML);
			jaxbMarshaller.marshal( usuario, os );
		} catch (JAXBException | FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}			
	}

	@Override
	public void guardarUsuarios(List<Usuario> listaUsuarios) {
		Usuarios usuarios= new Usuarios();
		usuarios.setListaUsuarios(listaUsuarios);
		JAXBContext jaxbContext;
		Marshaller jaxbMarshaller =null;
		try {
			jaxbContext = JAXBContext.newInstance( Usuarios.class );
			jaxbMarshaller   = jaxbContext.createMarshaller();
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			jaxbMarshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
			
			OutputStream os = new FileOutputStream( RUTA_FICHERO_USUARIOS_XML );
			jaxbMarshaller.marshal( usuarios, os );
		} catch (JAXBException | FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
