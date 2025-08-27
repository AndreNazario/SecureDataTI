import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        AuthSystem auth = new AuthSystem();

        System.out.print("Usuário: ");
        String username = sc.nextLine();
        System.out.print("Senha: ");
        String password = sc.nextLine();

        User loggedUser = auth.login(username, password);

        if (loggedUser != null) {
            System.out.println("Login realizado com sucesso!");
            auth.showMenu(loggedUser, sc);
        } else {
            System.out.println("Login inválido!");
        }

        sc.close();
    }
}
