package ge.tsu.client.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.cell.core.client.form.ComboBoxCell;
import com.sencha.gxt.core.client.dom.ScrollSupport;
import com.sencha.gxt.core.client.util.Margins;
import com.sencha.gxt.data.shared.LabelProvider;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.widget.core.client.Dialog;
import com.sencha.gxt.widget.core.client.FramedPanel;
import com.sencha.gxt.widget.core.client.box.ConfirmMessageBox;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.AbstractHtmlLayoutContainer;
import com.sencha.gxt.widget.core.client.container.HtmlLayoutContainer;
import com.sencha.gxt.widget.core.client.container.MarginData;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.event.DialogHideEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.form.*;
import ge.tsu.client.App;
import ge.tsu.client.images.Images;
import ge.tsu.client.presenter.Form200_7Presenter;
import ge.tsu.shared.UserModel;
import ge.tsu.shared.UserModelProperties;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vako on 19/06/14.
 */
public class Form200_7View implements Form200_7Presenter.Display {

    private int FORM_WIDTH = (int) (Window.getClientWidth() * 0.7);
    UserModelProperties props = GWT.create(UserModelProperties.class);

    private FramedPanel panel;
    private TextButton saveButton;
    private TextButton yesSaveButton;
    private ComboBox<UserModel> usersCombo;
    private TextField division;
    private TextField lastName;
    private DateField endDate;
    private DateField startDate;
    private TextArea recommendation;
    private TextField policeNumber;
    private TextField signature;
    private ComboBox<UserModel> doctorsCombo;


    @Override
    public Widget asWidget() {
        panel = new FramedPanel();
        panel.setHeaderVisible(false);
        panel.setBorders(false);
//        panel.setBodyStyle("background: none;");

        createForm();
        createButtons();

        panel.addButton(saveButton);

        return panel;
    }

    @Override
    public void setUsersLoadMask(boolean mask) {
        if (mask) {
            usersCombo.mask(App.messages.loading());
        } else {
            usersCombo.unmask();
        }
    }

    @Override
    public void setComboData(List<UserModel> userModels) {
        usersCombo.getStore().addAll(userModels);
        usersCombo.focus();

        List<UserModel> doctors = new ArrayList<UserModel>();
        for (UserModel userModel : userModels) {
            if (userModel.isDoctor()) {
                doctors.add(userModel);
            }
        }
        doctorsCombo.getStore().addAll(doctors);
    }

    private void createForm() {
        int cw = (FORM_WIDTH / 2) - 80;
        int rw = FORM_WIDTH - 35;

        VerticalLayoutContainer vlc = new VerticalLayoutContainer();
        vlc.setScrollMode(ScrollSupport.ScrollMode.AUTOY);

        HtmlLayoutContainer con = new HtmlLayoutContainer(getTableMarkup());
        vlc.add(con, new VerticalLayoutContainer.VerticalLayoutData(1, 1));

        panel.add(vlc, new MarginData(new Margins(10, 5, 5, 20)));

//        VerticalLayoutContainer con = new VerticalLayoutContainer();
//        panel.add(con);

        ListStore<UserModel> usersStore = new ListStore<UserModel>(props.id());

        usersCombo = new ComboBox<UserModel>(usersStore, new LabelProvider<UserModel>() {
            @Override
            public String getLabel(UserModel model) {
                return model.getFirstName() + " " + model.getLastName() + " / " + model.getPn();
            }
        });
        usersCombo.setAllowBlank(false);
        usersCombo.setForceSelection(true);
        usersCombo.setTriggerAction(ComboBoxCell.TriggerAction.ALL);
        usersCombo.setWidth(rw);
        usersCombo.setEmptyText(App.messages.select() + " " + App.messages.patient());

        con.add(new FieldLabel(usersCombo, App.messages.patient()), new AbstractHtmlLayoutContainer.HtmlData(".user"));

        division = new TextField();
        division.setWidth(cw);
//        division.setToolTip(App.messages.fillsAutomatically());
        con.add(new FieldLabel(division, App.messages.division()), new AbstractHtmlLayoutContainer.HtmlData(".G"));

//        lastName = new TextField();
//        lastName.setWidth(cw);
//        lastName.setToolTip(App.messages.fillsAutomatically());
//        con.add(new FieldLabel(lastName, App.messages.lastName()), new AbstractHtmlLayoutContainer.HtmlData(".ln"));


        policeNumber = new TextField();
        policeNumber.setWidth(cw);
        con.add(new FieldLabel(policeNumber, App.messages.policeNumber()), new AbstractHtmlLayoutContainer.HtmlData(".police"));

        startDate = new DateField();
        startDate.setWidth(cw);
//        startDate.setToolTip(App.messages.fillsAutomatically());
        con.add(new FieldLabel(startDate, App.messages.beginningDate()), new AbstractHtmlLayoutContainer.HtmlData(".startDate"));

        endDate = new DateField();
        endDate.setWidth(cw);
//        endDate.setToolTip(App.messages.fillsAutomatically());
        con.add(new FieldLabel(endDate, App.messages.endDate()), new AbstractHtmlLayoutContainer.HtmlData(".endDate"));

        recommendation = new TextArea();
        recommendation.setWidth(rw);
        recommendation.setHeight(60);
//        recommendation.setToolTip(App.messages.fillsAutomatically());
        con.add(new FieldLabel(recommendation, App.messages.recommendation() + " / " + App.messages.diagnose()), new AbstractHtmlLayoutContainer.HtmlData(".recommendation"));


        signature = new TextField();
        signature.setWidth(cw);
//        signature.setToolTip(App.messages.fillsAutomatically());
        con.add(new FieldLabel(signature, App.messages.bloodGroup()), new AbstractHtmlLayoutContainer.HtmlData(".sign"));


        ListStore<UserModel> doctorsStore = new ListStore<UserModel>(props.id());
        doctorsCombo = new ComboBox<UserModel>(doctorsStore, new LabelProvider<UserModel>() {
            @Override
            public String getLabel(UserModel model) {
                return model.getFirstName() + " " + model.getLastName() + " / " + model.getPn();
            }
        });
        doctorsCombo.setAllowBlank(false);
        doctorsCombo.setForceSelection(true);
        doctorsCombo.setTriggerAction(ComboBoxCell.TriggerAction.ALL);
        doctorsCombo.setWidth(cw);
        doctorsCombo.setEmptyText(App.messages.select() + " " + App.messages.doctor());
        con.add(new FieldLabel(doctorsCombo, App.messages.insuranceCompany()), new AbstractHtmlLayoutContainer.HtmlData(".doctor"));


        // need to call after everything is constructed
        List<FieldLabel> labels = FormPanelHelper.getFieldLabels(panel);
        for (FieldLabel lbl : labels) {
            lbl.setLabelAlign(FormPanel.LabelAlign.TOP);
        }
    }

    private native String getTableMarkup() /*-{
        return [ '<table width=100% cellpadding=0 cellspacing=0>',
            '<tr><td class=user colspan=2></td></tr>',
            '<tr><td class=G width=50%></td><td class=police width=50%></td></tr>',
            '<tr><td class=startDate></td><td class=endDate></td></tr>',
            '<tr><td class=recommendation colspan=2></td></tr>',
            '<tr><td class=doctor></td><td class=sign></td></tr>', '</table>'

        ].join("");
    }-*/;

    private void createButtons() {

        saveButton = new TextButton(App.messages.save(), Images.INSTANCE.save());

        yesSaveButton = new TextButton();

        saveButton.addSelectHandler(new SelectEvent.SelectHandler() {
            @Override
            public void onSelect(final SelectEvent event) {
                ConfirmMessageBox confirmMessageBox = new ConfirmMessageBox(App.messages.confirm(), App.messages.sureSave() + " " + App.messages.formMenu() + " 200 - 7 ?");
                confirmMessageBox.getButton(Dialog.PredefinedButton.YES).setText(App.messages.yes());
                confirmMessageBox.getButton(Dialog.PredefinedButton.NO).setText(App.messages.no());
                confirmMessageBox.addDialogHideHandler(new DialogHideEvent.DialogHideHandler() {
                    @Override
                    public void onDialogHide(DialogHideEvent dialogHideEvent) {
                        if (dialogHideEvent.getHideButton() == Dialog.PredefinedButton.YES)
                            yesSaveButton.fireEvent(event);
                    }
                });
                confirmMessageBox.show();
            }
        });
    }
}
