package ge.tsu.client.presenter;

import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;

/**
 * Created by vako on 29/05/14.
 */
public abstract interface Presenter {
    public abstract void go(final HasWidgets container);
}