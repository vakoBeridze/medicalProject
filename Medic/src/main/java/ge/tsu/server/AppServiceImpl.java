package ge.tsu.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import ge.tsu.client.service.AppService;
import ge.tsu.server.ejb.AppLocal;
import ge.tsu.server.entities.Doctor;
import ge.tsu.server.entities.InsuranceCompany;
import ge.tsu.server.entities.Person;
import ge.tsu.server.entities.medfacts.Allergy;
import ge.tsu.server.entities.medfacts.Disease;
import ge.tsu.server.entities.medfacts.Surgery;
import ge.tsu.server.util.EntityToModelHelper;
import ge.tsu.server.util.ModelToEntityHelper;
import ge.tsu.shared.*;
import org.apache.log4j.Logger;

import javax.ejb.EJB;
import javax.ejb.EJBTransactionRolledbackException;
import java.util.List;
import java.util.Set;

@SuppressWarnings("serial")
public class AppServiceImpl extends RemoteServiceServlet implements AppService {

    Logger logger = Logger.getLogger(getClass());

    EntityToModelHelper entityToModelHelper = new EntityToModelHelper();
    ModelToEntityHelper modelToEntityHelper = new ModelToEntityHelper();

    @EJB
    private AppLocal appLocal;

    @Override
    public void logout() {
        System.out.println("<<<<<<<<<<<<<<<<<<<<< Logout User: " + getThreadLocalRequest().getUserPrincipal().getName());
        getThreadLocalRequest().getSession().invalidate();
    }

    @Override
    public void logToServer(Throwable th) {
        System.out.println(" Error  =============================================   Error: " + th.getMessage());
        th.printStackTrace();
    }

    @Override
    public void testMethod(String testParam) {
        System.out.println("testMethod: " + testParam);
    }

    @Override
    public List<UserModel> loadUsers() {
        Set<? extends Person> users = appLocal.loadUsers();
        return entityToModelHelper.personsToUserModels(users);
    }

    @Override
    public UserModel saveUser(UserModel userModel) throws MedicException {
        try {
            if (userModel.isDoctor()) {
                Doctor doctor = modelToEntityHelper.userModelToDoctor(userModel);
                return entityToModelHelper.doctorToUserModel(appLocal.saveDoctor(doctor));
            } else {
                Person person = modelToEntityHelper.userModelToPerson(userModel);
                return entityToModelHelper.personToUserModel(appLocal.savePerson(person));
            }
        } catch (EJBTransactionRolledbackException ex) {
            throw new MedicException(userModel.getEmail() + " Not Unique");
        }
    }

    @Override
    public void deleteUser(UserModel userModel) {
        Person person = new Person();
        person.setId(userModel.getId());
        appLocal.deleteUser(person);
    }

    @Override
    public UserModel loadCurrentUser() {
        String login = getThreadLocalRequest().getUserPrincipal().getName();

        // FIXME this if will be while development
        if (login.equals("sa")) {
            UserModel model = new UserModel();
            model.setFirstName("ვაკო");
            model.setLastName("ბერიძე");
            model.setEmail("sys.admin@mail.com");
            return model;
        }

        return entityToModelHelper.doctorToUserModel(appLocal.getUserByUserName(login));
    }

    @Override
    public void saveForm200a(BloodTransfusionModel transfusionModel, List<CustomerAllergyModel> customerAllergyModels, List<CustomerSurgeryModel> customerSurgeryModels, List<CustomerDiseaseModel> customerDiseaseModels, PoliceModel policeModel) {

        if (transfusionModel != null)
            appLocal.saveTransfusion(modelToEntityHelper.transfusionModelToEntity(transfusionModel));

        if (customerAllergyModels != null)
            appLocal.saveCustomerAllergies(modelToEntityHelper.customerAllergyModelsToEntities(customerAllergyModels));

        if (customerSurgeryModels != null)
            appLocal.saveCustomerSurgeries(modelToEntityHelper.customerSurgeryModelsToEntities(customerSurgeryModels));

        if (customerDiseaseModels != null)
            appLocal.saveCustomerDiseases(modelToEntityHelper.customerDiseaseModelsToEntities(customerDiseaseModels));

        if (policeModel != null)
            appLocal.savePolice(modelToEntityHelper.policeModelToEntity(policeModel));
    }

    @Override
    public List<AllergyModel> loadAllergies() {
        List<Allergy> allergies = appLocal.loadAllergies();
        return entityToModelHelper.allergiesToModels(allergies);
    }

    @Override
    public List<SurgeryModel> loadSurgeries() {
        List<Surgery> surgeries = appLocal.loadSurgeries();
        return entityToModelHelper.surgeriesToModels(surgeries);
    }

    @Override
    public List<DiseaseModel> loadDiseases(boolean chronicDisease) {
        List<Disease> diseases = appLocal.loadDiseases(chronicDisease);
        return entityToModelHelper.diseasesToModels(diseases);
    }

    @Override
    public List<InsuranceCompanyModel> loadInsuranceCompanies() {

        List<InsuranceCompany> companies = appLocal.loadInsuranceCompanies();
        return entityToModelHelper.insuranceCompaniesToModels(companies);
    }

    @Override
    public UserModel loadUser(UserModel userModel) {
        Person p = appLocal.loadUser(userModel.getId(), userModel.isDoctor());
        UserModel user = entityToModelHelper.personToUserModelFull(p);
        return user;
    }
}
