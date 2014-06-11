package ge.tsu.server.util;

import ge.tsu.server.entities.Doctor;
import ge.tsu.server.entities.InsuranceCompany;
import ge.tsu.server.entities.Person;
import ge.tsu.server.entities.Police;
import ge.tsu.server.entities.medfacts.Allergy;
import ge.tsu.server.entities.medfacts.Disease;
import ge.tsu.server.entities.medfacts.Surgery;
import ge.tsu.server.entities.medwork.BloodTransfusion;
import ge.tsu.server.entities.medwork.CustomerAllergy;
import ge.tsu.server.entities.medwork.CustomerDisease;
import ge.tsu.server.entities.medwork.CustomerSurgery;
import ge.tsu.shared.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Vako
 * Date: 01.06.2014
 * Time: 19:36
 */
public class ModelToEntityHelper {


    private ModelHelper<UserModel, Person> personModelHelper = new ModelHelper<UserModel, Person>();
    private ModelHelper<AllergyModel, Allergy> allergyModelHelper = new ModelHelper<AllergyModel, Allergy>();
    private ModelHelper<BloodTransfusionModel, BloodTransfusion> bloodModelHelper = new ModelHelper<BloodTransfusionModel, BloodTransfusion>();
    private ModelHelper<CustomerDiseaseModel, CustomerDisease> customerDiseaseModelHelper = new ModelHelper<CustomerDiseaseModel, CustomerDisease>();
    private ModelHelper<DiseaseModel, Disease> diseaseModelHelper = new ModelHelper<DiseaseModel, Disease>();
    private ModelHelper<PoliceModel, Police> policeModelHelper = new ModelHelper<PoliceModel, Police>();


    public Person userModelToPerson(UserModel model) {
        Person person = new Person();
        personModelHelper.toEntity(model, person);
//        person.setId(model.getId());
//        person.setFirstName(model.getFirstName());
//        person.setLastName(model.getLastName());
//        person.setFatherName(model.getFatherName());
//        person.setGender(model.getGender());
//        person.setEmail(model.getEmail());
//        person.setPn(model.getPn());
//        person.setPhoneNumber(model.getPhoneNumber());
//        person.setBloodGroup(model.getBloodGroup());
//        person.setBirthDate(model.getBirthDate());
//        person.setProfessionAndJob(model.getProfessionAndJob());

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
        bloodModelHelper.toEntity(model, transfusion);
//        transfusion.setId(model.getId());
//        transfusion.setBloodVolume(model.getBloodVolume());
//        transfusion.setComment(model.getComment());
//        transfusion.setTransfusionDate(model.getTransfusionDate());
//        transfusion.setCustomer(new Person(model.getCustomerId()));
//        transfusion.setIssuer(new Person(model.getIssuerId()));

        transfusion.setCustomer(userModelToPerson(model.getCustomerModel()));
        transfusion.setIssuer(userModelToPerson(model.getIssuerModel()));

        return transfusion;
    }

    public Allergy allergyModelToEntity(AllergyModel allergyModel) {
        Allergy allergy = new Allergy();
        allergyModelHelper.toEntity(allergyModel, allergy);
        return allergy;
    }

    public List<CustomerAllergy> customerAllergyModelsToEntities(List<CustomerAllergyModel> customerAllergyModels) {

        List<CustomerAllergy> customerAllergies = new ArrayList<CustomerAllergy>();
        for (CustomerAllergyModel customerAllergyModel : customerAllergyModels) {
            CustomerAllergy customerAllergy = new CustomerAllergy();
            customerAllergy.setAllergy(allergyModelToEntity(customerAllergyModel.getAllergyModel()));
            customerAllergy.setCustomer(userModelToPerson(customerAllergyModel.getUserModel()));
            customerAllergies.add(customerAllergy);
        }

        return customerAllergies;
    }

    public List<CustomerSurgery> customerSurgeryModelsToEntities(List<CustomerSurgeryModel> customerSurgeryModels) {

        List<CustomerSurgery> customerSurgeries = new ArrayList<CustomerSurgery>();
        for (CustomerSurgeryModel model : customerSurgeryModels) {
            CustomerSurgery surgery = new CustomerSurgery();
            surgery.setId(model.getId());
            surgery.setBeginningDate(model.getBeginningDate());
            surgery.setEndDate(model.getEndDate());
            surgery.setComment(model.getComment());
            surgery.setCustomer(userModelToPerson(model.getCustomerModel()));
            surgery.setSurgery(surgeryModelToEntity(model.getSurgeryModel()));
            customerSurgeries.add(surgery);
        }

        return customerSurgeries;
    }

    private Surgery surgeryModelToEntity(SurgeryModel model) {
        Surgery surgery = new Surgery();
        surgery.setId(model.getId());
        surgery.setSurgeryName(model.getSurgeryName());
        return surgery;
    }

    public List<CustomerDisease> customerDiseaseModelsToEntities(List<CustomerDiseaseModel> customerDiseaseModels) {
        List<CustomerDisease> customerDiseases = new ArrayList<CustomerDisease>();
        for (CustomerDiseaseModel model : customerDiseaseModels) {
            CustomerDisease disease = new CustomerDisease();
            customerDiseaseModelHelper.toEntity(model, disease);
            disease.setCustomer(userModelToPerson(model.getCustomerModel()));
            disease.setDisease(diseaseModelToEntity(model.getDiseaseModel()));
            customerDiseases.add(disease);
        }
        return customerDiseases;
    }

    private Disease diseaseModelToEntity(DiseaseModel diseaseModel) {
        return diseaseModelHelper.toEntity(diseaseModel, new Disease());
    }

    public Police policeModelToEntity(PoliceModel model) {
        Police police = new Police();
        policeModelHelper.toEntity(model, police);
        police.setCompany(insuranceCompanyModelToEntity(model.getInsuranceCompanyModel()));
        police.setCustomer(userModelToPerson(model.getCustomerModel()));
        return police;
    }

    private InsuranceCompany insuranceCompanyModelToEntity(InsuranceCompanyModel model) {
        return new InsuranceCompany(model.getId(), model.getName());
    }
}
