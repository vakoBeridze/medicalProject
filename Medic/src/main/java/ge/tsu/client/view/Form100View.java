package ge.tsu.client.view;

import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Widget;
import ge.tsu.client.presenter.Form100Presenter;
import ge.tsu.client.presenter.Form200Presenter;

/**
 * Created by vako on 29/05/14.
 */
public class Form100View implements Form100Presenter.Display {

    @Override
    public Widget asWidget() {
        return new HTML("FORM 100");
    }
}
