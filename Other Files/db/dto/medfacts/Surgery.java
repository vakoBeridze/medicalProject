/**
 *
 */
package ge.tsu.server.db.dto.medfacts;

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
}
