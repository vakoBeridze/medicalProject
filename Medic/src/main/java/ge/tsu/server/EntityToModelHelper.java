package ge.tsu.server;

import ge.tsu.server.entities.Person;
import ge.tsu.shared.UserModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Vako
 * Date: 01.06.2014
 * Time: 17:32
 */
public class EntityToModelHelper {

	public List<UserModel> personsToUserModels(List<Person> persons) {

		List<UserModel> models = new ArrayList<UserModel>();
		for (Person person : persons) {
			models.add(personToUserModel(person));
		}
		return models;
	}

	public UserModel personToUserModel(Person person) {
		UserModel userModel = new UserModel();
		userModel.setId(person.getId());
		userModel.setUserName(person.getEmail());
		userModel.setFirstName(person.getFirstName());
		userModel.setLastName(person.getLastName());
		userModel.setFatherName(person.getFatherName());
		userModel.setGender(person.getGender());
		userModel.setEmail(person.getEmail());
		userModel.setPassword(person.getPassword());
		userModel.setPn(person.getPn());
		userModel.setPhoneNumber(person.getPhoneNumber());
		userModel.setBloodGroup(person.getBloodGroup());
		userModel.setBirthDate(person.getBirthDate());
		userModel.setProfessionAndJob(person.getProfessionAndJob());

		return userModel;
	}
}
