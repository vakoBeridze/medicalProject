package ge.tsu.client.presenter;

import com.google.gwt.event.logical.shared.HasSelectionHandlers;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.menu.Item;
import com.sencha.gxt.widget.core.client.selection.SelectionChangedEvent;
import ge.tsu.client.App;
import ge.tsu.client.service.AppService;
import ge.tsu.client.view.EditUserView;
import ge.tsu.shared.UserModel;

import java.util.Date;
import java.util.List;

/**
 * Created by vako on 29/05/14.
 */
public class UserManagerPresenter implements Presenter {

    Display display;

    public UserManagerPresenter(Display display) {
        this.display = display;
    }

    private void bind() {

        display.getGridSelectionChangeHandler().addSelectionChangedHandler(new SelectionChangedEvent.SelectionChangedHandler<UserModel>() {
            @Override
            public void onSelectionChanged(SelectionChangedEvent<UserModel> event) {
                UserModel selectedUser = event.getSource().getSelectedItem();
                if (selectedUser != null) {
                    loadUserDetails(selectedUser);
                } else {
                    display.clearDetails();
                }
            }
        });

        display.getAddDoctorButton().addSelectionHandler(new SelectionHandler<Item>() {
            @Override
            public void onSelection(SelectionEvent<Item> itemSelectionEvent) {
                EditUserPresenter presenter = new EditUserPresenter(new EditUserView(createEmptyUser(true)), display);
                presenter.getDisplay().asWidget();
                presenter.go();
            }
        });

        display.getAddPatientButton().addSelectionHandler(new SelectionHandler<Item>() {
            @Override
            public void onSelection(SelectionEvent<Item> itemSelectionEvent) {
                EditUserPresenter presenter = new EditUserPresenter(new EditUserView(createEmptyUser(false)), display);
                presenter.getDisplay().asWidget();
                presenter.go();
            }
        });

        display.getEditButton().addSelectHandler(new SelectEvent.SelectHandler() {
            @Override
            public void onSelect(SelectEvent selectEvent) {
                UserModel selectedUser = display.getSelectedUser();
                if (selectedUser != null) {
                    EditUserPresenter presenter = new EditUserPresenter(new EditUserView(selectedUser), display);
                    presenter.getDisplay().asWidget();
                    presenter.go();
                }
            }
        });

        display.getDeleteButton().addSelectHandler(new SelectEvent.SelectHandler() {
            @Override
            public void onSelect(SelectEvent event) {
                UserModel modelToDelete = display.getSelectedUser();
                if (modelToDelete != null) {
                    doDelete(modelToDelete);
                }
            }
        });

        display.getFilterButton().addSelectHandler(new SelectEvent.SelectHandler() {
            @Override
            public void onSelect(SelectEvent selectEvent) {
                display.filter();
            }
        });
    }

    private void loadUserDetails(UserModel userModel) {
        AppService.Util.getInstance().loadUser(userModel, new AsyncCallback<UserModel>() {
            @Override
            public void onFailure(Throwable throwable) {
                App.logError(throwable);
            }

            @Override
            public void onSuccess(UserModel userModel) {
                display.showDetailsInfo(userModel);
            }
        });
    }

    private UserModel createEmptyUser(boolean doctor) {
        UserModel model = new UserModel();
        model.setId(0);
        model.setFirstName("");
        model.setLastName("");
        model.setFatherName("");
        model.setGender(1);
        model.setDoctor(doctor);
        model.setEmail("");
        model.setPassword("password");
        model.setLicense("");
        model.setPn("");
        model.setPhoneNumber("");
        model.setBirthDate(new Date());
        model.setProfessionAndJob("");
        model.setBloodGroup(1);
        return model;
    }

    private void doDelete(final UserModel selectedUser) {
        AppService.Util.getInstance().deleteUser(selectedUser, new AsyncCallback<Void>() {
            @Override
            public void onFailure(Throwable throwable) {
                App.logError(throwable);
            }

            @Override
            public void onSuccess(Void aVoid) {
                display.delete(selectedUser);
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
                display.setData(userModels);
            }
        });
    }

    public Display getDisplay() {
        return display;
    }

    @Override
    public void go() {
        bind();
        initData();
    }

    public interface Display {
        Widget asWidget();

        SelectEvent.HasSelectHandlers getEditButton();

        SelectEvent.HasSelectHandlers getDeleteButton();

        UserModel getSelectedUser();

        void delete(UserModel selectedUser);

        void setData(List<UserModel> userModels);

        void add(UserModel userModel);

        void update(UserModel savedUserModel);

        void filter();

        SelectEvent.HasSelectHandlers getFilterButton();

        HasSelectionHandlers<Item> getAddDoctorButton();

        HasSelectionHandlers<Item> getAddPatientButton();

        void setLoadMask(boolean mask);

        SelectionChangedEvent.HasSelectionChangedHandlers<UserModel> getGridSelectionChangeHandler();

        void showDetailsInfo(UserModel userModel);

        void clearDetails();
    }

}
