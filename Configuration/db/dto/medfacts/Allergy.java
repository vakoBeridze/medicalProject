/**
 *
 */
package ge.tsu.server.db.dto.medfacts;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author vamekh
 */

@Entity
@Table(name = "ALLERGIES")
public class Allergy {
    @Id
    private Long id;
    private String name;
    private String standart;
    private String comment;
}
