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
@Table(name = "CUSTOMER_MEASUREMENT")
public class CustomerMeasurement {
    @Id
    private Long id;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Person customer;


    @ManyToOne
    @JoinColumn(name = "mesurement_id")
    private Measurement measurement;

    @Temporal(value = TemporalType.TIMESTAMP)
    private Date measurementDate;

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

    public Measurement getMeasurement() {
        return measurement;
    }

    public void setMeasurement(Measurement mesurement) {
        this.measurement = mesurement;
    }

    public Date getMeasurementDate() {
        return measurementDate;
    }

    public void setMeasurementDate(Date mesurementDate) {
        this.measurementDate = mesurementDate;
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
