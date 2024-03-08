package org.me.gcu.pullparser2;

public class Thing {

    private String bolt;

    private String nut;

    private String washer;


    public void setBolt(String bolt) {
        this.bolt = bolt;
    }

    public void setNut(String nut) {
        this.nut = nut;
    }

    public void setWasher(String washer) {
        this.washer = washer;
    }

    public String getBolt() {
        return bolt;
    }

    public String getNut() {
        return nut;
    }

    public String getWasher() {
        return washer;
    }

    public String toString(){
        return "Bolt: "  + bolt + "Nut: " + nut + " Washer: " + washer;
    }

}
