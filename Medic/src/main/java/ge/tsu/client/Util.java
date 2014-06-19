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

        if (menu.isRoot())
            return null;
        ImageResource icon = null;
        switch (menu.getMenu()) {
            case USER_MANAGER: {
                icon = Images.INSTANCE.userManager();
                break;
            }
            case FORM_200_a:
            case FORM_200_7_a: {
                icon = Images.INSTANCE.form();
                break;
            }
            case FORM_200_1_a:
            case FORM_200_2_a:
            case FORM_200_3_a:
            case FORM_200_4_a:
            case FORM_200_5_a:
            case FORM_200_6_a:
            case FORM_200_8_a: {
                icon = Images.INSTANCE.formGreen();
                break;
            }
            default: {
                icon = Images.INSTANCE.formRed();
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
                case FORM_200_7_a: {
                    presenter = new Form200_7Presenter(new Form200_7View());
                    widget = ((Form200_7Presenter) presenter).getDisplay().asWidget();
                    break;
                }
                case FORM_200_1_a: {
                    presenter = new Form200_1Presenter(new Form200_1View());
                    widget = ((Form200_1Presenter) presenter).getDisplay().asWidget();
                    break;
                }
                case FORM_200_2_a: {
                    presenter = new Form200_2Presenter(new Form200_2View());
                    widget = ((Form200_2Presenter) presenter).getDisplay().asWidget();
                    break;
                }
                case FORM_200_3_a: {
                    presenter = new Form200_3Presenter(new Form200_3View());
                    widget = ((Form200_3Presenter) presenter).getDisplay().asWidget();
                    break;
                }
                case FORM_200_4_a: {
                    presenter = new Form200_4Presenter(new Form200_4View());
                    widget = ((Form200_4Presenter) presenter).getDisplay().asWidget();
                    break;
                }
                case FORM_200_5_a: {
                    presenter = new Form200_5Presenter(new Form200_5View());
                    widget = ((Form200_5Presenter) presenter).getDisplay().asWidget();
                    break;
                }
                case FORM_200_6_a: {
                    presenter = new Form200_6Presenter(new Form200_6View());
                    widget = ((Form200_6Presenter) presenter).getDisplay().asWidget();
                    break;
                }
                case FORM_200_8_a: {
                    presenter = new Form200_8Presenter(new Form200_8View());
                    widget = ((Form200_8Presenter) presenter).getDisplay().asWidget();
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
