package ge.tsu.client.images;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ImageResource;

public interface Images extends ClientBundle {

	public Images INSTANCE = GWT.create(Images.class);

	@Source("logo.png")
	ImageResource logo();

	@Source("user_medical.png")
	ImageResource userManager();

	@Source("document_editing.png")
	ImageResource form100();

	@Source("document_black.png")
	ImageResource form200();

	@Source("document_green.png")
	ImageResource form300();

	@Source("document_red.png")
	ImageResource form400();

	@Source("document_yellow.png")
	ImageResource form500();

	ImageResource filter();

	@Source("user_add.png")
	ImageResource addUser();

	@Source("user_edit.png")
	ImageResource editUser();

	@Source("user_delete.png")
	ImageResource deleteUser();
}
