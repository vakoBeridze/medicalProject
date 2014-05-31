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

    // FIXME
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "desease_id")
    private Disease desease;

    //სიმწვავე(მსუბუქი, ქრონიკული, მწვავე)
    private Integer deseaseStrength;

    private Boolean isInfection;

    private Boolean isGenetic;

    @Temporal(value = TemporalType.DATE)
    private Date detectionDate;

    @Temporal(value = TemporalType.DATE)
    private Date cureDate;

    @Temporal(value = TemporalType.DATE)
    private Date justificationDate;

    // FIXME
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "previus_diagnose_id")
    private CustomerDisease previousDiagnose;

    private Boolean isFinalDiagnose;
    private Boolean isPreDiagnose;

    //ამისთვის განსაზღვრულია enum-ი.
    private Integer diagnoseState;

    // FIXME
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    // FIXME
    @OneToOne(fetch = FetchType.EAGER)
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

    public Disease getDesease() {
        return desease;
    }

    public void setDesease(Disease desease) {
        this.desease = desease;
    }

    public Integer getDeseaseStrength() {
        return deseaseStrength;
    }

    public void setDeseaseStrength(Integer deseaseStrength) {
        this.deseaseStrength = deseaseStrength;
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
