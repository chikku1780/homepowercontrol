/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hpc.dao;

import hpc.main.HomePowerControl;
import hpc.room.Bulb;
import hpc.room.Fan;
import hpc.room.PowerSocket;
import hpc.room.Room;
import hpc.room.TV;
import java.util.List;

/**
 *
 * @author sys6003
 */
public class DAO {

    private static HomePowerControl hpc = new HomePowerControl();

    public DAO() {
    }

    public static void clearResources() {
        
        if (hpc != null) {
            hpc = null;
        }
    }

    public boolean checkAndUpdateRoomExistance(List<Room> rooms, String roomName, int newStatus) {

        boolean updated = false;
        try {
            for (Room room : rooms) {
                if (room.getRoomName().equalsIgnoreCase(roomName)) {
                    updateBulbStatus(room, newStatus);
                    updateFanStatus(room, newStatus);
                    updateTVStatus(room, newStatus);
                    updatePowerSocketStatus(room, newStatus);
                    updated = true;
                    break;
                }
            }
        } catch (Exception e) {
            System.out.println("Error Message " + e.getMessage() + " at checkQueryInRooms@HomePowerControl");
            hpc.stop();
        } finally {
        }
        return updated;
    }

    public boolean checkForRoomExistance(List<Room> rooms,String roomName) {

        boolean found = false;
        try {
            for (Room room : rooms) {
                if (room.getRoomName().equalsIgnoreCase(roomName)) {
                    found = true;
                    break;
                }
            }
        } catch (Exception e) {
            System.out.println("Error Message " + e.getMessage() + " at checkForRoomExistance@HomePowerControl");
            hpc.stop();
        } finally {
        }
        return found;
    }

    public Room getRoomExistanceObj(List<Room> rooms,String roomName) {

        Room found = null;
        try {
            for (Room room : rooms) {
                if (room.getRoomName().equalsIgnoreCase(roomName)) {
                    found = room;
                    break;
                }
            }
        } catch (Exception e) {
            System.out.println("Error Message " + e.getMessage() + " at getRoomExistanceObj@HomePowerControl");
            hpc.stop();
        } finally {
        }
        return found;
    }

    public boolean updateBulbStatus(Room room, int newStatus) {

        boolean updated = false;
        try {
            if (room.getBulbCount() > 0) {
                for (Bulb bulb : room.getBulbs()) {
                    bulb.setBulbStatus(newStatus);
                }
                updated = true;
            }
        } catch (Exception e) {
            System.out.println("Error Message " + e.getMessage() + " at updateBulbStatus@HomePowerControl");
//            stop();
        } finally {
        }
        return updated;
    }

    public boolean updateFanStatus(Room room, int newStatus) {

        boolean updated = false;
        try {
            if (room.getFanCount() > 0) {
                for (Fan fan : room.getFans()) {
                    fan.setFanStatus(newStatus);
                }
                updated = true;
            }
        } catch (Exception e) {
            System.out.println("Error Message " + e.getMessage() + " at updateFanStatus@HomePowerControl");
//            stop();
        } finally {
        }
        return updated;
    }

    public boolean updateTVStatus(Room room, int newStatus) {

        boolean updated = false;
        try {
            if (room.getTvCount() > 0) {
                for (TV tv : room.getTvs()) {
                    tv.setTvStatus(newStatus);
                }
                updated = true;
            }
        } catch (Exception e) {
            System.out.println("Error Message " + e.getMessage() + " at updateTVStatus@HomePowerControl");
//            stop();
        } finally {
        }
        return updated;
    }

    public boolean updatePowerSocketStatus(Room room, int newStatus) {

        boolean updated = false;
        try {
            if (room.getPowerSocketCount() > 0) {
                for (PowerSocket ps : room.getPss()) {
                    ps.setPsStatus(newStatus);
                }
                updated = true;
            }
        } catch (Exception e) {
            System.out.println("Error Message " + e.getMessage() + " at updatePowerSocketStatus@HomePowerControl");
//            stop();
        } finally {
        }
        return updated;
    }

    public boolean checkAndUpdateBulb(Room room, String str, int newStatus) {

        boolean updated = false;
        try {
            for (Bulb bulb : room.getBulbs()) {
                if (bulb.getBulbName().equalsIgnoreCase(str)) {
                    bulb.setBulbStatus(newStatus);
                    updated = true;
                }
            }
        } catch (Exception e) {
            System.out.println("Error Message " + e.getMessage() + " at checkAndUpdateBulb@HomePowerControl");
            hpc.stop();
        } finally {

        }
        return updated;
    }

    public boolean checkAndUpdateFan(Room room, String str, int newStatus) {

        boolean updated = false;
        try {
            for (Fan fan : room.getFans()) {
                if (fan.getFanName().equalsIgnoreCase(str)) {
                    fan.setFanStatus(newStatus);
                    updated = true;
                }
            }
        } catch (Exception e) {
            System.out.println("Error Message " + e.getMessage() + " at checkAndUpdateFan@HomePowerControl");
            hpc.stop();
        } finally {

        }
        return updated;
    }

    public boolean checkAndUpdateTV(Room room, String str, int newStatus) {

        boolean updated = false;
        try {
            for (TV tv : room.getTvs()) {
                if (tv.getTvName().equalsIgnoreCase(str)) {
                    tv.setTvStatus(newStatus);
                    updated = true;
                }
            }
        } catch (Exception e) {
            System.out.println("Error Message " + e.getMessage() + " at checkAndUpdateTV@HomePowerControl");
            hpc.stop();
        } finally {

        }
        return updated;
    }

    public boolean checkAndUpdatePS(Room room, String str, int newStatus) {

        boolean updated = false;
        try {
            for (PowerSocket ps : room.getPss()) {
                if (ps.getPsName().equalsIgnoreCase(str)) {
                    ps.setPsStatus(newStatus);
                    updated = true;
                }
            }
        } catch (Exception e) {
            System.out.println("Error Message " + e.getMessage() + " at checkAndUpdatePS@HomePowerControl");
            hpc.stop();
        } finally {

        }
        return updated;
    }
}
