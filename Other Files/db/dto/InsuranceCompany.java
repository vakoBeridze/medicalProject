/**
 *
 */
package ge.tsu.server.db.dto;

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
}
