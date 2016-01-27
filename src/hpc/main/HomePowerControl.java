/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hpc.main;

import hpc.constants.Contstants;
import hpc.controller.Controller;
import hpc.dao.DAO;
import hpc.room.Bulb;
import hpc.room.Fan;
import hpc.room.PowerSocket;
import hpc.room.Room;
import hpc.room.TV;
import hpc.utility.UtilityClass;
import hpc.showinfo.ShowInfo;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 *
 * @author sys6003
 */
public class HomePowerControl {

    /**
     * @param args the command line arguments
     */
    private int roomCount;
    private List<Room> allRooms;
    boolean keepRunning = false;
    BufferedReader keyboardInput = new BufferedReader(new InputStreamReader(System.in));
    private static HomePowerControl hpc = new HomePowerControl();

    public List<Room> getAllRooms() {
        return allRooms;
    }

    public void setAllRooms(List<Room> allRooms) {
        this.allRooms = allRooms;
    }

    public int getRoomCount() {
        return roomCount;
    }

    public void setRoomCount(int roomCount) {
        this.roomCount = roomCount;
    }

    public static void main(String[] args) {
        ShowInfo.displayAuthorInfo();
        hpc.initialize();
    }

    public void initialize() {

        keepRunning = true;
        while (keepRunning) {
            int switchOff = 0;
            int switchOn = 1;
            Controller ctrl = null;
            try {
                ShowInfo.displayProductInfo();
                int rc = Integer.parseInt(UtilityClass.takeInputFromKeyBoard("Enter no of rooms you have", Contstants.intString));
                if (rc > 0) {
                    hpc.setRoomCount(rc);
                    ShowInfo.displayRoomEnterInfo();
                    ctrl = new Controller();
                    hpc.setAllRooms(ctrl.getHomeData(getRoomCount(), switchOn));
                    System.out.println("---------------------- Current Home Stats ----------------------------");
                    ShowInfo.displayCurrentPowerStatsTable(hpc.getAllRooms());
                    ShowInfo.displayOperationMessage();
                    userCommandLineControl();
                } else {
                    System.out.println("Oops! You don't have any rooms.. Sorry for that..");
                    hpc.stop();
                }
            } catch (Exception e) {
                ShowInfo.displayExceptionAndExit(e, " initialize@HomePowerControl");
            } finally {
                ctrl = null;
            }
        }
    }

    public void userCommandLineControl() {

        try {
            String userInput = getUserInput().trim();
            if (userInput.equalsIgnoreCase("quit") || userInput.equalsIgnoreCase("q")) {
                hpc.stop();
            } else if (userInput.equalsIgnoreCase("show")) {
                ShowInfo.displayCurrentPowerStatsTable(hpc.getAllRooms());
                userCommandLineControl();
            } else if (userInput.equalsIgnoreCase("info")) {
                ShowInfo.displayOperationMessage();
                userCommandLineControl();
            } else if (userInput.equalsIgnoreCase("restart")) {
                hpc.setAllRooms(null);
                hpc.setRoomCount(0);
                keepRunning = false;
                initialize();
            } else {
                checkQueryStructure(userInput);
            }
        } catch (Exception e) {
            ShowInfo.displayExceptionAndExit(e, "userCommandLineControl@HomePowerControl");
        }
    }

    public void checkQueryStructure(String userInput) {

        try {
            if (userInput.split("=").length == 2) {
                if (userInput.split("=")[0] == null || userInput.split("=")[1] == null || userInput.split("=")[0].equalsIgnoreCase("") || userInput.split("=")[1].equalsIgnoreCase("")) {
                    ShowInfo.displayWrongInput();
                } else {
                    if (UtilityClass.isInteger(userInput.split("=")[1])) {
                        int newStatus = Integer.parseInt(userInput.split("=")[1]);
                        if (newStatus == 0 || newStatus == 1) {
                            checkQueryInRooms(userInput.split("=")[0], newStatus);
                        } else {
                            ShowInfo.displayStatusValue0or1();
                        }
                    } else {
                        ShowInfo.displayNumberRequired();
                    }
                }
            } else {
                ShowInfo.displayWrongInput();
            }
        } catch (Exception e) {
            ShowInfo.displayExceptionAndExit(e, "checkQueryStructure@HomePowerControl");
        } finally {
        }
    }

    public void checkQueryInRooms(String query, int newStatus) {

        DAO dao = new DAO();
        try {
            if (query.split("_").length == 1) {
                if (dao.checkAndUpdateRoomExistance(hpc.getAllRooms(), query, newStatus)) {
                    ShowInfo.displayChangeStatusSuccess();
                } else {
                    ShowInfo.displayNotFound("Room=" + query);
                }
            } else if (query.split("_").length == 2) {
                if (query.split("_")[0] == null || query.split("_")[1] == null || query.split("_")[0].equals("") || query.split("_")[1].equals("")) {
                    ShowInfo.displayErrorMessage("Ohho... Empty Values... Try Again.");
                } else {
                    String roomName = query.split("_")[0].trim();
                    if (dao.checkForRoomExistance(hpc.getAllRooms(), roomName)) {
                        if (dao.getRoomExistanceObj(hpc.getAllRooms(), roomName) != null) {
                            Room foundRoom = dao.getRoomExistanceObj(hpc.getAllRooms(), roomName);
                            String ed = query.split("_")[1].trim();
                            String edName = ed.split("-").length == 2 ? ed.split("-")[1].trim() : "";
                            if (ed.startsWith("B") || ed.startsWith("b") || ed.equalsIgnoreCase("bulb")) {
                                if (ed.split("-").length == 1) {
                                    if (dao.updateBulbStatus(foundRoom, newStatus)) {
                                        ShowInfo.displayChangeStatusSuccess();
                                    }
                                } else if (ed.split("-").length == 2) {
                                    if (dao.checkAndUpdateBulb(foundRoom, edName, newStatus)) {
                                        ShowInfo.displayChangeStatusSuccess();
                                    } else {
                                        ShowInfo.displayNotFound(edName);
                                    }
                                } else {
                                    ShowInfo.displayWrongInput();
                                }
                            } else if (ed.startsWith("F") || ed.startsWith("f") || ed.equalsIgnoreCase("fan")) {
                                if (ed.split("-").length == 1) {
                                    if (dao.updateFanStatus(foundRoom, newStatus)) {
                                        ShowInfo.displayChangeStatusSuccess();
                                    }
                                } else if (ed.split("-").length == 2) {
                                    if (dao.checkAndUpdateFan(foundRoom, edName, newStatus)) {
                                        ShowInfo.displayChangeStatusSuccess();
                                    } else {
                                        ShowInfo.displayNotFound(edName);
                                    }
                                } else {
                                    ShowInfo.displayWrongInput();
                                }
                            } else if (ed.startsWith("T") || ed.startsWith("T") || ed.equalsIgnoreCase("tv")) {
                                if (ed.split("-").length == 1) {
                                    if (dao.updateTVStatus(foundRoom, newStatus)) {
                                        ShowInfo.displayChangeStatusSuccess();
                                    }
                                } else if (ed.split("-").length == 2) {
                                    if (dao.checkAndUpdateTV(foundRoom, edName, newStatus)) {
                                        ShowInfo.displayChangeStatusSuccess();
                                    } else {
                                        ShowInfo.displayNotFound(edName);
                                    }
                                } else {
                                    ShowInfo.displayWrongInput();
                                }
                            } else if (ed.startsWith("P") || ed.startsWith("p") || ed.equalsIgnoreCase("PowerSocket") || ed.equalsIgnoreCase("ps")) {
                                if (ed.split("-").length == 1) {
                                    if (dao.updatePowerSocketStatus(foundRoom, newStatus)) {
                                        ShowInfo.displayChangeStatusSuccess();
                                    }
                                } else if (ed.split("-").length == 2) {
                                    if (dao.checkAndUpdatePS(foundRoom, edName, newStatus)) {
                                        ShowInfo.displayChangeStatusSuccess();
                                    } else {
                                        ShowInfo.displayNotFound(edName);
                                    }
                                } else {
                                    ShowInfo.displayWrongInput();
                                }
                            } else {
                                ShowInfo.displayErrorMessage("There is No Electical Device Found with " + ed + "");
                            }
                        } else {
                            ShowInfo.displayErrorMessage("OPPS ! There is no Electrical Devices found in that room.");
                        }
                    } else {
                        ShowInfo.displayNotFound("Room");
                    }
                }
            } else {
                ShowInfo.displayErrorMessage("\t Too many devices are not allowed! \n\t You can try like this :  roomname = 0/1 directly");
            }
        } catch (Exception e) {
            ShowInfo.displayExceptionAndExit(e, " checkQueryInRooms11@HomePowerControl");
        } finally {
        }
    }

    public String getUserInput() {

        String userInput = "";
        try {
            System.out.print("Enter >\t");
            userInput = keyboardInput.readLine().trim();
            if (userInput.equals("")) {
                getUserInput();
            }
        } catch (Exception e) {
            ShowInfo.displayExceptionAndExit(e, " getUserInput@HomePowerControl");
        } finally {
        }
        return userInput;
    }

    public void stop() {

        ShowInfo.clearResources();
        DAO.clearResources();
        keyboardInput = null;
        hpc.setAllRooms(null);
        hpc.setRoomCount(0);
        hpc = null;
        keepRunning = false;
        System.out.println("-------------------------- Bye Bye ---------------------------------------------");
    }

}
