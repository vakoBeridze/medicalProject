package ge.tsu.server.ejb;

/**
 * Created by Vako on 5/11/2014
 */
public interface AuthorizationLocal {
	String checkUser(String userName, String password);
}
