package ge.tsu.server;

import ge.tsu.server.entities.Person;
import ge.tsu.shared.UserModel;

/**
 * Created with IntelliJ IDEA.
 * User: Vako
 * Date: 01.06.2014
 * Time: 19:36
 */
public class ModelToEntityHelper {


	public Person userModelToPerson(UserModel model) {
		Person person = new Person();
		person.setId(model.getId());
		person.setFirstName(model.getFirstName());
		person.setLastName(model.getLastName());
		person.setFatherName(model.getFatherName());
		person.setGender(model.getGender());
		person.setEmail(model.getEmail());
//FIXME
//		person.setPassword(model.getPassword());
		person.setPn(model.getPn());
		person.setPhoneNumber(model.getPhoneNumber());
		person.setBloodGroup(model.getBloodGroup());
		person.setBirthDate(model.getBirthDate());
		person.setProfessionAndJob(model.getProfessionAndJob());

		return person;
	}
}
