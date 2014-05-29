package ge.tsu.client.view;

import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Widget;
import ge.tsu.client.presenter.Form400Presenter;

/**
 * Created by vako on 29/05/14.
 */
public class Form400View implements Form400Presenter.Display {

    @Override
    public Widget asWidget() {
        return new HTML("FORM 400");
    }
}
