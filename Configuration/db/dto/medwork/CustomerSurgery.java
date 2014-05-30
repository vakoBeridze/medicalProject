/**
 *
 */
package ge.tsu.server.db.dto.medwork;

import ge.tsu.server.db.dto.Hospital;
import ge.tsu.server.db.dto.Person;
import ge.tsu.server.db.dto.meddocs.CustomerVisit;
import ge.tsu.server.db.dto.medfacts.Surgery;

import javax.persistence.*;
import java.util.Date;

/**
 * @author vamekh
 *         აღწერს  რა ქირურგიული ოპერაციები აქვს გაკეთებული პიროვნებას
 */

@Entity
public class CustomerSurgery {
    @Id
    private Long id;
    @JoinColumn(name = "customer_id")
    private Person customer;
    @JoinColumn(name = "surgery_id")
    private Surgery surgery;
    private String comment;
    @JoinColumn(name = "hospital_id")
    private Hospital hospital;

    @JoinColumn(name = "visit_id")
    private CustomerVisit visit;

    @Temporal(value = TemporalType.TIMESTAMP)
    private Date beginningDate;
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date endDate;
}
