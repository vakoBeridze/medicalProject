package ge.tsu.server.ejb;

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

import java.util.List;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: Vako
 * Date: 01.06.2014
 * Time: 17:11
 */
public interface AppLocal {
    Set<? extends Person> loadUsers();

    Person savePerson(Person person);

    Doctor saveDoctor(Doctor doctor);

    void deleteUser(Person person);

    Doctor getUserByUserName(String login);

    BloodTransfusion saveTransfusion(BloodTransfusion bloodTransfusion);

    List<Allergy> loadAllergies();

    void saveCustomerAllergies(List<CustomerAllergy> customerAllergies);

    List<Surgery> loadSurgeries();

    void saveCustomerSurgeries(List<CustomerSurgery> customerSurgeries);

    List<Disease> loadDiseases(boolean chronicDisease);

    void saveCustomerDiseases(List<CustomerDisease> customerDiseases);

    List<InsuranceCompany> loadInsuranceCompanies();

    void savePolice(Police police);

    Person loadUser(long id, boolean doctor);
}
