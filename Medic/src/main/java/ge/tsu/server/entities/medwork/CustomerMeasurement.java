package ge.tsu.server.entities.medwork;

import ge.tsu.server.entities.Hospital;
import ge.tsu.server.entities.Person;
import ge.tsu.server.entities.medfacts.Measurement;

import javax.persistence.*;
import java.util.Date;

/*
 * აღიწერება სხვადასხვა გაზომვები, მაგალითად წნევა, პულსი...
 * 
 * 
 */

@Entity
public class CustomerMeasurement {
    @Id
    private Long id;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Person customer;


    @ManyToOne
    @JoinColumn(name = "mesurement_id")
    private Measurement mesurement;

    @Temporal(value = TemporalType.TIMESTAMP)
    private Date mesurementDate;

    private String value;
    private String note;


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

    public void setCustomer(Person customer) {
        this.customer = customer;
    }

    public Measurement getMesurement() {
        return mesurement;
    }

    public void setMesurement(Measurement mesurement) {
        this.mesurement = mesurement;
    }

    public Date getMesurementDate() {
        return mesurementDate;
    }

    public void setMesurementDate(Date mesurementDate) {
        this.mesurementDate = mesurementDate;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Hospital getHospital() {
        return hospital;
    }

    public void setHospital(Hospital hospital) {
        this.hospital = hospital;
    }
}
