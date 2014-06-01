package ge.tsu.server.ejb;

import ge.tsu.server.entities.Person;
import ge.tsu.shared.UserModel;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Vako
 * Date: 01.06.2014
 * Time: 17:11
 */
public interface AppLocal {
	List<Person> loadUsers();

	Person saveUser(Person person);

	void deleteUser(Person person);

	Person getUserByUserName(String login);
}
