package contactBook;

import java.util.Objects;

public class Address {
    private String name;
    private String street;
    private String district;
    private String number;
    private String city;
    private CountryState countryState;
    private String zipCode;

    private Address() {}

    public Address(
            String name,
            String street,
            String district,
            String number,
            String city,
            contactBook.CountryState countryState,
            String zipCode
    ) {
        this.name = name;
        this.street = street;
        this.district = district;
        this.number = number;
        this.city = city;
        this.countryState = countryState;
        this.zipCode = zipCode;
    }

    public String getName() { return name; }

    public String getStreet() {
        return street;
    }

    public String getDistrict() {
        return district;
    }

    public String getNumber() {
        return number;
    }

    public String getCity() { return city; }

    public CountryState getCountryState() {
        return countryState;
    }

    public String getZipCode() {
        return zipCode;
    }

    @Override
    public String toString() {
        return String.format(
                """
                        Endereco: %s
                        %s. numero: %s.
                        Bairro: %s. %s-%s
                        CEP: %s
                """,
                name, street, number, district, city, countryState, zipCode
        );
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return number.equals(address.number) && zipCode.equals(address.zipCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, zipCode);
    }
}
