package ge.tsu.client.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.google.gwt.text.shared.AbstractSafeHtmlRenderer;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.cell.core.client.SimpleSafeHtmlCell;
import com.sencha.gxt.core.client.util.Margins;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.widget.core.client.ContentPanel;
import com.sencha.gxt.widget.core.client.Dialog;
import com.sencha.gxt.widget.core.client.box.ConfirmMessageBox;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.BorderLayoutContainer;
import com.sencha.gxt.widget.core.client.container.MarginData;
import com.sencha.gxt.widget.core.client.container.SimpleContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.sencha.gxt.widget.core.client.grid.ColumnModel;
import com.sencha.gxt.widget.core.client.grid.Grid;
import com.sencha.gxt.widget.core.client.toolbar.ToolBar;
import ge.tsu.client.App;
import ge.tsu.client.presenter.UserManagerPresenter;
import ge.tsu.shared.UserModel;
import ge.tsu.shared.UserModelProperties;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vako on 29/05/14.
 */
public class UserManagerView implements UserManagerPresenter.Display {


    private static final UserModelProperties props = GWT.create(UserModelProperties.class);
    private TextButton addButton;
    private TextButton editButton;
    private TextButton deleteButton;
    private TextButton yesDeleteButton;
    private Grid<UserModel> grid;

    @Override
    public Widget asWidget() {
        BorderLayoutContainer blc = new BorderLayoutContainer();
        blc.setBorders(false);

        BorderLayoutContainer.BorderLayoutData eastData = new BorderLayoutContainer.BorderLayoutData(.5);
        eastData.setMargins(new Margins(5, 0, 0, 5));
//        eastData.setSplit(true);
        eastData.setCollapsible(false);
        eastData.setCollapseMini(false);

        ContentPanel east = new ContentPanel();
        east.setHeaderVisible(false);
        east.setBodyBorder(true);
        east.add(initDetailsInfo());

        MarginData centerData = new MarginData();
        centerData.setMargins(new Margins(5, 5, 0, 5));

        SimpleContainer center = new SimpleContainer();
        center.add(initCenterPanel());

        blc.setEastWidget(east, eastData);
        blc.setCenterWidget(center, centerData);

        return blc;
    }

    @Override
    public SelectEvent.HasSelectHandlers getAddButton() {
        return addButton;
    }

    @Override
    public SelectEvent.HasSelectHandlers getEditButton() {
        return editButton;
    }

    @Override
    public SelectEvent.HasSelectHandlers getDeleteButton() {
        return yesDeleteButton;
    }

    @Override
    public UserModel getSelectedUser() {
        return grid.getSelectionModel().getSelectedItem();
    }

    @Override
    public void delete(UserModel selectedUser) {
        grid.getStore().remove(selectedUser);
    }

    private Widget initCenterPanel() {
        VerticalLayoutContainer vlc = new VerticalLayoutContainer();

        ToolBar toolBar = new ToolBar();
        yesDeleteButton = new TextButton();
        addButton = new TextButton(App.messages.add());
        editButton = new TextButton(App.messages.edit());
        deleteButton = new TextButton(App.messages.delete());
        deleteButton.addSelectHandler(new SelectEvent.SelectHandler() {
            @Override
            public void onSelect(final SelectEvent selectEvent) {
                ConfirmMessageBox messageBox = new ConfirmMessageBox(App.messages.confirm(), App.messages.sureDelete());
                yesDeleteButton = messageBox.getButton(Dialog.PredefinedButton.YES);
                yesDeleteButton.addSelectHandler(new SelectEvent.SelectHandler() {
                    @Override
                    public void onSelect(SelectEvent event) {
                        // TODO
//                        yesDeleteButton.fireEvent(selectEvent);
                    }
                });
                messageBox.show();
            }
        });
        toolBar.add(addButton);
        toolBar.add(editButton);
        toolBar.add(deleteButton);

        initGrid();

        vlc.add(toolBar, new VerticalLayoutContainer.VerticalLayoutData(1, -1));
        vlc.add(grid, new VerticalLayoutContainer.VerticalLayoutData(1, 1));

        return vlc;
    }

    private Widget initDetailsInfo() {

        // TODO

        return new HTML("Details Info");
    }

    private void initGrid() {

        ColumnConfig<UserModel, String> firstNameCol = new ColumnConfig<UserModel, String>(props.firstName(), 50, SafeHtmlUtils.fromTrustedString("<b>" + App.messages.firstName() + "</b>"));
        ColumnConfig<UserModel, String> lastNameCol = new ColumnConfig<UserModel, String>(props.lastName(), 50, App.messages.lastName());
        ColumnConfig<UserModel, String> userNameCol = new ColumnConfig<UserModel, String>(props.userName(), 50, App.messages.userName());
        ColumnConfig<UserModel, String> emailAddressCol = new ColumnConfig<UserModel, String>(props.emailAddress(), 100, App.messages.emailAddress());
        ColumnConfig<UserModel, Boolean> adminCol = new ColumnConfig<UserModel, Boolean>(props.admin(), 55, App.messages.admin());
        adminCol.setCell(new SimpleSafeHtmlCell<Boolean>(new AbstractSafeHtmlRenderer<Boolean>() {
            @Override
            public SafeHtml render(Boolean object) {
                return SafeHtmlUtils.fromString(object ? App.messages.yes() : App.messages.no());
            }
        }));
        List<ColumnConfig<UserModel, ?>> l = new ArrayList<ColumnConfig<UserModel, ?>>();
        l.add(firstNameCol);
        l.add(lastNameCol);
        l.add(userNameCol);
        l.add(emailAddressCol);
        l.add(adminCol);
        ColumnModel<UserModel> cm = new ColumnModel<UserModel>(l);

        ListStore<UserModel> store = new ListStore<UserModel>(props.id());

        grid = new Grid<UserModel>(store, cm);
        grid.getView().setAutoExpandColumn(firstNameCol);
        grid.getView().setAutoFill(true);
        grid.getView().setStripeRows(true);
        grid.getView().setColumnLines(true);
        grid.setBorders(false);

        grid.setColumnReordering(true);

        grid.getStore().addAll(getSampleData());
    }

    public List<UserModel> getSampleData() {
        List<UserModel> data = new ArrayList<UserModel>();
        UserModel m1 = new UserModel();
        m1.setId(1);
        m1.setFirstName("Vako");
        m1.setLastName("Beridze");
        m1.setUserName("v.beridze");
        m1.setEmailAddress("vako.beridze@gmail.com");
        m1.setAdmin(true);
        data.add(m1);

        UserModel m = new UserModel();
        m.setId(2);
        m.setFirstName("Vako1");
        m.setLastName("Beridze1");
        m.setUserName("v.beridze");
        m.setEmailAddress("vako.beridze@gmail.com");
        m.setAdmin(false);
        data.add(m);

        return data;
    }
}
