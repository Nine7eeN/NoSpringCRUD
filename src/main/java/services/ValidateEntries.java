package services;

public class ValidateEntries {

    public static String[] validateSave(String entry){
        String[] validEntry = entry.split(",");
        if (validEntry.length < 2) throw new IllegalArgumentException("Você precisa inserir ao menos dois dados para cadastrar um usuário!");
        if (validEntry[0].length()<3) throw new IllegalArgumentException("O nome de usuário deve conter ao menos 3 caracteres.");
        if (!validEntry[1].matches(".+@.+\\.com")) throw new IllegalArgumentException("O email deve conter um formato válido!");
        return validEntry;
    }
}
