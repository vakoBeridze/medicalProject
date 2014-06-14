package ge.tsu.shared;

import java.io.Serializable;

/**
 * Created by Vako on 08.06.2014.
 */
public class CustomerAllergyModel implements Serializable {

    private long id;
    private UserModel userModel;
    private AllergyModel allergyModel;

    public CustomerAllergyModel() {
    }

    public CustomerAllergyModel(AllergyModel allergyModel, UserModel userModel) {
        this.allergyModel = allergyModel;
        this.userModel = userModel;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public UserModel getUserModel() {
        return userModel;
    }

    public void setUserModel(UserModel userModel) {
        this.userModel = userModel;
    }

    public AllergyModel getAllergyModel() {
        return allergyModel;
    }

    public void setAllergyModel(AllergyModel allergyModel) {
        this.allergyModel = allergyModel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CustomerAllergyModel that = (CustomerAllergyModel) o;

        if (allergyModel != null ? !allergyModel.equals(that.allergyModel) : that.allergyModel != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return allergyModel != null ? allergyModel.hashCode() : 0;
    }
}
