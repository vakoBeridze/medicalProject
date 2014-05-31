/**
 *
 */
package ge.tsu.server.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author vamekh
 */

@Entity
public class InsuranceCompany {
    @Id
    private Long id;
    private String name;

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
