package org.academiadecodigo.argicultores;

import java.io.*;
import java.net.Socket;
import java.util.List;

public class Player extends Thread {

    private String name;
    private Socket socket;
    private BufferedReader br;
    private BufferedWriter bw;
    private PlayerInterface playerInterface;
    private Server server;

    public Player(Socket socket, String name, Server server) {

        this.name = name;
        this.socket = socket;
        this.server = server;

        try {
            br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {

        try {
            playerInterface = new PlayerInterface(this, server);
            server.addReadyPlayer();
            server.arePlayersReady();
            playerInterface.gameInterface();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public Socket getSocket() {
        return socket;
    }

    public BufferedWriter getBw() {
        return bw;
    }

    public void close() {
        try {
            bw.close();
            br.close();
            socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public PlayerInterface getPlayerInterface() {
        return playerInterface;
    }
}
