package ge.tsu.client.view;

import com.google.gwt.user.client.ui.Widget;

/**
 * Created with IntelliJ IDEA.
 * User: Vako
 * Date: 26.05.2014
 * Time: 15:00
 */
public class Tab extends Widget {
	private int tabCode;
	private Widget widget;

	public Tab(int tabCode, Widget widget) {
		this.tabCode = tabCode;
		this.widget = widget;
	}

	public Tab() {
	}

	public int getTabCode() {
		return tabCode;
	}

	public void setTabCode(int tabCode) {
		this.tabCode = tabCode;
	}

	public Widget getWidget() {
		return widget;
	}

	public void setWidget(Widget widget) {
		this.widget = widget;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Tab tab = (Tab) o;

		if (tabCode != tab.tabCode) return false;

		return true;
	}

	@Override
	public int hashCode() {
		return tabCode;
	}
}
