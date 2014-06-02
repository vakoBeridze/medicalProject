package ge.tsu.server.entities;

import javax.persistence.Entity;

@Entity
public class Doctor extends Person {

    private Integer doctorType;

    private String license;

    private String password;

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
