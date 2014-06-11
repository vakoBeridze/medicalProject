package ge.tsu.shared;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by vako on 11/06/14.
 */
public class PoliceModel implements Serializable {

    private long id;

    private UserModel customerModel;
    private InsuranceCompanyModel insuranceCompanyModel;

    private String policeNumber;
    private Date insuranceBeginDate;
    private Date insuranceExpireDate;

    public PoliceModel() {
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

    public String getPoliceNumber() {
        return policeNumber;
    }

    public void setPoliceNumber(String policeNumber) {
        this.policeNumber = policeNumber;
    }

    public InsuranceCompanyModel getInsuranceCompanyModel() {
        return insuranceCompanyModel;
    }

    public void setInsuranceCompanyModel(InsuranceCompanyModel insuranceCompanyModel) {
        this.insuranceCompanyModel = insuranceCompanyModel;
    }

    public Date getInsuranceBeginDate() {
        return insuranceBeginDate;
    }

    public void setInsuranceBeginDate(Date insuranceBeginDate) {
        this.insuranceBeginDate = insuranceBeginDate;
    }

    public Date getInsuranceExpireDate() {
        return insuranceExpireDate;
    }

    public void setInsuranceExpireDate(Date insuranceExpireDate) {
        this.insuranceExpireDate = insuranceExpireDate;
    }
}
