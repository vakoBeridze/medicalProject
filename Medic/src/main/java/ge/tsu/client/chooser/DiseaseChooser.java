package ge.tsu.client.chooser;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.sencha.gxt.cell.core.client.form.ComboBoxCell;
import com.sencha.gxt.core.client.util.ToggleGroup;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.form.*;
import com.sencha.gxt.widget.core.client.info.Info;
import ge.tsu.client.App;
import ge.tsu.client.presenter.Form200Presenter;
import ge.tsu.client.service.AppService;
import ge.tsu.shared.CustomerDiseaseModel;
import ge.tsu.shared.DiseaseModel;
import ge.tsu.shared.DiseaseModelProperties;

import java.util.List;

/**
 * Created by Vako on 08.06.2014.
 */
public class DiseaseChooser extends Chooser {

    private boolean chronicDisease;
    Form200Presenter.Display display;
    private ComboBox<DiseaseModel> comboBox;

    private Integer diseaseStrength;

    private DateField detectionDate;
    private DateField cureDate;
    private DateField justificationDate;

    private Radio r1;
    private Radio r2;
    private Radio r3;
    private Radio r4;
    private CheckBox isGenetic;
    private CheckBox isFinalDiagnose;

    public DiseaseChooser(final Form200Presenter.Display display, boolean chronicDisease) {
        super();
        this.display = display;
        this.chronicDisease = chronicDisease;

        window.setPixelSize(500, 400);
        window.setHeadingText(chronicDisease ? App.messages.chronicDiseases() : App.messages.infectionDiseases());
        createForm();
        window.show();

        chooseButton.addSelectHandler(new SelectEvent.SelectHandler() {
            @Override
            public void onSelect(SelectEvent selectEvent) {

                Info.display("DiseaseChooser", "Choose");
                // TODO
                comboBox.finishEditing();
                if (comboBox.getValue() != null) {
                    CustomerDiseaseModel model = new CustomerDiseaseModel();
                    model.setDiseaseModel(comboBox.getValue());
                    model.setCureDate(cureDate.getValue());
                    model.setDetectionDate(detectionDate.getValue());
                    model.setJustificationDate(justificationDate.getValue());
                    model.setDiseaseStrength(getDiseaseStrength());
                    model.setIsGenetic(isGenetic.getValue());
                    model.setIsInfection(!DiseaseChooser.this.chronicDisease);
                    model.setIsFinalDiagnose(isFinalDiagnose.getValue());
                    model.setIsPreDiagnose(!isFinalDiagnose.getValue());
                    if (DiseaseChooser.this.chronicDisease) {
                        display.addChronicDisease(false, model);
                    } else {
                        display.addInfectionDisease(false, model);
                    }
                    window.hide();
                }
            }
        });

        clearButton.addSelectHandler(new SelectEvent.SelectHandler() {
            @Override
            public void onSelect(SelectEvent selectEvent) {
                if (DiseaseChooser.this.chronicDisease) {
                    display.addChronicDisease(true, null);
                } else {
                    display.addInfectionDisease(true, null);
                }
                window.hide();
            }
        });
    }

    private Integer getDiseaseStrength() {
        int diseaseStrength = 0;
        if (r1.getValue()) {
            diseaseStrength = 1;
        } else if (r2.getValue()) {
            diseaseStrength = 2;
        } else if (r3.getValue()) {
            diseaseStrength = 3;
        } else if (r4.getValue()) {
            diseaseStrength = 4;
        }
        return diseaseStrength;
    }

    private void createForm() {
        VerticalLayoutContainer.VerticalLayoutData layoutData = new VerticalLayoutContainer.VerticalLayoutData(1, -1);

        DiseaseModelProperties props = GWT.create(DiseaseModelProperties.class);
        ListStore<DiseaseModel> diseaseStore = new ListStore<DiseaseModel>(props.id());

        comboBox = new ComboBox<DiseaseModel>(diseaseStore, props.name());
        comboBox.setAllowBlank(false);
        comboBox.setForceSelection(true);
        comboBox.setTriggerAction(ComboBoxCell.TriggerAction.ALL);
        vlc.add(new FieldLabel(comboBox, App.messages.infectionDiseases()), layoutData);

        detectionDate = new DateField();
        vlc.add(new FieldLabel(detectionDate, App.messages.detectionDate()), layoutData);

        cureDate = new DateField();
        vlc.add(new FieldLabel(cureDate, App.messages.cureDate()), layoutData);

        justificationDate = new DateField();
        vlc.add(new FieldLabel(justificationDate, App.messages.justificationDate()), layoutData);

        r1 = new Radio();
        r1.setBoxLabel(App.messages.light());
        r1.setValue(true);

        r2 = new Radio();
        r2.setBoxLabel(App.messages.chronic());

        r3 = new Radio();
        r3.setBoxLabel(App.messages.acute());

        r4 = new Radio();
        r4.setBoxLabel(App.messages.subAcute());

        HorizontalPanel diseaseStrengthHP = new HorizontalPanel();
        diseaseStrengthHP.add(r1);
        diseaseStrengthHP.add(r2);
        diseaseStrengthHP.add(r3);
        diseaseStrengthHP.add(r4);

        vlc.add(new FieldLabel(diseaseStrengthHP, App.messages.diseaseStrength()));
        // we can set name on radios or use toggle group
        ToggleGroup bloodToggle = new ToggleGroup();
        bloodToggle.add(r1);
        bloodToggle.add(r2);
        bloodToggle.add(r3);
        bloodToggle.add(r4);

        isFinalDiagnose = new CheckBox();
        isFinalDiagnose.setBoxLabel("");
        vlc.add(new FieldLabel(isFinalDiagnose, App.messages.finalDiagnose()));

        isGenetic = new CheckBox();
        isGenetic.setBoxLabel("");
        vlc.add(new FieldLabel(isGenetic, App.messages.genetic()));
    }

    public void loadData() {
        comboBox.setEnabled(false);
        comboBox.setEmptyText(App.messages.loading() + "...");
        AppService.Util.getInstance().loadDiseases(this.chronicDisease, new AsyncCallback<List<DiseaseModel>>() {
            @Override
            public void onFailure(Throwable throwable) {
                App.logError(throwable);
                comboBox.setEnabled(true);
                comboBox.setEmptyText("");
            }

            @Override
            public void onSuccess(List<DiseaseModel> diseaseModels) {
                comboBox.getStore().addAll(diseaseModels);
                comboBox.setEnabled(true);
                comboBox.setEmptyText("");
            }
        });
    }
}
