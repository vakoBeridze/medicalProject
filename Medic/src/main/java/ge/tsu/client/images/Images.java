package ge.tsu.client.images;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.resources.client.TextResource;

public interface Images extends ClientBundle {

    public Images INSTANCE = GWT.create(Images.class);

    @Source("logo.png")
    ImageResource logo();

    @Source("user_medical_female.png")
    ImageResource userManager();

    @Source("document_editing.png")
    ImageResource form100();

    @Source("document_green.png")
    ImageResource form200();

    @Source("document_black.png")
    ImageResource form300();

    @Source("document_red.png")
    ImageResource form400();

    @Source("document_yellow.png")
    ImageResource form500();

    ImageResource filter();

    @Source("add.png")
    ImageResource add();

    @Source("edit.png")
    ImageResource edit();

    @Source("delete.png")
    ImageResource delete();

    @Source("emotion_medic.png")
    ImageResource addDoctor();

    @Source("emotion_patient.png")
    ImageResource addPatient();

    ImageResource geFlag();

    ImageResource engFlag();

    @Source("user_medical.png")
    ImageResource user();

    @Source("change_password.png")
    ImageResource changePassword();

    ImageResource logout();

    @Source("disk.png")
    ImageResource save();

    @Source("information.png")
    ImageResource info();

    @Source("home.html")
    TextResource about();
}
