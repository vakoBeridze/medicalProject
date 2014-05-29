package ge.tsu.shared;

import ge.tsu.client.Menu;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: Vako
 * Date: 25.05.2014
 * Time: 14:31
 */
public class MenuModel implements Serializable {
    private String code;
    private String label;
    private boolean root;
    private Menu menu;

    public MenuModel() {
    }

    public MenuModel(String code, String label, boolean root, Menu menu) {
        this.code = code;
        this.label = label;
        this.root = root;
        this.menu = menu;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public boolean isRoot() {
        return root;
    }

    public void setRoot(boolean root) {
        this.root = root;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }
}
