package ge.tsu.client.presenter;

import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;
import ge.tsu.client.view.Form200View;

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

    public interface Display {
        public Widget asWidget();
    }


    @Override
    public void go(HasWidgets container) {
        // TODO
    }
}
