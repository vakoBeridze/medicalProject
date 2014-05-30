package ge.tsu.client.presenter;

import com.google.gwt.user.client.ui.Widget;

/**
 * Created by vako on 29/05/14.
 */
public class Form500Presenter implements Presenter {

    private Display display;

    public Form500Presenter(Display display) {
        this.display = display;
    }

    public Display getDisplay() {
        return display;
    }

    public interface Display {
        public Widget asWidget();
    }


    @Override
    public void go() {
        // TODO
    }
}
