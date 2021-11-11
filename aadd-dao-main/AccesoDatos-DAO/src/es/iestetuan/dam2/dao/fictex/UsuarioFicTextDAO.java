package es.iestetuan.dam2.dao.fictex;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import es.iestetuan.dam2.GestorConfiguracion;
import es.iestetuan.dam2.dao.IUsuarioDao;
import es.iestetuan.dam2.vo.Usuario;

public class UsuarioFicTextDAO implements IUsuarioDao {
	
	private static String FICHERO_USUARIOS=GestorConfiguracion.getInfoAtributoConfiguracion("rutaFicheroTexto");
	
	public Usuario getUsuario(int id) {
		Usuario usuarioRespuesta=null;
		File fichero= new File(FICHERO_USUARIOS);
		FileReader fr;
		BufferedReader br= null;
		String sLinea=null;
		boolean primerVez=true;
		try {
			fr = new FileReader(fichero, Charset.forName("UTF-8"));
			br= new BufferedReader(fr);
			while((sLinea=br.readLine())!=null) {
				if(primerVez) {
					primerVez=false;
				}else{
					// Información de una línea
					String[] partesLinea=sLinea.split(",");
					String sId = partesLinea[0];
					int idRespuesta = Integer.parseInt(sId);
					if(idRespuesta == id){
						String nickname= partesLinea[1];
						String email= partesLinea[2];
						// Se carga la información de un usuario
						usuarioRespuesta=new Usuario();
						usuarioRespuesta.setId(idRespuesta);
						usuarioRespuesta.setNickname(nickname);
						usuarioRespuesta.setEmail(email);
						break;
					}
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return usuarioRespuesta;
	}

	public List<Usuario> getListaUsuarios() {
		List<Usuario> listaUsuarios = new ArrayList<Usuario>();
		
		// Consultar fichero, procesar información y cargar la lista de usuarios
		File fichero= new File(FICHERO_USUARIOS);
		FileReader fr;
		BufferedReader br= null;
		String sLinea=null;
		boolean primerVez=true;
		try {
			fr = new FileReader(fichero, Charset.forName("UTF-8"));
			br= new BufferedReader(fr);
			while((sLinea=br.readLine())!=null) {
				if(primerVez) {
					primerVez=false;
				}else{
					Usuario usuarioRespuesta=new Usuario();
					// Información de una línea
					String[] partesLinea=sLinea.split(",");
					String sId = partesLinea[0];
					int idRespuesta = Integer.parseInt(sId);
					String nickname= partesLinea[1];
					String email= partesLinea[2];
					// Se carga la información de un usuario				
					usuarioRespuesta.setId(idRespuesta);
					usuarioRespuesta.setNickname(nickname);
					usuarioRespuesta.setEmail(email);
					listaUsuarios.add(usuarioRespuesta);
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return listaUsuarios;
	}

	@Override
	public void guardarUsuario(Usuario usuario) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void guardarUsuarios(List<Usuario> usuarios) {
		// TODO Auto-generated method stub
		
	}

}
