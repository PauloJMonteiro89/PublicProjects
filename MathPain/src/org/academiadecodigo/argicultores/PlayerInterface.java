package org.academiadecodigo.argicultores;

import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.bootcamp.scanners.integer.IntegerInputScanner;
import org.academiadecodigo.bootcamp.scanners.string.StringInputScanner;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class PlayerInterface {

    private final Player p;
    private BufferedReader br;
    private BufferedWriter bw;
    private Server server;
    private Prompt prompt = null;
    private String playerUsername;

    public PlayerInterface(Player p, Server server) throws IOException, InterruptedException {

        this.server = server;
        this.p = p;

        try {
            br = new BufferedReader(new InputStreamReader(p.getSocket().getInputStream()));
            bw = new BufferedWriter(new OutputStreamWriter(p.getSocket().getOutputStream()));
            prompt = new Prompt(p.getSocket().getInputStream(), new PrintStream(p.getSocket().getOutputStream()));

        } catch (IOException e) {
            e.printStackTrace();
        }

        StringInputScanner username = new StringInputScanner();
        bw.write("\n" + " " +
                "███╗░░░███╗░█████╗░████████╗██╗░░██╗░░░██████╗░░█████╗░██╗███╗░░██╗\n" +
                "████╗░████║██╔══██╗╚══██╔══╝██║░░██║░░░██╔══██╗██╔══██╗██║████╗░██║\n" +
                "██╔████╔██║███████║░░░██║░░░███████║░░░██████╔╝███████║██║██╔██╗██║\n" +
                "██║╚██╔╝██║██╔══██║░░░██║░░░██╔══██║░░░██╔═══╝░██╔══██║██║██║╚████║\n" +
                "██║░╚═╝░██║██║░░██║░░░██║░░░██║░░██║██╗██║░░░░░██║░░██║██║██║░╚███║\n" +
                "╚═╝░░░░░╚═╝╚═╝░░╚═╝░░░╚═╝░░░╚═╝░░╚═╝╚═╝╚═╝░░░░░╚═╝░░╚═╝╚═╝╚═╝░░╚══╝\n");
        bw.newLine();
        bw.flush();
        username.setMessage("Username: ");
        playerUsername = prompt.getUserInput(username);
        p.setName(playerUsername);

    }

    public void gameInterface() throws IOException {

        GameEngine ge = new GameEngine(6);
        greetingMessage();

        while (ge.getLevel() <= ge.getMaxLevels() && !server.isVictory()) {

            if(ge.getLevel()== ge.getMaxLevels()){
                bw.write("\n" +
                        "\n" +
                        "── ░█▀▀▀ ─▀─ █▀▀▄ █▀▀█ █── 　 ░█▀▀█ █▀▀█ █──█ █▀▀▄ █▀▀▄ ── \n" +
                        "▀▀ ░█▀▀▀ ▀█▀ █──█ █▄▄█ █── 　 ░█▄▄▀ █──█ █──█ █──█ █──█ ▀▀ \n" +
                        "── ░█─── ▀▀▀ ▀──▀ ▀──▀ ▀▀▀ 　 ░█─░█ ▀▀▀▀ ─▀▀▀ ▀──▀ ▀▀▀─ ──\n\n");
                bw.newLine();
                bw.flush();

                try {
                    Thread.sleep(1500);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            IntegerInputScanner question = new IntegerInputScanner();
            question.setMessage(ge.levelGenerator(ge.getLevel()));
            int answer = prompt.getUserInput(question);

            if (ge.getLevels().containsValue((answer))) {
                ge.increaseLevel();
            }
        }
        bw.write("\n\n" +
                "\n" +
                "██╗░░░██╗██╗░█████╗░████████╗░█████╗░██████╗░██╗░░░██╗\n" +
                "██║░░░██║██║██╔══██╗╚══██╔══╝██╔══██╗██╔══██╗╚██╗░██╔╝\n" +
                "╚██╗░██╔╝██║██║░░╚═╝░░░██║░░░██║░░██║██████╔╝░╚████╔╝░\n" +
                "░╚████╔╝░██║██║░░██╗░░░██║░░░██║░░██║██╔══██╗░░╚██╔╝░░\n" +
                "░░╚██╔╝░░██║╚█████╔╝░░░██║░░░╚█████╔╝██║░░██║░░░██║░░░\n" +
                "░░░╚═╝░░░╚═╝░╚════╝░░░░╚═╝░░░░╚════╝░╚═╝░░╚═╝░░░╚═╝░░░");
        bw.newLine();
        bw.flush();
        server.setVictory();
        server.gameOver(playerUsername);

    }

    public void greetingMessage(){

        try {

            bw.write("\nWelcome to Math.Pain " + Server.TEXT_RED + playerUsername + Server.TEXT_RESET + "!\n");
            bw.newLine();
            bw.flush();
            bw.write("3");
            bw.newLine();
            bw.flush();
            Thread.sleep(1000);
            bw.write("2");
            bw.newLine();
            bw.flush();
            Thread.sleep(1000);
            bw.write("1");
            bw.newLine();
            bw.flush();
            Thread.sleep(1000);
            bw.write("Go!\n");
            bw.newLine();
            bw.flush();
            Thread.sleep(1000);

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public String getPlayerUsername() {
        return playerUsername;
    }
}
