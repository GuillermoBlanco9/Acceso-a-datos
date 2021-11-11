package es.iestetuan.dam2.bo;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Scanner;

import es.iestetuan.dam2.GestorConfiguracion;
import es.iestetuan.dam2.dao.IUsuarioDao;
import es.iestetuan.dam2.dao.fictex.UsuarioFicTextDAO;
import es.iestetuan.dam2.dao.json.UsuarioJSONDAO;
import es.iestetuan.dam2.dao.xml.UsuarioJAXBDAO;
import es.iestetuan.dam2.dao.xml.UsuarioXMLDAO;
import es.iestetuan.dam2.vo.Usuario;

public class GestorUsuario {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		GestorUsuario gu = new GestorUsuario();
		Scanner nombreObjeto = new Scanner(System.in);
		int nia = nombreObjeto.nextInt();
		nombreObjeto.close();
		//gu.guardarUsuarioJSON(nia);
		// gu.guardarUsuarioXML(nia, parser);
		gu.guardarUsuariosXML();
		//gu.imprimirUsuario(171);
		//gu.imprimirUsuarios();
	}
	public void imprimirUsuario(int nia) {
		IUsuarioDao usuarioDAO = new UsuarioFicTextDAO();
		
		Usuario usuario =usuarioDAO.getUsuario(nia);
		
		System.out.println(usuario.getId() + " - " + usuario.getNickname() + " - " + usuario.getEmail());
		
	}
	public void guardarUsuarioXML(int nia, int parser) {
//		IUsuarioDao usuarioDAO = new UsuarioFicTextDAO();
		IUsuarioDao usuarioDAO =new UsuarioXMLDAO();
/*		
		if(parser==1) {
			usuarioDAO = new UsuarioXMLDAO();
		}else {
			usuarioDAO = new UsuarioJAXBDAO();
		}
*/		
		Usuario usuario1 =usuarioDAO.getUsuario(nia);
		if(usuario1!=null) {
			System.out.println(usuario1.getId() + " - " + usuario1.getNombre() + " - " + usuario1.getApellido1()  + " - " + usuario1.getApellido2() + " - " + usuario1.getNickname() + " - " + usuario1.getEmail());
		}
		
		List<Usuario> lista =usuarioDAO.getListaUsuarios();
		for (Usuario usuario : lista) {
			System.out.println(usuario.getId() + " - " + usuario.getNombre() + " - " + usuario.getApellido1()  + " - " + usuario.getApellido2() + " - " + usuario.getNickname() + " - " + usuario1.getEmail());	
		}

		

	}	

	public void guardarUsuarioJSON(int nia) {
		IUsuarioDao usuarioDAO =new UsuarioJSONDAO();
		Usuario usuario1 =usuarioDAO.getUsuario(nia);
		if(usuario1!=null) {
			System.out.println(usuario1.getId() + " - " + usuario1.getNombre() + " - " + usuario1.getApellido1()  + " - " + usuario1.getApellido2() + " - " + usuario1.getNickname() + " - " + usuario1.getEmail());
		}
		
		List<Usuario> lista =usuarioDAO.getListaUsuarios();
		for (Usuario usuario : lista) {
			System.out.println(usuario.getId() + " - " + usuario.getNombre() + " - " + usuario.getApellido1()  + " - " + usuario.getApellido2() + " - " + usuario.getNickname() + " - " + usuario1.getEmail());	
		}

		

	}	

	public void guardarUsuariosXML() {
		IUsuarioDao usuarioDAO = new UsuarioFicTextDAO();
		
		List<Usuario> listaUsuarios =usuarioDAO.getListaUsuarios();
		
		usuarioDAO = new UsuarioXMLDAO();

		usuarioDAO.guardarUsuarios(listaUsuarios);
		
	}	
	public void imprimirUsuarios() {
		IUsuarioDao usuarioDAO =null;
		String sClase=GestorConfiguracion.getInfoAtributoConfiguracion("usuariodaoimp");
		try {
			Class<?> clase= Class.forName(sClase);
			Constructor<?> constructor= clase.getDeclaredConstructor();
			usuarioDAO = (IUsuarioDao)constructor.newInstance(null);
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<Usuario> listaUsuarios =usuarioDAO.getListaUsuarios();
		
		for (Usuario user :listaUsuarios) {
			System.out.println(user.getId() + " - " + user.getNickname() + " - " + user.getEmail());
		}	
	}

}
