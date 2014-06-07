package ge.tsu.server.ejb;

import ge.tsu.server.entities.Doctor;
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
	java.util.Set<? extends Person> loadUsers();

	Person savePerson(Person person);

    Doctor saveDoctor(Doctor doctor);

	void deleteUser(Person person);

	Doctor getUserByUserName(String login);

}
