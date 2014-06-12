package ge.tsu.shared;

import com.google.gwt.user.client.rpc.IsSerializable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by vako on 29/05/14.
 */
public class UserModel implements IsSerializable, Serializable {

    private long id;
    private String pn;
    private String firstName;
    private String lastName;
    private String fatherName;
    private Integer gender;
    private String maritalStatus;

    private Integer invalidStatus;
    private Integer bloodGroup;
    private Integer rhFactory;

    private Date birthDate;

    private String professionAndJob;

    private String license;

    private String phoneNumber;
    private String email;

    private String userName;
    private String password;

    private boolean doctor;

    List<BloodTransfusionModel> bloodTransfusionModels = new ArrayList<BloodTransfusionModel>();
    List<CustomerAllergyModel> customerAllergyModels = new ArrayList<CustomerAllergyModel>();
    List<CustomerSurgeryModel> customerSurgeryModels = new ArrayList<CustomerSurgeryModel>();
    List<CustomerDiseaseModel> customerDiseaseModels = new ArrayList<CustomerDiseaseModel>();
    List<PoliceModel> customerPoliceModels = new ArrayList<PoliceModel>();
//    List<CustomerImmunization> customerImmunizations = new ArrayList<CustomerImmunization>();


    public UserModel() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPn() {
        return pn;
    }

    public void setPn(String pn) {
        this.pn = pn;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public Integer getInvalidStatus() {
        return invalidStatus;
    }

    public void setInvalidStatus(Integer invalidStatus) {
        this.invalidStatus = invalidStatus;
    }

    public Integer getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(Integer bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public Integer getRhFactory() {
        return rhFactory;
    }

    public void setRhFactory(Integer rhFactory) {
        this.rhFactory = rhFactory;
    }

    public String getProfessionAndJob() {
        return professionAndJob;
    }

    public void setProfessionAndJob(String professionAndJob) {
        this.professionAndJob = professionAndJob;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isDoctor() {
        return doctor;
    }

    public void setDoctor(boolean doctor) {
        this.doctor = doctor;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
    }

    public List<BloodTransfusionModel> getBloodTransfusionModels() {
        return bloodTransfusionModels;
    }

    public void setBloodTransfusionModels(List<BloodTransfusionModel> bloodTransfusionModels) {
        this.bloodTransfusionModels = bloodTransfusionModels;
    }

    public List<CustomerAllergyModel> getCustomerAllergyModels() {
        return customerAllergyModels;
    }

    public void setCustomerAllergyModels(List<CustomerAllergyModel> customerAllergyModels) {
        this.customerAllergyModels = customerAllergyModels;
    }

    public List<CustomerSurgeryModel> getCustomerSurgeryModels() {
        return customerSurgeryModels;
    }

    public void setCustomerSurgeryModels(List<CustomerSurgeryModel> customerSurgeryModels) {
        this.customerSurgeryModels = customerSurgeryModels;
    }

    public List<CustomerDiseaseModel> getCustomerDiseaseModels() {
        return customerDiseaseModels;
    }

    public void setCustomerDiseaseModels(List<CustomerDiseaseModel> customerDiseaseModels) {
        this.customerDiseaseModels = customerDiseaseModels;
    }

    public List<PoliceModel> getCustomerPoliceModels() {
        return customerPoliceModels;
    }

    public void setCustomerPoliceModels(List<PoliceModel> customerPoliceModels) {
        this.customerPoliceModels = customerPoliceModels;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserModel userModel = (UserModel) o;

        if (id != userModel.id) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }
}
