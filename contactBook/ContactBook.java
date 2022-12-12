package contactBook;

import util.ConsoleUIHelper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class ContactBook {
    private List<Contact> contacts;

    public ContactBook() {
        this.contacts = new ArrayList<>();
    }

    public List<Contact> getContacts() {
        return Collections.unmodifiableList(contacts);
    }

    private boolean contactAlreadyExists(Contact contact) {
        return this.contacts.stream().anyMatch(registeredContact -> registeredContact.equals(contact));
    }

    public void addContacts(List<Contact> newContacts) {
        newContacts.forEach(newContact -> {
            if (contactAlreadyExists(newContact)) {
                throw new RuntimeException("Contato já cadastrado");
            }
        });

        if (newContacts.stream().distinct().count() != newContacts.size()) {
            throw new RuntimeException("Contatos duplicados na lista a ser adicionada");
        }

        this.contacts.addAll(newContacts);
    }

    public Contact getContactByIndex(int contactIndex) {
        return this.contacts.get(contactIndex);
    }

    public Contact getContactByFullName(String searchedName) {
        return this.contacts.stream().filter(
                contact -> contact.getFullName().toLowerCase().contains(searchedName)
        ).findFirst().get();
    }

    public void removeContact(int contactIndex) {
        if (contactIndex > this.contacts.size()) {
            throw new RuntimeException("Indice inválido");
        }

        this.contacts.remove(contactIndex);
    }

    public void removeAllContacts() {
        this.contacts.clear();
    }
}
