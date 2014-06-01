package ge.tsu.server.ejb;

import org.apache.log4j.Logger;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * Created by Vako on 5/11/2014
 */
@Stateless
@Local(AuthorizationLocal.class)
public class AuthorizationSession implements AuthorizationLocal {
	Logger logger = Logger.getLogger(getClass());

	@PersistenceContext
	private EntityManager em;

	@Override
	public String checkUser(String login, String password) {
		logger.info("User: " + login + " Checked");
		Query query = em.createQuery("select p.password from Person p where p.email=:login");
		query.setParameter("login", login);
		return (String) query.getSingleResult();
	}

}
