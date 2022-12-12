package View;

import contactBook.*;
import util.ConsoleUIHelper;

import java.io.Console;
import java.util.List;

public class ContactView {

    private static void successFeedback() {
        System.out.println("Operação realizada com sucesso!");
    }

    private static void failedFeedBack(String errorMessage) {
        System.out.printf("Erro ao cadastrar: %s %n", errorMessage);
    }

    public static Contact getContactById(ContactBook contactBook) {
        int contactIndex = Integer.parseInt(ConsoleUIHelper.askSimpleInput("Informe o ID do contato"));
        return contactBook.getContactByIndex(contactIndex);
    }
    public static void contactDetailsById(ContactBook contactBook) {
        ConsoleUIHelper.drawLine();
        ConsoleUIHelper.drawWithPadding("DETALHE DO CONTATO");
        ConsoleUIHelper.drawLine();

        System.out.println(getContactById(contactBook).toString());
    }

    public static void contactDetailsByName(ContactBook contactBook) {
        String search = (ConsoleUIHelper.askSimpleInput("Digite sua busca:"));
        Contact contact = contactBook.getContactByFullName(search);
        ConsoleUIHelper.drawLine();
        ConsoleUIHelper.drawWithPadding("DETALHE DO CONTATO");
        ConsoleUIHelper.drawLine();
        System.out.println(contact.toString());
    }

    public static void addContact(ContactBook contactBook) {
        ConsoleUIHelper.drawLine();
        ConsoleUIHelper.drawWithPadding("ADICIONAR CONTATO");
        ConsoleUIHelper.drawLine();

        String name = ConsoleUIHelper.askNoEmptyInput("Nome do contato");
        String lastName = ConsoleUIHelper.askNoEmptyInput("Sobrenome do contato");
        String email = ConsoleUIHelper.askNoEmptyInput("Email do contato");

        Contact newContact = new Contact(name, lastName, email);

        try {
            contactBook.addContacts(List.of(newContact));
            successFeedback();
        } catch (RuntimeException e) {
            failedFeedBack(e.getMessage());
        }
    }

    public static void addPhoneToContact(ContactBook contactBook) {
        ConsoleUIHelper.drawLine();
        ConsoleUIHelper.drawWithPadding("ADICIONAR TELEFONE AO CONTATO");
        ConsoleUIHelper.drawLine();

        Contact contact = getContactById(contactBook);

        String name = ConsoleUIHelper.askSimpleInput("Informe um nome para este telefone(Ex: Fixo do vizinho)");
        String ddd = ConsoleUIHelper.askSimpleInput("Informe o DDD");
        String number = ConsoleUIHelper.askSimpleInput("Informe o numero do telefone");

        Phone newPhone = new Phone(name, ddd, number);

        try {
            contact.addPhone(newPhone);
            successFeedback();
        } catch (RuntimeException e) {
            failedFeedBack(e.getMessage());
        }
    }

    public static void addAddressToContact(ContactBook contactBook) {
        ConsoleUIHelper.drawLine();
        ConsoleUIHelper.drawWithPadding("ADICIONAR ENDERECO AO CONTATO");
        ConsoleUIHelper.drawLine();

        Contact contact = getContactById(contactBook);

        String name = ConsoleUIHelper.askSimpleInput("Informe um nome para este endereco(Ex: Casa de praia)");
        String street = ConsoleUIHelper.askSimpleInput("Rua/avenida/logradouro");
        String number = ConsoleUIHelper.askSimpleInput("Numero do endereco");
        String district = ConsoleUIHelper.askSimpleInput("Bairro");
        String city = ConsoleUIHelper.askSimpleInput("Cidade");
        String uf = ConsoleUIHelper.askSimpleInput("Informe a sigla do estado");
        String zipCode = ConsoleUIHelper.askSimpleInput("CEP");

        Address newAddress = new Address(
                name,
                street,
                district,
                number,
                city,
                CountryState.valueOf(uf.toUpperCase()),
                zipCode
        );

        try {
            contact.addAddress(newAddress);
            successFeedback();
        } catch (RuntimeException e) {
            failedFeedBack(e.getMessage());
        }
    }

    public static void removeContact(ContactBook contactBook) {
        ConsoleUIHelper.drawLine();
        ConsoleUIHelper.drawWithPadding("REMOVER CONTATO");
        ConsoleUIHelper.drawLine();

        int contactIndex = Integer.parseInt(
                ConsoleUIHelper.askSimpleInput("Informe o ID do contato que deseja remover")
        );

        try {
            contactBook.removeContact(contactIndex);
            successFeedback();
        } catch (RuntimeException e) {
            failedFeedBack(e.getMessage());
        }
    }

    public static void showContacts(ContactBook contactBook) {
        List<Contact> contacts = contactBook.getContacts();

        if (contacts.isEmpty()) {
            ConsoleUIHelper.drawWithPadding("Nenhum contato cadastrado.");
            return;
        }

        contactBook.getContacts().stream().map(contact -> {
            StringBuilder sb = new StringBuilder();

            String optionsValue = String.valueOf(contacts.indexOf(contact));
            sb.append(ConsoleUIHelper.columnPaddingRight(optionsValue,3));
            sb.append(ConsoleUIHelper.columnPaddingRight(contact.getFullName(), 25));
            sb.append(ConsoleUIHelper.columnPaddingRight(contact.getEmail(), 17));

            return sb.toString();
        }).forEach(System.out::println);

        ConsoleUIHelper.drawLine();
    }

    public static void showPhones(Contact contact) {
        List<Phone> phones = contact.getPhones();

        if (phones.isEmpty()) {
            ConsoleUIHelper.drawWithPadding("Nenhum telefone cadastrado.");
            return;
        }

        phones.stream().map(phone -> {
            StringBuilder sb = new StringBuilder();

            String optionValue = String.valueOf(phones.indexOf(phone));
            sb.append(ConsoleUIHelper.columnPaddingRight(optionValue,3));
            sb.append(ConsoleUIHelper.columnPaddingRight(phone.toString(), 52));

            return sb.toString();
        }).forEach(System.out::println);

        ConsoleUIHelper.drawLine();
    }

    public static void showAddresses(Contact contact) {
        List<Address> Addresses = contact.getAddresses();

        if (Addresses.isEmpty()) {
            ConsoleUIHelper.drawWithPadding("Nenhum endereco cadastrado.");
            return;
        }

        Addresses.stream().map(address -> {
            StringBuilder sb = new StringBuilder();

            String optionValue = String.valueOf(Addresses.indexOf(address));

            sb.append(ConsoleUIHelper.columnPaddingRight(optionValue,3));
            sb.append(ConsoleUIHelper.columnPaddingRight(
                    address.getStreet() + ", " + address.getNumber(),
                    20
            ));
            sb.append(ConsoleUIHelper.columnPaddingRight(
                    address.getDistrict() + ", " + address.getCity() + "/" + address.getCountryState(),
                    22
            ));
            sb.append(ConsoleUIHelper.columnPaddingRight(address.getZipCode(), 10));

            return sb.toString();
        }).forEach(System.out::println);

        ConsoleUIHelper.drawLine();
    }
    public static void removePhoneFromContact(ContactBook contactBook) {
        ConsoleUIHelper.drawLine();
        ConsoleUIHelper.drawWithPadding("REMOVER TELEFONE DO CONTATO");
        ConsoleUIHelper.drawLine();

        Contact contact = getContactById(contactBook);

        showPhones(contact);

        int phoneIndex = Integer.parseInt(
                ConsoleUIHelper.askSimpleInput("Informe o ID do telefone que deseja remover")
        );

        try {
            contact.removePhone(phoneIndex);
            successFeedback();
        } catch (RuntimeException e) {
            failedFeedBack(e.getMessage());
        }
    }

    public static void removeAddressFromContact(ContactBook contactBook) {
        ConsoleUIHelper.drawLine();
        ConsoleUIHelper.drawWithPadding("REMOVER ENDERECO DO CONTATO");
        ConsoleUIHelper.drawLine();

        Contact contact = getContactById(contactBook);

        showAddresses(contact);

        int addressIndex = Integer.parseInt(
                ConsoleUIHelper.askSimpleInput("Informe o ID do endereco que deseja remover")
        );

        try {
            contact.removeAddress(addressIndex);
            successFeedback();
        } catch (RuntimeException e) {
            failedFeedBack(e.getMessage());
        }
    }

    public static void removeAllContacts(ContactBook contactBook) {
        ConsoleUIHelper.drawLine();
        ConsoleUIHelper.drawWithPadding("REMOVER TODOS CONTATOS");
        ConsoleUIHelper.drawLine();

        boolean confirm = ConsoleUIHelper.askConfirm(
                "Tem certeza que deseja excluir todos contatos?",
                "Sim",
                "Não"
        );

        if (confirm) {
            contactBook.removeAllContacts();
            successFeedback();
        }
    }
}
