/**
 * 
 */
package ge.tsu.server.db.dao;


import ge.tsu.server.db.dto.*;
import ge.tsu.server.db.dto.medwork.CustomerDisease;
import ge.tsu.server.db.dto.medwork.CustomerImunization;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import java.util.Date;
import java.util.List;

/**
 * @author vamekh
 *
 */
public class MainDaoImpl implements MainDao {
	
	private SessionFactory sessionFactory;
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}


	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}


	public Session getSession(){
		return this.sessionFactory.getCurrentSession();
	}
	
	public Person getCustomer(String pn){
		Session session = getSession();
		session.beginTransaction();
		
		Criteria criteria = session.createCriteria(Person.class).add(Restrictions.eq("pn", pn));
		List<Person> result =(List<Person>) criteria.list();
		
		session.getTransaction().commit();
		session.close();
		
		if(result.size() != 1){
			return null;
		}
		return result.get(0);
	}
	
	
	public List<CustomerDisease> getCustomerInfectionDisesases(Person customer){
		Session session = getSession();
		session.beginTransaction();
		
		Query query = session.createQuery("from CustomerDisease where customer = ? and isInfection = 1");
		query.setParameter(0, customer);
		List<CustomerDisease> result = (List<CustomerDisease>) query.list();
		
		session.getTransaction().commit();
		session.close();
		
		if(result.size() == 0){
			return null;
		}
		return result;
	}
	
	
	public List<CustomerDisease> getCustomerChronicDiseases(Person customer){
		Session session = getSession();
		session.beginTransaction();
		
		Query query = session.createQuery("from CustomerDisease where customer = ? and deseaseStrength = 4");
		query.setParameter(0, customer);
		List<CustomerDisease> result = (List<CustomerDisease>) query.list();
		
		session.getTransaction().commit();
		session.close();
		
		if(result.size() == 0){
			return null;
		}
		return result;
	}
	
	public List<Police> getCustomerPolices(Person customer){
		Session session = getSession();
		session.beginTransaction();
		
		Query query = session.createQuery("from CustomerDisease where customer = ? and insuranceExpireDate < ?");
		query.setParameter(0, customer);
		query.setTimestamp(1, new Date());
		List<Police> result = (List<Police>) query.list();
		
		session.getTransaction().commit();
		session.close();
		
		if(result.size() == 0){
			return null;
		}
		return result;
	}
	
	public List<CustomerImunization> getCustomerImunizations(Person customer){
		Session session = getSession();
		session.beginTransaction();
		
		Query query = session.createQuery("from CustomerImunization where customer = ?");
		query.setParameter(0, customer);
		List<CustomerImunization> result = (List<CustomerImunization>) query.list();
		
		session.getTransaction().commit();
		session.close();
		
		if(result.size() == 0){
			return null;
		}
		return result;
	}
	
	public void createTables(){
		new Address();
		new Doctor();
		new Hospital();
	}
	
	/*public static void main(String[] args) {
		Configuration cfg = new Configuration();
		cfg.addResource("hibernate.cfg.xml");  // mapping to your hibernate mapping xml
		Properties props = new Properties();
		props.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
		cfg.setProperties(props);  // hibernate configuration properties, like dialect, connection url, etc.
		
		
		SchemaExport schemaExport = new SchemaExport(cfg);
		schemaExport.setDelimiter(";");
		schemaExport.setOutputFile("database-script.sql");
		schemaExport.setFormat(true);
		schemaExport.execute(false, false, false, true);
	}*/
}
