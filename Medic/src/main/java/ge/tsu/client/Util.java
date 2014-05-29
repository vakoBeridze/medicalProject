package ge.tsu.client;

import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.widget.core.client.TabItemConfig;
import ge.tsu.client.images.Images;
import ge.tsu.client.view.Tab;
import ge.tsu.shared.MenuModel;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: Vako
 * Date: 25.05.2014
 * Time: 16:59
 */
public class Util {
	private static List<MenuModel> openTabs = new ArrayList<MenuModel>();

	public static ImageResource getMenuIcon(MenuModel menu) {
		ImageResource icon = null;
		switch (menu.getMenuNumber()) {
			case 11: {
				icon = Images.INSTANCE.userManager();
				break;
			}
			case 100: {
				icon = Images.INSTANCE.form100();
				break;
			}
			case 200: {
				icon = Images.INSTANCE.form200();
				break;
			}
			case 300: {
				icon = Images.INSTANCE.form300();
				break;
			}
			case 400: {
				icon = Images.INSTANCE.form400();
				break;
			}
			case 500: {
				icon = Images.INSTANCE.form500();
				break;
			}

		}
		return icon;
	}

	public static void addTab(MenuModel menu) {
		switch (menu.getMenuNumber()) {
			case 11: {
				MainPanel.tabPanel.add(new HTML("UserManager"), App.messages.userManager());
				break;
			}
			case 100:
			case 200:
			case 300:
			case 400:
			case 500: {
				if(!openTabs.contains(menu)) {
					openTabs.add(menu);
					Widget widget = new HTML(App.messages.formMenu() + " " + menu.getMenuNumber());
					MainPanel.tabPanel.add(new Tab(menu.getMenuNumber(), widget).getWidget(), new TabItemConfig(App.messages.formMenu() + " " + menu.getMenuNumber(), true));
				} else {
					// TODO
				}
				break;
			}
		}
	}
}
