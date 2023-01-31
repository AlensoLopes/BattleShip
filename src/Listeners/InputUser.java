package Listeners;

import java.util.Scanner;

public class InputUser {
    static Scanner input = new Scanner(System.in);
    protected int reqTypeShip(){
        System.out.println("Select the ship you want to place : " +
                "- Submarine : 1 " +
                "- Torpedo : 2 " +
                "- Cruiser : 3" +
                "- Torpedo : 4");
        return input.nextInt();
    }
    protected int reqAxis(){
        System.out.println("Select the axis : " +
                "1 : Vertical " +
                "2 : Horizontal");
        return input.nextInt();
    }

    public static int reqCoordinateColumn(){
        System.out.print("Select the column for your ship: ");
        return input.nextInt();
    }
    public static int reqCoordinateLine(){
        System.out.print("Select the line for your ship: ");
        return input.nextInt();
    }
}