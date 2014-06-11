package ge.tsu.shared;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by vako on 11/06/14.
 */
public class CustomerSurgeryModel implements Serializable {

    private Long id;
    private UserModel customerModel;
    private SurgeryModel surgeryModel;
    private String comment;

    private Date beginningDate;
    private Date endDate;

    // TODO add CustomerVisit and Hospital

    public CustomerSurgeryModel() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserModel getCustomerModel() {
        return customerModel;
    }

    public void setCustomerModel(UserModel customerModel) {
        this.customerModel = customerModel;
    }

    public SurgeryModel getSurgeryModel() {
        return surgeryModel;
    }

    public void setSurgeryModel(SurgeryModel surgeryModel) {
        this.surgeryModel = surgeryModel;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getBeginningDate() {
        return beginningDate;
    }

    public void setBeginningDate(Date beginningDate) {
        this.beginningDate = beginningDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CustomerSurgeryModel that = (CustomerSurgeryModel) o;

        if (surgeryModel != null ? !surgeryModel.equals(that.surgeryModel) : that.surgeryModel != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return surgeryModel != null ? surgeryModel.hashCode() : 0;
    }
}
