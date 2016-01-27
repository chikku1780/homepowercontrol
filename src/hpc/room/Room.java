/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hpc.room;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author sys6003
 */
public class Room {

    private int roomId;
    private String roomName;
    private int bulbCount;
    private int fanCount;
    private int tvCount;
    private int powerSocketCount;
    private List<Bulb> bulbs;
    private List<Fan> fans;
    private List<TV> tvs;
    private List<PowerSocket> pss;

    public List<Bulb> getBulbs() {
        return bulbs;
    }

    public void setBulbs(List<Bulb> bulbs) {
        this.bulbs = bulbs;
    }

    public List<Fan> getFans() {
        return fans;
    }

    public void setFans(List<Fan> fans) {
        this.fans = fans;
    }

    public List<TV> getTvs() {
        return tvs;
    }

    public void setTvs(List<TV> tvs) {
        this.tvs = tvs;
    }

    public List<PowerSocket> getPss() {
        return pss;
    }

    public void setPss(List<PowerSocket> pss) {
        this.pss = pss;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public int getBulbCount() {
        return bulbCount;
    }

    public void setBulbCount(int bulbCount) {
        this.bulbCount = bulbCount;
    }

    public int getFanCount() {
        return fanCount;
    }

    public void setFanCount(int fanCount) {
        this.fanCount = fanCount;
    }

    public int getTvCount() {
        return tvCount;
    }

    public void setTvCount(int tvCount) {
        this.tvCount = tvCount;
    }

    public int getPowerSocketCount() {
        return powerSocketCount;
    }

    public void setPowerSocketCount(int powerSocketCount) {
        this.powerSocketCount = powerSocketCount;
    }

}
