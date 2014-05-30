/**
 *
 */
package ge.tsu.server.db.dto.medwork;

import ge.tsu.server.db.dto.Doctor;
import ge.tsu.server.db.dto.Person;
import ge.tsu.server.db.dto.meddocs.CustomerVisit;
import ge.tsu.server.db.dto.medfacts.Disease;

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

    @JoinColumn(name = "customer_id")
    private Person customer;

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

    @JoinColumn(name = "previus_diagnose_id")
    private CustomerDisease previousDiagnose;

    private Boolean isFinalDiagnose;
    private Boolean isPreDiagnose;

    //ამისთვის განსაზღვრულია enum-ი.
    private Integer diagnoseState;

    @JoinColumn(name = "doctor_id")
    private Doctor doctor;


    @JoinColumn(name = "customer_visit_id")
    private CustomerVisit visit;
}
