package ge.tsu.shared;

import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;

public interface MenuModelProperties extends PropertyAccess<MenuModel> {
	ModelKeyProvider<MenuModel> code();

	ValueProvider<MenuModel, String> label();

}
