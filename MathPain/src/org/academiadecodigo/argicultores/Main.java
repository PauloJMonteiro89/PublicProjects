package org.academiadecodigo.argicultores;

public class Main {

    public static void main(String[] args) {

        if(args.length != 1){
            System.out.println("Usage: java Main [Max Players] e é se queres");
        }

        Server server;
        server = new Server(Integer.parseInt(args[0]));
        server.startServer();

    }
}
