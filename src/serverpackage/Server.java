import serverpackage.ClientProcess;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws Exception {
        int port = 1234;

        InetAddress adresse;
        if (args.length > 0) {
            adresse = InetAddress.getByName(args[0]);
        } else {
            adresse = InetAddress.getLocalHost();
        }

        ServerSocket serverSocket = new ServerSocket();
        serverSocket.bind(new InetSocketAddress(adresse, port));
        System.out.println("Serveur démarré sur " + adresse.getHostAddress() + ":" + port);

        int nb = 0;
        while (true) {
            Socket socket = serverSocket.accept();
            nb++;
            System.out.println("Client n°" + nb + " connecté : " + socket.getRemoteSocketAddress());
            new ClientProcess(socket, nb).start();
        }
    }
}