package ge.tsu.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import ge.tsu.client.service.GreetingService;
import ge.tsu.client.service.GreetingServiceAsync;

public class App implements EntryPoint {

    private static final String SERVER_ERROR = "An error occurred while attempting to contact the server. " +
            "Please check your network connection and try again.";

    public static final Messages messages = GWT.create(Messages.class);

    @Override
    public void onModuleLoad() {

        MainPanel mainPanel = new MainPanel();
        RootLayoutPanel.get().add(mainPanel);
    }
}
