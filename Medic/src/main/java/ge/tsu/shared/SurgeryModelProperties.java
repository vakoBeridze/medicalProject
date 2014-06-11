package ge.tsu.shared;

import com.google.gwt.editor.client.Editor;
import com.sencha.gxt.data.shared.LabelProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;

/**
 * Created by vako on 11/06/14.
 */
public interface SurgeryModelProperties extends PropertyAccess<SurgeryModel> {
    ModelKeyProvider<SurgeryModel> id();

    @Editor.Path("surgeryName")
    LabelProvider<SurgeryModel> surgeryName();
}
