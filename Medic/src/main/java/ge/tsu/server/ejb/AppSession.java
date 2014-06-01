package ge.tsu.server.ejb;

import ge.tsu.server.entities.Person;
import org.apache.log4j.Logger;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Vako
 * Date: 01.06.2014
 * Time: 17:12
 */
@Stateless
@Local(AppLocal.class)
public class AppSession implements AppLocal {
	Logger logger = Logger.getLogger(getClass());

	@PersistenceContext
	private EntityManager em;


	@Override
	public List<Person> loadUsers() {
		Query query = em.createQuery("SELECT p FROM Person p");
		List<Person> persons = query.getResultList();
		return persons;
	}

	@Override
	public Person saveUser(Person person) {
		return em.merge(person);
	}

	@Override
	public void deleteUser(Person person) {
		Person branch = em.find(Person.class, person.getId());
		em.remove(branch);
	}

	@Override
	public Person getUserByUserName(String login) {
		Query query = em.createQuery("select p from Person as p where p.email=:login");
		query.setParameter("login", login);
		return (Person) query.getSingleResult();
	}
}
