package ge.tsu.server.entities.meddocs;

import ge.tsu.server.entities.Hospital;
import ge.tsu.server.entities.Person;
import ge.tsu.server.entities.medfacts.Appealing;
import ge.tsu.server.entities.medwork.CustomerAppealing;

import javax.persistence.*;
import java.util.Date;
import java.util.List;


@Entity
@Table(name = "CUSTOMER_VISITS")
public class CustomerVisit {
    @Id
    private Long id;

    private Integer visitType;

    @Temporal(value = TemporalType.TIMESTAMP)
    private Date visitDate;
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date leaveDate;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Person customer;

    @ManyToOne
    @JoinColumn(name = "hospital_id")
    private Hospital hospital;

    @ManyToOne
    @JoinColumn(name = "replace_hospital_id")
    private Hospital replaceHospital;

    private Integer visitConsequence;
    private String note;


    @OneToMany(fetch = FetchType.LAZY, mappedBy = "visit")
    private List<CustomerAppealing> appealings;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getVisitType() {
        return visitType;
    }

    public void setVisitType(Integer visitType) {
        this.visitType = visitType;
    }

    public Date getVisitDate() {
        return visitDate;
    }

    public void setVisitDate(Date visitDate) {
        this.visitDate = visitDate;
    }

    public Date getLeaveDate() {
        return leaveDate;
    }

    public void setLeaveDate(Date leaveDate) {
        this.leaveDate = leaveDate;
    }

    public Person getCustomer() {
        return customer;
    }

    public void setCustomer(Person customer) {
        this.customer = customer;
    }

    public Hospital getHospital() {
        return hospital;
    }

    public void setHospital(Hospital hospital) {
        this.hospital = hospital;
    }

    public Hospital getReplaceHospital() {
        return replaceHospital;
    }

    public void setReplaceHospital(Hospital replaceHospital) {
        this.replaceHospital = replaceHospital;
    }

    public Integer getVisitConsequence() {
        return visitConsequence;
    }

    public void setVisitConsequence(Integer visitConsequence) {
        this.visitConsequence = visitConsequence;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public List<CustomerAppealing> getAppealings() {
        return appealings;
    }

    public void setAppealings(List<CustomerAppealing> appealings) {
        this.appealings = appealings;
    }
}
