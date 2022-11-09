package curso.java;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.List;

import javax.swing.JOptionPane;

import curso.java.entity.Alumno;
import curso.java.util.JpaUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

public class MainBDD {
	
	private static final Logger logger = LogManager.getLogger(MainBDD.class);
	public static void main(String[] args) {
		logger.debug("Empezando");
		MainBDD main = new MainBDD();
		
		//main.insertarAlumnos();
		//main.listarAlumnos();
		//main.buscarAlumnosPK();
		main.buscarAlumnosDNI();
		
		
	}
	
	/**
	 * Metodo para insertar 3 alumnos en la tabla alumnos de la base de datos curso_java
	 */
	private void insertarAlumnos() {
	logger.debug("Entrando al metodo insertarAlumnos");
	EntityManager em = JpaUtil.getEntityManager();
	Alumno alumno1	= new Alumno("Rosa","Navarro","11441267D");
	Alumno alumno2	= new Alumno("Pedro","Rodriguez","31223205S");
	Alumno alumno3	= new Alumno("Maria","Navas","68302629N");
	em.getTransaction().begin();
	em.persist(alumno1);
	em.persist(alumno2);
	em.persist(alumno3);
	em.getTransaction().commit();
	em.close();
	
	}
	
	/**
	 * Metodo para listar todos los alumnos en la tabla alumnos de la base de datos curso_java
	 */
	private void listarAlumnos() {
	logger.debug("Entrando al metodo listarAlumnos");
	EntityManager em = JpaUtil.getEntityManager();
	List<Alumno> alumnos = em.createQuery("from Alumno",Alumno.class).getResultList();
	alumnos.forEach(logger::info);
	em.close();
	}
	
	/**
	 * Metodo para buscar los alumnos por primary key en la tabla alumnos de la base de datos curso_java
	 */
	private void buscarAlumnosPK() {
	logger.debug("Entrando al metodo buscarAlumnosPK");
	EntityManager em = JpaUtil.getEntityManager();
	Long id = Long.valueOf(JOptionPane.showInputDialog("Ingrese el id del cliente a buscar:"));
	Alumno alumno = em.find(Alumno.class, id);
	logger.info(alumno);
	em.close();
	}
	
	/**
	 * Metodo para buscar los alumnos por DNI en la tabla alumnos de la base de datos curso_java.
	 */
	private void buscarAlumnosDNI() {
	logger.debug("Entrando al metodo buscarAlumnosDNI");
	EntityManager em = JpaUtil.getEntityManager();
	Query query = em.createQuery("select a from Alumno a where a.dni=?1", Alumno.class);
	
	String dni = JOptionPane.showInputDialog("Ingrese el DNI a buscar:");
	query.setParameter(1, dni);
	query.setMaxResults(1);
	Alumno alumno = (Alumno)query.getSingleResult();
	logger.info(alumno);
	em.close();
	}

}
