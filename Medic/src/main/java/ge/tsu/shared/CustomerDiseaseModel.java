package ge.tsu.shared;


import java.io.Serializable;
import java.util.Date;

/**
 * Created by vako on 11/06/14.
 */
public class CustomerDiseaseModel implements Serializable {

    private long id;
    private UserModel customerModel;
    private DiseaseModel diseaseModel;
    //სიმწვავე(მსუბუქი, ქრონიკული, მწვავე, ქვემწვავე)
    private Integer diseaseStrength;
    private Boolean isInfection;
    private Boolean isGenetic;

    private Date detectionDate;
    private Date cureDate;
    private Date justificationDate;

    private Boolean isFinalDiagnose;
    private Boolean isPreDiagnose;

    //ამისთვის განსაზღვრულია enum-ი.
    private Integer diagnoseState;

    // TODO add previousDiagnose,  doctor, visit


    public CustomerDiseaseModel() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public UserModel getCustomerModel() {
        return customerModel;
    }

    public void setCustomerModel(UserModel customerModel) {
        this.customerModel = customerModel;
    }

    public DiseaseModel getDiseaseModel() {
        return diseaseModel;
    }

    public void setDiseaseModel(DiseaseModel diseaseModel) {
        this.diseaseModel = diseaseModel;
    }

    public Integer getDiseaseStrength() {
        return diseaseStrength;
    }

    public void setDiseaseStrength(Integer diseaseStrength) {
        this.diseaseStrength = diseaseStrength;
    }

    public Boolean getIsInfection() {
        return isInfection;
    }

    public void setIsInfection(Boolean isInfection) {
        this.isInfection = isInfection;
    }

    public Boolean getIsGenetic() {
        return isGenetic;
    }

    public void setIsGenetic(Boolean isGenetic) {
        this.isGenetic = isGenetic;
    }

    public Date getDetectionDate() {
        return detectionDate;
    }

    public void setDetectionDate(Date detectionDate) {
        this.detectionDate = detectionDate;
    }

    public Date getCureDate() {
        return cureDate;
    }

    public void setCureDate(Date cureDate) {
        this.cureDate = cureDate;
    }

    public Date getJustificationDate() {
        return justificationDate;
    }

    public void setJustificationDate(Date justificationDate) {
        this.justificationDate = justificationDate;
    }

    public Boolean getIsFinalDiagnose() {
        return isFinalDiagnose;
    }

    public void setIsFinalDiagnose(Boolean isFinalDiagnose) {
        this.isFinalDiagnose = isFinalDiagnose;
    }

    public Boolean getIsPreDiagnose() {
        return isPreDiagnose;
    }

    public void setIsPreDiagnose(Boolean isPreDiagnose) {
        this.isPreDiagnose = isPreDiagnose;
    }

    public Integer getDiagnoseState() {
        return diagnoseState;
    }

    public void setDiagnoseState(Integer diagnoseState) {
        this.diagnoseState = diagnoseState;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CustomerDiseaseModel that = (CustomerDiseaseModel) o;

        if (diseaseModel != null ? !diseaseModel.equals(that.diseaseModel) : that.diseaseModel != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return diseaseModel != null ? diseaseModel.hashCode() : 0;
    }
}
