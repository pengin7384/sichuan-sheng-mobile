package com.example.penguin.sichuan;

/**
 * Created by Penguin on 2018-03-18.
 */

public class Path {
    private int x;
    private int y;
    private int direction;

    public void set(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }


    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }
}
