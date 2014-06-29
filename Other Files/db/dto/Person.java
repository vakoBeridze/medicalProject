/**
 *
 */
package ge.tsu.server.db.dto;

import ge.tsu.server.db.dto.medwork.BloodTransfusion;
import ge.tsu.server.db.dto.medwork.CustomerAllergy;
import ge.tsu.server.db.dto.medwork.CustomerDisease;
import ge.tsu.server.db.dto.medwork.CustomerSurgery;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author vamekh
 */

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Person {
    @Id
    private Long id;
    private String pn;

    @OneToMany
    @JoinTable(name = "person_cityzenships",
            joinColumns = @JoinColumn(name = "person_id"),
            inverseJoinColumns = @JoinColumn(name = "cityzenship_addr_id"))
    private List<Address> citizenship = new ArrayList<Address>();

    @Temporal(value = TemporalType.TIMESTAMP)
    private Date birthDate;
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date deathDate;

    private String firstName;
    private String lastName;
    private String fatherName;
    private Integer gender;
    private String maritalStatus;

    private Integer invalidStatus;
    private Integer bloodGroup;
    private Integer rhFactory;

    private String professionAndJob;

    private String phoneNumber;
    private String email;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "address_id")
    private Address address;


    @OneToMany(mappedBy = "customer")
    List<BloodTransfusion> bloodTransfusions = new ArrayList<BloodTransfusion>();

    @OneToMany(mappedBy = "customer")
    List<CustomerAllergy> customerAllergies = new ArrayList<CustomerAllergy>();

    @OneToMany(mappedBy = "customer")
    List<CustomerSurgery> customerSurgeries = new ArrayList<CustomerSurgery>();

    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY)
    List<CustomerDisease> customerDiseases = new ArrayList<CustomerDisease>();

    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY)
    List<Police> customerPolices = new ArrayList<Police>();


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPn() {
        return pn;
    }

    public void setPn(String pn) {
        this.pn = pn;
    }

    public List<Address> getCitizenship() {
        return citizenship;
    }

    public void setCitizenship(List<Address> citizenship) {
        this.citizenship = citizenship;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Date getDeathDate() {
        return deathDate;
    }

    public void setDeathDate(Date deathDate) {
        this.deathDate = deathDate;
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

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<BloodTransfusion> getBloodTransfusions() {
        return bloodTransfusions;
    }

    public void setBloodTransfusions(List<BloodTransfusion> bloodTransfusions) {
        this.bloodTransfusions = bloodTransfusions;
    }

    public List<CustomerAllergy> getCustomerAllergies() {
        return customerAllergies;
    }

    public void setCustomerAllergies(List<CustomerAllergy> customerAllergies) {
        this.customerAllergies = customerAllergies;
    }

    public List<CustomerSurgery> getCustomerSurgeries() {
        return customerSurgeries;
    }

    public void setCustomerSurgeries(List<CustomerSurgery> customerSurgeries) {
        this.customerSurgeries = customerSurgeries;
    }

    public List<CustomerDisease> getCustomerDiseases() {
        return customerDiseases;
    }

    public void setCustomerDiseases(List<CustomerDisease> customerDiseases) {
        this.customerDiseases = customerDiseases;
    }

    public List<Police> getCustomerPolices() {
        return customerPolices;
    }

    public void setCustomerPolices(List<Police> customerPolices) {
        this.customerPolices = customerPolices;
    }


}
