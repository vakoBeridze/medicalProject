package ge.tsu.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import ge.tsu.client.service.AppService;
import ge.tsu.server.ejb.AppLocal;
import ge.tsu.server.entities.Person;
import ge.tsu.shared.UserModel;

import javax.ejb.EJB;
import java.util.List;

/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class AppServiceImpl extends RemoteServiceServlet implements AppService {

	EntityToModelHelper entityToModelHelper = new EntityToModelHelper();
	ModelToEntityHelper modelToEntityHelper = new ModelToEntityHelper();

	@EJB
	private AppLocal appLocal;

	@Override
	public void logout() {
		System.out.println("<<<<<<<<<<<<<<<<<<<<< Logout User: " + getThreadLocalRequest().getUserPrincipal().getName());
		getThreadLocalRequest().getSession().invalidate();
	}

	@Override
	public void logToServer(Throwable th) {
		System.out.println("Error: " + th.getMessage());
		th.printStackTrace();
	}

	@Override
	public void testMethod(String testParam) {
		System.out.println("testMethod: " + testParam);
	}

	@Override
	public List<UserModel> loadUsers() {
		List<Person> persons = appLocal.loadUsers();
		return entityToModelHelper.personsToUserModels(persons);
	}

	@Override
	public UserModel saveUser(UserModel userModel) {
		Person person = modelToEntityHelper.userModelToPerson(userModel);
		return entityToModelHelper.personToUserModel(appLocal.saveUser(person));
	}

	@Override
	public void deleteUser(UserModel userModel) {
		Person person = new Person();
		person.setId(userModel.getId());
		appLocal.deleteUser(person);
	}

	@Override
	public UserModel loadCurrentUser() {
		String login = getThreadLocalRequest().getUserPrincipal().getName();

		// FIXME this if will be while development
		if(login.equals("sa")) {
			UserModel model = new UserModel();
			model.setFirstName("ვაკო");
			model.setLastName("ბერიძე");
			model.setEmail("sys.admin@mail.com");
			return model;
		}

		return entityToModelHelper.personToUserModel(appLocal.getUserByUserName(login));
	}
}
