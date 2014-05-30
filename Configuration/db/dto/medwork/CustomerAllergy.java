/**
 *
 */
package ge.tsu.server.db.dto.medwork;

import ge.tsu.server.db.dto.Person;
import ge.tsu.server.db.dto.medfacts.Allergy;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


/**
 * @author vamekh
 */
public class CustomerAllergy {
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
}
