package ge.tsu.client;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.core.client.util.Margins;
import com.sencha.gxt.widget.core.client.ContentPanel;
import com.sencha.gxt.widget.core.client.TabItemConfig;
import com.sencha.gxt.widget.core.client.TabPanel;
import com.sencha.gxt.widget.core.client.box.AlertMessageBox;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.*;
import com.sencha.gxt.widget.core.client.event.CloseEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.toolbar.FillToolItem;
import com.sencha.gxt.widget.core.client.toolbar.ToolBar;
import ge.tsu.client.images.Images;
import ge.tsu.client.service.AppServiceAsync;
import ge.tsu.client.view.MenuView;

public class MainPanel extends BorderLayoutContainer {

	public static TabPanel tabPanel;

	public MainPanel() {

		monitorWindowResize = true;
		Window.enableScrolling(false);
		setPixelSize(Window.getClientWidth(), Window.getClientHeight());

		VerticalLayoutContainer headerWidgets = new VerticalLayoutContainer();
		headerWidgets.add(initHeader(), new VerticalLayoutContainer.VerticalLayoutData(1, -1));

		BorderLayoutData northData = new BorderLayoutData(35);
		setNorthWidget(headerWidgets, northData);

		BorderLayoutData westData = new BorderLayoutData(210);
		westData.setMargins(new Margins(5, 0, 0, 5));
		westData.setSplit(true);
		westData.setCollapsible(true);
		westData.setCollapseMini(true);

		ContentPanel west = new ContentPanel();
		west.setHeadingText(App.messages.navigation());
		west.setBodyBorder(true);
		// TODO add menu
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
				// TODO
			}
		});

		CenterLayoutContainer center = new CenterLayoutContainer();
//		center.add(new HTML("<div><img src=\"" + GWT.getModuleBaseURL() + "/images/logo.png\"></div>"));
		center.add(new Image(Images.INSTANCE.logo()));
		tabPanel.add(center, new TabItemConfig(App.messages.home(), false));

//		Label item2 = new Label("Test Tab 1");
//		tabPanel.add(item2, "Tab 1");

		return tabPanel;
	}

	private IsWidget initHeader() {
		ToolBar toolBar = new ToolBar();
		toolBar.setSpacing(10);

		HtmlLayoutContainer title = new HtmlLayoutContainer("<font size='3' color='black'>" + App.messages.appTitle() + "</font>");
		toolBar.add(title);

		toolBar.add(new FillToolItem());
		final TextButton logout = new TextButton(App.messages.logout());
		logout.addSelectHandler(new SelectEvent.SelectHandler() {
			public void onSelect(SelectEvent selectEvent) {
				logout();
			}
		});
		toolBar.add(logout);

		return toolBar;
	}

	private void logout() {
		AppServiceAsync.Util.getInstance().logout(new AsyncCallback<Void>() {
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
