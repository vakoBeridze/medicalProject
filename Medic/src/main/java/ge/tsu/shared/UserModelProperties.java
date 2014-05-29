package ge.tsu.shared;

import com.google.gwt.editor.client.Editor;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;

/**
 * Created by vako on 29/05/14.
 */
public interface UserModelProperties extends PropertyAccess<UserModel> {


    ModelKeyProvider<UserModel> id();

    @Editor.Path("emailAddress")
    ValueProvider<UserModel, String> userName();

    @Editor.Path("firstName")
    ValueProvider<UserModel, String> firstName();

    @Editor.Path("lastName")
    ValueProvider<UserModel, String> lastName();

    @Editor.Path("emailAddress")
    ValueProvider<UserModel, String> emailAddress();

    @Editor.Path("admin")
    ValueProvider<UserModel, Boolean> admin();
}
