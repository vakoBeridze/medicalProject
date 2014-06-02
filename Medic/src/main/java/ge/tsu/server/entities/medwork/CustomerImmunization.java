/**
 *
 */
package ge.tsu.server.entities.medwork;


import ge.tsu.server.entities.Doctor;
import ge.tsu.server.entities.Hospital;
import ge.tsu.server.entities.Person;
import ge.tsu.server.entities.medfacts.Immunization;
import org.joda.time.LocalDate;
import org.joda.time.Years;

import javax.persistence.*;
import java.util.Date;


/**
 * @author vamekh
 */

@Entity
@Table(name = "CUSTOMER_IMMUNIZATIONS")
public class CustomerImmunization {
    @Id
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_id")
    private Person customer;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "immunization_id")
    private Immunization immunization;

    //FIXME whats the problem??
    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    private String injection;
    private String vaccinVersion;
    private String note;
    @Temporal(TemporalType.DATE)
    private Date injectionDate;


    @OneToOne
    @JoinColumn(name = "hospital_id")
    private Hospital hospital;

    @Transient
    private Integer customerAge;

    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }


    public Person getCustomer() {
        return customer;
    }


    public void setCustomer(Person customer) {
        this.customer = customer;
    }


    public Immunization getImmunization() {
        return immunization;
    }


    public void setImmunization(Immunization immunization) {
        this.immunization = immunization;
    }


    public Doctor getDoctor() {
        return doctor;
    }


    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }


    public String getInjection() {
        return injection;
    }


    public void setInjection(String injection) {
        this.injection = injection;
    }


    public String getVaccinVersion() {
        return vaccinVersion;
    }


    public void setVaccinVersion(String vaccinVerssion) {
        this.vaccinVersion = vaccinVerssion;
    }


    public String getNote() {
        return note;
    }


    public void setNote(String note) {
        this.note = note;
    }


    public Date getInjectionDate() {
        return injectionDate;
    }


    public void setInjectionDate(Date injectionDate) {
        this.injectionDate = injectionDate;
    }


    public Hospital getHospital() {
        return hospital;
    }


    public void setHospital(Hospital hospital) {
        this.hospital = hospital;
    }


    @Transient
    public Integer getCustomerAge() throws NullPointerException {
        try {
            if (this.customerAge != null) {
                return this.customerAge;
            }
            LocalDate birthDate = new LocalDate(this.customer.getBirthDate());
            Integer detectedAge = Years.yearsBetween(birthDate, new LocalDate()).getYears();
            if (detectedAge > 0) {
                this.customerAge = detectedAge;
            }
            return detectedAge;
        } catch (NullPointerException e) {
            System.out.println("getCustomerAge() - NullPointerException");
            return -1;
        }

    }
}
