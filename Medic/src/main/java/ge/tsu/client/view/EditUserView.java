package ge.tsu.client.view;

import com.google.gwt.user.client.ui.HorizontalPanel;
import com.sencha.gxt.core.client.util.ToggleGroup;
import com.sencha.gxt.widget.core.client.FramedPanel;
import com.sencha.gxt.widget.core.client.Window;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.MarginData;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.event.ParseErrorEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.form.*;
import com.sencha.gxt.widget.core.client.form.validator.MaxDateValidator;
import com.sencha.gxt.widget.core.client.form.validator.MinLengthValidator;
import com.sencha.gxt.widget.core.client.info.Info;
import ge.tsu.client.App;
import ge.tsu.client.presenter.EditUserPresenter;
import ge.tsu.shared.UserModel;

import java.util.Date;

/**
 * Created by vako on 30/05/14.
 */
public class EditUserView implements EditUserPresenter.Display {

    private UserModel userModel;

    private TextButton saveButton;
    private Window window;
    private TextField firstName;
    private TextField lastName;
    private TextField fatherName;
    private Radio maleRadio;
    private TextField email;
    private PasswordField password;
    private TextField license;
    private TextField pn;
    private IntegerField phoneNumber;
    private DateField birthDate;
    private TextArea professionAndJob;
    private Radio r1;
    private Radio r2;
    private Radio r3;
    private Radio r4;
    private Radio femaleRadio;

    public EditUserView(UserModel userModel) {
        this.userModel = userModel;
    }

    @Override
    public void asWidget() {
        window = new Window();
        window.setPixelSize(500, 470);
        window.setModal(true);
        window.setBlinkModal(true);
        window.setHeadingText(this.userModel.getId() == 0 ? App.messages.addUser() : App.messages.editUser());

        FramedPanel panel = new FramedPanel();
        initForm(panel);

        fillForm();

        saveButton = new TextButton(App.messages.save());
        panel.addButton(saveButton);

        window.add(panel, new MarginData(10));

        window.show();
    }

    private void fillForm() {
        firstName.setValue(this.userModel.getFirstName() == null ? "" : this.userModel.getFirstName());
        lastName.setValue(this.userModel.getLastName() == null ? "" : this.userModel.getLastName());
        email.setValue(this.userModel.getEmail() == null ? "" : this.userModel.getEmail());
        fatherName.setValue(this.userModel.getFatherName() == null ? "" : this.userModel.getFatherName());
        if (this.userModel.isDoctor()) {
            license.setValue(this.userModel.getLicense() == null ? "" : this.userModel.getLicense());
            window.setHeight(490);
        }
        setGender(this.userModel.getGender() == null ? 0 : this.userModel.getGender());
        birthDate.setValue(this.userModel.getBirthDate());
//        phoneNumber.setValue(Integer.valueOf(this.userModel.getPhoneNumber() == null ? "0" : this.userModel.getPhoneNumber()));
        phoneNumber.setValue((this.userModel.getPhoneNumber() == null || this.userModel.getPhoneNumber().equals("")) ? null : Integer.valueOf(this.userModel.getPhoneNumber()));
        pn.setValue(this.userModel.getPn() == null ? "" : this.userModel.getPn());
        professionAndJob.setValue(this.userModel.getProfessionAndJob() == null ? "" : this.userModel.getProfessionAndJob());
        setBloodGroup(this.userModel.getBloodGroup() == null ? 0 : this.userModel.getBloodGroup());
    }

    private void setGender(int gender) {
        maleRadio.setValue(false);
        femaleRadio.setValue(false);
        switch (gender) {
            case 1:
                maleRadio.setValue(true);
                break;
            case 4:
                femaleRadio.setValue(true);
                break;
        }
    }

    private void setBloodGroup(int bloodGroup) {
        r1.setValue(false);
        r2.setValue(false);
        r3.setValue(false);
        r4.setValue(false);
        switch (bloodGroup) {
            case 1:
                r1.setValue(true);
                break;
            case 2:
                r2.setValue(true);
                break;
            case 3:
                r3.setValue(true);
                break;
            case 4:
                r4.setValue(true);
                break;
        }
    }

    private void initForm(FramedPanel panel) {
        panel.setHeaderVisible(false);
        panel.setBorders(false);
        panel.setBodyStyle("background: none;");

        VerticalLayoutContainer vlc = new VerticalLayoutContainer();
        panel.add(vlc);

        VerticalLayoutContainer.VerticalLayoutData layoutData = new VerticalLayoutContainer.VerticalLayoutData(1, -1);

        firstName = new TextField();
        firstName.setAllowBlank(false);
        vlc.add(new FieldLabel(firstName, App.messages.firstName()), layoutData);

        lastName = new TextField();
        lastName.setAllowBlank(false);
        vlc.add(new FieldLabel(lastName, App.messages.lastName()), layoutData);

        fatherName = new TextField();
        fatherName.setAllowBlank(false);
        vlc.add(new FieldLabel(fatherName, App.messages.fatherName()), layoutData);

        maleRadio = new Radio();
        maleRadio.setBoxLabel(App.messages.male());
        maleRadio.setValue(true);
        femaleRadio = new Radio();
        femaleRadio.setBoxLabel(App.messages.female());
        HorizontalPanel hp = new HorizontalPanel();
        hp.add(maleRadio);
        hp.add(femaleRadio);
        vlc.add(new FieldLabel(hp, App.messages.gender()));
        // we can set name on radios or use toggle group
        ToggleGroup toggle = new ToggleGroup();
        toggle.add(maleRadio);
        toggle.add(femaleRadio);

        email = new TextField();
        email.setAllowBlank(false);
        vlc.add(new FieldLabel(email, App.messages.emailAddress()), layoutData);

        if (this.userModel.isDoctor()) {
            password = new PasswordField();
            vlc.add(new FieldLabel(password, App.messages.password()), layoutData);

            license = new TextField();
            license.setAllowBlank(false);
            vlc.add(new FieldLabel(license, App.messages.license()), layoutData);

        }

        pn = new TextField();
        pn.setAllowBlank(false);
        vlc.add(new FieldLabel(pn, App.messages.pn()), layoutData);

        phoneNumber = new IntegerField();
        phoneNumber.setEmptyText(App.messages.example() + " 555330455");
        phoneNumber.addParseErrorHandler(new ParseErrorEvent.ParseErrorHandler() {

            @Override
            public void onParseError(ParseErrorEvent event) {
                Info.display("Parse Error", event.getErrorValue() + " could not be parsed as a number");
            }
        });
        phoneNumber.setAllowBlank(false);
        vlc.add(new FieldLabel(phoneNumber, App.messages.phoneNumber()), layoutData);

        birthDate = new DateField();
        birthDate.addParseErrorHandler(new ParseErrorEvent.ParseErrorHandler() {

            @Override
            public void onParseError(ParseErrorEvent event) {
                Info.display("Parse Error", event.getErrorValue() + " could not be parsed as a date");
            }
        });

        birthDate.addValidator(new MaxDateValidator(new Date()));
        vlc.add(new FieldLabel(birthDate, App.messages.birthday()), layoutData);

        professionAndJob = new TextArea();
        professionAndJob.setAllowBlank(false);
        professionAndJob.addValidator(new MinLengthValidator(10));
        vlc.add(new FieldLabel(professionAndJob, App.messages.professionAndJob()), new VerticalLayoutContainer.VerticalLayoutData(1, 60));


        r1 = new Radio();
        r1.setBoxLabel(App.messages.roman1());
        r1.setValue(true);

        r2 = new Radio();
        r2.setBoxLabel(App.messages.roman2());

        r3 = new Radio();
        r3.setBoxLabel(App.messages.roman3());

        r4 = new Radio();
        r4.setBoxLabel(App.messages.roman4());

        HorizontalPanel bloodHP = new HorizontalPanel();
        bloodHP.add(r1);
        bloodHP.add(r2);
        bloodHP.add(r3);
        bloodHP.add(r4);

        vlc.add(new FieldLabel(bloodHP, App.messages.bloodGroup()));
        // we can set name on radios or use toggle group
        ToggleGroup bloodToggle = new ToggleGroup();
        bloodToggle.add(r1);
        bloodToggle.add(r2);
        bloodToggle.add(r3);
        bloodToggle.add(r4);
    }

    @Override
    public SelectEvent.HasSelectHandlers getSaveButton() {
        return saveButton;
    }

    @Override
    public void close() {
        window.hide();
    }

    @Override
    public UserModel getModel() {
        this.userModel.setFirstName(firstName.getValue());
        this.userModel.setLastName(lastName.getValue());
        this.userModel.setEmail(email.getValue());
        this.userModel.setFatherName(fatherName.getValue());
        this.userModel.setUserName(email.getValue());
        this.userModel.setPhoneNumber(phoneNumber.getValue().toString());
        this.userModel.setBirthDate(birthDate.getValue() == null ? new Date() : birthDate.getValue());
        this.userModel.setPn(pn.getValue());
        this.userModel.setProfessionAndJob(professionAndJob.getValue());
        this.userModel.setGender(maleRadio.getValue() ? 1 : 4);
        if (this.userModel.isDoctor()) {
            this.userModel.setPassword(password.getValue() == null || password.getValue().equals("") ? this.userModel.getPassword() : password.getValue());
            this.userModel.setLicense(license.getValue());
        }

        int bloodGroup = 0;
        if (r1.getValue()) {
            bloodGroup = 1;
        } else if (r2.getValue()) {
            bloodGroup = 2;
        } else if (r3.getValue()) {
            bloodGroup = 3;
        } else if (r4.getValue()) {
            bloodGroup = 4;
        }
        this.userModel.setBloodGroup(bloodGroup);

        return this.userModel;
    }

    @Override
    public void setSaveButtonEnabled(boolean enabled) {
        saveButton.setEnabled(enabled);
    }

    @Override
    public void validateEmail() {
        email.setValue("");
        email.validate();
    }
}
