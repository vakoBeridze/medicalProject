package ge.tsu.client.view;

import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Widget;
import ge.tsu.client.presenter.Form200Presenter;
import ge.tsu.client.presenter.Form300Presenter;

/**
 * Created by vako on 29/05/14.
 */
public class Form300View implements Form300Presenter.Display {

    @Override
    public Widget asWidget() {
        return new HTML("FORM 300");
    }
}
