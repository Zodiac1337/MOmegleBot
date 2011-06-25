package com.bot;

import com.bot.environment.Environment;
import com.bot.irc.IRCConnection;
import com.bot.omegle.Omegle;

/**
 * Created by IntelliJ IDEA.
 * User: TeJanca
 * Date: 6/24/11
 * Time: 4:48 PM
 * To change this template use File | Settings | File Templates.
 */
public class Application {

    public static boolean stop = false;
    public static boolean omegle = false;
    public static boolean connected = false;

    public Application() {
        final Omegle omegle = new Omegle();
        new Thread(omegle).start();
        new Thread(omegle.getQueue()).start();

        IRCConnection.getInstance().connect(Environment.SERVER);
        IRCConnection.getInstance().signIn(Environment.NICK, "Omegle", Environment.PASSWORD);
        IRCConnection.getInstance().joinChannel(Environment.CHANNEL);
    }

    public static void main(String args[]) {
        new Application();
    }
}
