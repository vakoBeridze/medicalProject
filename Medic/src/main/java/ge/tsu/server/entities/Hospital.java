package ge.tsu.server.entities;

import javax.persistence.*;

@Entity
@Table(name = "HOSPITAL")
public class Hospital {
    @Id
    private Long id;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    private Hospital parentHospital;

    private String name;

    @ManyToOne
    @JoinColumn(name = "address_id")
    private Address address;

    private String organizationCode;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Hospital getParentHospital() {
        return parentHospital;
    }

    public void setParentHospital(Hospital parentHospital) {
        this.parentHospital = parentHospital;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getOrganizationCode() {
        return organizationCode;
    }

    public void setOrganizationCode(String organizationCode) {
        this.organizationCode = organizationCode;
    }
}
