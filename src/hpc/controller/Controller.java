/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hpc.controller;

import hpc.constants.Contstants;
import hpc.room.Bulb;
import hpc.room.Fan;
import hpc.room.PowerSocket;
import hpc.room.Room;
import hpc.room.TV;
import hpc.utility.UtilityClass;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 *
 * @author sys6003
 */
public class Controller {


    public List<Room> getHomeData(int roomCount, int switchStatus) {

        List<Room> rooms = new ArrayList<>();
        try {
            HashSet<String> checkDupInRoom = new HashSet<>();
            for (int i = 1; i <= roomCount; i++) {
                HashSet<String> checkDupInED = new HashSet<>();
                Room roomObj = new Room();
                System.out.println("Give details for Room_" + i);
                roomObj.setRoomId(i);
                roomObj.setRoomName(UtilityClass.takeInputFromKeyBoard("Enter Room Name", Contstants.strString, checkDupInRoom));
                roomObj.setBulbCount(Integer.parseInt(UtilityClass.takeInputFromKeyBoard("Enter Bulb Count", Contstants.intString)));
                if (roomObj.getBulbCount() > 0) {
                    roomObj.setBulbs(getBulbReferences(roomObj.getBulbCount(), switchStatus, checkDupInED));
                }
                roomObj.setFanCount(Integer.parseInt(UtilityClass.takeInputFromKeyBoard("Enter Fans Count", Contstants.intString)));
                if (roomObj.getFanCount() > 0) {
                    roomObj.setFans(getFanReferences(roomObj.getFanCount(), switchStatus, checkDupInED));
                }
                roomObj.setTvCount(Integer.parseInt(UtilityClass.takeInputFromKeyBoard("Enter TV Count", Contstants.intString)));
                if (roomObj.getTvCount() > 0) {
                    roomObj.setTvs(getTVReferences(roomObj.getTvCount(), switchStatus, checkDupInED));
                }
                roomObj.setPowerSocketCount(Integer.parseInt(UtilityClass.takeInputFromKeyBoard("Enter P.Socket Count", Contstants.intString)));
                if (roomObj.getPowerSocketCount() > 0) {
                    roomObj.setPss(getPowerSocketReferences(roomObj.getPowerSocketCount(), switchStatus, checkDupInED));
                }
                rooms.add(roomObj);
                System.out.println("-------------------------------------------------------");
                checkDupInED = null;
                roomObj = null;
            }
            checkDupInRoom = null;
        } catch (Exception e) {

        } finally {

        }
        return rooms;
    }

    public List<Bulb> getBulbReferences(int buIbCount, int switchStatus, HashSet<String> checkDup) {

        List<Bulb> bulbs = new ArrayList<>();
        try {
            for (int k = 1; k <= buIbCount; k++) {
                Bulb bulbObj = new Bulb();
                bulbObj.setBulbName(UtilityClass.takeInputFromKeyBoard("Give Bulb_" + k + " Reference/Name", Contstants.strString, checkDup));
                bulbObj.setBulbStatus(switchStatus);
                bulbs.add(bulbObj);
                bulbObj = null;
            }
        } catch (Exception e) {
            System.out.println("Error " + e.getMessage());
        } finally {

        }
        return bulbs;
    }

    public List<Fan> getFanReferences(int fanCount, int switchStatus, HashSet<String> checkDup) {

        List<Fan> fans = new ArrayList<>();
        try {
            for (int k = 1; k <= fanCount; k++) {
                Fan fanObj = new Fan();
                fanObj.setFanName(UtilityClass.takeInputFromKeyBoard("Give Fan_" + k + " Reference/Name", Contstants.strString, checkDup));
                fanObj.setFanStatus(switchStatus);
                fans.add(fanObj);
                fanObj = null;
            }
        } catch (Exception e) {
            System.out.println("Error " + e.getMessage());
        } finally {

        }
        return fans;
    }

    public List<TV> getTVReferences(int tvCount, int switchStatus, HashSet<String> checkDup) {

        List<TV> tvs = new ArrayList<>();
        try {
            for (int k = 1; k <= tvCount; k++) {
                TV tvObj = new TV();
                tvObj.setTvName(UtilityClass.takeInputFromKeyBoard("Give TV_" + k + " Reference/Name", Contstants.strString, checkDup));
                tvObj.setTvStatus(switchStatus);
                tvs.add(tvObj);
                tvObj = null;
            }
        } catch (Exception e) {
            System.out.println("Error " + e.getMessage());
        } finally {

        }
        return tvs;
    }

    public List<PowerSocket> getPowerSocketReferences(int psCount, int switchStatus, HashSet<String> checkDup) {

        List<PowerSocket> pss = new ArrayList<>();
        try {
            for (int k = 1; k <= psCount; k++) {
                PowerSocket psObj = new PowerSocket();
                psObj.setPsName(UtilityClass.takeInputFromKeyBoard("Give PS_" + k + " Reference/Name", Contstants.strString, checkDup));
                psObj.setPsStatus(switchStatus);
                pss.add(psObj);
                psObj = null;
            }
        } catch (Exception e) {
            System.out.println("Error " + e.getMessage());
        } finally {

        }
        return pss;
    }

}
