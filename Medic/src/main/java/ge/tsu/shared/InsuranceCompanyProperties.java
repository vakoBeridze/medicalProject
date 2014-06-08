/**
 *
 */
package ge.tsu.shared;

import com.google.gwt.editor.client.Editor;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;

import javax.persistence.Entity;
import javax.persistence.Id;

public interface InsuranceCompanyProperties extends PropertyAccess<InsuranceCompany> {

    ModelKeyProvider<InsuranceCompany> id();

    @Editor.Path("name")
    ValueProvider<InsuranceCompany, String> name();

}
