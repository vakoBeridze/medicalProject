package ge.tsu.client.presenter;

import com.google.gwt.event.logical.shared.HasValueChangeHandlers;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.widget.core.client.event.FocusEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.info.Info;
import ge.tsu.client.App;
import ge.tsu.client.chooser.AllergyChooser;
import ge.tsu.client.chooser.BloodTransfusionChooser;
import ge.tsu.client.chooser.DiseaseChooser;
import ge.tsu.client.chooser.SurgeryChooser;
import ge.tsu.client.service.AppService;
import ge.tsu.shared.*;

import java.util.Date;
import java.util.List;

/**
 * Created by vako on 29/05/14.
 */
public class Form200Presenter implements Presenter {

    private Display display;

    public Form200Presenter(Display display) {
        this.display = display;
    }

    public Display getDisplay() {
        return display;
    }

    @Override
    public void go() {
        bind();
        initData();
    }

    private void bind() {
        display.getSaveButton().addSelectHandler(new SelectEvent.SelectHandler() {
            @Override
            public void onSelect(SelectEvent selectEvent) {
                doSave();
            }
        });

        display.getComboBoxHandler().addValueChangeHandler(new ValueChangeHandler<UserModel>() {
            @Override
            public void onValueChange(ValueChangeEvent<UserModel> userModelValueChangeEvent) {
                UserModel model = userModelValueChangeEvent.getValue();
                display.fillOrClearDetails(model);
                loadDetails(model);
            }
        });

        display.getBloodTransfusion().addFocusHandler(new FocusEvent.FocusHandler() {
            @Override
            public void onFocus(FocusEvent focusEvent) {
                if (userSelected()) {
                    BloodTransfusionChooser chooser = new BloodTransfusionChooser(display);
                    chooser.setData(display.getBloodTransfusionDate(), display.getBloodTransfusionVolume(), display.getBloodTransfusionComment());
                }
            }
        });

        display.getAllergy().addFocusHandler(new FocusEvent.FocusHandler() {
            @Override
            public void onFocus(FocusEvent focusEvent) {
                if (userSelected()) {
                    AllergyChooser chooser = new AllergyChooser(display);
                    chooser.loadData();
                }
            }
        });

        display.getSurgery().addFocusHandler(new FocusEvent.FocusHandler() {
            @Override
            public void onFocus(FocusEvent focusEvent) {
                if (userSelected()) {
                    SurgeryChooser chooser = new SurgeryChooser(display);
                    chooser.loadData();
                }
            }
        });

        display.getInfectionDiseases().addFocusHandler(new FocusEvent.FocusHandler() {
            @Override
            public void onFocus(FocusEvent focusEvent) {
                if (userSelected()) {
                    DiseaseChooser chooser = new DiseaseChooser(display, false);
                    chooser.loadData();
                }
            }
        });

        display.getChronicDiseases().addFocusHandler(new FocusEvent.FocusHandler() {
            @Override
            public void onFocus(FocusEvent focusEvent) {
                if (userSelected()) {
                    DiseaseChooser chooser = new DiseaseChooser(display, true);
                    chooser.loadData();
                }
            }
        });
    }

    private boolean userSelected() {
        return display.checkUserSelectedAndScroll();
    }

    private void loadDetails(UserModel selectedUser) {
        if (selectedUser != null) {
            AppService.Util.getInstance().loadUser(selectedUser, new AsyncCallback<UserModel>() {
                @Override
                public void onFailure(Throwable throwable) {
                    App.logError(throwable);
                }

                @Override
                public void onSuccess(UserModel model) {
                    display.fillFullDetails(model);
                }
            });
        }
    }

    private void doSave() {
        display.setSavingMask(true);
        AppService.Util.getInstance().saveForm200a(display.getUserModel(), display.getBloodTransfusionModel(), display.getCustomerAllergyModels(), display.getCustomerSurgeryModels(), display.getCustomerDiseases(), display.getPoliceModel(), new AsyncCallback<Void>() {
            @Override
            public void onFailure(Throwable throwable) {
                App.logError(throwable);
                display.setSavingMask(false);
            }

            @Override
            public void onSuccess(Void aVoid) {
                Info.display("Success", "saveForm200a saved");
                display.setSavingMask(false);
                display.clearForm();
            }
        });
    }

    private void initData() {
        display.setUsersLoadMask(true);
        AppService.Util.getInstance().loadUsers(new AsyncCallback<List<UserModel>>() {
            @Override
            public void onFailure(Throwable throwable) {
                display.setUsersLoadMask(false);
                App.logError(throwable);
            }

            @Override
            public void onSuccess(List<UserModel> userModels) {
                display.setUsersLoadMask(false);
                display.setComboData(userModels);
            }
        });

        display.setCompaniesLoadMask(true);
        AppService.Util.getInstance().loadInsuranceCompanies(new AsyncCallback<List<InsuranceCompanyModel>>() {
            @Override
            public void onFailure(Throwable throwable) {
                display.setCompaniesLoadMask(false);
                App.logError(throwable);
            }

            @Override
            public void onSuccess(List<InsuranceCompanyModel> insuranceCompanyModels) {
                display.setCompaniesLoadMask(false);
                display.setInsuranceCompaniesData(insuranceCompanyModels);
            }
        });

    }

    public interface Display {
        public Widget asWidget();

        void setComboData(List<UserModel> userModels);

        void setInsuranceCompaniesData(List<InsuranceCompanyModel> insuranceCompanyModels);

        SelectEvent.HasSelectHandlers getSaveButton();

        FocusEvent.HasFocusHandlers getBloodTransfusion();

        FocusEvent.HasFocusHandlers getAllergy();

        FocusEvent.HasFocusHandlers getSurgery();

        FocusEvent.HasFocusHandlers getInfectionDiseases();

        FocusEvent.HasFocusHandlers getChronicDiseases();

        void setBloodTransfusion(Date transfusionDate, String bloodVolume, String comment);

        void clearBloodTransfusion();

        Date getBloodTransfusionDate();

        String getBloodTransfusionVolume();

        String getBloodTransfusionComment();

        BloodTransfusionModel getBloodTransfusionModel();

        void clearForm();

        void addAllergy(boolean clear, CustomerAllergyModel model);

        void setSavingMask(boolean mask);

        void setUsersLoadMask(boolean mask);

        void setCompaniesLoadMask(boolean mask);

        List<CustomerAllergyModel> getCustomerAllergyModels();

        void addSurgery(boolean clear, CustomerSurgeryModel model);

        List<CustomerSurgeryModel> getCustomerSurgeryModels();

        void addInfectionDisease(boolean clear, CustomerDiseaseModel model);

        void addChronicDisease(boolean clear, CustomerDiseaseModel model);

        List<CustomerDiseaseModel> getCustomerDiseases();

        PoliceModel getPoliceModel();

        HasValueChangeHandlers<UserModel> getComboBoxHandler();

        void fillOrClearDetails(UserModel model);

        void fillFullDetails(UserModel model);

        boolean checkUserSelectedAndScroll();

        UserModel getUserModel();
    }
}
