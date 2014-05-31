/**
 *
 */
package ge.tsu.server.entities.medwork;


import ge.tsu.server.entities.meddocs.CustomerVisit;
import ge.tsu.server.entities.medfacts.Appealing;

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

    // FIXME
    @ManyToOne
    @JoinColumn(name = "visit_id")
    private CustomerVisit visit;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Appealing getAppealing() {
        return appealing;
    }

    public void setAppealing(Appealing appealing) {
        this.appealing = appealing;
    }

    public CustomerVisit getVisit() {
        return visit;
    }

    public void setVisit(CustomerVisit visit) {
        this.visit = visit;
    }
}
