package ge.tsu.client.presenter;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.sencha.gxt.widget.core.client.box.AlertMessageBox;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import ge.tsu.client.App;
import ge.tsu.client.service.AppService;

/**
 * Created with IntelliJ IDEA.
 * User: Vako
 * Date: 11/27/13
 * Time: 12:34 PM
 */
public class PasswordChangePresenter implements Presenter {

    private Display display;

    public PasswordChangePresenter(Display display) {
        this.display = display;
    }

    private void bind() {
        this.display.getSaveButtonHandler().addSelectHandler(new SelectEvent.SelectHandler() {
            @Override
            public void onSelect(SelectEvent event) {
                if (display.validPassword()) {
                    if (display.passwordsMatch()) {
                        AppService.Util.getInstance().changePassword(App.currentUser.getId(), display.getNewPassword(), new AsyncCallback<Void>() {

                            @Override
                            public void onFailure(Throwable caught) {
                                App.logError(caught);
                            }

                            @Override
                            public void onSuccess(Void result) {
                                display.confirmAndHide();
                            }
                        });
                    } else {
                        new AlertMessageBox("Error", "Passwords not match !").show();
                    }
                } else {
                    new AlertMessageBox("Error", "Password is not valid !").show();
                }
            }
        });
    }

    @Override
    public void go() {
        display.asWidget();
        bind();
    }

    public interface Display {

        void asWidget();

        SelectEvent.HasSelectHandlers getSaveButtonHandler();

        String getNewPassword();

        boolean passwordsMatch();

        void confirmAndHide();

        boolean validPassword();
    }

}
