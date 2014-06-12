package ge.tsu.client.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.HasSelectionHandlers;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.google.gwt.text.shared.AbstractSafeHtmlRenderer;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.cell.core.client.SimpleSafeHtmlCell;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.core.client.util.Margins;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.widget.core.client.ContentPanel;
import com.sencha.gxt.widget.core.client.Dialog;
import com.sencha.gxt.widget.core.client.box.ConfirmMessageBox;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.*;
import com.sencha.gxt.widget.core.client.event.DialogHideEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.form.TextField;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.sencha.gxt.widget.core.client.grid.ColumnModel;
import com.sencha.gxt.widget.core.client.grid.Grid;
import com.sencha.gxt.widget.core.client.menu.Item;
import com.sencha.gxt.widget.core.client.menu.Menu;
import com.sencha.gxt.widget.core.client.menu.MenuItem;
import com.sencha.gxt.widget.core.client.selection.SelectionChangedEvent;
import com.sencha.gxt.widget.core.client.toolbar.SeparatorToolItem;
import com.sencha.gxt.widget.core.client.toolbar.ToolBar;
import ge.tsu.client.App;
import ge.tsu.client.images.Images;
import ge.tsu.client.presenter.UserManagerPresenter;
import ge.tsu.shared.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by vako on 29/05/14.
 */
public class UserManagerView implements UserManagerPresenter.Display {

    private static final UserModelProperties props = GWT.create(UserModelProperties.class);

    private ContentPanel bloodTransfusionCP;
    private ContentPanel allergyCP;
    private ContentPanel customerSurgeryCP;
    private ContentPanel diseaseCP;
    private ContentPanel policeCP;

    private Grid<BloodTransfusionModel> bloodGrid;
    private Grid<CustomerAllergyModel> allergyGrid;
    private Grid<CustomerSurgeryModel> customerSurgeryGrid;
    private Grid<CustomerDiseaseModel> diseaseGrid;
    private Grid<PoliceModel> policeGrid;

    private List<UserModel> allGridItems;
    private MenuItem addDoctorButton;
    private MenuItem addPatientButton;
    private TextButton addButton;
    private TextButton editButton;
    private TextButton deleteButton;
    private TextButton yesDeleteButton;
    private Grid<UserModel> grid;
    // filter
    private TextField idFilter;
    private TextField firstNameFilter;
    private TextField lastNameFilter;
    private TextField emailFilter;
    private TextField sexFilter;
    private TextField birthDayFilter;
    private TextField phoneFilter;
    private TextField bloodGroupFilter;
    private TextButton gridFilterButton;

    @Override
    public Widget asWidget() {
        BorderLayoutContainer blc = new BorderLayoutContainer();
        blc.setBorders(false);

        BorderLayoutContainer.BorderLayoutData eastData = new BorderLayoutContainer.BorderLayoutData(.2);
        eastData.setMargins(new Margins(5, 0, 0, 5));
        eastData.setSplit(true);
        eastData.setCollapsible(true);
        eastData.setCollapseMini(true);

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

    private ToolBar initFilterToolBar() {

        idFilter = new TextField();
        idFilter.setEmptyText(App.messages.pn());

        firstNameFilter = new TextField();
        firstNameFilter.setEmptyText(App.messages.firstName());

        lastNameFilter = new TextField();
        lastNameFilter.setEmptyText(App.messages.lastName());

        emailFilter = new TextField();
        emailFilter.setEmptyText(App.messages.emailAddress());

        sexFilter = new TextField();
        sexFilter.setEmptyText(App.messages.gender());

        birthDayFilter = new TextField();
        birthDayFilter.setEmptyText(App.messages.birthday());

        phoneFilter = new TextField();
        phoneFilter.setEmptyText(App.messages.phoneNumber());

        bloodGroupFilter = new TextField();
        bloodGroupFilter.setEmptyText(App.messages.bloodGroup());

        // Filter Button
        gridFilterButton = new TextButton("", Images.INSTANCE.filter());

        ToolBar filterTollBar = new ToolBar();
        filterTollBar.setSpacing(3);

        BoxLayoutContainer.BoxLayoutData flex = new BoxLayoutContainer.BoxLayoutData();
        flex.setFlex(1);

        filterTollBar.add(idFilter, flex);
        filterTollBar.add(firstNameFilter, flex);
        filterTollBar.add(lastNameFilter, flex);
        filterTollBar.add(emailFilter, flex);
        filterTollBar.add(sexFilter, flex);
        filterTollBar.add(birthDayFilter, flex);
        filterTollBar.add(phoneFilter, flex);
        filterTollBar.add(bloodGroupFilter, flex);
        filterTollBar.add(new SeparatorToolItem());
        filterTollBar.add(gridFilterButton);

        return filterTollBar;
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

    @Override
    public void setData(List<UserModel> userModels) {
        allGridItems = userModels;
        grid.getStore().addAll(userModels);
    }

    @Override
    public void add(UserModel userModel) {
        grid.getStore().add(userModel);
    }

    @Override
    public void update(UserModel savedUserModel) {
        grid.getStore().update(savedUserModel);
    }

    private Widget initCenterPanel() {
        VerticalLayoutContainer vlc = new VerticalLayoutContainer();

        ToolBar toolBar = new ToolBar();

        initButtons();

        toolBar.add(addButton);
        toolBar.add(editButton);
        toolBar.add(deleteButton);

        initGrid();

        vlc.add(toolBar, new VerticalLayoutContainer.VerticalLayoutData(1, -1));
        vlc.add(initFilterToolBar(), new VerticalLayoutContainer.VerticalLayoutData(1, -1));
        vlc.add(grid, new VerticalLayoutContainer.VerticalLayoutData(1, 1));

        return vlc;
    }

    private void initButtons() {

        addDoctorButton = new MenuItem(App.messages.addDoctor(), Images.INSTANCE.addDoctor());
        addPatientButton = new MenuItem(App.messages.addPatient(), Images.INSTANCE.addPatient());

        yesDeleteButton = new TextButton();
        addButton = new TextButton(App.messages.add(), Images.INSTANCE.add());
        addButton.setMenu(createMenu());
        editButton = new TextButton(App.messages.edit(), Images.INSTANCE.edit());
        deleteButton = new TextButton(App.messages.delete(), Images.INSTANCE.delete());

        deleteButton.addSelectHandler(new SelectEvent.SelectHandler() {

            @Override
            public void onSelect(final SelectEvent event) {
                ConfirmMessageBox confirmMessageBox = new ConfirmMessageBox(App.messages.confirm(), App.messages.sureDelete());
                confirmMessageBox.addDialogHideHandler(new DialogHideEvent.DialogHideHandler() {
                    @Override
                    public void onDialogHide(DialogHideEvent dialogHideEvent) {
                        if (dialogHideEvent.getHideButton() == Dialog.PredefinedButton.YES)
                            yesDeleteButton.fireEvent(event);
                    }
                });
                confirmMessageBox.show();
            }
        });

    }

    private Menu createMenu() {
        Menu menu = new Menu();
        menu.add(addDoctorButton);
        menu.add(addPatientButton);
        return menu;
    }

    private Widget initDetailsInfo() {
        ContentPanel detailsPanel = new ContentPanel();
        detailsPanel.setBodyBorder(false);
        detailsPanel.setHeadingText(App.messages.detailsInfo());
        detailsPanel.getHeader().setIcon(Images.INSTANCE.info());

        AccordionLayoutContainer con = new AccordionLayoutContainer();
        con.setExpandMode(AccordionLayoutContainer.ExpandMode.SINGLE_FILL);
        detailsPanel.add(con);

        AccordionLayoutContainer.AccordionLayoutAppearance appearance = GWT.<AccordionLayoutContainer.AccordionLayoutAppearance>create(AccordionLayoutContainer.AccordionLayoutAppearance.class);

        bloodTransfusionCP = new ContentPanel(appearance);
        bloodTransfusionCP.setAnimCollapse(false);
        con.add(bloodTransfusionCP);
        bloodGrid = new CustomGrid<BloodTransfusionModel>() {
            @Override
            protected String getCustomValue(BloodTransfusionModel model) {
                return model.getBloodVolume() + " / " + DateTimeFormat.getFormat("dd.MM.yyyy").format(model.getTransfusionDate()) + " / " + model.getComment();
            }
        }.getGrid();
        bloodTransfusionCP.add(bloodGrid);

        allergyCP = new ContentPanel(appearance);
        allergyCP.setAnimCollapse(false);
        con.add(allergyCP);
        allergyGrid = new CustomGrid<CustomerAllergyModel>() {
            @Override
            protected String getCustomValue(CustomerAllergyModel model) {
                return model.getAllergyModel().getName() + " / " + model.getAllergyModel().getComment();
            }
        }.getGrid();
        allergyCP.add(allergyGrid);

        customerSurgeryCP = new ContentPanel(appearance);
        con.add(customerSurgeryCP);
        customerSurgeryGrid = new CustomGrid<CustomerSurgeryModel>() {
            @Override
            protected String getCustomValue(CustomerSurgeryModel model) {
                return model.getSurgeryModel().getSurgeryName();
            }
        }.getGrid();
        customerSurgeryCP.add(customerSurgeryGrid);

        diseaseCP = new ContentPanel(appearance);
        diseaseCP.setAnimCollapse(false);
        con.add(diseaseCP);
        diseaseGrid = new CustomGrid<CustomerDiseaseModel>() {
            @Override
            protected String getCustomValue(CustomerDiseaseModel model) {
                return DateTimeFormat.getFormat("dd.MM.yyyy").format(model.getDetectionDate()) + " / " + model.getDiseaseModel().getName();
            }
        }.getGrid();
        diseaseCP.add(diseaseGrid);


        policeCP = new ContentPanel(appearance);
        policeCP.setAnimCollapse(false);
        con.add(policeCP);
        policeGrid = new CustomGrid<PoliceModel>() {
            @Override
            protected String getCustomValue(PoliceModel model) {
                return model.getPoliceNumber() + " / " + model.getInsuranceCompanyModel().getName();
            }
        }.getGrid();
        policeCP.add(policeGrid);

        clearDetails();

        return detailsPanel;
    }

    private void initGrid() {

        ColumnConfig<UserModel, String> firstNameCol = new ColumnConfig<UserModel, String>(props.firstName(), 50, SafeHtmlUtils.fromTrustedString("<b>" + App.messages.firstName() + "</b>"));
        ColumnConfig<UserModel, String> lastNameCol = new ColumnConfig<UserModel, String>(props.lastName(), 50, App.messages.lastName());
        ColumnConfig<UserModel, String> pnCol = new ColumnConfig<UserModel, String>(props.pn(), 50, App.messages.pn());
        ColumnConfig<UserModel, String> genderCol = new ColumnConfig<UserModel, String>(new ValueProvider<UserModel, String>() {
            @Override
            public String getValue(UserModel userModel) {
                if (userModel.getGender() == 1)
                    return App.messages.male();
                else if (userModel.getGender() == 4)
                    return App.messages.female();
                return "-";
            }

            @Override
            public void setValue(UserModel userModel, String s) {
            }

            @Override
            public String getPath() {
                return "";
            }
        }, 35, App.messages.gender());
        ColumnConfig<UserModel, Date> birthDateCol = new ColumnConfig<UserModel, Date>(props.birthDate(), 50, App.messages.birthday());
        ColumnConfig<UserModel, String> emailAddressCol = new ColumnConfig<UserModel, String>(props.emailAddress(), 50, App.messages.emailAddress());
        ColumnConfig<UserModel, String> phoneNumberCol = new ColumnConfig<UserModel, String>(props.phoneNumber(), 50, App.messages.phoneNumber());
        ColumnConfig<UserModel, Integer> bloodGroupCol = new ColumnConfig<UserModel, Integer>(props.bloodGroup(), 50, App.messages.bloodGroup());
        ColumnConfig<UserModel, Boolean> doctorCol = new ColumnConfig<UserModel, Boolean>(props.doctor(), 35, App.messages.doctor());
        doctorCol.setCell(new SimpleSafeHtmlCell<Boolean>(new AbstractSafeHtmlRenderer<Boolean>() {
            @Override
            public SafeHtml render(Boolean object) {
                return SafeHtmlUtils.fromString(object ? App.messages.yes() : App.messages.no());
            }
        }));
        List<ColumnConfig<UserModel, ?>> columnConfigs = new ArrayList<ColumnConfig<UserModel, ?>>();
        columnConfigs.add(pnCol);
        columnConfigs.add(firstNameCol);
        columnConfigs.add(lastNameCol);
        columnConfigs.add(emailAddressCol);
        columnConfigs.add(genderCol);
        columnConfigs.add(birthDateCol);
        columnConfigs.add(phoneNumberCol);
        columnConfigs.add(bloodGroupCol);
        columnConfigs.add(doctorCol);
        ColumnModel<UserModel> cm = new ColumnModel<UserModel>(columnConfigs);

        ListStore<UserModel> store = new ListStore<UserModel>(props.id());

        grid = new Grid<UserModel>(store, cm);
        grid.getView().setEmptyText(App.messages.noData());
        grid.getView().setAutoExpandColumn(firstNameCol);
        grid.getView().setAutoFill(true);
        grid.getView().setStripeRows(true);
        grid.getView().setColumnLines(true);
        grid.setBorders(false);

        grid.setColumnReordering(true);
    }

    @Override
    public void filter() {
        idFilter.finishEditing();
        firstNameFilter.finishEditing();
        lastNameFilter.finishEditing();
        emailFilter.finishEditing();
        sexFilter.finishEditing();
        birthDayFilter.finishEditing();
        phoneFilter.finishEditing();
        bloodGroupFilter.finishEditing();

        List<UserModel> filteredModels = new ArrayList<UserModel>();
        boolean add;
        for (UserModel model : allGridItems) {
            add = true;
            add &= (idFilter != null ? model.getPn().toLowerCase().contains(idFilter.getValue().toLowerCase()) : add);
            add &= (firstNameFilter != null ? model.getFirstName().toLowerCase().contains(firstNameFilter.getValue().toLowerCase()) : add);
            add &= (lastNameFilter != null ? model.getLastName().toLowerCase().contains(lastNameFilter.getValue().toLowerCase()) : add);
            add &= (emailFilter != null ? model.getEmail().toLowerCase().contains(emailFilter.getValue().toLowerCase()) : add);
//			add &= (sexFilter != null ? model.getGender().toLowerCase().contains(sexFilter.getValue().toLowerCase()) : add);
            add &= (birthDayFilter != null ? model.getBirthDate().toString().toLowerCase().contains(birthDayFilter.getValue().toLowerCase()) : add);
            add &= (phoneFilter != null ? model.getPhoneNumber().toLowerCase().contains(phoneFilter.getValue().toLowerCase()) : add);
            add &= (bloodGroupFilter != null ? model.getBloodGroup().toString().toLowerCase().contains(bloodGroupFilter.getValue().toLowerCase()) : add);

            if (add) {
                filteredModels.add(model);
            }
        }
        grid.getStore().clear();
        grid.getStore().addAll(filteredModels);
    }

    @Override
    public SelectEvent.HasSelectHandlers getFilterButton() {
        return gridFilterButton;
    }

    @Override
    public HasSelectionHandlers<Item> getAddDoctorButton() {
        return addDoctorButton;
    }

    @Override
    public HasSelectionHandlers<Item> getAddPatientButton() {
        return addPatientButton;
    }

    @Override
    public void setLoadMask(boolean mask) {
        if (mask) {
            grid.mask(App.messages.loading());
        } else {
            grid.unmask();
        }
    }

    @Override
    public SelectionChangedEvent.HasSelectionChangedHandlers<UserModel> getGridSelectionChangeHandler() {
        return grid.getSelectionModel();
    }

    @Override
    public void showDetailsInfo(UserModel userModel) {
        clearDetails();

        bloodTransfusionCP.setHeadingText(App.messages.bloodTransfusion() + ": " + userModel.getBloodTransfusionModels().size());
        bloodGrid.getStore().addAll(userModel.getBloodTransfusionModels());

        allergyCP.setHeadingText(App.messages.allergy() + ": " + userModel.getCustomerAllergyModels().size());
        allergyGrid.getStore().addAll(userModel.getCustomerAllergyModels());

        customerSurgeryCP.setHeadingText(App.messages.surgery() + ": " + userModel.getCustomerSurgeryModels().size());
        customerSurgeryGrid.getStore().addAll(userModel.getCustomerSurgeryModels());

        diseaseCP.setHeadingText(App.messages.disease() + ": " + userModel.getCustomerDiseaseModels().size());
        diseaseGrid.getStore().addAll(userModel.getCustomerDiseaseModels());

        policeCP.setHeadingText(App.messages.police() + ": " + userModel.getCustomerPoliceModels().size());
        policeGrid.getStore().addAll(userModel.getCustomerPoliceModels());
    }

    @Override
    public void clearDetails() {
        bloodGrid.getStore().clear();
        allergyGrid.getStore().clear();
        customerSurgeryGrid.getStore().clear();
        diseaseGrid.getStore().clear();
        policeGrid.getStore().clear();

        bloodTransfusionCP.setHeadingText(App.messages.bloodTransfusion());
        allergyCP.setHeadingText(App.messages.allergy());
        customerSurgeryCP.setHeadingText(App.messages.surgery());
        diseaseCP.setHeadingText(App.messages.disease());
        policeCP.setHeadingText(App.messages.police());
    }

}
