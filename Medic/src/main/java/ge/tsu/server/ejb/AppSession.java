package ge.tsu.server.ejb;

import ge.tsu.server.entities.Doctor;
import ge.tsu.server.entities.Person;
import ge.tsu.server.entities.medfacts.Allergy;
import ge.tsu.server.entities.medwork.BloodTransfusion;
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
        Person branch = em.find(Person.class, person.getId());
        em.remove(branch);
    }

    @Override
    public Doctor getUserByUserName(String login) {
        Query query = em.createQuery("select d from Doctor as d where d.email=:login");
        query.setParameter("login", login);
        return (Doctor) query.getSingleResult();
    }

    @Override
    public BloodTransfusion saveTransfusion(BloodTransfusion bloodTransfusion) {
        return em.merge(bloodTransfusion);
    }

    @Override
    public List<Allergy> loadAllergies() {
        Query loadAllergies = em.createQuery("SELECT a FROM Allergy a");
        return loadAllergies.getResultList();
    }
}
