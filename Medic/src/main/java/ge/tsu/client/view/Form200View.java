package ge.tsu.client.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.HasValueChangeHandlers;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.cell.core.client.form.ComboBoxCell;
import com.sencha.gxt.core.client.dom.ScrollSupport;
import com.sencha.gxt.core.client.util.Margins;
import com.sencha.gxt.core.client.util.ToggleGroup;
import com.sencha.gxt.data.shared.LabelProvider;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.widget.core.client.Dialog;
import com.sencha.gxt.widget.core.client.FramedPanel;
import com.sencha.gxt.widget.core.client.box.ConfirmMessageBox;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.AbstractHtmlLayoutContainer.HtmlData;
import com.sencha.gxt.widget.core.client.container.HtmlLayoutContainer;
import com.sencha.gxt.widget.core.client.container.MarginData;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.event.DialogHideEvent;
import com.sencha.gxt.widget.core.client.event.FocusEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.form.*;
import ge.tsu.client.App;
import ge.tsu.client.images.Images;
import ge.tsu.client.presenter.Form200Presenter;
import ge.tsu.shared.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by vako on 29/05/14.
 */
public class Form200View implements Form200Presenter.Display {


    private static final int FORM_WIDTH = (int) (Window.getClientWidth() * 0.7);

    private FramedPanel panel;

    private TextField firstName;
    private TextField lastName;
    private TextField bloodGroup;
    private Radio maleRadio;
    private Radio femaleRadio;
    private TextField rhFactory;
    private TextField phoneNumber;
    private DateField birthDate;
    private TextField pn;
    private TextArea professionAndJob;
    private TextArea bloodTransfusion;
    private TextArea allergy;
    private TextArea surgery;
    private TextArea chronicDiseases;
    private TextArea infectionDiseases;
    private TextField policeNumber;
    private ComboBox<InsuranceCompanyModel> insuranceCompany;
    private ListStore<UserModel> usersStore;
    private TextButton saveButton;
    private TextButton yesSaveButton;
    private ComboBox<UserModel> usersCombo;
    private VerticalLayoutContainer vlc;

    @Override
    public Widget asWidget() {
        panel = new FramedPanel();
        panel.setHeaderVisible(false);
        panel.setBorders(false);
//        panel.setBodyStyle("background: none;");

        createForm();
        createButtons();

        panel.addButton(saveButton);

        return panel;
    }

    private void createButtons() {

        saveButton = new TextButton(App.messages.save(), Images.INSTANCE.save());

        yesSaveButton = new TextButton();

        saveButton.addSelectHandler(new SelectEvent.SelectHandler() {
            @Override
            public void onSelect(final SelectEvent event) {
                ConfirmMessageBox confirmMessageBox = new ConfirmMessageBox(App.messages.confirm(), App.messages.sureSave() + " " + App.messages.formMenu() + " 200 ?");
                confirmMessageBox.getButton(Dialog.PredefinedButton.YES).setText(App.messages.yes());
                confirmMessageBox.getButton(Dialog.PredefinedButton.NO).setText(App.messages.no());
                confirmMessageBox.addDialogHideHandler(new DialogHideEvent.DialogHideHandler() {
                    @Override
                    public void onDialogHide(DialogHideEvent dialogHideEvent) {
                        if (dialogHideEvent.getHideButton() == Dialog.PredefinedButton.YES)
                            yesSaveButton.fireEvent(event);
                    }
                });
                confirmMessageBox.show();
            }
        });
    }

    @Override
    public void setComboData(List<UserModel> userModels) {
        usersStore.addAll(userModels);
        usersCombo.focus();
    }

    @Override
    public void setInsuranceCompaniesData(List<InsuranceCompanyModel> insuranceCompanyModels) {
        insuranceCompany.getStore().addAll(insuranceCompanyModels);
    }

    @Override
    public SelectEvent.HasSelectHandlers getSaveButton() {
        return yesSaveButton;
    }

    @Override
    public FocusEvent.HasFocusHandlers getBloodTransfusion() {
        return bloodTransfusion;
    }

    @Override
    public FocusEvent.HasFocusHandlers getAllergy() {
        return allergy;
    }

    @Override
    public FocusEvent.HasFocusHandlers getSurgery() {
        return surgery;
    }

    @Override
    public FocusEvent.HasFocusHandlers getInfectionDiseases() {
        return infectionDiseases;
    }

    @Override
    public FocusEvent.HasFocusHandlers getChronicDiseases() {
        return chronicDiseases;
    }

    @Override
    public void setBloodTransfusion(Date transfusionDate, String bloodVolume, String comment) {
        String dateString = DateTimeFormat.getFormat("dd.MM.yyyy").format(transfusionDate);
        String transfusionString = App.messages.transfusionDate() + ": " + dateString + "; " +
                App.messages.bloodVolume() + " " + bloodVolume + "; \n" + App.messages.comment() + ": " + comment;
        bloodTransfusion.setValue(transfusionString);

        bloodTransfusion.setData("transfusionDate", transfusionDate);
        bloodTransfusion.setData("bloodVolume", bloodVolume);
        bloodTransfusion.setData("comment", comment);
    }

    @Override
    public void clearBloodTransfusion() {
        bloodTransfusion.setValue(null);
        bloodTransfusion.setData("transfusionDate", null);
        bloodTransfusion.setData("bloodVolume", null);
        bloodTransfusion.setData("comment", null);
    }

    @Override
    public Date getBloodTransfusionDate() {
        return bloodTransfusion.getData("transfusionDate");
    }

    @Override
    public String getBloodTransfusionVolume() {
        return bloodTransfusion.getData("bloodVolume");
    }

    @Override
    public String getBloodTransfusionComment() {
        return bloodTransfusion.getData("comment");
    }

    @Override
    public BloodTransfusionModel getBloodTransfusionModel() {
        if (bloodTransfusion.getValue() != null) {
            BloodTransfusionModel model = new BloodTransfusionModel();
            model.setTransfusionDate(getBloodTransfusionDate());
            model.setBloodVolume(Integer.valueOf(getBloodTransfusionVolume()));
            model.setComment(getBloodTransfusionComment());
            model.setCustomerModel(usersCombo.getValue());
            // TODO change issuer
            model.setIssuerModel(usersCombo.getValue());
            return model;
        }
        return null;
    }

    @Override
    public void clearForm() {
        fillOrClearDetails(null);
    }

    @Override
    @SuppressWarnings("unchecked")
    public void addAllergy(boolean clear, CustomerAllergyModel model) {
        if (clear) {
            allergy.setValue(null);
            allergy.setData("data", null);
        } else {
            ArrayList<CustomerAllergyModel> customerAllergyModels = new ArrayList<CustomerAllergyModel>();
            Object data = allergy.getData("data");
            model.setUserModel(usersCombo.getValue());
            if (data == null) {
                customerAllergyModels.add(model);
                allergy.setData("data", customerAllergyModels);
            } else {
                customerAllergyModels = (ArrayList<CustomerAllergyModel>) data;
                if (!customerAllergyModels.contains(model)) {
                    customerAllergyModels.add(model);
                    allergy.setData("data", customerAllergyModels);
                }
            }
            // update field value
            updateAllergyValue(customerAllergyModels);
        }
    }

    private void updateAllergyValue(List<CustomerAllergyModel> customerAllergyModels) {
        String value = " ";
        for (CustomerAllergyModel customerAllergyModel : customerAllergyModels) {
            value = value.concat(customerAllergyModel.getAllergyModel().getName()).concat(", ");
        }
        allergy.setValue(value.substring(0, value.length() - 2));
    }

    @Override
    public void setSavingMask(boolean mask) {
        if (mask) {
            panel.mask(App.messages.saving());
        } else {
            panel.unmask();
        }
    }

    @Override
    public void setUsersLoadMask(boolean mask) {
        if (mask) {
            usersCombo.mask(App.messages.loading());
        } else {
            usersCombo.unmask();
        }
    }

    @Override
    public void setCompaniesLoadMask(boolean mask) {
        if (mask) {
            insuranceCompany.mask(App.messages.loading());
        } else {
            insuranceCompany.unmask();
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<CustomerAllergyModel> getCustomerAllergyModels() {

        return (ArrayList<CustomerAllergyModel>) allergy.getData("data");
        /*
        if (allergy.getData("data") != null) {
            ArrayList<AllergyModel> allergyModels = (ArrayList<AllergyModel>) allergy.getData("data");
            List<CustomerAllergyModel> customerAllergyModels = new ArrayList<CustomerAllergyModel>();

            for (AllergyModel allergyModel : allergyModels) {
                CustomerAllergyModel customerAllergyModel = new CustomerAllergyModel(allergyModel, usersCombo.getValue());
                customerAllergyModels.add(customerAllergyModel);
            }

            return customerAllergyModels.isEmpty() ? null : customerAllergyModels;
        }
        return null;
        */
    }

    @SuppressWarnings("unchecked")
    @Override
    public void addSurgery(boolean clear, CustomerSurgeryModel model) {
        if (clear) {
            surgery.setValue(null);
            surgery.setData("data", null);
        } else {
            ArrayList<CustomerSurgeryModel> customerSurgeryModels = new ArrayList<CustomerSurgeryModel>();
            Object data = surgery.getData("data");
            if (data == null) {
                model.setCustomerModel(usersCombo.getValue());
                customerSurgeryModels.add(model);
                surgery.setData("data", customerSurgeryModels);
            } else {
                customerSurgeryModels = (ArrayList<CustomerSurgeryModel>) data;
                if (!customerSurgeryModels.contains(model)) {
                    model.setCustomerModel(usersCombo.getValue());
                    customerSurgeryModels.add(model);
                    surgery.setData("data", customerSurgeryModels);
                }
            }
            // update field value
            updateSurgeryValue(customerSurgeryModels);
        }
    }

    private void updateSurgeryValue(List<CustomerSurgeryModel> customerSurgeryModels) {
        String value = "  ";
        for (CustomerSurgeryModel customerSurgeryModel : customerSurgeryModels) {
            value = value.concat(customerSurgeryModel.getSurgeryModel().getSurgeryName() + " - " + customerSurgeryModel.getComment()).concat(", ");
        }
        surgery.setValue(value.substring(0, value.length() - 2));
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<CustomerSurgeryModel> getCustomerSurgeryModels() {
        return (ArrayList<CustomerSurgeryModel>) surgery.getData("data");
    }

    @SuppressWarnings("unchecked")
    @Override
    public void addInfectionDisease(boolean clear, CustomerDiseaseModel model) {
        if (clear) {
            infectionDiseases.setValue(null);
            infectionDiseases.setData("data", null);
        } else {
            ArrayList<CustomerDiseaseModel> customerDiseaseModels = new ArrayList<CustomerDiseaseModel>();
            Object data = infectionDiseases.getData("data");
            if (data == null) {
                model.setCustomerModel(usersCombo.getValue());
                customerDiseaseModels.add(model);
                infectionDiseases.setData("data", customerDiseaseModels);
            } else {
                customerDiseaseModels = (ArrayList<CustomerDiseaseModel>) data;
                if (!customerDiseaseModels.contains(model)) {
                    model.setCustomerModel(usersCombo.getValue());
                    customerDiseaseModels.add(model);
                    infectionDiseases.setData("data", customerDiseaseModels);
                }
            }
            // update field value
            updateInfectionDiseases(customerDiseaseModels);
        }
    }

    private void updateInfectionDiseases(List<CustomerDiseaseModel> customerDiseaseModels) {
        String value = "  ";
        for (CustomerDiseaseModel customerDiseasesModel : customerDiseaseModels) {
            value = value.concat(customerDiseasesModel.getDiseaseModel().getName()).concat(", ");
        }
        infectionDiseases.setValue(value.substring(0, value.length() - 2));
    }

    @SuppressWarnings("unchecked")
    @Override
    public void addChronicDisease(boolean clear, CustomerDiseaseModel model) {
        if (clear) {
            chronicDiseases.setValue(null);
            chronicDiseases.setData("data", null);
        } else {
            ArrayList<CustomerDiseaseModel> customerDiseaseModels = new ArrayList<CustomerDiseaseModel>();
            Object data = chronicDiseases.getData("data");
            if (data == null) {
                model.setCustomerModel(usersCombo.getValue());
                customerDiseaseModels.add(model);
                chronicDiseases.setData("data", customerDiseaseModels);
            } else {
                customerDiseaseModels = (ArrayList<CustomerDiseaseModel>) data;
                if (!customerDiseaseModels.contains(model)) {
                    model.setCustomerModel(usersCombo.getValue());
                    customerDiseaseModels.add(model);
                    chronicDiseases.setData("data", customerDiseaseModels);
                }
            }
            // update field value
            updateChronicDisease(customerDiseaseModels);
        }
    }

    private void updateChronicDisease(List<CustomerDiseaseModel> customerDiseaseModels) {
        String value = "  ";
        for (CustomerDiseaseModel customerDiseasesModel : customerDiseaseModels) {
            value = value.concat(customerDiseasesModel.getDiseaseModel().getName()).concat(", ");
        }
        chronicDiseases.setValue(value.substring(0, value.length() - 2));
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<CustomerDiseaseModel> getCustomerDiseases() {
        ArrayList<CustomerDiseaseModel> infDiseases = (ArrayList<CustomerDiseaseModel>) infectionDiseases.getData("data");
        ArrayList<CustomerDiseaseModel> chrDiseases = (ArrayList<CustomerDiseaseModel>) chronicDiseases.getData("data");

        List<CustomerDiseaseModel> diseases = new ArrayList<CustomerDiseaseModel>();
        if (infDiseases != null) {
            diseases.addAll(infDiseases);
        }
        if (chrDiseases != null) {
            diseases.addAll(chrDiseases);
        }
        return diseases.isEmpty() ? null : diseases;
    }

    @Override
    public PoliceModel getPoliceModel() {
        insuranceCompany.finishEditing();
        policeNumber.finishEditing();

        if (insuranceCompany.getValue() != null && (policeNumber.getValue() != null && !policeNumber.getValue().trim().equals(""))) {
            PoliceModel model = new PoliceModel();
            model.setPoliceNumber(policeNumber.getValue());
            model.setCustomerModel(usersCombo.getValue());
            model.setInsuranceCompanyModel(insuranceCompany.getValue());
            return model;
        }
        return null;
    }

    @Override
    public HasValueChangeHandlers<UserModel> getComboBoxHandler() {
        return usersCombo;
    }

    @Override
    public void fillOrClearDetails(UserModel userModel) {
        if (userModel != null) {
            firstName.setValue(userModel.getFirstName());
            lastName.setValue(userModel.getLastName());
            maleRadio.setValue(userModel.getGender() == 1);
            femaleRadio.setValue(userModel.getGender() != 1);
            phoneNumber.setValue(userModel.getPhoneNumber());
            birthDate.setValue(userModel.getBirthDate());
            pn.setValue(userModel.getPn());
            professionAndJob.setValue(userModel.getProfessionAndJob());
            bloodGroup.setValue(userModel.getBloodGroup() == null ? null : userModel.getBloodGroup().toString());
            rhFactory.setValue(userModel.getRhFactory() == null ? null : userModel.getRhFactory().toString());
        } else {
            firstName.clear();
            lastName.clear();
            maleRadio.setValue(true);
            femaleRadio.setValue(false);
            phoneNumber.clear();
            birthDate.clear();
            pn.clear();
            professionAndJob.clear();
            bloodGroup.clear();
            rhFactory.clear();

            bloodTransfusion.clear();

//            allergy.setData("data", null);
            allergy.clear();
//            surgery.setData("data", null);
            surgery.clear();
//            infectionDiseases.setData("data", null);
            insuranceCompany.clear();
//            chronicDiseases.setData("data", null);
            chronicDiseases.clear();
            insuranceCompany.clear();
            policeNumber.clear();
        }
    }

    @Override
    public void fillFullDetails(UserModel model) {
        /*List<AllergyModel> allergyModels = new ArrayList<AllergyModel>();
        for (CustomerAllergyModel customerAllergyModel : model.getCustomerAllergyModels()) {
            allergyModels.add(customerAllergyModel.getAllergyModel());
        }*/
        allergy.setData("data", model.getCustomerAllergyModels());
        updateAllergyValue(model.getCustomerAllergyModels());

        surgery.setData("data", model.getCustomerSurgeryModels());
        updateSurgeryValue(model.getCustomerSurgeryModels());

        List<CustomerDiseaseModel> infectionCDMs = new ArrayList<CustomerDiseaseModel>();
        List<CustomerDiseaseModel> chronicCDMs = new ArrayList<CustomerDiseaseModel>();
        for (CustomerDiseaseModel cdm : model.getCustomerDiseaseModels()) {
            if (cdm.getDiseaseModel().isInfection()) {
                infectionCDMs.add(cdm);
            }
            if (cdm.getDiseaseModel().isChronic()) {
                chronicCDMs.add(cdm);
            }
        }
        infectionDiseases.setData("data", infectionCDMs);
        updateInfectionDiseases(infectionCDMs);

        chronicDiseases.setData("data", chronicCDMs);
        updateChronicDisease(chronicCDMs);
    }

    @Override
    public boolean checkUserSelectedAndScroll() {
        if (usersCombo.getValue() != null) {
            return true;
        } else {
            usersCombo.validate(true);
            return false;
        }
    }

    @Override
    public UserModel getUserModel() {
        return usersCombo.getValue();
    }

    private void createForm() {

        int cw = (FORM_WIDTH / 2) - 80;
        int rw = FORM_WIDTH - 35;

        VerticalLayoutContainer vlc = new VerticalLayoutContainer();
        vlc.setScrollMode(ScrollSupport.ScrollMode.AUTOY);

        HtmlLayoutContainer con = new HtmlLayoutContainer(getTableMarkup());
        vlc.add(con, new VerticalLayoutContainer.VerticalLayoutData(1, 1));

        panel.add(vlc, new MarginData(new Margins(10, 5, 5, 20)));

//        VerticalLayoutContainer con = new VerticalLayoutContainer();
//        panel.add(con);

        UserModelProperties props = GWT.create(UserModelProperties.class);
        usersStore = new ListStore<UserModel>(props.id());

        usersCombo = new ComboBox<UserModel>(usersStore, new LabelProvider<UserModel>() {
            @Override
            public String getLabel(UserModel model) {
                return model.getFirstName() + " " + model.getLastName() + " / " + model.getPn();
            }
        });
        usersCombo.setAllowBlank(false);
        usersCombo.setForceSelection(true);
        usersCombo.setTriggerAction(ComboBoxCell.TriggerAction.ALL);
        usersCombo.setWidth(rw);
        usersCombo.setEmptyText(App.messages.select() + " " + App.messages.patient());

        con.add(new FieldLabel(usersCombo, App.messages.patient()), new HtmlData(".user"));

        firstName = new TextField();
        firstName.setEnabled(false);
        firstName.setWidth(cw);
        firstName.setToolTip(App.messages.fillsAutomatically());
        con.add(new FieldLabel(firstName, App.messages.firstName()), new HtmlData(".fn"));

        lastName = new TextField();
        lastName.setEnabled(false);
        lastName.setWidth(cw);
        lastName.setToolTip(App.messages.fillsAutomatically());
        con.add(new FieldLabel(lastName, App.messages.lastName()), new HtmlData(".ln"));

        maleRadio = new Radio();
        maleRadio.setEnabled(false);
        maleRadio.setBoxLabel(App.messages.male());
        maleRadio.setToolTip(App.messages.fillsAutomatically());
        femaleRadio = new Radio();
        femaleRadio.setEnabled(false);
        femaleRadio.setBoxLabel(App.messages.female());
        femaleRadio.setToolTip(App.messages.fillsAutomatically());

        HorizontalPanel hp = new HorizontalPanel();
        hp.add(maleRadio);
        hp.add(femaleRadio);
        FieldLabel genderFieldLabel = new FieldLabel(hp, App.messages.gender());
//        genderFieldLabel.setEnabled(false);
        genderFieldLabel.setWidth(cw);
        con.add(genderFieldLabel, new HtmlData(".gender"));
        ToggleGroup toggle = new ToggleGroup();
        toggle.add(maleRadio);
        toggle.add(femaleRadio);

        birthDate = new DateField();
        birthDate.setEnabled(false);
        birthDate.setWidth(cw);
        birthDate.setToolTip(App.messages.fillsAutomatically());
        con.add(new FieldLabel(birthDate, App.messages.birthday()), new HtmlData(".birthday"));

        pn = new TextField();
        pn.setEnabled(false);
        pn.setWidth(cw);
        pn.setToolTip(App.messages.fillsAutomatically());
        con.add(new FieldLabel(pn, App.messages.pn()), new HtmlData(".pn"));

        phoneNumber = new TextField();
        phoneNumber.setEnabled(false);
        phoneNumber.setWidth(cw);
        phoneNumber.setToolTip(App.messages.fillsAutomatically());
        con.add(new FieldLabel(phoneNumber, App.messages.phoneNumber()), new HtmlData(".phone"));

        professionAndJob = new TextArea();
        professionAndJob.setEnabled(false);
        professionAndJob.setWidth(rw);
        professionAndJob.setToolTip(App.messages.fillsAutomatically());
        con.add(new FieldLabel(professionAndJob, App.messages.professionAndJob()), new HtmlData(".job"));

        bloodGroup = new TextField();
        bloodGroup.setEnabled(false);
        bloodGroup.setWidth(cw);
        bloodGroup.setToolTip(App.messages.fillsAutomatically());
        con.add(new FieldLabel(bloodGroup, App.messages.bloodGroup()), new HtmlData(".blood"));

        rhFactory = new TextField();
        rhFactory.setEnabled(false);
        rhFactory.setWidth(cw);
        rhFactory.setToolTip(App.messages.fillsAutomatically());
        con.add(new FieldLabel(rhFactory, App.messages.rhFactory()), new HtmlData(".rh"));

        bloodTransfusion = new TextArea();
        bloodTransfusion.setEmptyText(App.messages.when() + " " + App.messages.and() + " " + App.messages.howMany());
        bloodTransfusion.setWidth(rw);
        con.add(new FieldLabel(bloodTransfusion, App.messages.bloodTransfusion()), new HtmlData(".transfusion"));

        allergy = new TextArea();
        allergy.setWidth(rw);
        allergy.setEmptyText(App.messages.medicaments() + " " + App.messages.food() + " " + App.messages.and() + " " + App.messages.other() + ". " + App.messages.reactionType());
        con.add(new FieldLabel(allergy, App.messages.allergy()), new HtmlData(".allergy"));

        surgery = new TextArea();
        surgery.setWidth(rw);
        con.add(new FieldLabel(surgery, App.messages.surgery()), new HtmlData(".surgery"));

        infectionDiseases = new TextArea();
        infectionDiseases.setWidth(rw);
        con.add(new FieldLabel(infectionDiseases, App.messages.infectionDiseases()), new HtmlData(".infection"));

        chronicDiseases = new TextArea();
        chronicDiseases.setWidth(rw);
        con.add(new FieldLabel(chronicDiseases, App.messages.chronicDiseases()), new HtmlData(".chronic"));

        policeNumber = new TextField();
        policeNumber.setWidth(cw);
        con.add(new FieldLabel(policeNumber, App.messages.policeNumber()), new HtmlData(".police"));

        InsuranceCompanyProperties insProps = GWT.create(InsuranceCompanyProperties.class);
        ListStore<InsuranceCompanyModel> companyStore = new ListStore<InsuranceCompanyModel>(insProps.id());
        insuranceCompany = new ComboBox<InsuranceCompanyModel>(companyStore, new LabelProvider<InsuranceCompanyModel>() {
            @Override
            public String getLabel(InsuranceCompanyModel insuranceCompany) {
                return insuranceCompany.getName();
            }
        });
        insuranceCompany.setWidth(cw);
        con.add(new FieldLabel(insuranceCompany, App.messages.insuranceCompany()), new HtmlData(".insurance"));

        // need to call after everything is constructed
        List<FieldLabel> labels = FormPanelHelper.getFieldLabels(panel);
        for (FieldLabel lbl : labels) {
            lbl.setLabelAlign(FormPanel.LabelAlign.TOP);
        }
    }

    private native String getTableMarkup() /*-{
        return [ '<table width=100% cellpadding=0 cellspacing=0>',
            '<tr><td class=user colspan=2></td></tr>',
            '<tr><td class=fn width=50%></td><td class=ln width=50%></td></tr>',
            '<tr><td class=gender></td><td class=phone></td></tr>',
            '<tr><td class=birthday></td><td class=pn></td></tr>',
            '<tr><td class=job colspan=2></td></tr>',
            '<tr><td class=blood></td><td class=rh></td></tr>',
            '<tr><td class=transfusion colspan=2></td></tr>',
            '<tr><td class=allergy colspan=2></td></tr>',
            '<tr><td class=surgery colspan=2></td></tr>',
            '<tr><td class=infection colspan=2></td></tr>',
            '<tr><td class=chronic colspan=2></td></tr>',
            '<tr><td class=police></td><td class=insurance></td></tr>', '</table>'

        ].join("");
    }-*/;
}
