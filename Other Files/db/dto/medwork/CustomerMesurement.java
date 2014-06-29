package ge.tsu.server.db.dto.medwork;

import ge.tsu.server.db.dto.Hospital;
import ge.tsu.server.db.dto.Person;
import ge.tsu.server.db.dto.medfacts.Measurement;

import javax.persistence.*;
import java.util.Date;

/*
 * აღიწერება სხვადასხვა გაზომვები, მაგალითად წნევა, პულსი...
 * 
 * 
 */

@Entity
public class CustomerMesurement {
    @Id
    private Long id;
    @JoinColumn(name = "customer_id")
    private Person customer;
    @JoinColumn(name = "mesurement_id")
    private Measurement mesurement;
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date mesurementDate;
    private String value;
    private String note;
    @JoinColumn(name = "hospital_id")
    private Hospital hospital;
}
