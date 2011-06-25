package com.bot.irc.listeners.iface;

import com.bot.irc.events.IRCMessageEvent;

/**
 * Created by IntelliJ IDEA.
 * User: TeJanca
 * Date: 6/24/11
 * Time: 8:30 PM
 * To change this template use File | Settings | File Templates.
 */
public interface IRCMessageListener {

    void messageReceived(IRCMessageEvent event);

}
