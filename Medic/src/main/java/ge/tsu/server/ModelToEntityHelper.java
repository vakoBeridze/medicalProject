package ge.tsu.server;

import ge.tsu.server.entities.Doctor;
import ge.tsu.server.entities.Person;
import ge.tsu.server.entities.medwork.BloodTransfusion;
import ge.tsu.shared.BloodTransfusionModel;
import ge.tsu.shared.UserModel;

/**
 * Created with IntelliJ IDEA.
 * User: Vako
 * Date: 01.06.2014
 * Time: 19:36
 */
public class ModelToEntityHelper {


    public Person userModelToPerson(UserModel model) {
        Person person = new Person();
        person.setId(model.getId());
        person.setFirstName(model.getFirstName());
        person.setLastName(model.getLastName());
        person.setFatherName(model.getFatherName());
        person.setGender(model.getGender());
        person.setEmail(model.getEmail());
        person.setPn(model.getPn());
        person.setPhoneNumber(model.getPhoneNumber());
        person.setBloodGroup(model.getBloodGroup());
        person.setBirthDate(model.getBirthDate());
        person.setProfessionAndJob(model.getProfessionAndJob());

        return person;
    }

    public Doctor userModelToDoctor(UserModel model) {
        Doctor doctor = new Doctor();
        doctor.setId(model.getId());
        doctor.setFirstName(model.getFirstName());
        doctor.setLastName(model.getLastName());
        doctor.setFatherName(model.getFatherName());
        doctor.setGender(model.getGender());
        doctor.setLicense(model.getLicense());
        doctor.setPassword(model.getPassword());
        doctor.setEmail(model.getEmail());
        doctor.setPn(model.getPn());
        doctor.setPhoneNumber(model.getPhoneNumber());
        doctor.setBloodGroup(model.getBloodGroup());
        doctor.setBirthDate(model.getBirthDate());
        doctor.setProfessionAndJob(model.getProfessionAndJob());

        return doctor;

    }

    public BloodTransfusion transfusionModelToEntity(BloodTransfusionModel model) {
        BloodTransfusion transfusion = new BloodTransfusion();
        transfusion.setId(model.getId());
        transfusion.setBloodVolume(model.getBloodVolume());
        transfusion.setComment(model.getComment());
        transfusion.setTransfusionDate(model.getTransfusionDate());
        transfusion.setCustomer(new Person(model.getCustomerId()));
        transfusion.setIssuer(new Person(model.getIssuerId()));

        return transfusion;
    }
}
