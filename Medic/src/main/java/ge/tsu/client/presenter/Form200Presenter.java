package ge.tsu.client.presenter;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.info.Info;
import ge.tsu.client.App;
import ge.tsu.client.service.AppService;
import ge.tsu.shared.UserModel;

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
    }

    private void doSave() {
        // TODO
        Info.display("TODO", "SAVE");
    }

    private void initData() {
        AppService.Util.getInstance().loadUsers(new AsyncCallback<List<UserModel>>() {
            @Override
            public void onFailure(Throwable throwable) {
                App.logError(throwable);
            }

            @Override
            public void onSuccess(List<UserModel> userModels) {
                display.setComboData(userModels);
            }
        });
    }

    public interface Display {
        public Widget asWidget();

        void setComboData(List<UserModel> userModels);

        SelectEvent.HasSelectHandlers getSaveButton();
    }
}
