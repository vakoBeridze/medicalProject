/**
 *
 */
package ge.tsu.server.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author vamekh
 */

@Entity
@Table(name = "INSURANCE_COMPANY")
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
