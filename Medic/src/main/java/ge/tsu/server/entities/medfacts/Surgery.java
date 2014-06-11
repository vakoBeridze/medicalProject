/**
 *
 */
package ge.tsu.server.entities.medfacts;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author vamekh
 *         აღწერს რა ქირურგიული ოპერაციები არსებობს ზოგადად
 */
@Entity
@Table(name = "SURGERIES")
public class Surgery {
    @Id
    private Long id;
    private String surgeryName;

    public Surgery() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSurgeryName() {
        return surgeryName;
    }

    public void setSurgeryName(String surgeryName) {
        this.surgeryName = surgeryName;
    }


}
