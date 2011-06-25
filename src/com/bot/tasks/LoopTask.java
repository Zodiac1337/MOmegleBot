package com.bot.tasks;

import com.bot.util.Utilities;

/**
 * Created by IntelliJ IDEA.
 * User: TeJanca
 * Date: 6/24/11
 * Time: 5:36 PM
 * To change this template use File | Settings | File Templates.
 */
public abstract class LoopTask extends Utilities implements Runnable {

    private boolean alive = false,
            stop = false, paused = false;

    public boolean onStart() {
        System.out.println("Starting new loop");
        return true;
    }

    public abstract int loop();

    public void onFinish() { }

    public final void run() {
        boolean start = false;
        try {
            start = onStart();
        } catch (ThreadDeath ignored) {
        } catch (Throwable t) {
            t.printStackTrace();
        }

        if (start) {
            alive = true;
            try {
                while (alive && !stop) {
                    if (!paused) {
                        int timeout = -1;
                        try {
                            timeout = loop();
                        } catch (ThreadDeath td) {
                            break;
                        } catch (Exception e) {
                            System.out.println("Uncaught Exception!");
                            e.printStackTrace();
                        }

                        if (timeout == -1)
                            break;

                        try {
                            sleep(timeout);
                        } catch (ThreadDeath td) {
                            break;
                        }
                    } else {
                        try {
                            sleep(1000);
                        } catch (ThreadDeath td) {
                            break;
                        }
                    }
                }

                try {
                    onFinish();
                } catch (ThreadDeath ignored) {
                } catch (RuntimeException e) {
                    e.printStackTrace();
                }
                alive = false;
            } catch (Throwable t) {
                t.printStackTrace();
                onFinish();
            }
        }
        alive = false;
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public boolean isStop() {
        return stop;
    }

    public void setStop(boolean stop) {
        this.stop = stop;
    }

    public boolean isPaused() {
        return paused;
    }

    public void setPaused(boolean paused) {
        this.paused = paused;
    }
}
