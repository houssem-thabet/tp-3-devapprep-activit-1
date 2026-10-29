package serverpackage;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientProcess extends Thread {
    private Socket socket;
    private int numero;

    public ClientProcess(Socket socket, int numero) {
        this.socket = socket;
        this.numero = numero;
    }

    public void run() {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            out.println("Vous êtes le client n°" + numero);

            String choix;
            while ((choix = in.readLine()) != null) {
                if (choix.equals("0")) {
                    break;
                }

                String valeur = in.readLine();
                if (valeur == null) {
                    break;
                }

                String reponse;
                try {
                    double x = Double.parseDouble(valeur.trim());
                    final double CONSTANTE = 5;
                    double resultat;

                    switch (choix.trim()) {
                        case "1":
                            resultat = x + CONSTANTE;
                            reponse = x + " + " + CONSTANTE + " = " + resultat;
                            break;
                        case "2":
                            resultat = x - CONSTANTE;
                            reponse = x + " - " + CONSTANTE + " = " + resultat;
                            break;
                        case "3":
                            resultat = x * CONSTANTE;
                            reponse = x + " * " + CONSTANTE + " = " + resultat;
                            break;
                        case "4":
                            resultat = x / CONSTANTE;
                            reponse = x + " / " + CONSTANTE + " = " + resultat;
                            break;
                        default:
                            reponse = "Erreur : opération invalide";
                    }
                } catch (NumberFormatException e) {
                    reponse = "Erreur : valeur invalide";
                }

                System.out.println("Client n°" + numero + " : choix=" + choix + ", x=" + valeur + " -> " + reponse);
                out.println(reponse);
            }

            System.out.println("Client n°" + numero + " déconnecté");
            socket.close();
        } catch (Exception e) {
            System.out.println("Erreur client n°" + numero + " : " + e.getMessage());
        }
    }
}