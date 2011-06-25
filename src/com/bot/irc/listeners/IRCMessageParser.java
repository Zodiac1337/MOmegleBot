package com.bot.irc.listeners;

import com.bot.irc.IRCOutput;
import com.bot.irc.events.IRCMessageEvent;
import com.bot.irc.listeners.iface.IRCMessageListener;

import static com.bot.irc.events.IRCMessageEvent.Type;

/**
 * Created by IntelliJ IDEA.
 * User: TeJanca
 * Date: 6/25/11
 * Time: 9:33 AM
 * To change this template use File | Settings | File Templates.
 */
public class IRCMessageParser implements IRCMessageListener {

    public void messageReceived(IRCMessageEvent event) {
        System.out.println("Event received! Type: " + event.getType());
        if (event.getType() == Type.PING)
            IRCOutput.sendRaw("PONG " + event.getMessage());

        if (event.getMessage().startsWith("[say]") && event.getType() == Type.PRIVMSG)
            IRCOutput.sendRaw("PRIVMSG " + event.getLocation() + " :" + event.getMessage().substring(5).trim());
    }


}
