package ge.tsu.shared;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Vako on 08.06.2014.
 */
public class BloodTransfusionModel implements Serializable {

    private long id;
    private UserModel customerModel;
    private UserModel issuerModel;
    private Date transfusionDate;
    private Integer bloodVolume;
    private String comment;

    public BloodTransfusionModel() {
    }

    // TODO add HospitalModel


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

    public UserModel getIssuerModel() {
        return issuerModel;
    }

    public void setIssuerModel(UserModel issuerModel) {
        this.issuerModel = issuerModel;
    }

    public Date getTransfusionDate() {
        return transfusionDate;
    }

    public void setTransfusionDate(Date transfusionDate) {
        this.transfusionDate = transfusionDate;
    }

    public Integer getBloodVolume() {
        return bloodVolume;
    }

    public void setBloodVolume(Integer bloodVolume) {
        this.bloodVolume = bloodVolume;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
