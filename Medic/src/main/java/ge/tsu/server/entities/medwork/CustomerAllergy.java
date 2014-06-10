/**
 *
 */
package ge.tsu.server.entities.medwork;

import ge.tsu.server.entities.Person;
import ge.tsu.server.entities.medfacts.Allergy;

import javax.persistence.*;


/**
 * @author vamekh
 */

@Entity
@Table(name = "CUSTOMER_ALLERGY")
public class CustomerAllergy {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_id")
    private Person customer;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "allergy_id")
    private Allergy allergy;

    public Person getCustomer() {
        return customer;
    }

    public void setCustomer(Person customer) {
        this.customer = customer;
    }

    public Allergy getAllergy() {
        return allergy;
    }

    public void setAllergy(Allergy allergy) {
        this.allergy = allergy;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
