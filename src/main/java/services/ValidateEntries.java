package services;

public class ValidateEntries {

    public static String validateUsername(String username){
        String validUsername = username.trim();
        if (validUsername.isBlank()) throw new IllegalArgumentException("O nome de usuário não pode estar vazio.");
        if (validUsername.length()<3) throw new IllegalArgumentException("O nome de usuário deve conter ao menos 3 caracteres.");
        if (!validUsername.matches("^[\\p{L} '-]+$")) throw new IllegalArgumentException("O nome de usuário não pode conter caracteres especiais.");
        return validUsername;
    }

    public static String validateEmail(String email){
        String validEmail = email.trim();
        if (validEmail.isBlank()) throw new IllegalArgumentException("O campo de email não pode estar vazio.");
        if (!validEmail.matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.com$")) throw new IllegalArgumentException("O email deve conter um formato válido!");
        return validEmail;
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
