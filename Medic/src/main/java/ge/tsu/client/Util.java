package ge.tsu.client;

import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.widget.core.client.TabItemConfig;
import ge.tsu.client.images.Images;
import ge.tsu.client.presenter.*;
import ge.tsu.client.view.*;
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
        ImageResource icon = null;
        switch (menu.getMenu()) {
            case USER_MANAGER: {
                icon = Images.INSTANCE.userManager();
                break;
            }
            case FORM_100: {
                icon = Images.INSTANCE.form100();
                break;
            }
            case FORM_200: {
                icon = Images.INSTANCE.form200();
                break;
            }
            case FORM_300: {
                icon = Images.INSTANCE.form300();
                break;
            }
            case FORM_400: {
                icon = Images.INSTANCE.form400();
                break;
            }
            case FORM_500: {
                icon = Images.INSTANCE.form500();
                break;
            }

        }
        return icon;
    }

    public static void addTab(MenuModel menu) {

        if (openTabs.get(menu.getMenu()) != null) {
            MainPanel.tabPanel.setActiveWidget(openTabs.get(menu.getMenu()));
        } else {
            Widget widget = null;
            switch (menu.getMenu()) {
                case USER_MANAGER: {
                    UserManagerPresenter presenter = new UserManagerPresenter(new UserManagerView());
                    widget = presenter.getDisplay().asWidget();
                    openTabs.put(menu.getMenu(), widget);
                    MainPanel.tabPanel.add(widget, new TabItemConfig(App.messages.userManager(), true));
                    MainPanel.tabPanel.setActiveWidget(widget);
                    return;
                }
                case FORM_100: {
                    Form100Presenter presenter = new Form100Presenter(new Form100View());
                    widget = presenter.getDisplay().asWidget();
                    break;
                }
                case FORM_200: {
                    Form200Presenter presenter = new Form200Presenter(new Form200View());
                    widget = presenter.getDisplay().asWidget();
                    break;
                }
                case FORM_300: {
                    Form300Presenter presenter = new Form300Presenter(new Form300View());
                    widget = presenter.getDisplay().asWidget();
                    break;
                }
                case FORM_400: {
                    Form400Presenter presenter = new Form400Presenter(new Form400View());
                    widget = presenter.getDisplay().asWidget();
                    break;
                }
                case FORM_500: {
                    Form500Presenter presenter = new Form500Presenter(new Form500View());
                    widget = presenter.getDisplay().asWidget();
                    break;
                }
            }
            if (widget != null) {
                openTabs.put(menu.getMenu(), widget);
                MainPanel.tabPanel.add(widget, new TabItemConfig(App.messages.formMenu() + " " + menu.getMenu().toString().substring(5), true));
                MainPanel.tabPanel.setActiveWidget(widget);
            }
        }
    }
}
