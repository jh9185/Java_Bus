package com.example.java_bus.bus;

public class VehId {
    public String plainNo;
    public double posX;
    public double posY;

    public VehId(String plainNo, double posX, double posY) {
        this.plainNo = plainNo;
        this.posX = posX;
        this.posY = posY;
    }

    public String getPlainNo() {
        return plainNo;
    }

    public void setPlainNo(String plainNo) {
        this.plainNo = plainNo;
    }

    public double getPosX() {
        return posX;
    }

    public void setPosX(double posX) {
        this.posX = posX;
    }

    public double getPosY() {
        return posY;
    }

    public void setPosY(double posY) {
        this.posY = posY;
    }
}
