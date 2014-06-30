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
import org.apache.log4j.Logger;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: Vako
 * Date: 01.06.2014
 * Time: 17:12
 */

@SuppressWarnings("unchecked")
@Stateless
@Local(AppLocal.class)
public class AppSession implements AppLocal {
    Logger logger = Logger.getLogger(getClass());

    @PersistenceContext
    private EntityManager em;

    @Override
    public Set<? extends Person> loadUsers() {
        Set<? extends Person> users = new HashSet<Person>();

        Query getPersonsQuery = em.createQuery("SELECT p FROM Person p");
        users.addAll(getPersonsQuery.getResultList());

        Query getDoctorsQuery = em.createQuery("SELECT d FROM Doctor d");
        users.addAll(getDoctorsQuery.getResultList());

        return users;
    }

    @Override
    public Person savePerson(Person person) {
        return em.merge(person);
    }

    @Override
    public Doctor saveDoctor(Doctor doctor) {
        return em.merge(doctor);
    }

    @Override
    public void deleteUser(Person person) {
        clearCustomerAllergies(person.getId());
        clearCustomerDiseases(person.getId());
        clearCustomerSurgeries(person.getId());
        clearTransfusion(person.getId());
        clearPolice(person.getId());

        Person p = em.find(Person.class, person.getId());
        em.remove(p);
    }

    @Override
    public Doctor getUserByUserName(String login) {
        Query query = em.createQuery("select d from Doctor as d where d.email=:login");
        query.setParameter("login", login);
        return (Doctor) query.getSingleResult();
    }

    @Override
    public BloodTransfusion saveTransfusion(long userId, BloodTransfusion bloodTransfusion) {
        return em.merge(bloodTransfusion);
    }

    @Override
    public List<Allergy> loadAllergies() {
        Query loadAllergies = em.createQuery("SELECT a FROM Allergy a");
        return loadAllergies.getResultList();
    }

    @Override
    public void saveCustomerAllergies(long userId, List<CustomerAllergy> customerAllergies) {
        clearCustomerAllergies(userId);
        for (CustomerAllergy customerAllergy : customerAllergies) {
            em.merge(customerAllergy);
        }
    }

    @Override
    public List<Surgery> loadSurgeries() {
        Query loadSurgeries = em.createQuery("SELECT s FROM Surgery s");
        return loadSurgeries.getResultList();
    }

    @Override
    public void saveCustomerSurgeries(long userId, List<CustomerSurgery> customerSurgeries) {
        clearCustomerSurgeries(userId);
        for (CustomerSurgery customerSurgery : customerSurgeries) {
            em.merge(customerSurgery);
        }
    }

    @Override
    public List<Disease> loadDiseases(boolean chronicDisease) {
        Query loadDiseases = null;
        if (chronicDisease) {
            loadDiseases = em.createQuery("SELECT s FROM Disease s where s.isChronic=true");
        } else {
            loadDiseases = em.createQuery("SELECT s FROM Disease s where s.isInfection=true");
        }
        return loadDiseases.getResultList();
    }

    @Override
    public void saveCustomerDiseases(long userId, List<CustomerDisease> customerDiseases) {
        clearCustomerDiseases(userId);
        for (CustomerDisease customerDisease : customerDiseases) {
            em.merge(customerDisease);
        }
    }

    @Override
    public List<InsuranceCompany> loadInsuranceCompanies() {
        Query loadCompanies = em.createQuery("SELECT s FROM InsuranceCompany s");
        return loadCompanies.getResultList();
    }

    @Override
    public void savePolice(long userId, Police police) {
        em.merge(police);
    }

    @Override
    public Person loadUser(long id, boolean doctor) {
        Person p = em.find(Person.class, id);
        p.getBloodTransfusions().isEmpty();
        p.getCustomerAllergies().isEmpty();
        p.getCustomerSurgeries().isEmpty();
        p.getCustomerDiseases().isEmpty();
        p.getCustomerPolices().isEmpty();
        p.getCitizenship().isEmpty();

        return p;
    }

    @Override
    public void changePassword(long userId, String newPassword) {
        Doctor doctor = em.find(Doctor.class, userId);
        if (doctor != null) {
            doctor.setPassword(newPassword);
            em.merge(doctor);
        }
    }

    @Override
    public void clearTransfusion(long userId) {
        Query query = em.createQuery("DELETE from BloodTransfusion bt WHERE bt.customer.id=:userId");
        query.setParameter("userId", userId);
        query.executeUpdate();
    }

    @Override
    public void clearCustomerAllergies(long userId) {
        Query query = em.createQuery("DELETE from CustomerAllergy ca WHERE ca.customer.id=:userId");
        query.setParameter("userId", userId);
        query.executeUpdate();
    }

    @Override
    public void clearCustomerSurgeries(long userId) {
        Query query = em.createQuery("DELETE from CustomerSurgery c WHERE c.customer.id=:userId");
        query.setParameter("userId", userId);
        query.executeUpdate();
    }

    @Override
    public void clearCustomerDiseases(long userId) {
        Query query = em.createQuery("DELETE from CustomerDisease c WHERE c.customer.id=:userId");
        query.setParameter("userId", userId);
        query.executeUpdate();

    }

    @Override
    public void clearPolice(long userId) {
        Query query = em.createQuery("DELETE from Police c WHERE c.customer.id=:userId");
        query.setParameter("userId", userId);
        query.executeUpdate();
    }
}
