package com.bot.omegle.listeners;

import com.bot.omegle.events.OmegleMessageEvent;

/**
 * Created by IntelliJ IDEA.
 * User: TeJanca
 * Date: 6/24/11
 * Time: 7:30 PM
 * To change this template use File | Settings | File Templates.
 */
public interface OmegleMessageListener {

    void messageReceived(OmegleMessageEvent event);

}
