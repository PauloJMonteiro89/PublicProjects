package org.academiadecodigo.argicultores;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class Server {

    public static final String TEXT_RESET = "\u001B[0m";
    public static final String TEXT_RED = "\u001B[31m";
    private ServerSocket serverSocket;
    private Socket playerSocket;
    private int maxPlayerCount;
    private int playerCount;
    private int playerReady;
    private List<Player> players;
    private boolean victory;
    private BufferedWriter bw;
    public static final int PORT = 4020;

    public Server(int maxPlayerCount) {

        try {

            serverSocket = new ServerSocket(PORT);

        } catch (IOException e) {
            e.printStackTrace();
        }

        this.maxPlayerCount = maxPlayerCount;
        playerCount = 0;
        players = new ArrayList<Player>();
    }


    public void startServer() {

        while (serverSocket.isBound() && playerCount < maxPlayerCount) {

            try {
                playerSocket = serverSocket.accept();
                bw = new BufferedWriter(new OutputStreamWriter(playerSocket.getOutputStream()));
                playerCount++;
                bw.write("Player " + playerCount + " connected");
                bw.newLine();
                bw.flush();
                Player p = new Player(playerSocket, "Player " + playerCount, this);
                players.add(p);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        for(Player p: players){
            p.start();
        }
    }

    public void gameOver(String userName) {
        for (Player p : players) {
            BufferedWriter bw = p.getBw();

            try {

                if (userName != p.getPlayerInterface().getPlayerUsername()) {
                    bw.write("\n\n\n" +
                            "\n" +
                            "██████╗░███████╗███████╗███████╗░█████╗░████████╗\n" +
                            "██╔══██╗██╔════╝██╔════╝██╔════╝██╔══██╗╚══██╔══╝\n" +
                            "██║░░██║█████╗░░█████╗░░█████╗░░███████║░░░██║░░░\n" +
                            "██║░░██║██╔══╝░░██╔══╝░░██╔══╝░░██╔══██║░░░██║░░░\n" +
                            "██████╔╝███████╗██║░░░░░███████╗██║░░██║░░░██║░░░\n" +
                            "╚═════╝░╚══════╝╚═╝░░░░░╚══════╝╚═╝░░╚═╝░░░╚═╝░░░");
                    bw.newLine();
                    bw.flush();
                }
                bw.newLine();
                bw.write("\nBow for the king " + TEXT_RED + userName + TEXT_RESET + "!\n");
                bw.newLine();
                bw.flush();
                p.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public synchronized void addReadyPlayer(){
        this.playerReady++;
        notifyAll();
    }

    public synchronized void setVictory() {
        this.victory = true;
    }

    public synchronized boolean isVictory() {
        return victory;
    }
    public synchronized void arePlayersReady() {

        if (playerReady < maxPlayerCount) {

            try {
                wait();

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
