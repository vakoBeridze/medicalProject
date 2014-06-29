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
@Table(name = "MEASUREMENTS")
public class Measurement {
    @Id
    private Long id;
    private String name;
    private String unit;
}
