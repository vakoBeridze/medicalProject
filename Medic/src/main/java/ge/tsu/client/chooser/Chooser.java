package ge.tsu.client.chooser;

import com.sencha.gxt.core.client.util.Margins;
import com.sencha.gxt.widget.core.client.FramedPanel;
import com.sencha.gxt.widget.core.client.Window;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.MarginData;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import ge.tsu.client.App;

/**
 * Created by Vako on 08.06.2014.
 */
public class Chooser {

    protected TextButton clearButton;
    protected TextButton chooseButton;
    protected Window window;
    protected VerticalLayoutContainer vlc;

    public Chooser() {
        window = new Window();
        window.setPixelSize(400, 250);
        window.setModal(true);
        window.setBlinkModal(true);

        FramedPanel panel = new FramedPanel();
        panel.setHeaderVisible(false);
        panel.setBorders(false);

        vlc = new VerticalLayoutContainer();
        panel.add(vlc);

        clearButton = new TextButton(App.messages.clear());
        panel.addButton(clearButton);

        chooseButton = new TextButton(App.messages.choose());
        panel.addButton(chooseButton);

        window.add(panel, new MarginData(new Margins(10, 10, 2, 10)));
    }
}
