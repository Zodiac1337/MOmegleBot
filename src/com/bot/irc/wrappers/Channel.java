package com.bot.irc.wrappers;

import com.bot.environment.Environment;
import com.bot.irc.IRCOutput;
import com.bot.util.Utilities;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: TeJanca
 * Date: 6/24/11
 * Time: 7:36 PM
 * To change this template use File | Settings | File Templates.
 */
public class Channel {

    private String channelName, keyword;
    private Map<String, String> users = new HashMap<String, String>();

    private String ranks[] = {"+", "%", "@", "&", "~"};

    public Channel(String name, String keyword) {
        this.channelName = name.trim().replaceFirst("#", "");
        this.keyword = keyword;
    }

    public void join() {
        IRCOutput.sendRaw("JOIN #" + channelName);
    }

    public void part() {
        IRCOutput.sendRaw("PART #" + channelName);
    }

    public void destruct() {
        Environment.CHANNELS.remove(this);
        part();
    }

    public String getUserRanks(String user) {
        user = user.toLowerCase();
        for (Map.Entry<String, String> uInfo : users.entrySet()) {
            if (uInfo.getKey().toLowerCase().equalsIgnoreCase(user)) {
                return uInfo.getValue();
            }
        }
        return null;
    }

    public int getRank(String user) {
        String symbs = getUserRanks(user);

        if (symbs == null)
            return 0;

        int rank = 0;
        for (int i = 0; i < ranks.length; i++) {
            if (symbs.contains(ranks[i]))
                rank = i + 1;
        }
        return rank;
    }

    public void setUsersRanks(String namesLine) {
        try {
            String users[] = namesLine.split(channelName + " :")[1].split(" ");
            for (String u : users) {
                String rank = "", user;

                if (!Utilities.containsOneOf(u, ranks)) {
                    this.users.put(u, "");
                    continue;
                }

                user = stripRank(u);
                for (String r : ranks)
                    if (u.contains(r))
                        rank += r;

                this.users.put(user, rank);
            }
        } catch (Exception ignore) { }
    }

    private String stripRank(String user) {
        return user.replace("~", "").replace("&", "").replace("@", "").replace("%", "").replace("+", "");
    }
}
