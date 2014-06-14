package ge.tsu.client;

import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.widget.core.client.TabItemConfig;
import ge.tsu.client.images.Images;
import ge.tsu.client.presenter.Form100Presenter;
import ge.tsu.client.presenter.Form200Presenter;
import ge.tsu.client.presenter.Presenter;
import ge.tsu.client.presenter.UserManagerPresenter;
import ge.tsu.client.view.Form100View;
import ge.tsu.client.view.Form200View;
import ge.tsu.client.view.UserManagerView;
import ge.tsu.shared.MenuModel;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Vako
 * Date: 25.05.2014
 * Time: 16:59
 */
public class Util {
    public static Map<Menu, Widget> openTabs = new HashMap<Menu, Widget>();

    public static ImageResource getMenuIcon(MenuModel menu) {

        if (menu.isRoot())
            return null;
        ImageResource icon = null;
        switch (menu.getMenu()) {
            case USER_MANAGER: {
                icon = Images.INSTANCE.userManager();
                break;
            }
            case FORM_200_a: {
                icon = Images.INSTANCE.form200();
                break;
            }
            default: {
                icon = Images.INSTANCE.form();
                break;
            }
        }
        return icon;
    }

    public static void addTab(MenuModel menu) {

        if (openTabs.get(menu.getMenu()) != null) {
            MainPanel.tabPanel.setActiveWidget(openTabs.get(menu.getMenu()));
        } else {
            Presenter presenter = null;
            Widget widget = null;
            switch (menu.getMenu()) {
                case USER_MANAGER: {
                    presenter = new UserManagerPresenter(new UserManagerView());
                    widget = ((UserManagerPresenter) presenter).getDisplay().asWidget();
                    break;
                }
                case FORM_200_a: {
                    presenter = new Form200Presenter(new Form200View());
                    widget = ((Form200Presenter) presenter).getDisplay().asWidget();
                    break;
                }
                default: {
                    presenter = new Form100Presenter(new Form100View());
                    widget = ((Form100Presenter) presenter).getDisplay().asWidget();
                    break;
                }
            }
            if (widget != null) {
                openTabs.put(menu.getMenu(), widget);
                MainPanel.tabPanel.add(widget, new TabItemConfig(menu.getLabel(), true));
                MainPanel.tabPanel.setActiveWidget(widget);

                presenter.go();
            }
        }
    }
}
