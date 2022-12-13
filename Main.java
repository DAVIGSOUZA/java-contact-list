import View.ContactView;
import contactBook.ContactBook;
import util.ConsoleUIHelper;
import util.JsonHelper;

public class Main {
    public static void main(String[] args) {
//        ContactBook contactBook = new ContactBook();
        ConsoleUIHelper.drawLine();
        ConsoleUIHelper.drawWithPadding("Inicializando aplicação");
        ConsoleUIHelper.fillVSpace(1);

        ContactBook contactBook = JsonHelper.importFromJson();

        ConsoleUIHelper.fillVSpace(1);

        int choice = 0;
        do {

            ConsoleUIHelper.drawHeader("AGENDA");
            ContactView.showContacts(contactBook);

            int option = ConsoleUIHelper.askChooseOption(
                    "Escolha uma opcao",
                    "Ver detalhes de um contato",   //0
                    "Buscar contato pelo nome",             //1
                    "Adicionar contato",                    //2
                    "Adicionar telefone a um contato",      //3
                    "Adicionar endereco a um contato",      //4
                    "Remover contato",                      //5
                    "Remover telefone de um contato",       //6
                    "Remover endereco de um contato",       //7
                    "Limpar agenda (remove todos contatos)" //8
            );

            switch (option) {
                case 0 -> ContactView.contactDetailsById(contactBook);
                case 1 -> ContactView.contactDetailsByName(contactBook);
                case 2 -> ContactView.addContact(contactBook);
                case 3 -> ContactView.addPhoneToContact(contactBook);
                case 4 -> ContactView.addAddressToContact(contactBook);
                case 5 -> ContactView.removeContact(contactBook);
                case 6 -> ContactView.removePhoneFromContact(contactBook);
                case 7 -> ContactView.removeAddressFromContact(contactBook);
                case 8 -> ContactView.removeAllContacts(contactBook);
                default -> {}
            }

            choice = ConsoleUIHelper.askChooseOption("Deseja continuar?","Sim","Não");
        } while (choice == 0);
    }
}
