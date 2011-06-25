package com.bot.irc.listeners;

import com.bot.irc.events.IRCMessageEvent;
import com.bot.irc.listeners.iface.IRCMessageListener;

/**
 * Created by IntelliJ IDEA.
 * User: TeJanca
 * Date: 6/25/11
 * Time: 9:33 AM
 * To change this template use File | Settings | File Templates.
 */
public class IRCMessageParser implements IRCMessageListener {

    public void messageReceived(IRCMessageEvent event) {
         System.out.println(event.getNick() + " @ " + event.getLocation() + " :: " + event.getMessage());
    }


}
