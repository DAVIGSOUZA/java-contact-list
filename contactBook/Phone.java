package contactBook;

import java.util.Objects;

public class Phone {
    private String name;
    private String ddd;
    private String number;

    public Phone(String name, String ddd, String number) {
        this.name = name;
        this.ddd = ddd;
        this.number = number;
    }

    @Override
    public String toString() {
        return String.format("Telefone: %s, (%s) %s \n", name, ddd, number);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Phone phone = (Phone) o;
        return ddd.equals(phone.ddd) && number.equals(phone.number);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ddd, number);
    }
}
