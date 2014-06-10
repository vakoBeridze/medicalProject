package ge.tsu.client.presenter;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.widget.core.client.event.FocusEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.info.Info;
import ge.tsu.client.App;
import ge.tsu.client.chooser.AllergyChooser;
import ge.tsu.client.chooser.BloodTransfusionChooser;
import ge.tsu.client.service.AppService;
import ge.tsu.shared.AllergyModel;
import ge.tsu.shared.BloodTransfusionModel;
import ge.tsu.shared.CustomerAllergyModel;
import ge.tsu.shared.UserModel;

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

        display.getBloodTransfusion().addFocusHandler(new FocusEvent.FocusHandler() {
            @Override
            public void onFocus(FocusEvent focusEvent) {
                BloodTransfusionChooser chooser = new BloodTransfusionChooser(display);
                chooser.setData(display.getBloodTransfusionDate(), display.getBloodTransfusionVolume(), display.getBloodTransfusionComment());
            }
        });

        display.getAllergy().addFocusHandler(new FocusEvent.FocusHandler() {
            @Override
            public void onFocus(FocusEvent focusEvent) {
                AllergyChooser chooser = new AllergyChooser(display);
                chooser.loadData();
            }
        });
    }

    private void doSave() {
        display.setSavingMask(true);
        AppService.Util.getInstance().saveForm200a(display.getBloodTransfusionModel(), display.getCustomerAllergyModels(), new AsyncCallback<Void>() {
            @Override
            public void onFailure(Throwable throwable) {
                App.logError(throwable);
                display.setSavingMask(false);
            }

            @Override
            public void onSuccess(Void aVoid) {
                Info.display("Success", "blood transfusion saved");
                display.setSavingMask(false);
                display.clearForm();
            }
        });
    }

    private void initData() {
        display.setLoadMask(true);
        AppService.Util.getInstance().loadUsers(new AsyncCallback<List<UserModel>>() {
            @Override
            public void onFailure(Throwable throwable) {
                display.setLoadMask(false);
                App.logError(throwable);
            }

            @Override
            public void onSuccess(List<UserModel> userModels) {
                display.setLoadMask(false);
                display.setComboData(userModels);
            }
        });
    }

    public interface Display {
        public Widget asWidget();

        void setComboData(List<UserModel> userModels);

        SelectEvent.HasSelectHandlers getSaveButton();

        FocusEvent.HasFocusHandlers getBloodTransfusion();

        FocusEvent.HasFocusHandlers getAllergy();

        void setBloodTransfusion(Date transfusionDate, String bloodVolume, String comment);

        Date getBloodTransfusionDate();

        String getBloodTransfusionVolume();

        String getBloodTransfusionComment();

        BloodTransfusionModel getBloodTransfusionModel();

        void clearForm();

        void addAllergy(boolean clear, AllergyModel model);

        void setSavingMask(boolean mask);

        void setLoadMask(boolean mask);

        List<CustomerAllergyModel> getCustomerAllergyModels();
    }
}
