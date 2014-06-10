/**
 *
 */
package ge.tsu.server.entities.medwork;

import ge.tsu.server.entities.Hospital;
import ge.tsu.server.entities.Person;

import javax.persistence.*;
import java.util.Date;

/**
 * @author vamekh
 */

@Entity
@Table(name = "BLOOD_TRANSFUSION")
public class BloodTransfusion {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "reciver_id")
    private Person customer;

    @ManyToOne
    @JoinColumn(name = "issuer_id")
    private Person issuer;

    private Date transfusionDate;
    private Integer bloodVolume;
    private String comment;

    @ManyToOne
    @JoinColumn(name = "hospital_id")
    private Hospital hospital;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Person getCustomer() {
        return customer;
    }

    public void setCustomer(Person receiver) {
        this.customer = receiver;
    }

    public Person getIssuer() {
        return issuer;
    }

    public void setIssuer(Person issuer) {
        this.issuer = issuer;
    }

    public Date getTransfusionDate() {
        return transfusionDate;
    }

    public void setTransfusionDate(Date transfusionDate) {
        this.transfusionDate = transfusionDate;
    }

    public Integer getBloodVolume() {
        return bloodVolume;
    }

    public void setBloodVolume(Integer bloodVolume) {
        this.bloodVolume = bloodVolume;
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
}
