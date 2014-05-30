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
@Table(name = "DISEASES")
public class Disease {
    @Id
    private Long id;
    private String name;
    private String standart;
    private boolean isChronic;
    private boolean isInfection;
    private boolean isAllergy;
    private Integer diseaseType;
    private String comment;
    private Integer category;

}
