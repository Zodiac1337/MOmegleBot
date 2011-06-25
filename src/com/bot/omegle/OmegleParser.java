package com.bot.omegle;

import com.bot.omegle.events.OmegleMessageEvent;
import com.bot.omegle.listeners.OmegleMessageListener;

/**
 * Created by IntelliJ IDEA.
 * User: TeJanca
 * Date: 6/24/11
 * Time: 7:32 PM
 * To change this template use File | Settings | File Templates.
 */
public class OmegleParser implements OmegleMessageListener {

    public OmegleParser() { }

    public void messageReceived(OmegleMessageEvent event) {
        if(event.getType() == OmegleMessageEvent.Type.CONNECTED) {

        }
    }
}
