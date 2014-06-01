package ge.tsu.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.sencha.gxt.widget.core.client.box.AlertMessageBox;
import com.sencha.gxt.widget.core.client.box.MessageBox;
import ge.tsu.client.service.AppService;
import ge.tsu.shared.UserModel;

public class App implements EntryPoint {

	public static UserModel currentUser;

	public static final Messages messages = GWT.create(Messages.class);

	private static final String SERVER_ERROR = "An error occurred while attempting to contact the server. " +
			"Please check your network connection and try again.";

	public static void logError(Throwable th) {
		AlertMessageBox errorMessage = new AlertMessageBox(SERVER_ERROR, th.getMessage());
		errorMessage.show();

		AppService.Util.getInstance().logToServer(th, new AsyncCallback<Void>() {
			@Override
			public void onFailure(Throwable throwable) {
				AlertMessageBox messageBox = new AlertMessageBox("Error Log To Server", throwable.getMessage());
				messageBox.show();
			}

			@Override
			public void onSuccess(Void aVoid) {
				MessageBox messageBox = new MessageBox("Message Logged to Server Successfully");
				messageBox.show();
			}
		});
	}

	@Override
	public void onModuleLoad() {
		GWT.setUncaughtExceptionHandler(new GWT.UncaughtExceptionHandler() {
			@Override
			public void onUncaughtException(Throwable throwable) {
				AlertMessageBox messageBox = new AlertMessageBox("UncaughtException Occurred, Please Contact Admin", throwable.getMessage());
				messageBox.show();
//				logError(throwable);
			}
		});

		AppService.Util.getInstance().loadCurrentUser(new AsyncCallback<UserModel>() {
			@Override
			public void onFailure(Throwable throwable) {
				App.logError(throwable);
			}

			@Override
			public void onSuccess(UserModel userModel) {
				App.currentUser = userModel;

				MainPanel mainPanel = new MainPanel();
				RootLayoutPanel.get().add(mainPanel);
			}
		});

	}
}
