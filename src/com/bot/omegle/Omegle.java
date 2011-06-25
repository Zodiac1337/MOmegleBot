package com.bot.omegle;

import com.bot.Application;
import com.bot.tasks.LoopTask;
import com.bot.util.Utilities;
import com.bot.util.queue.SimpleQueue;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

/**
 * Created by IntelliJ IDEA.
 * User: TeJanca
 * Date: 6/24/11
 * Time: 4:48 PM
 * To change this template use File | Settings | File Templates.
 */
public class Omegle extends LoopTask {

    int cAttempts = 0;
    public static String ID = null;

    public QueueHandler queue = null;

    public Omegle() {

    }

    public int loop() {
        if (Application.omegle) {
            if (ID == null) {
                System.out.println("Generating Chat ID");
                ID = getID();
                System.out.println("ID is: " + ID);
            } else {
                try {
                    String events = post(Common.OMEGLE_EVENTS, "id=" + encode(ID));
                    if (events == null)
                        return 100;
                    //System.out.println(events);
                    processEvents(events);
                } catch (Exception ignored) { }
            }
        }
        return 120;
    }

    private void processEvents(String data) {
        String events[] = data.split("\n");
        for (String event : events) {
            if (event != null && !event.isEmpty()) {
                event = event.replaceAll("null", "");
                if (event != null && !event.isEmpty())
                    System.out.println(event);
            }
        }
    }

    private static String getID() {
        String id = post(Common.OMEGLE_START, "rcs=1&spid=");
        if (id == null || id.isEmpty()) return null;
        return id.substring(id.indexOf('"') + 1, id.lastIndexOf('"'));
    }

    private static String post(String url, String info) {
        String toReturn = null;

        try {
            URLConnection connection = new URL(url).openConnection();
            connection.setDoOutput(true);

            DataOutputStream outputStream = new DataOutputStream(connection.getOutputStream());
            outputStream.writeBytes(info);
            outputStream.flush();
            outputStream.close();

            String data;
            BufferedReader incoming = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            while ((data = incoming.readLine()) != null)
                toReturn += data + "\n";
        } catch (Exception ex) { return null; }
        return toReturn;
    }

    public QueueHandler getQueue() {
        if (queue == null)
            queue = new QueueHandler();
        return queue;
    }

    public class QueueHandler extends LoopTask {
        SimpleQueue<Object> queue = new SimpleQueue<Object>();

        public int loop() {
            if (ID != null) {
                System.out.println("Looping QueueHandler");
                try {
                    Object message = queue.pull();
                    System.out.println("[PULLED FROM QUEUE] " + message);
                    post(Common.OMEGLE_SEND, encode("id=" + ID));
                } catch (Exception ignored) { }
            }
            return 120;
        }

        public void queue(Object message) {
            System.out.println("QUEUING MESSAGE] " + message);
            queue.put(message);
        }
    }
}
