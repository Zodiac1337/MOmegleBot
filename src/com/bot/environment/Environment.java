package com.bot.environment;

import com.bot.irc.wrappers.Channel;

import java.util.ArrayList;

/**
 * Created by IntelliJ IDEA.
 * User: TeJanca
 * Date: 6/24/11
 * Time: 8:04 PM
 * To change this template use File | Settings | File Templates.
 */
public class Environment {

    public static String SERVER = "irc.strictfp.com",
            NICK = "MOmegle", PASSWORD = "omegleirc";

    public static Channel CHANNEL = new Channel("#Janca", null);

    //TODO add support for multichannel
    public static ArrayList<Channel> CHANNELS = new ArrayList<Channel>();
}
