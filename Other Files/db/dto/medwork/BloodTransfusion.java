/**
 *
 */
package ge.tsu.server.db.dto.medwork;

import ge.tsu.server.db.dto.Hospital;
import ge.tsu.server.db.dto.Person;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Date;

/**
 * @author vamekh
 */

@Entity
public class BloodTransfusion {
    @Id
    private Long id;

    @JoinColumn(name = "reciver_pn")
    private Person reciver;

    @ManyToOne
    @JoinColumn(name = "issuer_pn")
    private Person issuer;
    private Date transfusionDate;
    private Integer bloodVolume;
    private String comment;
    @JoinColumn(name = "hospital_id")
    private Hospital hospital;
}
