package es.iestetuan.dam2.dao.json;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

import es.iestetuan.dam2.GestorConfiguracion;
import es.iestetuan.dam2.dao.IUsuarioDao;
import es.iestetuan.dam2.vo.Usuario;


public class UsuarioJSONDAO implements IUsuarioDao {

	private static String RUTA_FICHERO_JSON=GestorConfiguracion.getInfoAtributoConfiguracion("rutaFicheroJSON");
	@Override
	public Usuario getUsuario(int id) {
		Usuario usuario = null;
		File fichero = new File (RUTA_FICHERO_JSON);
		String jsonString= null;
		try {
			jsonString= new String(Files.readAllBytes(fichero.toPath()), Charset.forName("UTF-8"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Gson gson = new Gson();
		Usuario[] usuarios= gson.fromJson(jsonString, Usuario[].class);
		for (int i = 0; i < usuarios.length; i++) {
			usuario = usuarios[i];
			if(usuario.getId() == id) {
				break;
			}	
		}

		return usuario;
	}

	@Override
	public List<Usuario> getListaUsuarios() {
		List<Usuario> listaUsuarios= new ArrayList<Usuario>();
		File fichero = new File (RUTA_FICHERO_JSON);
		String jsonString= null;
		try {
			jsonString= new String(Files.readAllBytes(fichero.toPath()), Charset.forName("UTF-8"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Gson gson = new Gson();
		Usuario[] usuarios= gson.fromJson(jsonString, Usuario[].class);
		for (int i = 0; i < usuarios.length; i++) {
			Usuario usuario = usuarios[i];
			listaUsuarios.add(usuario);
		}
		return listaUsuarios;
	}

	@Override
	public void guardarUsuario(Usuario usuario) {
	}
	@Override
	public void guardarUsuarios(List<Usuario> listaUsuarios) {
	}
}
