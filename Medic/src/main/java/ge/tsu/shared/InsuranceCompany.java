/**
 *
 */
package ge.tsu.shared;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * @author vamekh
 */

@Entity
public class InsuranceCompany implements Serializable {
    @Id
    private Long id;
    private String name;

    public InsuranceCompany() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
