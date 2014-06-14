package ge.tsu.client.view;

import com.google.gwt.cell.client.ValueUpdater;
import com.google.gwt.core.shared.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.text.shared.SimpleSafeHtmlRenderer;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.cell.core.client.SimpleSafeHtmlCell;
import com.sencha.gxt.core.client.Style;
import com.sencha.gxt.core.client.dom.ScrollSupport;
import com.sencha.gxt.core.client.util.Margins;
import com.sencha.gxt.data.shared.IconProvider;
import com.sencha.gxt.data.shared.Store;
import com.sencha.gxt.data.shared.TreeStore;
import com.sencha.gxt.widget.core.client.container.BoxLayoutContainer;
import com.sencha.gxt.widget.core.client.container.FlowLayoutContainer;
import com.sencha.gxt.widget.core.client.container.MarginData;
import com.sencha.gxt.widget.core.client.form.StoreFilterField;
import com.sencha.gxt.widget.core.client.toolbar.ToolBar;
import com.sencha.gxt.widget.core.client.tree.Tree;
import ge.tsu.client.App;
import ge.tsu.client.Menu;
import ge.tsu.client.Util;
import ge.tsu.shared.MenuModel;
import ge.tsu.shared.MenuModelProperties;

/**
 * Created with IntelliJ IDEA.
 * User: Vako
 * Date: 25.05.2014
 * Time: 14:17
 */
public class MenuView extends Widget implements IsWidget {

    private Tree<MenuModel, String> tree;
    private MenuModel ambulanceMenuModel;
    private MenuModel adminMenuModel;
    private MenuModel stationaryMenuModel;

    public MenuView() {
    }

    @Override
    public Widget asWidget() {
        MenuModelProperties menuModelProperties = GWT.create(MenuModelProperties.class);
        TreeStore<MenuModel> store = new TreeStore<MenuModel>(menuModelProperties.code());
        tree = new Tree<MenuModel, String>(store, menuModelProperties.label());

        FlowLayoutContainer con = new FlowLayoutContainer();

        StoreFilterField<MenuModel> filter = new StoreFilterField<MenuModel>() {

            @Override
            protected boolean doSelect(Store<MenuModel> menuModelStore, MenuModel menuModel, MenuModel item, String text) {
                if (item.isRoot()) {
                    return false;
                }
                String name = item.getLabel() != null ? item.getLabel().toLowerCase() : null;
                return name != null && filter != null && name.contains(text.toLowerCase());
            }
        };
        filter.bind(tree.getStore());
        filter.setEmptyText(App.messages.filter());

        SimpleSafeHtmlCell<String> cell = new SimpleSafeHtmlCell<String>(SimpleSafeHtmlRenderer.getInstance(), "click") {
            @Override
            public void onBrowserEvent(Context context, Element parent, String value, NativeEvent event, ValueUpdater<String> valueUpdater) {
                super.onBrowserEvent(context, parent, value, event, valueUpdater);
                if ("click".equals(event.getType())) {
                    MenuModel menu = tree.getSelectionModel().getSelectedItem();
                    if (!menu.isRoot()) {
                        Util.addTab(menu);
                    }
                }
            }
        };

        tree.getSelectionModel().setSelectionMode(Style.SelectionMode.SINGLE);
        tree.setIconProvider(new IconProvider<MenuModel>() {
            @Override
            public ImageResource getIcon(MenuModel model) {
                return Util.getMenuIcon(model);
            }
        });
        tree.setCell(cell);

        ToolBar toolBar = new ToolBar();
        toolBar.add(filter, new BoxLayoutContainer.BoxLayoutData(new Margins(10)));

        con.add(toolBar);
        con.add(tree, new MarginData(5));

        con.setScrollMode(ScrollSupport.ScrollMode.AUTOY);

        createMenuStore();

        tree.setAutoExpand(true);
        tree.expandAll();

        return con;
    }

    private void createMenuStore() {
        createMenuRoots();
        createMenuItems();
    }

    private void createMenuItems() {
        TreeStore<MenuModel> store = tree.getStore();
       /* MenuModel menu;
        for (int i = 1; i < 6; i++) {
            menu = new MenuModel();
            menu.setLabel(App.messages.formMenu() + " " + (i * 100));
            menu.setCode("form " + (i * 100));
            menu.setMenu(Menu.values()[i]);
            store.add(formsMenuModel, menu);
        }*/

        MenuModel form200a = new MenuModel("form200a", App.messages.formMenu() + " №IV-200 / " + App.messages.a(), false, Menu.FORM_200_a);
        store.add(ambulanceMenuModel, form200a);

        for (int i = 1; i < 14; i++) {
            MenuModel menuModel = new MenuModel("form200_" + i + "a", App.messages.formMenu() + " №IV-200-" + i + " / " + App.messages.a(), false, Menu.valueOf("FORM_200_" + i + "_a"));
            store.add(ambulanceMenuModel, menuModel);
        }

        MenuModel form300a = new MenuModel("form300a", App.messages.formMenu() + " №IV-300 / " + App.messages.a(), false, Menu.FORM_300_a);
        store.add(stationaryMenuModel, form300a);

        for (int i = 1; i < 17; i++) {
            MenuModel menuModel = new MenuModel("form300_" + i + "a", App.messages.formMenu() + " №IV-300-" + i + " / " + App.messages.a(), false, Menu.valueOf("FORM_300_" + i + "_a"));
            store.add(stationaryMenuModel, menuModel);
        }

        MenuModel form301a = new MenuModel("form301a", App.messages.formMenu() + " №IV-301 / " + App.messages.a(), false, Menu.FORM_300_1_a);
        store.add(stationaryMenuModel, form301a);

        MenuModel userManagement = new MenuModel("userManagement", App.messages.userManager(), false, Menu.USER_MANAGER);
        store.add(adminMenuModel, userManagement);
    }

    private void createMenuRoots() {
        adminMenuModel = new MenuModel();
        adminMenuModel.setCode("admin");
        adminMenuModel.setLabel(App.messages.administrationMenu());
        adminMenuModel.setMenu(Menu.ROOT_ADMIN);
        adminMenuModel.setRoot(true);

        ambulanceMenuModel = new MenuModel();
        ambulanceMenuModel.setCode("ambulance");
        ambulanceMenuModel.setLabel(App.messages.ambulanceMenu());
        ambulanceMenuModel.setMenu(Menu.ROOT_AMBULANCE);
        ambulanceMenuModel.setRoot(true);

        stationaryMenuModel = new MenuModel();
        stationaryMenuModel.setCode("stationary");
        stationaryMenuModel.setLabel(App.messages.stationary());
        stationaryMenuModel.setMenu(Menu.ROOT_STATIONARY);
        stationaryMenuModel.setRoot(true);

        tree.getStore().add(adminMenuModel);
        tree.getStore().add(ambulanceMenuModel);
        tree.getStore().add(stationaryMenuModel);
    }

}
