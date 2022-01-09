package es.redmetro.dam2.dao.orm;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import es.redmetro.dam2.dao.IBaseDeDatos;
import es.redmetro.dam2.utilidades.UtilidadHibernate;
import es.redmetro.dam2.vo.Tren;

public class TrenHibernateDAO implements IBaseDeDatos<Tren> {
	
	public void crear(Tren entidad) {
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

	public void modificar(Tren entidad) {
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

	public void borrar(Tren entidad) {
		Session session = UtilidadHibernate.getSession();
		
		Transaction tx = session.beginTransaction();
		
		Tren tren = session.find(Tren.class, entidad.getCodigoTren());
		
		if(tren!=null) {
			session.delete(entidad);
			tx.commit();
		}
		else
			System.out.println("No existe el tren a borrar");
		
		session.close();
	}

	public Tren consultarPorID(int codEntidad, Class<Tren> clase) {
		Session session = UtilidadHibernate.getSession();
		
		Tren tren = session.find(Tren.class, codEntidad);
		
		if(tren==null) 
			System.out.println("No existe el tren");
		
		session.close();
		return tren;
	}

	public List<Tren> consultarLista(Class<Tren> clase) {
		List<Tren> listaTrenes = null;
		Session session = UtilidadHibernate.getSession();
		
		listaTrenes = session.createNativeQuery("SELECT * FROM T_TREN", Tren.class).list();
		return listaTrenes;
	}
	
}
