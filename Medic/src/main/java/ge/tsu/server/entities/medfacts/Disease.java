/**
 *
 */
package ge.tsu.server.entities.medfacts;

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
    private String standard;
    private boolean isChronic;
    private boolean isInfection;
    private boolean isAllergy;
    private Integer diseaseType;
    private String comment;
    private Integer category;

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

    public String getStandard() {
        return standard;
    }

    public void setStandard(String standard) {
        this.standard = standard;
    }

    public boolean isChronic() {
        return isChronic;
    }

    public void setChronic(boolean isChronic) {
        this.isChronic = isChronic;
    }

    public boolean isInfection() {
        return isInfection;
    }

    public void setInfection(boolean isInfection) {
        this.isInfection = isInfection;
    }

    public boolean isAllergy() {
        return isAllergy;
    }

    public void setAllergy(boolean isAllergy) {
        this.isAllergy = isAllergy;
    }

    public Integer getDiseaseType() {
        return diseaseType;
    }

    public void setDiseaseType(Integer diseaseType) {
        this.diseaseType = diseaseType;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Integer getCategory() {
        return category;
    }

    public void setCategory(Integer category) {
        this.category = category;
    }
}
