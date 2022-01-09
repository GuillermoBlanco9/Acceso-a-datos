package es.redmetro.dam2.dao.orm;

import java.util.List;


import org.hibernate.Session;
import org.hibernate.Transaction;

import es.redmetro.dam2.dao.IoperacionesBBDD;
import es.redmetro.dam2.utilidades.UtilidadHibernate;
import es.redmetro.dam2.vo.Linea;

public class LineaHibernate implements IoperacionesBBDD<Linea> {
	
	public void crear(Linea entidad) {
		Session session = UtilidadHibernate.getSession();
		
		Transaction tx = session.beginTransaction();
		
		try {
			session.save(entidad);
			tx.commit();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			session.close();
		}
	}

	public void modificar(Linea entidad) {
		Session session = UtilidadHibernate.getSession();
		
		Transaction tx = session.beginTransaction();
		
		try {
			session.update(entidad);
			tx.commit();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			session.close();
		}
	}

	public void borrar(Linea entidad) {
		Session session = UtilidadHibernate.getSession();
		
		Transaction tx = session.beginTransaction();
		
		Linea linea = session.find(Linea.class, entidad.getCodigoLinea());
		
		if(linea!=null) {
			session.delete(entidad);
			tx.commit();
		}
		else
			System.out.println("No existe la linea a borrar");
		
		session.close();
	}

	public Linea consultarPorID(int codEntidad, Class<Linea> clase) {
		Session session = UtilidadHibernate.getSession();
		
		Linea linea = session.find(Linea.class, codEntidad);
		
		if(linea==null) 
			System.out.println("No existe el tren");
		
		session.close();
		return linea;
	}

	public List<Linea> consultarLista(Class<Linea> clase) {
		List<Linea> listaLineas = null;
		Session session = UtilidadHibernate.getSession();
		
		listaLineas = session.createNativeQuery("SELECT * FROM T_LINEA", Linea.class).list();
		
		return listaLineas;
	}

}
