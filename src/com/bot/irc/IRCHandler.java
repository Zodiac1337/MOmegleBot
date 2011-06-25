package com.bot.irc;

import com.bot.environment.Environment;
import com.bot.irc.events.IRCMessageEvent;
import com.bot.irc.listeners.IRCMessageParser;
import com.bot.irc.listeners.iface.IRCMessageListener;
import com.bot.tasks.LoopTask;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: TeJanca
 * Date: 6/24/11
 * Time: 8:23 PM
 * To change this template use File | Settings | File Templates.
 */
public class IRCHandler extends LoopTask {

    private List<IRCMessageListener> listeners = new LinkedList<IRCMessageListener>();

    public boolean onStart() {
        this.addListener(new IRCMessageParser());
        return true;
    }

    @Override
    public int loop() {
        String raw;

        try {
            if ((raw = IRCConnection.getInstance().getIn().readLine()) != null) {
                System.out.println(raw);
                String nick, location, host, message;
                nick = location = host = message = null;
                IRCMessageEvent.Type type;

                try {
                    if (raw.split(" ")[0].equals("PING")) {
                        type = IRCMessageEvent.Type.PING;
                        message = raw.split(" :")[1];
                        fireEvent(new IRCMessageEvent(this, type, nick, host, location, message));
                    }
                } catch (Exception ignore) { }

                try {
                    if (raw.split(" ")[1].equals("NOTICE")) {
                        type = IRCMessageEvent.Type.NOTICE;
                        location = raw.split(":")[1].split("!")[0];
                        nick = location;
                        host = raw.split("@")[1].split(" ")[0];
                        message = raw.split(Environment.NICK + " :")[1];
                        fireEvent(new IRCMessageEvent(this, type, nick, host, location, message));
                    }
                } catch (Exception ignore) { }

                try {
                    if (raw.split(" ")[1].equals("PRIVMSG")) {
                        type = IRCMessageEvent.Type.PRIVMSG;
                        nick = raw.split(":")[1].split("!")[0];
                        location = raw.split("PRIVMSG ")[1].split(" :")[0];
                        host = raw.split("@")[1].split(" ")[0];
                        message = raw.split(location + " :")[1];
                        fireEvent(new IRCMessageEvent(this, type, nick, host, location, message));
                    }
                } catch (Exception ignore) { }

                try {
                    if (raw.split(" ")[1].equals("JOIN")) {
                        type = IRCMessageEvent.Type.JOIN;
                        nick = raw.split(":")[1].split("!")[0];
                        location = raw.split(" :")[1].trim();
                        host = raw.split("@")[1].split(" ")[0];
                        fireEvent(new IRCMessageEvent(this, type, nick, host, location, message));
                    }
                } catch (Exception ignore) { }

                try {
                    if (raw.split(" ")[1].equals("KICK")) {
                        type = IRCMessageEvent.Type.JOIN;
                        location = raw.split("KICK ")[1].split(" ")[0].trim();
                        nick = raw.split(location)[1].split(" :")[0].trim();
                        message = raw.split(" :")[1];
                        fireEvent(new IRCMessageEvent(this, type, nick, host, location, message));
                    }
                } catch (Exception ignore) { }
                return 10;
            }
        } catch (Exception todo) {
            System.out.println("IRCHandler failed!");
            todo.printStackTrace();
        }
        return -1;
    }

    public synchronized void fireEvent(IRCMessageEvent event) {
        for (IRCMessageListener listener : listeners) {
            //System.out.println("Sending event to listener: " + listener.getClass().getName());
            listener.messageReceived(event);
        }
    }

    public synchronized void addListener(IRCMessageListener listener) {
        listeners.add(listener);
    }

    public synchronized void removeListener(IRCMessageListener listener) {
        if (listeners.contains(listener))
            listeners.remove(listener);
    }
}
