package ge.tsu.server.db.dto;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Police {

    @Id
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_pn")
    private Person customer;

    private String policeNumber;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "company_id")
    private InsuranceCompany company;

    @Temporal(value = TemporalType.DATE)
    private Date insuranceBeginDate;

    @Temporal(value = TemporalType.DATE)
    private Date insuranceExpireDate;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Person getCustomer() {
        return customer;
    }

    public void setCustomer(Person customer) {
        this.customer = customer;
    }

    public String getPoliceNumber() {
        return policeNumber;
    }

    public void setPoliceNumber(String policeNumber) {
        this.policeNumber = policeNumber;
    }

    public InsuranceCompany getCompany() {
        return company;
    }

    public void setCompany(InsuranceCompany company) {
        this.company = company;
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
