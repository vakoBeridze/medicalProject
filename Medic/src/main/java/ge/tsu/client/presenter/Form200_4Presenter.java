package ge.tsu.client.presenter;

import com.google.gwt.user.client.ui.Widget;

/**
 * Created by vako on 19/06/14.
 */
public class Form200_4Presenter implements Presenter {

    private Display display;

    public Form200_4Presenter(Display display) {
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
