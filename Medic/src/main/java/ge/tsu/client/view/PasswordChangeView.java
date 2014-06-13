package ge.tsu.client.view;

import com.sencha.gxt.core.client.util.Margins;
import com.sencha.gxt.widget.core.client.Window;
import com.sencha.gxt.widget.core.client.box.MessageBox;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.MarginData;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.form.FieldLabel;
import com.sencha.gxt.widget.core.client.form.TextField;
import com.sencha.gxt.widget.core.client.form.validator.MaxLengthValidator;
import com.sencha.gxt.widget.core.client.form.validator.MinLengthValidator;
import ge.tsu.client.App;
import ge.tsu.client.images.Images;
import ge.tsu.client.presenter.PasswordChangePresenter;

/**
 * Created with IntelliJ IDEA.
 * User: Vako
 * Date: 11/27/13
 * Time: 12:38 PM
 */
public class PasswordChangeView implements PasswordChangePresenter.Display {

    private Window window;
    private TextField password;
    private TextField confirmPassword;
    private TextButton saveButton;


    @Override
    public void asWidget() {
        window = new Window();
        window.setHeadingHtml("<div align=\"center\">" + App.messages.changePassword() + "</div>");
        window.setBodyStyleName("pad-text");
        window.getBody().addClassName("pad-text");
        window.setModal(true);
        window.setBlinkModal(true);
        window.setAnimCollapse(true);
        window.setSize("300px", "170px");

        VerticalLayoutContainer vlc = new VerticalLayoutContainer();

        password = new TextField();
        password.addValidator(new MinLengthValidator(4));
        password.addValidator(new MaxLengthValidator(20));
        FieldLabel passwordFieldLabel = new FieldLabel(password, App.messages.newPassword());
        vlc.add(passwordFieldLabel, new VerticalLayoutContainer.VerticalLayoutData(1, -1));

        confirmPassword = new TextField();
        FieldLabel confirmPasswordFieldLabel = new FieldLabel(confirmPassword, App.messages.confirmPassword());
        vlc.add(confirmPasswordFieldLabel, new VerticalLayoutContainer.VerticalLayoutData(1, -1));

        saveButton = new TextButton(App.messages.save(), Images.INSTANCE.save());
        vlc.add(saveButton, new VerticalLayoutContainer.VerticalLayoutData(-1, -1, new Margins(5, 0, 0, 120)));

        window.add(vlc, new MarginData(10));
        window.show();
    }

    @Override
    public SelectEvent.HasSelectHandlers getSaveButtonHandler() {
        return saveButton;
    }

    @Override
    public String getNewPassword() {
        password.finishEditing();
        return password.getValue();
    }

    @Override
    public boolean passwordsMatch() {
        password.finishEditing();
        confirmPassword.finishEditing();
        return password.getValue().equals(confirmPassword.getValue());
    }

    @Override
    public void confirmAndHide() {
        window.hide();
        new MessageBox(App.messages.success(), App.messages.passwordChanged()).show();
    }

    @Override
    public boolean validPassword() {
        if (password.getValue().length() < 4 || password.getValue().length() > 20)
            return false;
        return true;
    }
}
