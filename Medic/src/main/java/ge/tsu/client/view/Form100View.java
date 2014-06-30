package ge.tsu.client.view;

import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.widget.core.client.container.CenterLayoutContainer;
import ge.tsu.client.images.Images;
import ge.tsu.client.presenter.Form100Presenter;

/**
 * Created by vako on 29/05/14.
 */
public class Form100View implements Form100Presenter.Display {

    @Override
    public Widget asWidget() {

        CenterLayoutContainer center = new CenterLayoutContainer();
        center.add(new Image(Images.INSTANCE.comingSoon()));
        return center;
    }
}
