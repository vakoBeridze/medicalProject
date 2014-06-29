/**
 *
 */
package ge.tsu.server.db.dto.medwork;


import ge.tsu.server.db.dto.meddocs.CustomerVisit;
import ge.tsu.server.db.dto.medfacts.Appealing;

import javax.persistence.*;

/**
 * @author vamekh
 */

@Entity
@Table(name = "CUSTOMER_APPEALINGS")
public class CustomerAppealing {
    @Id
    private Long id;
    @OneToOne
    @JoinColumn(name = "appealing_id")
    private Appealing appealing;
    @JoinColumn(name = "visit_id")
    private CustomerVisit visit;
}
