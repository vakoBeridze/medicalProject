package ge.tsu.shared;

import com.google.gwt.editor.client.Editor;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;

import java.util.Date;

/**
 * Created by vako on 29/05/14.
 */
public interface UserModelProperties extends PropertyAccess<UserModel> {


	ModelKeyProvider<UserModel> id();

	@Editor.Path("email")
	ValueProvider<UserModel, String> userName();

	@Editor.Path("firstName")
	ValueProvider<UserModel, String> firstName();

	@Editor.Path("lastName")
	ValueProvider<UserModel, String> lastName();

	@Editor.Path("email")
	ValueProvider<UserModel, String> emailAddress();

	@Editor.Path("admin")
	ValueProvider<UserModel, Boolean> admin();

	ValueProvider<UserModel, String> pn();

	ValueProvider<UserModel, Date> birthDate();

	ValueProvider<UserModel, String> phoneNumber();

	ValueProvider<UserModel, Integer> bloodGroup();
}
