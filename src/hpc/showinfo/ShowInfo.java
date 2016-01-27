/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hpc.showinfo;

import hpc.main.HomePowerControl;
import hpc.room.Bulb;
import hpc.room.Fan;
import hpc.room.PowerSocket;
import hpc.room.Room;
import hpc.room.TV;
import hpc.utility.UtilityClass;
import java.util.List;

/**
 *
 * @author sys6003
 */
public class ShowInfo {

    private static HomePowerControl hpc = new HomePowerControl();

    public ShowInfo() {
    }

    public static void clearResources() {
        
        if (hpc != null) {
            hpc = null;
        }
    }

    //----------
    public static void displayAuthorInfo() {

        System.out.println("--------------------  A small product from YDWD  --------------------");
        System.out.println("Author: chikku");
//        System.out.println("----------------------------------------------------------------------");
    }

    public static void displayProductInfo() {

        System.out.println("--------------------Welcome to Home Power Control--------------------");
        System.out.println("----------------------------------------------------------------------");
    }

    public static void displayRoomEnterInfo() {

        System.out.println("----------------------------------------------------------------------");
        System.out.println("Room can have RoomName, Bulbs, Fans, TVs, PowerSockets");
        System.out.println("Example : Hall, 3, 2, 1, 4");
        System.out.println("----------------------------------------------------------------------");
    }

    public static void displayOperationMessage() {

        System.out.println("----------------------------------- Information ------------------------------------------------------------");
        System.out.println("You can Change Room's Device Status By typing --> ");
        System.out.println("\t\t\t For Example ==> <RoomName>_<Device>-<DeviceName>=<1/0> (1= ON, 0=OFF)");
        System.out.println("\t\t\t For Bulb ==> Ex: <roomName>_<B>-<bulbName>=0");
        System.out.println("\t\t\t For Fan  ==> Ex: <roomName>_<F>-<fanName>=1");
        System.out.println("\t\t\t For Total Room  ==> Ex: <roomName>= 1");
        System.out.println("You can show cuurent home power stats by typing --> show");
        System.out.println("You can quit from program by typing --> quit");
        System.out.println("You can even restart by typing --> restart");
        System.out.println("You can view this information section by typing --> info");
        System.out.println("---------------------------------------------------------------------------------------------------------------");
    }

    public static void displayCurrentPowerStats(List<Room> rooms) {

        try {
            System.out.println("-----------------------------------------------------------------------------------------");
            System.out.println("RoomName\t\tBulbs\t\t Fans\t\tTVs\t\tPowerSockets");
            for (Room room : rooms) {
                System.out.print(room.getRoomName() + "\t");
                System.out.print("\t\tTotal=" + room.getBulbCount());
                System.out.print("\t\tTotal=" + room.getFanCount());
                System.out.print("\t\tTotal=" + room.getTvCount());
                System.out.print("\t\tTotal=" + room.getPowerSocketCount());

                System.out.println();
                System.out.print("\t");
                if (room.getBulbCount() > 0) {
                    System.out.print("\t\t");
                    for (Bulb bulb : room.getBulbs()) {
                        System.out.print(bulb.getBulbName());
                        System.out.print("-" + (bulb.getBulbStatus() == 0 ? "OFF" : "ON") + ",");
                    }
                } else {
                    System.out.print("\t\t");
                    System.out.print("NONE");
                }
                if (room.getFanCount() > 0) {
                    System.out.print("\t\t");
                    for (Fan fan : room.getFans()) {
                        System.out.print(fan.getFanName());
                        System.out.print("-" + (fan.getFanStatus() == 0 ? "OFF" : "ON") + ",");
                    }
                } else {
                    System.out.print("\t\t");
                    System.out.print("NONE");
                }
                if (room.getTvCount() > 0) {
                    System.out.print("\t\t");
                    for (TV tv : room.getTvs()) {
                        System.out.print(tv.getTvName());
                        System.out.print("-" + (tv.getTvStatus() == 0 ? "OFF" : "ON") + ",");
                    }
                } else {
                    System.out.print("\t\t");
                    System.out.print("NONE");
                }
                if (room.getPowerSocketCount() > 0) {
                    System.out.print("\t\t");
                    for (PowerSocket ps : room.getPss()) {
                        System.out.print(ps.getPsName());
                        System.out.print("-" + (ps.getPsStatus() == 0 ? "OFF" : "ON") + ",");
                    }
                } else {
                    System.out.print("\t\t");
                    System.out.print("NONE");
                }
            }
            System.out.println();
            System.out.println("-----------------------------------------------------------------------------------------");
        } catch (Exception e) {
            ShowInfo.displayExceptionAndExit(e, " displayCurrentPowerStats@ShowInfo");
        } finally {
        }
    }
    
    public static void displayCurrentPowerStatsTable(List<Room> rooms) {

        try {
            System.out.println("-----------------------------------------------------------------------------------------");
            UtilityClass.tableViewStructure(rooms);
            System.out.println("-----------------------------------------------------------------------------------------");
        } catch (Exception e) {
            ShowInfo.displayExceptionAndExit(e, " displayCurrentPowerStatsTable@ShowInfo");
        } finally {
        }
    }

    public void showEDStats(int edCount) {

        try {

        } catch (Exception e) {

        } finally {

        }
    }

    public static void displayNotFound(String str) {

        System.out.println("Opps! " + str + " Not Found");
        hpc.userCommandLineControl();
    }

    public static void displayWrongInput() {

        System.out.println("\t Opps, Wrong Input - !");
        hpc.userCommandLineControl();
    }

    public static void displayNumberRequired() {

        System.out.println("\t Number Required");
        hpc.userCommandLineControl();
    }

    public static void displayStatusValue0or1() {

        System.out.println("\t Status should be either 0 or 1");
        hpc.userCommandLineControl();
    }

    public static void displayChangeStatusSuccess() {

        System.out.println("Successfully status has changed");
        System.out.println("You can check by typing --> show");
        hpc.userCommandLineControl();
    }

    public static void displayErrorMessage(String str) {

        System.out.println(str);
        hpc.userCommandLineControl();
    }
    
    public static void displayExceptionAndExit(Exception e, String str) {

        System.out.println("Error Message " + e.getMessage() + " at " + str);
        hpc.stop();
    }

}
