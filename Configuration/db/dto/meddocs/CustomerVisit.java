package ge.tsu.server.db.dto.meddocs;

import ge.tsu.server.db.dto.Hospital;
import ge.tsu.server.db.dto.Person;
import ge.tsu.server.db.dto.medfacts.Appealing;

import javax.persistence.*;
import java.util.Date;
import java.util.List;


@Entity
@Table(name = "CUSTOMER_VISITS")
public class CustomerVisit {
    @Id
    private Long id;

    private Integer visitType;

    @Temporal(value = TemporalType.TIMESTAMP)
    private Date visitDate;
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date leaveDate;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Person customer;
    @ManyToOne
    @JoinColumn(name = "hospital_id")
    private Hospital hospital;

    @ManyToOne
    @JoinColumn(name = "replace_hospital_id")
    private Hospital replaceHospital;

    private Integer visitConsequance;
    private String note;


    @OneToMany(fetch = FetchType.LAZY, mappedBy = "visit")
    private List<Appealing> appealings;
}
