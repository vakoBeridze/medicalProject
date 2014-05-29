package ge.tsu.client.view;

import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Widget;
import ge.tsu.client.presenter.Form500Presenter;

/**
 * Created by vako on 29/05/14.
 */
public class Form500View implements Form500Presenter.Display {

    @Override
    public Widget asWidget() {
        return new HTML("FORM 500");
    }
}
