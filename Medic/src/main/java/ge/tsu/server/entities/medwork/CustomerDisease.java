/**
 *
 */
package ge.tsu.server.entities.medwork;

import ge.tsu.server.entities.Doctor;
import ge.tsu.server.entities.Person;
import ge.tsu.server.entities.meddocs.CustomerVisit;
import ge.tsu.server.entities.medfacts.Disease;

import javax.persistence.*;
import java.util.Date;

/**
 * @author vamekh
 *         აქ ფიქსირდება რა დაავადებები შემთხვევია როდესმე პაციენტს
 */

@Entity
@Table(name = "CUSTOMER_DISEASES")
public class CustomerDisease {
    @Id
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_id")
    private Person customer;

    // FIXME ras erchi?
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "disease_id")
    private Disease disease;

    //სიმწვავე(მსუბუქი, ქრონიკული, მწვავე, ქვემწვავე)
    private Integer diseaseStrength;

    private Boolean isInfection;

    private Boolean isGenetic;

    @Temporal(value = TemporalType.DATE)
    private Date detectionDate;

    @Temporal(value = TemporalType.DATE)
    private Date cureDate;

    @Temporal(value = TemporalType.DATE)
    private Date justificationDate;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "previous_diagnose_id")
    private CustomerDisease previousDiagnose;

    private Boolean isFinalDiagnose;
    private Boolean isPreDiagnose;

    //ამისთვის განსაზღვრულია enum-ი.
    private Integer diagnoseState;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_visit_id")
    private CustomerVisit visit;

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

    public Disease getDisease() {
        return disease;
    }

    public void setDisease(Disease disease) {
        this.disease = disease;
    }

    public Integer getDiseaseStrength() {
        return diseaseStrength;
    }

    public void setDiseaseStrength(Integer diseaseStrength) {
        this.diseaseStrength = diseaseStrength;
    }

    public Boolean getIsInfection() {
        return isInfection;
    }

    public void setIsInfection(Boolean isInfection) {
        this.isInfection = isInfection;
    }

    public Boolean getIsGenetic() {
        return isGenetic;
    }

    public void setIsGenetic(Boolean isGenetic) {
        this.isGenetic = isGenetic;
    }

    public Date getDetectionDate() {
        return detectionDate;
    }

    public void setDetectionDate(Date detectionDate) {
        this.detectionDate = detectionDate;
    }

    public Date getCureDate() {
        return cureDate;
    }

    public void setCureDate(Date cureDate) {
        this.cureDate = cureDate;
    }

    public Date getJustificationDate() {
        return justificationDate;
    }

    public void setJustificationDate(Date justificationDate) {
        this.justificationDate = justificationDate;
    }

    public CustomerDisease getPreviousDiagnose() {
        return previousDiagnose;
    }

    public void setPreviousDiagnose(CustomerDisease previousDiagnose) {
        this.previousDiagnose = previousDiagnose;
    }

    public Boolean getIsFinalDiagnose() {
        return isFinalDiagnose;
    }

    public void setIsFinalDiagnose(Boolean isFinalDiagnose) {
        this.isFinalDiagnose = isFinalDiagnose;
    }

    public Boolean getIsPreDiagnose() {
        return isPreDiagnose;
    }

    public void setIsPreDiagnose(Boolean isPreDiagnose) {
        this.isPreDiagnose = isPreDiagnose;
    }

    public Integer getDiagnoseState() {
        return diagnoseState;
    }

    public void setDiagnoseState(Integer diagnoseState) {
        this.diagnoseState = diagnoseState;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public CustomerVisit getVisit() {
        return visit;
    }

    public void setVisit(CustomerVisit visit) {
        this.visit = visit;
    }
}
