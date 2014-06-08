package ge.tsu.client.chooser;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.sencha.gxt.cell.core.client.form.ComboBoxCell;
import com.sencha.gxt.data.shared.LabelProvider;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.form.ComboBox;
import com.sencha.gxt.widget.core.client.form.FieldLabel;
import com.sencha.gxt.widget.core.client.info.Info;
import ge.tsu.client.App;
import ge.tsu.client.presenter.Form200Presenter;
import ge.tsu.client.service.AppService;
import ge.tsu.shared.AllergyModel;
import ge.tsu.shared.AllergyModelProperties;

import java.util.List;

/**
 * Created by Vako on 08.06.2014.
 */
public class AllergyChooser extends Chooser {

    Form200Presenter.Display display;
    private ComboBox<AllergyModel> comboBox;

    public AllergyChooser(final Form200Presenter.Display display) {
        super();
        this.display = display;

        window.setHeadingText(App.messages.allergy());
        window.setHeight(140);
        createForm();
        window.show();

        chooseButton.addSelectHandler(new SelectEvent.SelectHandler() {
            @Override
            public void onSelect(SelectEvent selectEvent) {

                Info.display("AllergyChooser", "Choose");
                // TODO
                comboBox.finishEditing();
                if (comboBox.getValue() != null) {
                    display.addAllergy(false, comboBox.getValue());
                    window.hide();
                }
            }
        });

        clearButton.addSelectHandler(new SelectEvent.SelectHandler() {
            @Override
            public void onSelect(SelectEvent selectEvent) {
                display.addAllergy(true, null);
                window.hide();
            }
        });
    }

    private void createForm() {
        VerticalLayoutContainer.VerticalLayoutData layoutData = new VerticalLayoutContainer.VerticalLayoutData(1, -1);

        AllergyModelProperties props = GWT.create(AllergyModelProperties.class);
        ListStore<AllergyModel> usersStore = new ListStore<AllergyModel>(props.id());

        comboBox = new ComboBox<AllergyModel>(usersStore, new LabelProvider<AllergyModel>() {
            @Override
            public String getLabel(AllergyModel model) {
                return model.getName() + " / " + model.getComment();
            }
        });
        comboBox.setAllowBlank(true);
        comboBox.setForceSelection(true);
        comboBox.setTriggerAction(ComboBoxCell.TriggerAction.ALL);
        vlc.add(new FieldLabel(comboBox, App.messages.allergy()), layoutData);
    }

    public void loadData() {
        comboBox.setEnabled(false);
        comboBox.setEmptyText(App.messages.loading() + "...");
        AppService.Util.getInstance().loadAllergies(new AsyncCallback<List<AllergyModel>>() {
            @Override
            public void onFailure(Throwable throwable) {
                App.logError(throwable);
                comboBox.setEnabled(true);
                comboBox.setEmptyText("");
            }

            @Override
            public void onSuccess(List<AllergyModel> allergyModels) {
                comboBox.getStore().addAll(allergyModels);
                comboBox.setEnabled(true);
                comboBox.setEmptyText("");
            }
        });
    }
}
