package ge.tsu.client.presenter;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.sencha.gxt.widget.core.client.box.AlertMessageBox;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.HasSelectHandlers;
import com.sencha.gxt.widget.core.client.info.Info;
import ge.tsu.client.App;
import ge.tsu.client.service.AppService;
import ge.tsu.shared.MedicException;
import ge.tsu.shared.UserModel;

/**
 * Created by vako on 30/05/14.
 */
public class EditUserPresenter implements Presenter {

    private UserManagerPresenter.Display userManagerDisplay;
    private Display display;

    public EditUserPresenter(Display display, UserManagerPresenter.Display userManagerDisplay) {
        this.display = display;
        this.userManagerDisplay = userManagerDisplay;
    }

    public Display getDisplay() {
        return display;
    }

    private void bind() {
        display.getSaveButton().addSelectHandler(new SelectEvent.SelectHandler() {
            @Override
            public void onSelect(SelectEvent selectEvent) {
                doSave(display.getModel());
            }
        });
    }

    private void doSave(final UserModel userModel) {
        display.setSaveButtonEnabled(false);

        AppService.Util.getInstance().saveUser(userModel, new AsyncCallback<UserModel>() {
            @Override
            public void onFailure(Throwable throwable) {
                display.setSaveButtonEnabled(true);
                if (throwable instanceof MedicException) {
                    display.validateEmail();
                    AlertMessageBox messageBox = new AlertMessageBox("Error", ((MedicException) throwable).getErrorMessage());
                    messageBox.show();
                } else {
                    App.logError(throwable);
                }
            }

            @Override
            public void onSuccess(UserModel savedUserModel) {
                if (userModel.getId() == 0) {
                    userManagerDisplay.add(savedUserModel);
                } else {
                    userManagerDisplay.update(savedUserModel);
                }
                display.close();
                Info.display("Success", "User Saved");
            }
        });
    }

    @Override
    public void go() {
        bind();
    }

    public interface Display {
        public void asWidget();

        HasSelectHandlers getSaveButton();

        void close();

        UserModel getModel();

        void setSaveButtonEnabled(boolean enabled);

        void validateEmail();
    }
}
