package ge.tsu.client.images;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.resources.client.TextResource;

public interface Images extends ClientBundle {

    public Images INSTANCE = GWT.create(Images.class);

    @Source("coming_soon.png")
    ImageResource comingSoon();

    @Source("user_medical_female.png")
    ImageResource userManager();

    @Source("document.png")
    ImageResource form();

    @Source("document_green.png")
    ImageResource formGreen();

    @Source("document_black.png")
    ImageResource form300();

    @Source("document_red.png")
    ImageResource formRed();

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

    @Source("form200-1.png")
    ImageResource form200_1();

    @Source("form200-2.png")
    ImageResource form200_2();

    @Source("form200-3.png")
    ImageResource form200_3();

    @Source("form200-4.png")
    ImageResource form200_4();

    @Source("form200-5.png")
    ImageResource form200_5();

    @Source("form200-6.png")
    ImageResource form200_6();


    @Source("form200-8.png")
    ImageResource form200_8();

}
