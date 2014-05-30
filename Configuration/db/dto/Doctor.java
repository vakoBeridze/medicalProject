package ge.tsu.server.db.dto;

import javax.persistence.Entity;

@Entity
public class Doctor extends Person {

    private Integer doctorType;
    private String license;
}
