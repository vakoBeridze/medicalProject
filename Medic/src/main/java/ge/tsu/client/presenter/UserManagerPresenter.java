package ge.tsu.client.presenter;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
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
		display.getAddButton().addSelectHandler(new SelectEvent.SelectHandler() {
			@Override
			public void onSelect(SelectEvent selectEvent) {
				EditUserPresenter presenter = new EditUserPresenter(new EditUserView(createEmptyUser()), display);
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

    private UserModel createEmptyUser() {
        UserModel model = new UserModel();
        model.setId(0);
        model.setFirstName("");
        model.setLastName("");
        model.setFatherName("");
        model.setGender(1);
        model.setEmail("");
        model.setPassword("password");
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
		AppService.Util.getInstance().loadUsers(new AsyncCallback<List<UserModel>>() {
			@Override
			public void onFailure(Throwable throwable) {
				App.logError(throwable);
			}

			@Override
			public void onSuccess(List<UserModel> userModels) {
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

		SelectEvent.HasSelectHandlers getAddButton();

		SelectEvent.HasSelectHandlers getEditButton();

		SelectEvent.HasSelectHandlers getDeleteButton();

		UserModel getSelectedUser();

		void delete(UserModel selectedUser);

		void setData(List<UserModel> userModels);

		void add(UserModel userModel);

		void update(UserModel savedUserModel);

		void filter();

		SelectEvent.HasSelectHandlers getFilterButton();
	}

}
