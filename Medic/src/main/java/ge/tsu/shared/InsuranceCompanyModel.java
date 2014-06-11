package ge.tsu.shared;

import java.io.Serializable;

/**
 * Created by Vako on 08.06.2014.
 */
public class InsuranceCompanyModel implements Serializable {

    private long id;
    private String name;

    public InsuranceCompanyModel() {
    }

    public InsuranceCompanyModel(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
