package com.bot.irc;

/**
 * Created by IntelliJ IDEA.
 * User: TeJanca
 * Date: 6/24/11
 * Time: 7:44 PM
 * To change this template use File | Settings | File Templates.
 */
public class IRCOutput {

    public static synchronized void sendRaw(String raw) {
        sendRaw(raw, true);
    }

    public static synchronized void sendRaw(String raw, boolean flush) {
        IRCConnection.getInstance().getOut().write(raw + "\r\n");
        if (flush)
            IRCConnection.getInstance().getOut().flush();
    }
}
