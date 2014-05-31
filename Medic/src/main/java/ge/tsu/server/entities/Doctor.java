package ge.tsu.server.entities;

import javax.persistence.Entity;

@Entity
public class Doctor extends Person {

    private Integer doctorType;
    private String license;

    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
    }

    public Integer getDoctorType() {
        return doctorType;
    }

    public void setDoctorType(Integer doctorType) {
        this.doctorType = doctorType;
    }
}
