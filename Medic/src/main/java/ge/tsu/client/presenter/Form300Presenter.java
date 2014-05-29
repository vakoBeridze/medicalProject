package ge.tsu.client.presenter;

import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;

/**
 * Created by vako on 29/05/14.
 */
public class Form300Presenter implements Presenter {

    private Display display;

    public Form300Presenter(Display display) {
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