package hellojpa;

import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
public class Address {

    private String city;
    private String zipcode;
    private String street;

    public Address (){

    }

    public Address(String city, String zipcode, String street) {
        this.city = city;
        this.zipcode = zipcode;
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    private void setCity(String city) {
        this.city = city;
    }

    public String getZipcode() {
        return zipcode;
    }

    private void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getStreet() {
        return street;
    }

    private void setStreet(String street) {
        this.street = street;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return Objects.equals(city, address.city) && Objects.equals(zipcode, address.zipcode) && Objects.equals(street, address.street);
    }

    @Override
    public int hashCode() {
        return Objects.hash(city, zipcode, street);
    }
}
