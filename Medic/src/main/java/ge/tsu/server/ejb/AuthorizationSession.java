package ge.tsu.server.ejb;

import org.apache.log4j.Logger;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
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
		logger.info("Doctor: " + login + " Checked");
		Query query = em.createQuery("select d.password from Doctor d where d.email=:login");
		query.setParameter("login", login);
        try {
            return (String) query.getSingleResult();
        } catch (NoResultException e) {
            logger.info("Doctor: " + login + " Not Found !!!");
            return null;
        }
	}

}
