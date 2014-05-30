package ge.tsu.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import com.sencha.gxt.widget.core.client.box.AlertMessageBox;
import ge.tsu.client.service.AppServiceAsync;

public class App implements EntryPoint {

    public static final Messages messages = GWT.create(Messages.class);
    private static final String SERVER_ERROR = "An error occurred while attempting to contact the server. " +
            "Please check your network connection and try again.";

    public static void log(Throwable th) {
        AppServiceAsync.Util.getInstance().logToServer(th, new AsyncCallback<Void>() {
            @Override
            public void onFailure(Throwable throwable) {
            }

            @Override
            public void onSuccess(Void aVoid) {
            }
        });
    }

    @Override
    public void onModuleLoad() {
        GWT.setUncaughtExceptionHandler(new GWT.UncaughtExceptionHandler() {
            @Override
            public void onUncaughtException(Throwable throwable) {
                AlertMessageBox messageBox = new AlertMessageBox("Error", throwable.getMessage());
                messageBox.show();
                log(throwable);
            }
        });

        try {
            MainPanel mainPanel = new MainPanel();
            RootLayoutPanel.get().add(mainPanel);
        } catch (Throwable ex) {
            AlertMessageBox messageBox = new AlertMessageBox("Error", ex.getMessage());
            messageBox.show();
            log(ex);
        }
    }
}
