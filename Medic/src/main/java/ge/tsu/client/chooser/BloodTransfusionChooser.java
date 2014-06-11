package ge.tsu.client.chooser;

import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.form.DateField;
import com.sencha.gxt.widget.core.client.form.FieldLabel;
import com.sencha.gxt.widget.core.client.form.TextArea;
import com.sencha.gxt.widget.core.client.form.TextField;
import com.sencha.gxt.widget.core.client.info.Info;
import ge.tsu.client.App;
import ge.tsu.client.presenter.Form200Presenter;

import java.util.Date;

/**
 * Created by Vako on 08.06.2014.
 */
public class BloodTransfusionChooser extends Chooser {

    Form200Presenter.Display display;

    private DateField transfusionDate;
    private TextField bloodVolume;
    private TextArea comment;

    public BloodTransfusionChooser(final Form200Presenter.Display display) {
        super();
        this.display = display;

        window.setHeadingText(App.messages.bloodTransfusion());
        createForm();
        window.show();

        chooseButton.addSelectHandler(new SelectEvent.SelectHandler() {
            @Override
            public void onSelect(SelectEvent selectEvent) {
                Info.display("BloodTransfusionChooser", "Choose");
                display.setBloodTransfusion(transfusionDate.getValue(), bloodVolume.getValue(), comment.getValue());
                window.hide();
            }
        });

        clearButton.addSelectHandler(new SelectEvent.SelectHandler() {
            @Override
            public void onSelect(SelectEvent selectEvent) {
                display.clearBloodTransfusion();
                window.hide();
            }
        });
    }

    private void createForm() {
        VerticalLayoutContainer.VerticalLayoutData layoutData = new VerticalLayoutContainer.VerticalLayoutData(1, -1);

        transfusionDate = new DateField();
        vlc.add(new FieldLabel(transfusionDate, App.messages.transfusionDate()), layoutData);

        bloodVolume = new TextField();
        vlc.add(new FieldLabel(bloodVolume, App.messages.bloodVolume()), layoutData);

        comment = new TextArea();
        vlc.add(new FieldLabel(comment, App.messages.comment()), layoutData);
    }

    public void setData(Date bloodTransfusionDate, String bloodTransfusionVolume, String bloodTransfusionComment) {
        transfusionDate.setValue(bloodTransfusionDate);
        bloodVolume.setValue(bloodTransfusionVolume);
        comment.setValue(bloodTransfusionComment);
    }
}
