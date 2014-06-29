package ge.tsu.server.db.dto;


import ge.tsu.server.db.dto.enums.AddressType;

import javax.persistence.*;

@Entity
@Table(name = "ADDRESSES")
public class Address {
    @Id
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "parent_address_id")
    private Address parentAddress;

    // ამისთვის დაწერილია enum-ი AddressType
    private String addressType;

    private String addressTitle;

    @Override
    public String toString() {
        try {
            String result = addressTitle + " ";
            Address parent = getParentAddress();
            if (addressType.equals(AddressType.COUNTRY)) {
                return addressTitle;
            }
            do {
                result += " " + parent.getAddressTitle();
                parent = parent.getParentAddress();
            }
            while (parent != null);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Address getParentAddress() {
        return parentAddress;
    }

    public void setParentAddress(Address parentAddress) {
        this.parentAddress = parentAddress;
    }

    public String getAddressType() {
        return addressType;
    }

    public void setAddressType(String addressType) {
        this.addressType = addressType;
    }

    public String getAddressTitle() {
        return addressTitle;
    }

    public void setAddressTitle(String addressTitle) {
        this.addressTitle = addressTitle;
    }

}
