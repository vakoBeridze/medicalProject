package ge.tsu.client.view;

import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.widget.core.client.container.CenterLayoutContainer;
import ge.tsu.client.images.Images;
import ge.tsu.client.presenter.Form200_1Presenter;

/**
 * Created by vako on 19/06/14.
 */
public class Form200_1View implements Form200_1Presenter.Display {

    @Override
    public Widget asWidget() {

        CenterLayoutContainer center = new CenterLayoutContainer();
        center.add(new Image(Images.INSTANCE.form200_1()));
        return center;
    }
}
