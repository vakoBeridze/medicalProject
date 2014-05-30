package ge.tsu.client.presenter;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.widget.core.client.box.AlertMessageBox;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.info.Info;
import ge.tsu.client.App;
import ge.tsu.client.view.EditUserView;
import ge.tsu.shared.UserModel;

/**
 * Created by vako on 29/05/14.
 */
public class UserManagerPresenter implements Presenter {

    Display display;

    public UserManagerPresenter(Display display) {
        this.display = display;
    }


    public interface Display {
        Widget asWidget();

        SelectEvent.HasSelectHandlers getAddButton();

        SelectEvent.HasSelectHandlers getEditButton();

        SelectEvent.HasSelectHandlers getDeleteButton();

        UserModel getSelectedUser();

        void delete(UserModel selectedUser);
    }


    private void bind() {
        Info.display("BINDING", "bindng UserManager");

        display.getAddButton().addSelectHandler(new SelectEvent.SelectHandler() {
            @Override
            public void onSelect(SelectEvent selectEvent) {
                EditUserPresenter presenter = new EditUserPresenter(new EditUserView());
                presenter.getDisplay().asWidget();
                presenter.go();
            }
        });

        display.getEditButton().addSelectHandler(new SelectEvent.SelectHandler() {
            @Override
            public void onSelect(SelectEvent selectEvent) {
                EditUserPresenter presenter = new EditUserPresenter(new EditUserView());
                presenter.getDisplay().asWidget();
                presenter.getDisplay().setModel(display.getSelectedUser());
                presenter.go();
            }
        });

        display.getDeleteButton().addSelectHandler(new SelectEvent.SelectHandler() {
            @Override
            public void onSelect(SelectEvent event) {
                doDelete(display.getSelectedUser());
            }
        });
    }

    private void doDelete(UserModel selectedUser) {
        // TODO delete in db

        display.delete(selectedUser);
    }

    @Override
    public void go() {
        bind();
    }

    public Display getDisplay() {
        return display;
    }

}
