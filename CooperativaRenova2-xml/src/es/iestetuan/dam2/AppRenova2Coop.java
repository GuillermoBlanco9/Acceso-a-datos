package es.iestetuan.dam2;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import es.iestetuan.dam2.dao.InterfazBaseGeneralDao;
import es.iestetuan.dam2.dao.hibernate.AreaCooperativaHibernateDao;
import es.iestetuan.dam2.dao.hibernate.ContratoHibernateDao;
import es.iestetuan.dam2.dao.hibernate.GrupoTrabajoHibernateDao;
import es.iestetuan.dam2.dao.hibernate.ServicioHibernateDao;
import es.iestetuan.dam2.dao.hibernate.SocioGrupoTrabajoHibernateDao;
import es.iestetuan.dam2.dao.hibernate.SocioHibernateDao;
import es.iestetuan.dam2.dao.jdbc.ServicioJdbcDao;
import es.iestetuan.dam2.exception.Renova2CoopException;
import es.iestetuan.dam2.vo.AreaCooperativa;
import es.iestetuan.dam2.vo.Contrato;
import es.iestetuan.dam2.vo.GrupoTrabajo;
import es.iestetuan.dam2.vo.PersonajeInspirador;
import es.iestetuan.dam2.vo.Servicio;
import es.iestetuan.dam2.vo.Socio;
import es.iestetuan.dam2.vo.SocioGrupoTrabajo;

public class AppRenova2Coop {
	public static void main(String[] args) {
		AppRenova2Coop appRenovaCoop = new AppRenova2Coop();
		// Consulta informaci�n Entrada
		appRenovaCoop.pruebasHibernate();
	}

	public void pruebasHibernate() {
		// Pruebas DAO con una entidad (JDBC & Hibernate) 
		pruebasDAOServicio();

		// Pruebas Hibernate 1:1
		pruebasGrupoTrabajoPersonajeInspiradorHibernate();

		// Pruebas 1:N � N:1
		pruebasSocioContratoServicioHibernate();
		
		// Pruebas N:M sin atributos relaci�n
		pruebasGrupoTrabajoAreaCooperativaHibernate();

		// Pruebas N:M con atributos relaci�n
		pruebasSocioGrupoTrabajoHibernate();
	}

	// Pruebas DAO con una entidad JDBC & Hibernate 
	private void pruebasDAOServicio() {
	    // PRUEBAS DAO JDBC sobre Servicio  
	    //InterfazBaseGeneralDao<Servicio> iServicio = new ServicioJdbcDao();

	    // PRUEBAS DAO HIBERNATE sobre Servicio  
	    InterfazBaseGeneralDao<Servicio> iServicio = new ServicioHibernateDao();


	    // Crear nuevos SERVICIOS

	    Servicio servicio = null;
	    Servicio servicio1 =null;
	    try {
		    servicio = new Servicio();
		    servicio.setCodigoServicio(50); // En Hibernate no es necesario incluir el c�digo de servicio ya que la clase del generador de IDs es "increment"
		    servicio.setDescripcion("Green Light");
		    servicio.setPrecio(35.7f);
		    
	    	iServicio.crear(servicio);

	    	// Crer nuevo SERVICIO
	    	servicio1 = new Servicio();
	    	servicio1.setCodigoServicio(51); // En Hibernate no es necesario incluir el c�digo de servicio ya que la clase del generador de IDs es "increment"
		    servicio1.setDescripcion("Blue velvet");
		    servicio1.setPrecio(55.5f);

	    	iServicio.crear(servicio1);
		} catch (Renova2CoopException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	    // Obtener SERVICIO por ID y Lista de SERVICIOS
	    Servicio servicio2 =null;
	    List<Servicio> servicios =null;
	    try {
	    	// Obtener SERVICIO por ID 
	    	long codigoServicio=1;
	    	servicio2= iServicio.getEntidadPorID(codigoServicio);
	    	
		    // Consulta SERVICIOS
		    servicios = iServicio.getListaEntidades();
		    System.out.println(servicios.size());

		} catch (Renova2CoopException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	    
	    // actualizar SERVICIO
	    try {
		    servicio2.setDescripcion("Green Light + Consultor�a");
		    servicio2.setPrecio(51.2f);
	    	if(servicio2 !=null)
	    		iServicio.actualizar(servicio2);
		} catch (Renova2CoopException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    	    
	    // borrar SERVICIO
	    try {
	    	// Obtener SERVICIO por ID 
	    	long codigoServicio=2;
	    	servicio2= iServicio.getEntidadPorID(codigoServicio);
	    	if(servicio2 !=null)
	    		iServicio.borrar(servicio2);

	    	// Obtener SERVICIO por ID --> Debe devolver null pues se ha borrado- 
	    	servicio2= iServicio.getEntidadPorID(codigoServicio);
		    // Consulta SERVICIOS 
		    servicios = iServicio.getListaEntidades();
		    System.out.println(servicios.size());

		} catch (Renova2CoopException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	// Pruebas Hibernate 1:1
	private void pruebasGrupoTrabajoPersonajeInspiradorHibernate() {
	    // Creaci�n de un socio consulta de un socio por ID  
	    InterfazBaseGeneralDao<GrupoTrabajo> iGrupoTrabajo = new GrupoTrabajoHibernateDao();
	    GrupoTrabajo grupoTrabajo= new GrupoTrabajo();
	    // grupoTrabajo.setCodigoGrupoTrabajo(0); No es necesario incluir el c�digo de grupo de trabajo  ya que la clase del generador de IDs es "increment"
	    grupoTrabajo.setDescripcionCorta("Grupo 1");
	    grupoTrabajo.setDescripcionLarga("Grupo encargado de la comunicaci�n interna dentro de la cooperativa.");
	    
	    PersonajeInspirador personajeInspirador = new PersonajeInspirador();
	    // personajeInspirador.setCodigoPersonaje(0); No es necesario incluir el c�digo del Personaje pues se obtiene del grupo de trabajo asociado.
	    personajeInspirador.setNombre("Jane Goodall");
	    personajeInspirador.setIndicadorPersonajeReal(true);
	    // Relaci�n 1:1
	    grupoTrabajo.setPersonajeInspirador(personajeInspirador);
	    personajeInspirador.setGrupoTrabajo(grupoTrabajo);
	    try {
	    	// Se crea el grupo de trabajo
			iGrupoTrabajo.crear(grupoTrabajo);
		} catch (Renova2CoopException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}

	// Pruebas 1:N � N:1
	private void pruebasSocioContratoServicioHibernate() {
	    // Creaci�n de un socio consulta de un socio por ID  
	    InterfazBaseGeneralDao<Socio> iSocio = new SocioHibernateDao();
	    // Creaci�n de un Socio.
	    Socio socio= new Socio();
	    // socio.setCodigoSocio(0); No es necesario incluir el c�digo de socio ya que la clase del generador de IDs es "increment"
	    socio.setNif("00000001R");
	    socio.setNombre("Carla");
	    socio.setNumeroCuenta("ES6000491500051234567892");
	    socio.setTelefono(666541256);
	    socio.setCantidadInicialAportada(100);
	    
	    socio.setSociosGrupoTrabajo(null); //El socio no est� vinculado a un grupo de trabajo
	    socio.setContratos(null); //El socio no tiene contratos
	    try {
	    	// SE crea el socio
			iSocio.crear(socio);
		} catch (Renova2CoopException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	    // A partir de un socio obtenido; Asignaci�n de un servicio a un  y a�adire un contrato a un socio. A partir de ah�:  
	    // Al crear el contrato se debe crear el contrato, el servicio y las relaciones.
	    // Construcci�n de un contrato.
	    Contrato contrato = new Contrato();
	    //contrato.setCodigoContrato(0); No es necesario incluir el c�digo de contrato ya que la clase del generador de IDs es "increment"
	    contrato.setIndicadorPagoMensual(false);
	    
	    // Construcci�n SERVICIO	    
	    Servicio servicio = new Servicio();
	    //servicio.setCodigoServicio(50); No es necesario incluir el c�digo de servicio ya que la clase del generador de IDs es "increment"
	    servicio.setDescripcion("Blue Tetu�n");
	    servicio.setPrecio(135.7f);
	    contrato.setServicio(servicio);
	    contrato.setSocio(socio);
	    // Creaci�n de un socio consulta de un socio por ID  
	    InterfazBaseGeneralDao<Contrato> iContrato = new ContratoHibernateDao();

	    Socio socio1= null;	    
	    try {
	    	// SE crea el socio
	    	iContrato.crear(contrato);
			//Se obtiene el socio con c�digo 1
	    	long codigoSocio=1;
			socio1=iSocio.getEntidadPorID(codigoSocio);

		} catch (Renova2CoopException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    // Debe aparecer el contrato previamente guardado.
	    List<Contrato> contratosSocio = socio1.getContratos();
	    System.out.println("contratosSocio: " + contratosSocio.size());

	}
	
	// Pruebas N:M sin atributos relaci�n
	private void pruebasGrupoTrabajoAreaCooperativaHibernate() {
	    // Creaci�n de un grupo de trabajo con area cooperativa  
	    InterfazBaseGeneralDao<GrupoTrabajo> iGrupoTrabajo = new GrupoTrabajoHibernateDao();
	    
	    GrupoTrabajo grupoTrabajo= new GrupoTrabajo();
	    // grupoTrabajo.setCodigoGrupoTrabajo(0); No es necesario incluir el c�digo de grupo de trabajo  ya que la clase del generador de IDs es "increment"
	    grupoTrabajo.setDescripcionCorta("Grupo 2");
	    grupoTrabajo.setDescripcionLarga("Grupo encargado de la comunicaci�n externa dentro de la cooperativa.");
	    
	    PersonajeInspirador personajeInspirador = new PersonajeInspirador();
	    // personajeInspirador.setCodigoPersonaje(0); No es necesario incluir el c�digo del Personaje pues se obtiene del grupo de trabajo asociado.
	    personajeInspirador.setNombre("Isacc Asimov");
	    personajeInspirador.setIndicadorPersonajeReal(true);
	    grupoTrabajo.setPersonajeInspirador(personajeInspirador);
	    
	    AreaCooperativa areaCooperativa = new AreaCooperativa();
	    // areaCooperativa.setCodigoAreaCooperativa(0); No es necesario incluir el c�digo del �rea de la Cooperativa pues se obtiene del grupo de trabajo asociado.
	    areaCooperativa.setDescripcion("�rea de comunicaci�n");


	    // Relaci�n N:M
	    Set<AreaCooperativa> areasComunicacionGrupoTrabajo= new HashSet<AreaCooperativa>();
	    areasComunicacionGrupoTrabajo.add(areaCooperativa);
	    areasComunicacionGrupoTrabajo.add(areaCooperativa);
	    grupoTrabajo.setAreasCooperativa(areasComunicacionGrupoTrabajo);
	    
	    Set<GrupoTrabajo> gruposTrabajoAreaComunicacion= new HashSet<GrupoTrabajo>();
	    gruposTrabajoAreaComunicacion.add(grupoTrabajo);
	    areaCooperativa.setGruposTrabajo(gruposTrabajoAreaComunicacion);

	    try {
	    	// SE crea el Grupo de Trabajo (as� como el Area Cooperativa, y su relaci�n)
			iGrupoTrabajo.crear(grupoTrabajo);
		} catch (Renova2CoopException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	

	    // Otro ejemplo (
	    // A�adir un nuevo grupo de trabajo a un �rea cooperativa existente   
	    InterfazBaseGeneralDao<AreaCooperativa> iAreaCooperativa = new AreaCooperativaHibernateDao();
	    AreaCooperativa areaCooperativa1=null;
	    GrupoTrabajo grupoTrabajoA= null;
	    try {
		    // Obtener �rea de comunicaci�n (previamente creado) 
	    	long codigoAreaCooperativa=1;
	    	areaCooperativa1= iAreaCooperativa.getEntidadPorID(codigoAreaCooperativa);
	    	
	    	long codigoGrupoTrabajo=1;
	    	grupoTrabajoA= iGrupoTrabajo.getEntidadPorID(codigoGrupoTrabajo);	 	    	
		} catch (Renova2CoopException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	    // Relaci�n N:M
	    Set<AreaCooperativa> areasComunicacionGrupoTrabajo1= new HashSet<AreaCooperativa>();
	    areasComunicacionGrupoTrabajo1.add(areaCooperativa1);
	    areasComunicacionGrupoTrabajo1.add(areaCooperativa1);
	    grupoTrabajoA.setAreasCooperativa(areasComunicacionGrupoTrabajo1);
	    
	    // Se a�ade un nuevo grupo de trabajo a los que devolvi� areaCooperativa1
	    Set<GrupoTrabajo> gruposTrabajoAreaComunicacion1= areaCooperativa1.getGruposTrabajo();
	    gruposTrabajoAreaComunicacion1.add(grupoTrabajoA);
	    areaCooperativa1.setGruposTrabajo(gruposTrabajoAreaComunicacion1);

	    System.out.println(areaCooperativa1.getGruposTrabajo().size());
	    try {
	    	// SE crea el Grupo de Trabajo (as� como el Area Cooperativa, y su relaci�n)
	    	iGrupoTrabajo.actualizar(grupoTrabajoA);
		} catch (Renova2CoopException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	

	}

	// Pruebas N:M con atributos relaci�n
	private void pruebasSocioGrupoTrabajoHibernate() {
	    // Creaci�n de un grupo de trabajo con area cooperativa  
	    InterfazBaseGeneralDao<SocioGrupoTrabajo> iSocioGrupoTrabajo = new SocioGrupoTrabajoHibernateDao();
	    
	    
	    InterfazBaseGeneralDao<GrupoTrabajo> iGrupoTrabajo = new GrupoTrabajoHibernateDao();
	    GrupoTrabajo grupoTrabajo= new GrupoTrabajo();
	    // grupoTrabajo.setCodigoGrupoTrabajo(0); No es necesario incluir el c�digo de grupo de trabajo  ya que la clase del generador de IDs es "increment"
	    grupoTrabajo.setDescripcionCorta("Grupo 3");
	    grupoTrabajo.setDescripcionLarga("Grupo encargado de la Atenci�n al Socio.");
	    PersonajeInspirador personajeInspirador = new PersonajeInspirador();
	    // personajeInspirador.setCodigoPersonaje(0); No es necesario incluir el c�digo del Personaje pues se obtiene del grupo de trabajo asociado.
	    personajeInspirador.setNombre("F�lix Rodr�guez de la Fuente");
	    personajeInspirador.setIndicadorPersonajeReal(true);
	    grupoTrabajo.setPersonajeInspirador(personajeInspirador);

	    try {
	    	// SE crea el Grupo de Trabajo
	    	iGrupoTrabajo.crear(grupoTrabajo);
		} catch (Renova2CoopException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	

	    
	    InterfazBaseGeneralDao<Socio> iSocio = new SocioHibernateDao();
	    Socio socio= new Socio();
	    // socio.setCodigoSocio(0); No es necesario incluir el c�digo de socio ya que la clase del generador de IDs es "increment"
	    socio.setNif("00000002R");
	    socio.setNombre("Blasco");
	    socio.setNumeroCuenta("ES6000671500901234567892");
	    socio.setTelefono(676541256);
	    socio.setCantidadInicialAportada(1000);
	    try {
	    	// SE crea el Socio
	    	iSocio.crear(socio);
		} catch (Renova2CoopException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	



	    // Relaci�n N:M con atributos de relaci�n  (fecha alta y fecha Baja)
	    // Se trabaja con la nueva entidad intermedia generada
	    SocioGrupoTrabajo socioGrupoTrabajo = new SocioGrupoTrabajo();
	    socioGrupoTrabajo.setSocio(socio);
	    socioGrupoTrabajo.setGrupoTrabajo(grupoTrabajo);
	    socioGrupoTrabajo.setFechaAlta(new Date());
	    socioGrupoTrabajo.setFechaBaja(null);
	    
	    try {		
	    	// SE crea la relaci�n Socio - Grupo de Trabajo
	    	iSocioGrupoTrabajo.crear(socioGrupoTrabajo);
		} catch (Renova2CoopException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}

}

