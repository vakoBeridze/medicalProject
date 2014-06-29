package ge.tsu.server.db.dto;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

@Entity
public class Hospital {
    @Id
    private Long id;
    @JoinColumn(name = "parent_id")
    private Hospital parentHospital;
    private String name;
    private Address address;
    private String organizationCode;
}
