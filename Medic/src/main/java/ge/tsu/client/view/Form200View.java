package ge.tsu.client.view;

import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.i18n.client.DateTimeFormat.PredefinedFormat;
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.core.client.util.DateWrapper;
import com.sencha.gxt.core.client.util.ToggleGroup;
import com.sencha.gxt.widget.core.client.FramedPanel;
import com.sencha.gxt.widget.core.client.Slider;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.CenterLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer.VerticalLayoutData;
import com.sencha.gxt.widget.core.client.event.ParseErrorEvent;
import com.sencha.gxt.widget.core.client.event.ParseErrorEvent.ParseErrorHandler;
import com.sencha.gxt.widget.core.client.form.*;
import com.sencha.gxt.widget.core.client.form.validator.MinDateValidator;
import com.sencha.gxt.widget.core.client.form.validator.MinLengthValidator;
import com.sencha.gxt.widget.core.client.info.Info;
import ge.tsu.client.presenter.Form200Presenter;

import java.util.Date;

/**
 * Created by vako on 29/05/14.
 */
public class Form200View implements Form200Presenter.Display {

    private CenterLayoutContainer container;

    @Override
    public Widget asWidget() {
        container = new CenterLayoutContainer();
        container.setBorders(false);
        createForm();
        return container;
    }

    private void createForm() {
        FramedPanel panel = new FramedPanel();
        panel.setHeaderVisible(false);
        panel.setBorders(false);
        panel.setWidth(800);
        panel.setBodyStyle("background: none;");

        VerticalLayoutContainer p = new VerticalLayoutContainer();
        panel.add(p);

        TextField firstName = new TextField();
        firstName.setAllowBlank(false);
        firstName.setEmptyText("Enter your name...");
        firstName.addValueChangeHandler(new ValueChangeHandler<String>() {
            @Override
            public void onValueChange(ValueChangeEvent<String> event) {
                Info.display("Value Changed", "First name changed to " + event.getValue() == null ? "blank" : event.getValue());
            }
        });
        p.add(new FieldLabel(firstName, "Name"), new VerticalLayoutData(1, -1));

        TextField email = new TextField();
        email.setAllowBlank(false);
        p.add(new FieldLabel(email, "Email"), new VerticalLayoutData(1, -1));

        PasswordField password = new PasswordField();
        p.add(new FieldLabel(password, "Password"), new VerticalLayoutData(1, -1));

        IntegerField age = new IntegerField();
        age.addParseErrorHandler(new ParseErrorHandler() {

            @Override
            public void onParseError(ParseErrorEvent event) {
                Info.display("Parse Error", event.getErrorValue() + " could not be parsed as a number");
            }
        });
        age.setAllowBlank(false);

        p.add(new FieldLabel(age, "Age"), new VerticalLayoutData(1, -1));

        DateField date = new DateField();
        date.addParseErrorHandler(new ParseErrorHandler() {

            @Override
            public void onParseError(ParseErrorEvent event) {
                Info.display("Parse Error", event.getErrorValue() + " could not be parsed as a date");
            }
        });

        date.addValueChangeHandler(new ValueChangeHandler<Date>() {

            @Override
            public void onValueChange(ValueChangeEvent<Date> event) {
                String v = event.getValue() == null ? "nothing"
                        : DateTimeFormat.getFormat(PredefinedFormat.DATE_MEDIUM).format(event.getValue());
                Info.display("Selected", "You selected " + v);

            }
        });
        date.addValidator(new MinDateValidator(new Date()));
        p.add(new FieldLabel(date, "Birthday"), new VerticalLayoutData(1, -1));

        TimeField time = new TimeField();
        time.setFormat(DateTimeFormat.getFormat("hh:mm a"));
        time.addParseErrorHandler(new ParseErrorHandler() {

            @Override
            public void onParseError(ParseErrorEvent event) {
                Info.display("Parse Error", event.getErrorValue() + " could not be parsed as a valid time");
            }
        });

        time.setMinValue(new DateWrapper().clearTime().addHours(8).asDate());
        time.setMaxValue(new DateWrapper().clearTime().addHours(18).addSeconds(1).asDate());
        p.add(new FieldLabel(time, "Time"), new VerticalLayoutData(1, -1));

        Slider slider = new Slider();
        slider.setMinValue(40);
        slider.setMaxValue(90);
        slider.setValue(0);
        slider.setIncrement(5);
        slider.setMessage("{0} inches tall");
        p.add(new FieldLabel(slider, "Size"), new VerticalLayoutData(1, -1));

        ValueChangeHandler<Boolean> musicHandler = new ValueChangeHandler<Boolean>() {

            @Override
            public void onValueChange(ValueChangeEvent<Boolean> event) {
                CheckBox check = (CheckBox)event.getSource();
                Info.display("Music Changed", check.getBoxLabel() + " " + (event.getValue() ? "selected" : "not selected"));
            }
        };

        CheckBox check1 = new CheckBox();
        check1.setEnabled(false);
        check1.setBoxLabel("Classical");
        check1.addValueChangeHandler(musicHandler);

        CheckBox check2 = new CheckBox();
        check2.setBoxLabel("Rock");
        check2.addValueChangeHandler(musicHandler);
        check2.setValue(true);

        CheckBox check3 = new CheckBox();
        check3.setBoxLabel("Blues");
        check3.addValueChangeHandler(musicHandler);

        HorizontalPanel hp = new HorizontalPanel();
        hp.add(check1);
        hp.add(check2);
        hp.add(check3);

        p.add(new FieldLabel(hp, "Music"));

        Radio radio = new Radio();
        radio.setBoxLabel("Red");

        Radio radio2 = new Radio();
        radio2.setBoxLabel("Blue");
        radio2.setValue(true);

        hp = new HorizontalPanel();
        hp.add(radio);
        hp.add(radio2);

        p.add(new FieldLabel(hp, "Color"));

        // we can set name on radios or use toggle group
        ToggleGroup toggle = new ToggleGroup();
        toggle.add(radio);
        toggle.add(radio2);
        toggle.addValueChangeHandler(new ValueChangeHandler<HasValue<Boolean>>() {

            @Override
            public void onValueChange(ValueChangeEvent<HasValue<Boolean>> event) {
                ToggleGroup group = (ToggleGroup)event.getSource();
                Radio radio = (Radio)group.getValue();
                Info.display("Color Changed", "You selected " + radio.getBoxLabel());
            }
        });

        TextArea description = new TextArea();
        description.setAllowBlank(false);
        description.addValidator(new MinLengthValidator(10));
        p.add(new FieldLabel(description, "Description"), new VerticalLayoutData(1, 100));

        final DoubleSpinnerField spinnerField = new DoubleSpinnerField();
        spinnerField.setIncrement(.1d);
        spinnerField.setMinValue(-10d);
        spinnerField.setMaxValue(10d);
        spinnerField.setAllowNegative(true);
        spinnerField.setAllowBlank(false);
        spinnerField.getPropertyEditor().setFormat(NumberFormat.getFormat("0.0"));
        spinnerField.addValueChangeHandler(new ValueChangeHandler<Double>() {

            @Override
            public void onValueChange(ValueChangeEvent<Double> event) {
                Info.display("Duration Changed",
                        "Duration changed to " + event.getValue() == null ? "nothing" : event.getValue() + "");
            }
        });
        spinnerField.addSelectionHandler(new SelectionHandler<Double>() {

            @Override
            public void onSelection(SelectionEvent<Double> event) {
                String msg = "nothing";
                if (event.getSelectedItem() != null) {
                    msg = spinnerField.getPropertyEditor().render(event.getSelectedItem());
                }

                Info.display("Spin Change", "Current value changed to " + msg);
            }
        });

        FieldLabel spinLabel = new FieldLabel(spinnerField, "Duration(s)");

        p.add(spinLabel, new VerticalLayoutData(1, -1));

        panel.addButton(new TextButton("Save"));
        panel.addButton(new TextButton("Cancel"));

        container.add(panel);
    }

}