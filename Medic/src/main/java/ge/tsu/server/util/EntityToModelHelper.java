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

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: Vako
 * Date: 01.06.2014
 * Time: 17:32
 */
public class EntityToModelHelper implements Serializable {

    //    private ModelHelper<UserModel, Person> personModelHelper = new ModelHelper<UserModel, Person>();
    private ModelHelper<DiseaseModel, Disease> diseaseModelHelper = new ModelHelper<DiseaseModel, Disease>();
    private ModelHelper<BloodTransfusionModel, BloodTransfusion> bloodModelHelper = new ModelHelper<BloodTransfusionModel, BloodTransfusion>();
    private ModelHelper<CustomerDiseaseModel, CustomerDisease> customerDiseaseModelHelper = new ModelHelper<CustomerDiseaseModel, CustomerDisease>();
    private ModelHelper<PoliceModel, Police> policeModelHelper = new ModelHelper<PoliceModel, Police>();
    private ModelHelper<CustomerSurgeryModel, CustomerSurgery> customerSurgeryModelHelper = new ModelHelper<CustomerSurgeryModel, CustomerSurgery>();

    public List<UserModel> personsToUserModels(Set<? extends Person> persons) {

        List<UserModel> models = new ArrayList<UserModel>();
        for (Person person : persons) {
            if (person instanceof Doctor) {
                Doctor doctor = (Doctor) person;
                models.add(doctorToUserModel(doctor));
            } else {
                models.add(personToUserModel(person));
            }
        }
        return models;
    }

    public UserModel doctorToUserModel(Doctor doctor) {
        UserModel userModel = new UserModel();
//        doctorModelHelper.toModel(userModel, doctor);
        userModel.setId(doctor.getId());
        userModel.setUserName(doctor.getEmail());
        userModel.setFirstName(doctor.getFirstName());
        userModel.setLastName(doctor.getLastName());
        userModel.setFatherName(doctor.getFatherName());
        userModel.setGender(doctor.getGender());
        userModel.setEmail(doctor.getEmail());
        userModel.setPassword(doctor.getPassword());
        userModel.setLicense(doctor.getLicense());
        userModel.setDoctor(true);
        userModel.setPn(doctor.getPn());
        userModel.setPhoneNumber(doctor.getPhoneNumber());
        userModel.setBloodGroup(doctor.getBloodGroup());
        userModel.setBirthDate(doctor.getBirthDate());
        userModel.setProfessionAndJob(doctor.getProfessionAndJob());

        return userModel;
    }


    public UserModel personToUserModel(Person person) {
        UserModel userModel = new UserModel();
//        personModelHelper.toModel(userModel, person);
        userModel.setId(person.getId());
        userModel.setUserName(person.getEmail());
        userModel.setFirstName(person.getFirstName());
        userModel.setLastName(person.getLastName());
        userModel.setFatherName(person.getFatherName());
        userModel.setGender(person.getGender());
        userModel.setEmail(person.getEmail());
        userModel.setDoctor(false);
        userModel.setPn(person.getPn());
        userModel.setPhoneNumber(person.getPhoneNumber());
        userModel.setBloodGroup(person.getBloodGroup());
        userModel.setBirthDate(person.getBirthDate());
        userModel.setProfessionAndJob(person.getProfessionAndJob());

        return userModel;
    }

    public List<AllergyModel> allergiesToModels(List<Allergy> allergies) {
        List<AllergyModel> allergyModels = new ArrayList<AllergyModel>();
        for (Allergy allergy : allergies) {
            allergyModels.add(allergyToModel(allergy));
        }
        return allergyModels;
    }

    private AllergyModel allergyToModel(Allergy allergy) {
        AllergyModel model = new AllergyModel();
        model.setId(allergy.getId());
        model.setComment(allergy.getComment());
        model.setName(allergy.getName());
        model.setStandard(allergy.getStandard());

        return model;
    }

    public List<SurgeryModel> surgeriesToModels(List<Surgery> surgeries) {
        List<SurgeryModel> surgeryModels = new ArrayList<SurgeryModel>();
        for (Surgery surgery : surgeries) {
            surgeryModels.add(surgeryToModel(surgery));
        }
        return surgeryModels;
    }

    private SurgeryModel surgeryToModel(Surgery surgery) {
        SurgeryModel model = new SurgeryModel();
        model.setId(surgery.getId());
        model.setSurgeryName(surgery.getSurgeryName());
        return model;
    }

    public List<DiseaseModel> diseasesToModels(List<Disease> diseases) {
        List<DiseaseModel> diseaseModels = new ArrayList<DiseaseModel>();
        for (Disease disease : diseases) {
            diseaseModels.add(diseaseToModel(disease));
        }
        return diseaseModels;
    }

    private DiseaseModel diseaseToModel(Disease disease) {
        DiseaseModel model = new DiseaseModel();
        diseaseModelHelper.toModel(model, disease);
        return model;
    }

    public List<InsuranceCompanyModel> insuranceCompaniesToModels(List<InsuranceCompany> companies) {
        List<InsuranceCompanyModel> models = new ArrayList<InsuranceCompanyModel>();
        for (InsuranceCompany company : companies) {
            models.add(new InsuranceCompanyModel(company.getId(), company.getName()));
        }
        return models;
    }

    public UserModel personToUserModelFull(Person person) {
        UserModel model = personToUserModel(person);
        model.setBloodTransfusionModels(bloodTransfusionsToModels(person.getBloodTransfusions()));
        model.setCustomerAllergyModels(customerAllergiesToModels(person.getCustomerAllergies()));
        model.setCustomerSurgeryModels(customerSurgeriesToModels(person.getCustomerSurgeries()));
        model.setCustomerDiseaseModels(customerDiseasesToModels(person.getCustomerDiseases()));
        model.setCustomerPoliceModels(customerPolicesToModels(person.getCustomerPolices()));
        return model;
    }

    private List<BloodTransfusionModel> bloodTransfusionsToModels(List<BloodTransfusion> bloodTransfusions) {
        List<BloodTransfusionModel> models = new ArrayList<BloodTransfusionModel>();

        for (BloodTransfusion bloodTransfusion : bloodTransfusions) {
            BloodTransfusionModel model = new BloodTransfusionModel();
            model.setIssuerModel(personToUserModel(bloodTransfusion.getIssuer()));
            model.setCustomerModel(personToUserModel(bloodTransfusion.getCustomer()));
            bloodModelHelper.toModel(model, bloodTransfusion);

            models.add(model);
        }

        return models;
    }

    private List<CustomerAllergyModel> customerAllergiesToModels(List<CustomerAllergy> customerAllergies) {
        List<CustomerAllergyModel> models = new ArrayList<CustomerAllergyModel>();

        for (CustomerAllergy customerAllergy : customerAllergies) {
            CustomerAllergyModel model = new CustomerAllergyModel();
            model.setId(customerAllergy.getId());
            model.setUserModel(personToUserModel(customerAllergy.getCustomer()));
            model.setAllergyModel(allergyToModel(customerAllergy.getAllergy()));

            models.add(model);
        }
        return models;
    }

    private List<CustomerSurgeryModel> customerSurgeriesToModels(List<CustomerSurgery> customerSurgeries) {
        List<CustomerSurgeryModel> models = new ArrayList<CustomerSurgeryModel>();

        for (CustomerSurgery customerSurgery : customerSurgeries) {
            CustomerSurgeryModel model = new CustomerSurgeryModel();
            customerSurgeryModelHelper.toModel(model, customerSurgery);
            model.setCustomerModel(personToUserModel(customerSurgery.getCustomer()));
            model.setSurgeryModel(surgeryToModel(customerSurgery.getSurgery()));

            models.add(model);
        }
        return models;
    }

    private List<CustomerDiseaseModel> customerDiseasesToModels(List<CustomerDisease> customerDiseases) {
        List<CustomerDiseaseModel> models = new ArrayList<CustomerDiseaseModel>();

        for (CustomerDisease customerDisease : customerDiseases) {
            CustomerDiseaseModel model = new CustomerDiseaseModel();
            customerDiseaseModelHelper.toModel(model, customerDisease);
            model.setCustomerModel(personToUserModel(customerDisease.getCustomer()));
            model.setDiseaseModel(diseaseToModel(customerDisease.getDisease()));

            models.add(model);
        }
        return models;
    }

    private List<PoliceModel> customerPolicesToModels(List<Police> polices) {
        List<PoliceModel> models = new ArrayList<PoliceModel>();

        for (Police police : polices) {
            PoliceModel model = new PoliceModel();
            policeModelHelper.toModel(model, police);
            model.setCustomerModel(personToUserModel(police.getCustomer()));
            model.setInsuranceCompanyModel(insuranceCompanyToModel(police.getCompany()));

            models.add(model);
        }
        return models;
    }

    private InsuranceCompanyModel insuranceCompanyToModel(InsuranceCompany company) {
        return new InsuranceCompanyModel(company.getId(), company.getName());
    }
}
