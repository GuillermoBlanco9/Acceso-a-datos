package es.redmetro.dam2;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import es.redmetro.dam2.dao.IoperacionesBBDD;
import es.redmetro.dam2.dao.orm.*;
import es.redmetro.dam2.utilidades.ConexionFTP;
import es.redmetro.dam2.utilidades.GestorConexion;
import es.redmetro.dam2.vo.*;

public class MainRedMetro {
	public static void main(String[] args) {
		
		MainRedMetro red = new MainRedMetro();
		AccesoHibernate operacionAcceso = new AccesoHibernate();
		TrenHibernate operacionTren = new TrenHibernate();
		EstacionHibernate operacionEstacion = new EstacionHibernate();
		

		List<Estacion> listaEstaciones = new ArrayList<Estacion>();
		List<Tren> listaTrenes = new ArrayList<Tren>();
		List<Acceso> listaAccesos = new ArrayList<Acceso>();
		
		ConexionFTP.parseo(listaTrenes,listaAccesos,listaEstaciones);
		
		for(Tren tren : listaTrenes) {
			operacionTren.crear(tren);
		}
		
		for(Estacion estacion : listaEstaciones) {
			operacionEstacion.crear(estacion);
		}
		
		for(Acceso acceso : listaAccesos) {
			operacionAcceso.crear(acceso);
		}
		
		
	}
}
