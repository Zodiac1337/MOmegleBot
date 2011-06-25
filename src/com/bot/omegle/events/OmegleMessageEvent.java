package com.bot.omegle.events;

import java.util.EventObject;

/**
 * Created by IntelliJ IDEA.
 * User: TeJanca
 * Date: 6/24/11
 * Time: 7:28 PM
 * To change this template use File | Settings | File Templates.
 */
public class OmegleMessageEvent extends EventObject {

    private String message;
    private Type type;

    public OmegleMessageEvent(Object source, Type type, String message) {
        super(source);
        this.type = type;
        this.message = message;
    }

    public Type getType() {
        return type;
    }

    public String getMessage() {
        return message;
    }

    public enum Type {
        CONNECTED, DISCONNECTED, MESSAGE, TYPING, ERROR, WAITING
    }
}
