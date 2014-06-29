/**
 * 
 */
package ge.tsu.server.db.dao;

import ge.tsu.server.db.dto.Person;
import ge.tsu.server.db.dto.Police;
import ge.tsu.server.db.dto.medwork.CustomerDisease;
import ge.tsu.server.db.dto.medwork.CustomerImunization;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

/**
 * @author vamekh
 *
 */
public interface MainDao {
	SessionFactory getSessionFactory();
	void setSessionFactory(SessionFactory sessionFactory);
	Session getSession();
	
	//custom methods
	
	Person getCustomer(String pn);
	
	List<CustomerDisease> getCustomerInfectionDisesases(Person customer);
	
	List<CustomerDisease> getCustomerChronicDiseases(Person customer);
	
	List<Police> getCustomerPolices(Person customer);
	
	List<CustomerImunization> getCustomerImunizations(Person customer);
	
	void createTables();
}
