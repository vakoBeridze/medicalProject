package ge.tsu.client.presenter;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Widget;
import ge.tsu.client.App;
import ge.tsu.client.service.AppService;
import ge.tsu.shared.UserModel;

import java.util.List;

/**
 * Created by vako on 19/06/14.
 */
public class Form200_7Presenter implements Presenter {

    private Display display;

    public Form200_7Presenter(Display display) {
        this.display = display;
    }

    public Display getDisplay() {
        return display;
    }

    public interface Display {
        public Widget asWidget();

        void setUsersLoadMask(boolean mask);

        void setComboData(List<UserModel> userModels);
    }


    @Override
    public void go() {
        bind();
        initData();
    }


    private void bind() {
        // TODO
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
    }
}
