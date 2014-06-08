/**
 *
 */
package ge.tsu.server.entities.medwork;

import ge.tsu.server.entities.Hospital;
import ge.tsu.server.entities.Person;
import ge.tsu.server.entities.meddocs.CustomerVisit;
import ge.tsu.server.entities.medfacts.Surgery;

import javax.persistence.*;
import java.util.Date;

/**
 * @author vamekh
 *         აღწერს  რა ქირურგიული ოპერაციები აქვს გაკეთებული პიროვნებას
 */

@Entity
@Table(name = "CUSTOMER_SURGERY")
public class CustomerSurgery {
    @Id
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_id")
    private Person customer;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "surgery_id")
    private Surgery surgery;

    private String comment;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "hospital_id")
    private Hospital hospital;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "visit_id")
    private CustomerVisit visit;

    @Temporal(value = TemporalType.TIMESTAMP)
    private Date beginningDate;

    @Temporal(value = TemporalType.TIMESTAMP)
    private Date endDate;











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

    public Surgery getSurgery() {
        return surgery;
    }

    public void setSurgery(Surgery surgery) {
        this.surgery = surgery;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Hospital getHospital() {
        return hospital;
    }

    public void setHospital(Hospital hospital) {
        this.hospital = hospital;
    }

    public CustomerVisit getVisit() {
        return visit;
    }

    public void setVisit(CustomerVisit visit) {
        this.visit = visit;
    }

    public Date getBeginningDate() {
        return beginningDate;
    }

    public void setBeginningDate(Date beginningDate) {
        this.beginningDate = beginningDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
