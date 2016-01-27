/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hpc.utility;

import hpc.constants.Contstants;
import hpc.room.Bulb;
import hpc.room.Fan;
import hpc.room.PowerSocket;
import hpc.room.Room;
import hpc.room.TV;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import org.nocrala.tools.texttablefmt.BorderStyle;
import org.nocrala.tools.texttablefmt.CellStyle;
import org.nocrala.tools.texttablefmt.CellStyle.AbbreviationStyle;
import org.nocrala.tools.texttablefmt.CellStyle.HorizontalAlign;
import org.nocrala.tools.texttablefmt.CellStyle.NullStyle;
import org.nocrala.tools.texttablefmt.ShownBorders;
import org.nocrala.tools.texttablefmt.Table;

import org.apache.log4j.Logger;

/**
 *
 * @author sys6003
 */
public class UtilityClass {
    
    private static Logger log = Logger.getLogger(UtilityClass.class);

    public UtilityClass() {
    }

    public static boolean isInteger(String intString) {

        boolean isInt = true;
        try {
            Integer.parseInt(intString);
        } catch (NumberFormatException e) {
            System.out.println("\tNote: Number Required");
            isInt = false;
        }
        return isInt;
    }

    public static boolean isNull(String enteredString) {

        boolean isNull = false;
        try {
            if (enteredString.equals("") || enteredString.equals("\\n")) {
                isNull = true;
            }
        } catch (Exception e) {
            isNull = true;
        }
        return isNull;
    }

    public static boolean isDuplicateInList(HashSet<String> hash, String str) {

        boolean isDup = false;
        try {
            if (hash.contains(str)) {
                System.out.println("\tSorry Ref Name already choosen. Try with other Name");
                isDup = true;
            } else {
                hash.add(str);
            }
        } catch (Exception e) {
            System.out.println("Error " + e.getMessage());
            isDup = true;
        } finally {
        }
        return isDup;
    }

    public static boolean isAllowed(String str) {

        boolean allow = true;
        try {
            if (str.contains("_") || str.contains("-")) {
                System.out.println("\t\tWe are extreamly sorry to say this");
                System.out.println("\t\tFor Some reasons you are not allowed to use ( - , _  ) in your text input");
                allow = false;
            }
        } catch (Exception e) {
            System.out.println("Error " + e.getMessage());
        } finally {

        }
        return allow;

    }

    public static String takeInputFromKeyBoard(String info, String dataType) {

        Scanner input = new Scanner(System.in);
        String s = "";
        boolean condition = false;
        boolean release = true;
        while (release) {
            System.out.print(info + " >\t");
            s = input.nextLine();
            if (dataType.equalsIgnoreCase(Contstants.intString)) {
                condition = !UtilityClass.isInteger(s);
            }
            release = s.equals("") || condition;
        }
        return s.trim();
    }

    public static String takeInputFromKeyBoard(String info, String dataType, HashSet<String> hash) {

        Scanner input = new Scanner(System.in);
        String s = "";
        boolean condition = false;
        boolean release = true;
        while (release) {
            System.out.print(info + " >\t");
            s = input.nextLine();
            if (dataType.equalsIgnoreCase(Contstants.intString)) {
                condition = !UtilityClass.isInteger(s);
            }
            release = s.equals("") || !isAllowed(s) || condition || UtilityClass.isDuplicateInList(hash, s);
        }
        return s.trim();
    }

    public static void tableViewStructure(List<Room> rooms) {

        try {

            String[] columnNames = {"RoomName", "Bulbs", "Fans", "TVs", "PowerSockets"};
            String roomStr = "NONE";
            String bulbStr = "NONE";
            String fanStr = "NONE";
            String tvStr = "NONE";
            String psStr = "NONE";
            CellStyle cs = new CellStyle(HorizontalAlign.left, AbbreviationStyle.crop, NullStyle.emptyString);

//            StringBuffer sb = new StringBuffer();
//  StreamingTable t = new StreamingTable(sb, 2, BorderStyle.CLASSIC,
//      ShownBorders.ALL, false, "");
//  t.addCell("abcdef", cs);
//  t.addCell("123456", cs);
//  t.addCell("mno", cs);
//  t.addCell("45689", cs);
//  t.addCell("xyztuvw", cs);
//  t.addCell("01234567", cs);
//  t.finishTable();
//  System.out.println(sb.toString());
            Table t = new Table(columnNames.length, BorderStyle.CLASSIC, ShownBorders.ALL, false, "");
            for (String header : columnNames) {
                t.addCell(header, cs);
            }
            if (rooms != null && !rooms.isEmpty()) {
                for (Room room : rooms) {
                    roomStr = room.getRoomName();
                    t.addCell(roomStr, cs);
                    if (room.getBulbCount() > 0) {
                        bulbStr = "";
                        for (Bulb bulb : room.getBulbs()) {
                            bulbStr += bulb.getBulbName() + "-" + (bulb.getBulbStatus() == 0 ? "OFF" : "ON") + ",";
                        }
                    }
                    t.addCell(bulbStr, cs);
                    if (room.getFanCount() > 0) {
                        fanStr = "";
                        for (Fan fan : room.getFans()) {
                            fanStr += fan.getFanName() + "-" + (fan.getFanStatus() == 0 ? "OFF" : "ON") + ",";
                        }
                    }
                    t.addCell(fanStr, cs);
                    if (room.getTvCount() > 0) {
                        tvStr = "";
                        for (TV tv : room.getTvs()) {
                            tvStr += tv.getTvName() + "-" + (tv.getTvStatus() == 0 ? "OFF" : "ON") + ",";
                        }
                    }
                    t.addCell(tvStr, cs);
                    if (room.getPowerSocketCount() > 0) {
                        psStr = "";
                        for (PowerSocket ps : room.getPss()) {
                            psStr += ps.getPsName() + "-" + (ps.getPsStatus() == 0 ? "OFF" : "ON") + ",";
                        }
                    }
                    t.addCell(psStr, cs);
                }
            } else {
                t.addCell(roomStr, cs);
                t.addCell(bulbStr, cs);
                t.addCell(fanStr, cs);
                t.addCell(tvStr, cs);
                t.addCell(psStr, cs);
            }
            System.out.println(t.render());
        } catch (Exception e) {
            System.out.println("Exception at tableStructure@UtilityClass, Error " + e.getMessage());
        } finally {
        }

    }
}
