package ge.tsu.client.view;

import com.sencha.gxt.widget.core.client.ContentPanel;
import com.sencha.gxt.widget.core.client.Window;
import com.sencha.gxt.widget.core.client.box.AlertMessageBox;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import ge.tsu.client.App;
import ge.tsu.client.presenter.EditUserPresenter;
import ge.tsu.shared.UserModel;

/**
 * Created by vako on 30/05/14.
 */
public class EditUserView implements EditUserPresenter.Display {

    private TextButton saveButton;
    private Window window;

    @Override
    public void asWidget() {
        // TODO
        window = new Window();
        window.setPixelSize(300, 500);
        window.setModal(true);
        window.setBlinkModal(true);
        window.setHeadingText("Hello Window");


        ContentPanel panel = new ContentPanel();
        panel.setHeaderVisible(false);

        saveButton = new TextButton(App.messages.save());

        panel.addButton(saveButton);


        window.add(panel);

        window.show();

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
    public void setModel(UserModel selectedUser) {
        // TODO
    }
}
