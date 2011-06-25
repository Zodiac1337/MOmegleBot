package com.bot.irc.events;

import java.util.EventObject;

/**
 * Created by IntelliJ IDEA.
 * User: TeJanca
 * Date: 6/24/11
 * Time: 8:25 PM
 * To change this template use File | Settings | File Templates.
 */
public class IRCMessageEvent extends EventObject {

    Type type;
    String nick, host, location, message;

    public IRCMessageEvent(Object source, Type type, String nick, String host, String location, String message) {
        super(source);
        this.nick = nick;
        this.host = host;
        this.location = location;
        this.message = message;
        this.type = type;
    }

    public Type getType() {
        return type;
    }

    public String getNick() {
        return nick;
    }

    public String getHost() {
        return host;
    }

    public String getLocation() {
        return location;
    }

    public String getMessage() {
        return message;
    }

    public enum Type {
        PRIVMSG, NOTICE, JOIN, INVITE, KICK, BAN, MODE, ACTION, PING
    }
}
