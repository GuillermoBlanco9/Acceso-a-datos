package es.redmetro.dam2.dao.orm;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import es.redmetro.dam2.dao.IoperacionesBBDD;
import es.redmetro.dam2.utilidades.UtilidadHibernate;
import es.redmetro.dam2.vo.Cochera;

public class CocheraHibernate implements IoperacionesBBDD<Cochera> {

	public void crear(Cochera entidad) {
		Session session = UtilidadHibernate.getSession();
		
		Transaction tx = session.beginTransaction();
		
		try {
			session.save(entidad);
			tx.commit();
		}
		catch(Exception e) {
			System.out.println("La cochera ya existe en la base de datos");
		}
		finally {
			session.close();
		}
	}

	public void modificar(Cochera entidad) {
		Session session = UtilidadHibernate.getSession();
		
		Transaction tx = session.beginTransaction();
		
		try {
			session.update(entidad);
			tx.commit();
		}
		catch(Exception e) {
			System.out.println("No existe la cochera a modificar");
		}
		finally {
			session.close();
		}
	}

	public void borrar(Cochera entidad) {
		Session session = UtilidadHibernate.getSession();
		
		Transaction tx = session.beginTransaction();
		
		Cochera cochera = session.find(Cochera.class, entidad.getCodigoCochera());
		
		if(cochera!=null) {
			session.delete(entidad);
			tx.commit();
		}
		else
			System.out.println("No existe la cochera a borrar");
		
		session.close();
	}

	public Cochera consultarPorID(int codEntidad, Class<Cochera> clase) {
		Session session = UtilidadHibernate.getSession();
		
		Cochera cochera = session.find(Cochera.class, codEntidad);
		
		if(cochera==null) 
			System.out.println("No existe la cochera");
		
		session.close();
		return cochera;
	}

	public List<Cochera> consultarLista(Class<Cochera> clase) {
		List<Cochera> listaCocheras = null;
		Session session = UtilidadHibernate.getSession();
		
		listaCocheras = session.createNativeQuery("SELECT * FROM T_COCHERA", Cochera.class).list();
		return listaCocheras;
	}
}

