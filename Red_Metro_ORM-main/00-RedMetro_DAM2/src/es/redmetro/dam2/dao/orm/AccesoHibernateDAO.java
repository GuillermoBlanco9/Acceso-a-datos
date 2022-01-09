package es.redmetro.dam2.dao.orm;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import es.redmetro.dam2.dao.IBaseDeDatos;
import es.redmetro.dam2.utilidades.UtilidadHibernate;
import es.redmetro.dam2.vo.Acceso;

public class AccesoHibernateDAO implements IBaseDeDatos<Acceso> {
	
	public void crear(Acceso entidad) {
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

	public void modificar(Acceso entidad) {
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

	public void borrar(Acceso entidad) {
		Session session = UtilidadHibernate.getSession();
		
		Transaction tx = session.beginTransaction();
		
		Acceso acceso = session.find(Acceso.class, entidad.getCodigoAcceso());
		
		if(acceso!=null) {
			session.delete(entidad);
			tx.commit();
		}
		else
			System.out.println("No existe el acceso a borrar");
		
		session.close();
	}

	public Acceso consultarPorID(int codEntidad, Class<Acceso> clase) {
		Session session = UtilidadHibernate.getSession();
		
		Acceso acceso = session.find(Acceso.class, codEntidad);
		
		if(acceso==null) 
			System.out.println("No existe el acceso");
		
		session.close();
		return acceso;
	}

	public List<Acceso> consultarLista(Class<Acceso> clase) {
		List<Acceso> listaAccesos = null;
		Session session = UtilidadHibernate.getSession();
		
		listaAccesos = session.createNativeQuery("SELECT * FROM T_ACCESO", Acceso.class).list();
		return listaAccesos;
	}
}
