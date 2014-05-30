package ge.tsu.client.presenter;

import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.HasSelectHandlers;
import ge.tsu.shared.UserModel;

/**
 * Created by vako on 30/05/14.
 */
public class EditUserPresenter implements Presenter{

    private Display display;

    public interface Display {
        public void asWidget();

        HasSelectHandlers getSaveButton();

        void close();

        void setModel(UserModel selectedUser);
    }


    public EditUserPresenter(Display display) {
        this.display = display;
    }

    public Display getDisplay() {
        return display;
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
        display.close();
    }

    @Override
    public void go() {
        bind();
    }
}
