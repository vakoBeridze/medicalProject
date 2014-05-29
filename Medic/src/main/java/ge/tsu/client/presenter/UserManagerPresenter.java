package ge.tsu.client.presenter;

import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;
import ge.tsu.client.view.UserManagerView;

/**
 * Created by vako on 29/05/14.
 */
public class UserManagerPresenter implements Presenter {

    Display display;

    public UserManagerPresenter(Display display) {
        this.display = display;
    }

    @Override
    public void go(HasWidgets container) {
        // TODO
    }

    public Display getDisplay() {
        return display;
    }

    public interface Display {
        Widget asWidget();
    }
}
