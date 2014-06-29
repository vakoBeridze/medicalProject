package ge.tsu.client;

import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.core.client.dom.ScrollSupport;
import com.sencha.gxt.core.client.util.Margins;
import com.sencha.gxt.widget.core.client.ContentPanel;
import com.sencha.gxt.widget.core.client.TabItemConfig;
import com.sencha.gxt.widget.core.client.TabPanel;
import com.sencha.gxt.widget.core.client.box.AlertMessageBox;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.*;
import com.sencha.gxt.widget.core.client.event.CloseEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.menu.Item;
import com.sencha.gxt.widget.core.client.menu.MenuItem;
import com.sencha.gxt.widget.core.client.toolbar.FillToolItem;
import com.sencha.gxt.widget.core.client.toolbar.ToolBar;
import ge.tsu.client.images.Images;
import ge.tsu.client.presenter.PasswordChangePresenter;
import ge.tsu.client.service.AppService;
import ge.tsu.client.view.MenuView;
import ge.tsu.client.view.PasswordChangeView;

import java.util.Map;

public class MainPanel extends BorderLayoutContainer {

	public static TabPanel tabPanel;

	public MainPanel() {

		monitorWindowResize = false;
		Window.enableScrolling(false);
		setPixelSize(Window.getClientWidth(), Window.getClientHeight());
//        setBorders(true);

		VerticalLayoutContainer headerWidgets = new VerticalLayoutContainer();
		headerWidgets.add(initHeader(), new VerticalLayoutContainer.VerticalLayoutData(1, -1));

		BorderLayoutData northData = new BorderLayoutData(35);
		setNorthWidget(headerWidgets, northData);

		BorderLayoutData westData = new BorderLayoutData(0.2);
		westData.setMargins(new Margins(5, 0, 0, 5));
		westData.setSplit(true);
		westData.setCollapsible(true);
		westData.setCollapseMini(true);

		ContentPanel west = new ContentPanel();
		west.setHeadingText(App.messages.navigation());
		west.setBodyBorder(true);
		west.add(new MenuView().asWidget());
//		west.add(new VerticalLayoutContainer());
		MarginData centerData = new MarginData();
		centerData.setMargins(new Margins(5, 5, 0, 5));

		SimpleContainer center = new SimpleContainer();
		center.add(initTabPanel());

		setWestWidget(west, westData);
		setCenterWidget(center, centerData);
	}

	private TabPanel initTabPanel() {
		tabPanel = new TabPanel();
		tabPanel.setAnimScroll(true);
		tabPanel.setTabScroll(true);
		tabPanel.setCloseContextMenu(true);

		tabPanel.addCloseHandler(new CloseEvent.CloseHandler<Widget>() {
			@Override
			public void onClose(CloseEvent<Widget> widgetCloseEvent) {
				Widget closedWidget = widgetCloseEvent.getItem();
				for (Map.Entry<Menu, Widget> menuWidgetEntry : Util.openTabs.entrySet()) {
					if (menuWidgetEntry.getValue().equals(closedWidget)) {
						Util.openTabs.put(menuWidgetEntry.getKey(), null);
						break;
					}
				}
			}
		});

//        CenterLayoutContainer center = new CenterLayoutContainer();
		VerticalLayoutContainer vlc = new VerticalLayoutContainer();
		vlc.setScrollMode(ScrollSupport.ScrollMode.AUTOY);

		HTMLPanel htmlPanel = new HTMLPanel(Images.INSTANCE.about().getText());
		vlc.add(htmlPanel, new VerticalLayoutContainer.VerticalLayoutData(1, -1));

		tabPanel.add(vlc, new TabItemConfig(App.messages.home(), false));
		return tabPanel;
	}

	private IsWidget initHeader() {
		ToolBar toolBar = new ToolBar();
		toolBar.setSpacing(10);

		HtmlLayoutContainer title = new HtmlLayoutContainer("<font size='3' color='black'>" + App.messages.appTitle() + "</font>");
		toolBar.add(title);

		toolBar.add(new FillToolItem());
		TextButton changeLanguageButton = new TextButton(App.messages.languageName().equals("English") ? "ქართული" : "English", App.messages.languageName().equals("English") ? Images.INSTANCE.geFlag() : Images.INSTANCE.engFlag());
		changeLanguageButton.addSelectHandler(new SelectEvent.SelectHandler() {
			@Override
			public void onSelect(SelectEvent selectEvent) {
				if (App.messages.languageName().equals("English")) {
					Window.open(com.google.gwt.core.client.GWT.getHostPageBaseURL() + "App.html?locale=ka", "_self", "");
				} else {
					Window.open(com.google.gwt.core.client.GWT.getHostPageBaseURL(), "_self", "");
				}
			}
		});
		toolBar.add(changeLanguageButton);

		String displayName = App.currentUser.getFirstName() + " " + App.currentUser.getLastName();// + " (" + App.currentUser.getEmail() + ") ";
//        HtmlLayoutContainer user = new HtmlLayoutContainer("<font size='2' color='black'> " + displayName + " </font>");
//        toolBar.add(user);

		final TextButton userButton = new TextButton(displayName, Images.INSTANCE.user());
		userButton.setMenu(createMenu());
		toolBar.add(userButton);

		return toolBar;
	}

	private com.sencha.gxt.widget.core.client.menu.Menu createMenu() {
		com.sencha.gxt.widget.core.client.menu.Menu menu = new com.sencha.gxt.widget.core.client.menu.Menu();


		MenuItem changePasswordMenu = new MenuItem(App.messages.changePassword(), Images.INSTANCE.changePassword());
		changePasswordMenu.addSelectionHandler(new SelectionHandler<Item>() {
			@Override
			public void onSelection(SelectionEvent<Item> itemSelectionEvent) {
				PasswordChangePresenter presenter = new PasswordChangePresenter(new PasswordChangeView());
				presenter.go();
			}
		});

		MenuItem logoutMenu = new MenuItem(App.messages.logout(), Images.INSTANCE.logout());
		logoutMenu.addSelectionHandler(new SelectionHandler<Item>() {
			@Override
			public void onSelection(SelectionEvent<Item> itemSelectionEvent) {
				logout();
			}
		});

		menu.add(changePasswordMenu);
		menu.add(logoutMenu);
		return menu;
	}

	private void logout() {
		AppService.Util.getInstance().logout(new AsyncCallback<Void>() {
			@Override
			public void onFailure(Throwable throwable) {
				new AlertMessageBox("Error", App.messages.errorLogout()).show();
			}

			@Override
			public void onSuccess(Void aVoid) {
				Window.Location.reload();
			}
		});
	}

	@Override
	protected void onWindowResize(int width, int height) {
		setPixelSize(width, height);
	}
}
