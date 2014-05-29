package ge.tsu.shared;

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
	private int menuNumber;

	public MenuModel() {
	}

	public MenuModel(String code, String label, boolean root, int menuNumber) {
		this.code = code;
		this.label = label;
		this.root = root;
		this.menuNumber = menuNumber;
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

	public int getMenuNumber() {
		return menuNumber;
	}

	public void setMenuNumber(int menuNumber) {
		this.menuNumber = menuNumber;
	}
}
