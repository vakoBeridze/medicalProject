package ge.tsu.client.service;

import com.google.gwt.user.client.rpc.AsyncCallback;
import ge.tsu.shared.*;

import java.util.List;

public interface AppServiceAsync {

    void logout(AsyncCallback<Void> asyncCallback);

    void logToServer(Throwable th, AsyncCallback<Void> asyncCallback);

    void testMethod(String testParam, AsyncCallback<Void> asyncCallback);

    void loadUsers(AsyncCallback<List<UserModel>> asyncCallback);

    void saveUser(UserModel userModel, AsyncCallback<UserModel> asyncCallback);

    void deleteUser(UserModel userModel, AsyncCallback<Void> asyncCallback);

    void loadCurrentUser(AsyncCallback<UserModel> asyncCallback);

    void saveForm200a(BloodTransfusionModel transfusionModel, List<CustomerAllergyModel> customerAllergyModels, List<CustomerSurgeryModel> customerSurgeryModels, List<CustomerDiseaseModel> customerDiseaseModels, PoliceModel policeModel, AsyncCallback<Void> asyncCallback);

    void loadAllergies(AsyncCallback<List<AllergyModel>> asyncCallback);

    void loadSurgeries(AsyncCallback<List<SurgeryModel>> asyncCallback);

    void loadDiseases(boolean chronicDisease, AsyncCallback<List<DiseaseModel>> asyncCallback);

    void loadInsuranceCompanies(AsyncCallback<List<InsuranceCompanyModel>> asyncCallback);
}
