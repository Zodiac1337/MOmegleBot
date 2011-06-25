package com.bot.irc;

import com.bot.irc.wrappers.Channel;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by IntelliJ IDEA.
 * User: TeJanca
 * Date: 6/24/11
 * Time: 7:43 PM
 * To change this template use File | Settings | File Templates.
 */
public class IRCConnection {

    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;

    private IRCHandler ircHandler = new IRCHandler();

    private int port;

    private static IRCConnection instance;

    private int[] ports = {6667, 6668, 6669};

    public static synchronized IRCConnection getInstance() {
        if (instance == null)
            instance = new IRCConnection();
        return instance;
    }

    public Socket getSocket() {
        return getInstance().socket;
    }

    public BufferedReader getIn() {
        return getInstance().in;
    }

    public PrintWriter getOut() {
        return getInstance().out;
    }

    public int getPort() {
        return getInstance().port;
    }

    public void connect(String server) {
        for (int p : ports) {
            if (socket == null) {
                try {
                    socket = new Socket(server, p);
                    System.out.println("Created Socket.");
                    in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    System.out.println("Created BufferedReader.");
                    out = new PrintWriter(new BufferedOutputStream(socket.getOutputStream()));
                    System.out.println("Created PrintWriter");
                    port = p;
                } catch (Exception ignored) { ignored.printStackTrace(); }
            } else {
                break;
            }
        }

        if (socket == null || in == null || out == null) {
            System.out.println("Unable to create the connection, please close this program and try again.");
            instance = null;
            return;
        }

        System.out.println("Starting IRCHandler");
        new Thread(ircHandler).start();
    }

    public void signIn(String nick, String username, String password) {
        IRCOutput.sendRaw("NICK " + nick + "\r\n", false);
        IRCOutput.sendRaw("USER  Janca 2012 * : Janca\r\n", false);
        if (password != null && !password.isEmpty())
            IRCOutput.sendRaw("PRIVMSG NickServ :identify " + password, false);
        getInstance().getOut().flush();
    }

    public void joinChannel(Channel channel) {
        channel.join();
    }
}
