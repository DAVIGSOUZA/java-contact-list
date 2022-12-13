package contactBook;

import util.ConsoleUIHelper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Contact {
    private String name;
    private String lastName;
    private String fullName;
    private String email;
    private List<Phone> phones;
    private List<Address> addresses;

    private Contact() {};

    public Contact(String name, String lastName, String email) {
        this.name = name;
        this.lastName = lastName;
        this.fullName = name + " " + lastName;
        this.email = email;
        this.phones = new ArrayList<>();
        this.addresses = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFullName() {
        return fullName;
    }

    public String getEmail() {
        return email;
    }

    public List<Phone> getPhones() {
        return Collections.unmodifiableList(phones);
    }

    public List<Address> getAddresses() {
        return Collections.unmodifiableList(addresses);
    }

    private boolean phoneAlreadyExists(Phone phone) {
        return this.phones.stream().anyMatch(registeredPhone -> registeredPhone.equals(phone));
    }

    private boolean addressAlreadyExists(Address address) {
        return this.addresses.stream().anyMatch(registeredAddress -> registeredAddress.equals(address));
    }

    public void addPhone(Phone newPhone) {
        if (phoneAlreadyExists(newPhone)) {
            throw new RuntimeException("Telefone já cadastrado!");
        }
        this.phones.add(newPhone);
    }

    public void removePhone(int phoneIndex) {
        if (phoneIndex > this.phones.size()) {
            throw new RuntimeException("Indice inválido");
        }

        this.phones.remove(phoneIndex);
    }

    public void addAddress(Address newAddress) {
        if (addressAlreadyExists(newAddress)) {
            throw new RuntimeException("Endereço já cadastrado");
        }
        this.addresses.add(newAddress);
    }

    public void removeAddress(int addressIndex) {
        if (addressIndex > this.addresses.size()) {
            throw new RuntimeException("Indice inválido");
        }

        this.addresses.remove(addressIndex);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contact contact = (Contact) o;
        return name.equals(contact.name) && lastName.equals(contact.lastName) && email.equals(contact.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, lastName, email);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%s %s \n email: %s \n", name, lastName, email));
        sb.append("Telefones\n\n");

        if (phones.isEmpty()) {
            sb.append("Nenhum telefone cadastrado. \n\n");
        } else {
            phones.forEach(phone -> sb.append(phone.toString()).append("\n"));
        }

        sb.append("Endereços\n\n");

        if (addresses.isEmpty()) {
            sb.append("Nenhum endereço cadastrado. \n\n");
        } else {
            addresses.forEach(address -> sb.append(address.toString()).append("\n"));
        }

        return sb.toString();
    }
}
