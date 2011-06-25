package com.bot.irc.wrappers;

import com.bot.irc.IRCOutput;

/**
 * Created by IntelliJ IDEA.
 * User: TeJanca
 * Date: 6/24/11
 * Time: 7:36 PM
 * To change this template use File | Settings | File Templates.
 */
public class Channel {

    private String channelName, keyword;

    public Channel(String name, String keyword) {
        this.channelName = name.trim().replaceFirst("#", "");
        this.keyword = keyword;
    }

    public void join() {
        IRCOutput.sendRaw("JOIN #" + channelName);
    }
}
