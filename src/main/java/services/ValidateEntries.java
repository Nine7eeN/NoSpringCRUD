package services;

public class ValidateEntries {

    public static String[] validateUserData(String entry){
        String[] validUserData = entry.split(",");
        if (validUserData.length < 2) throw new IllegalArgumentException("Insira ao menos 2 dados de usuário.");
        validUserData[0] = validUserData[0].trim();
        validUserData[1] = validUserData[1].trim();
        if (validUserData[0].isBlank()) throw new IllegalArgumentException("O nome de usuário não pode ser vazio.");
        if (validUserData[0].length()<3) throw new IllegalArgumentException("O nome de usuário deve conter ao menos 3 caracteres.");
        if (validUserData[1].isBlank()) throw new IllegalArgumentException("O email não pode ser vazio.");
        if (!validUserData[1].matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.com$")) throw new IllegalArgumentException("O email deve conter um formato válido!");
        return validUserData;
    }

    public static int validateUserID(String entry){
            try {
                int validID = Integer.parseInt(entry);
                if (validID <= 0) throw new IllegalArgumentException("O ID de usuário deve ser maior que 0.");
                return validID;
            } catch (NumberFormatException e){
                throw new IllegalArgumentException("Formato de ID inválido!");
            }
    }
}
