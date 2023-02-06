package Player;

import Board.CreateBoard;
import Listeners.InputUser;
import Ship.Armoured;
import Ship.Cruiser;
import Ship.Submarine;
import Ship.Torpedo;

import java.util.Objects;
import java.util.Scanner;

public class PlayerHuman extends Player{

    public PlayerHuman() {
    }

    @Override
    public void setPlayerName() {
        String name;
        Scanner input = new Scanner(System.in);
        System.out.println("Please enter a username : ");
        name = input.nextLine();

        System.out.println("Your username is now " + name);
        playerName = name;
    }

    public String getPlayerName(){
        return playerName;
    }

    public static int getNbShipAlive(String[][] board) {
        int nbShipAlive = 0;
        for (int i = 0; i < CreateBoard.DIM; i++) {
            for (int j = 0; j < CreateBoard.DIM; j++) {
                if(!board[i][j].equals(" ")) nbShipAlive++;
            }
        }
        return nbShipAlive;
    }

    @Override
    public boolean shoot(String[][] array) {
        int column = InputUser.reqCoordinate("Select the column where to shoot a ship : ");
        int line = InputUser.reqCoordinate("Select the line where to shoot a ship : ");
        return shoot(line, column, array);
    }

    @Override
    public boolean shoot(int x, int y, String[][] array) {
        incrementNbTotalShoot();
        return checkCoordAndHit(array, x, y, false);
    }

    protected static boolean checkCoordAndHit(String[][] array, int x, int y, boolean bot) {
        if(!(x >= 0 && x < CreateBoard.DIM) && (y>= 0 && y<CreateBoard.DIM)) return false;

        if(Objects.equals(array[x][y], " ") || array[x][y].equalsIgnoreCase(" ")){
            if(!bot) System.out.println("Player failed !");
            else System.out.println("Bot failed !");
            return false;
        }
        hitSmallBoat(x, y, array);
        hitMediumBoat(x, y, array);
        hitLargeBoat(x, y, array);
        hitLargiestBoat(x, y, array);
        incrementNbSuccessShoot();
        return true;
    }

    private static void hitSmallBoat(int x, int y, String[][] array){
        if(!(array[x][y].equals(new Submarine().getStyle()))) return;
        destroyShipV(x, y, array, new Submarine().getSize());
    }


    private static void hitMediumBoat(int x, int y, String[][] array) {
        Torpedo t = new Torpedo();
        checkAndDestroyV(x, y, array, t.getStyle(), t.getSize());
        checkAndDestroyH(x, y, array, t.getStyle(), t.getSize());
    }

    private static void hitLargeBoat(int x, int y, String[][] array){
        Cruiser c = new Cruiser();
        checkAndDestroyV(x, y, array, c.getStyle(), c.getSize());
        checkAndDestroyH(x, y, array, c.getStyle(), c.getSize());
    }

    private static void hitLargiestBoat(int x, int y, String[][] array){
        Armoured a = new Armoured();
        checkAndDestroyV(x, y, array, a.getStyle(), a.getSize());
        checkAndDestroyH(x, y, array, a.getStyle(), a.getSize());
    }
    private static void checkAndDestroyV(int x, int y, String[][] array, String style, int size){
        if(isAxisVertical(x, y, style, array, size)) destroyShipV(x, y, array, size);
    }
    private static void checkAndDestroyH(int x, int y, String[][] array, String style, int size){
        if(isAxisHorizontal(x, y, style, array, size)) destroyShipH(x, y, array, size);
    }

    private static boolean isAxisVertical(int x, int y, String style, String[][]array, int size){
        int v = 0;
        for (int i = 0; i < size; i++) {
            if(x+i >= 10) return false;
            if(array[x+i][y].equals(style)){
                v++;
            }
        }
        return v == size;
    }
    private static boolean isAxisHorizontal(int x, int y, String style, String[][]array, int size){
        int h = 0;
        for (int i = 0; i < size; i++) {
            if(y+i >= 10) return false;
            if(array[x][y+i].equals(style)){
                h++;
            }
        }
        return h == size;
    }
    private static void destroyShipV(int x, int y, String[][] array, int size) {
        for (int i = 0; i < size; i++) {
            array[x+i][y] = " ";
        }
    }
    private static void destroyShipH(int x, int y, String[][] array, int size){
        for (int i = 0; i < size; i++) {
            array[x][y+i] = " ";
        }
    }
}