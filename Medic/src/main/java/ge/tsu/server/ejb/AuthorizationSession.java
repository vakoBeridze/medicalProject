package ge.tsu.server.ejb;

import org.apache.log4j.Logger;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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
	public String checkUser(String userName, String password) {
		logger.info("User: " + userName + " Checked");
		return "vako";
	}

}
