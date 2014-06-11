package ge.tsu.client.chooser;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.sencha.gxt.cell.core.client.form.ComboBoxCell;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.form.ComboBox;
import com.sencha.gxt.widget.core.client.form.DateField;
import com.sencha.gxt.widget.core.client.form.FieldLabel;
import com.sencha.gxt.widget.core.client.form.TextArea;
import com.sencha.gxt.widget.core.client.info.Info;
import ge.tsu.client.App;
import ge.tsu.client.presenter.Form200Presenter;
import ge.tsu.client.service.AppService;
import ge.tsu.shared.CustomerSurgeryModel;
import ge.tsu.shared.SurgeryModel;
import ge.tsu.shared.SurgeryModelProperties;

import java.util.List;

/**
 * Created by Vako on 08.06.2014.
 */
public class SurgeryChooser extends Chooser {

    Form200Presenter.Display display;
    private ComboBox<SurgeryModel> comboBox;

    private DateField beginningDate;
    private DateField endDate;
    private TextArea comment;

    public SurgeryChooser(final Form200Presenter.Display display) {
        super();
        this.display = display;

        window.setHeadingText(App.messages.surgery());
        createForm();
        window.show();

        chooseButton.addSelectHandler(new SelectEvent.SelectHandler() {
            @Override
            public void onSelect(SelectEvent selectEvent) {

                Info.display("SurgeryChooser", "Choose");
                // TODO
                comboBox.finishEditing();
                if (comboBox.getValue() != null) {
                    CustomerSurgeryModel model = new CustomerSurgeryModel();
                    model.setComment(comment.getValue());
                    model.setBeginningDate(beginningDate.getValue());
                    model.setEndDate(endDate.getValue());
                    model.setSurgeryModel(comboBox.getValue());
                    display.addSurgery(false, model);
                    window.hide();
                }
            }
        });

        clearButton.addSelectHandler(new SelectEvent.SelectHandler() {
            @Override
            public void onSelect(SelectEvent selectEvent) {
                display.addSurgery(true, null);
                window.hide();
            }
        });
    }

    private void createForm() {
        VerticalLayoutContainer.VerticalLayoutData layoutData = new VerticalLayoutContainer.VerticalLayoutData(1, -1);

        SurgeryModelProperties props = GWT.create(SurgeryModelProperties.class);
        ListStore<SurgeryModel> surgeryStore = new ListStore<SurgeryModel>(props.id());

        comboBox = new ComboBox<SurgeryModel>(surgeryStore, props.surgeryName());
        comboBox.setAllowBlank(true);
        comboBox.setForceSelection(true);
        comboBox.setTriggerAction(ComboBoxCell.TriggerAction.ALL);
        vlc.add(new FieldLabel(comboBox, App.messages.surgery()), layoutData);

        beginningDate = new DateField();
        vlc.add(new FieldLabel(beginningDate, App.messages.beginningDate()), layoutData);

        endDate = new DateField();
        vlc.add(new FieldLabel(endDate, App.messages.endDate()), layoutData);

        comment = new TextArea();
        vlc.add(new FieldLabel(comment, App.messages.comment()), layoutData);
    }

    public void loadData() {
        comboBox.setEnabled(false);
        comboBox.setEmptyText(App.messages.loading() + "...");
        AppService.Util.getInstance().loadSurgeries(new AsyncCallback<List<SurgeryModel>>() {
            @Override
            public void onFailure(Throwable throwable) {
                App.logError(throwable);
                comboBox.setEnabled(true);
                comboBox.setEmptyText("");
            }

            @Override
            public void onSuccess(List<SurgeryModel> surgeryModels) {
                comboBox.getStore().addAll(surgeryModels);
                comboBox.setEnabled(true);
                comboBox.setEmptyText("");
            }
        });
    }
}
