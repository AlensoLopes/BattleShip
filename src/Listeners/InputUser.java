package Listeners;

import Listeners.Warship;

import java.util.Scanner;

public class InputUser {
    Scanner input = new Scanner(System.in);
    protected int reqTypeShip(){
        System.out.println("Select the ship you want to place : " +
                "- Submarine : 1 " +
                "-  : 2 " +
                "- : 3" +
                "- : 4");
        return input.nextInt();
    }
    protected int reqAxis(){
        System.out.println("Select the sens: 1 : 2");
        return input.nextInt();
    }

    protected int reqCoordinateX(){
        System.out.print("First select x coordinate for your ship: ");
        return input.nextInt();
    }
    protected int reqCoordinateY(){
        System.out.print("First select y coordinate for your ship: ");
        return input.nextInt();
    }
}
