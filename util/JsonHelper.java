package util;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import contactBook.ContactBook;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;

public class JsonHelper {
    public static void exportToJson (ContactBook contactBook) {
        try {
            new ObjectMapper()
                    .setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY)
                    .writeValue(Paths.get("contactBook.json").toFile(), contactBook);

            ConsoleUIHelper.drawWithPadding("Dados salvos!");

        } catch (IOException e) {
            ConsoleUIHelper.drawWithPadding("Operação não foi salva: " + e.getMessage());
        }
    }

    public static ContactBook importFromJson () {
        ContactBook contactBook;
        try {
            contactBook = new ObjectMapper()
                    .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                    .readValue(new URL("file:contactBook.json"),ContactBook.class);

            ConsoleUIHelper.drawWithPadding("Agenda importada com sucesso");

        } catch (Exception e) {
            ConsoleUIHelper.drawWithPadding("Erro ao buscar arquivo:" + e.getMessage());
            ConsoleUIHelper.fillVSpace(1);
            ConsoleUIHelper.drawWithPadding("Nova agenda criada");

            contactBook = new ContactBook();
        }

        return contactBook;
    }
}
