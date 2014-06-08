package ge.tsu.shared;

import com.google.gwt.editor.client.Editor;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;

public interface InsuranceCompanyProperties extends PropertyAccess<InsuranceCompanyModel> {

    ModelKeyProvider<InsuranceCompanyModel> id();

    @Editor.Path("name")
    ValueProvider<InsuranceCompanyModel, String> name();

}
