import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);

        String ip;
        if (args.length > 0) {
            ip = args[0];
        } else {
            System.out.print("Adresse IP du serveur : ");
            ip = sc.nextLine().trim();
        }
        int port = 1234;

        Socket socket = new Socket();
        socket.connect(new InetSocketAddress(ip, port), 5000);

        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

        System.out.println(in.readLine());

        while (true) {
            System.out.println();
            System.out.println("===== Calculatrice =====");
            System.out.println("1. Addition");
            System.out.println("2. Soustraction");
            System.out.println("3. Multiplication");
            System.out.println("4. Division");
            System.out.println("0. Quitter");
            System.out.print("Votre choix : ");
            String choix = sc.nextLine().trim();

            if (choix.equals("0")) {
                out.println("0");
                break;
            }
            if (!choix.matches("[1-4]")) {
                System.out.println("Choix invalide.");
                continue;
            }

            System.out.print("Valeur de x : ");
            String x = sc.nextLine().trim();

            out.println(choix);
            out.println(x);

            System.out.println("Résultat : " + in.readLine());
        }

        socket.close();
        sc.close();
    }
}