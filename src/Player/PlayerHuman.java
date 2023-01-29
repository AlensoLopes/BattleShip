package Player;

import Board.CreateBoard;
import Board.DisplayBoard;
import Ship.Armoured;
import Ship.Cruiser;
import Ship.Submarine;
import Ship.Torpedo;

import java.util.Objects;
import java.util.Scanner;

public class PlayerHuman extends Player{

    public PlayerHuman() {
        setNbShipAlive(5);
        setPlayerName();
    }

    @Override
    protected void setPlayerName() {
        String name;
        Scanner input = new Scanner(System.in);
        System.out.println("Please enter a username : ");
        name = input.nextLine();

        System.out.println("Your username is now " + name);
        this.playerName = name;
    }

    @Override
    public boolean shoot(String[][] array) {
        return shoot(reqCoordinateX(), reqCoordinateY(), array);
    }

    @Override
    public boolean shoot(int x, int y, String[][] array) {
        incrementNbTotalShoot();
        return checkCoordAndHit(array, x, y);
    }

    protected boolean checkCoordAndHit(String[][] array, int x, int y) {
        if(!(x >= 0 && x < CreateBoard.DIM) && (y>= 0 && y<CreateBoard.DIM)) return false;

        if(Objects.equals(array[x][y], " ")){
            System.out.println("No ship here !");
            return false;
        }
        hitSmallBoat(x, y, array);
        hitMediumBoat(x, y, array);
        hitLargeBoat(x, y, array);
        hitLargiestBoat(x, y, array);
        incrementNbSuccessShoot();
        return true;
    }

    private void hitSmallBoat(int x, int y, String[][] array){
        if(!(array[x][y].equals(new Submarine().getStyle()))) return;
        destroyShipV(x, y, array, new Submarine().getSize());
        new DisplayBoard().displayBoard(array);
    }


    private void hitMediumBoat(int x, int y, String[][] array) {
        Torpedo t = new Torpedo();
        checkAndDestroyV(x, y, array, t.getStyle(), t.getSize());
        checkAndDestroyH(x, y, array, t.getStyle(), t.getSize());
        new DisplayBoard().displayBoard(array);
    }

    private void hitLargeBoat(int x, int y, String[][] array){
        Cruiser c = new Cruiser();
        checkAndDestroyV(x, y, array, c.getStyle(), c.getSize());
        checkAndDestroyH(x, y, array, c.getStyle(), c.getSize());
        new DisplayBoard().displayBoard(array);
    }

    private void hitLargiestBoat(int x, int y, String[][] array){
        Armoured a = new Armoured();
        checkAndDestroyV(x, y, array, a.getStyle(), a.getSize());
        checkAndDestroyH(x, y, array, a.getStyle(), a.getSize());
        new DisplayBoard().displayBoard(array);
    }
    private void checkAndDestroyV(int x, int y, String[][] array, String style, int size){
        if(isAxisVertical(x, y, style, array, size)) destroyShipV(x, y, array, size);
    }
    private void checkAndDestroyH(int x, int y, String[][] array, String style, int size){
        if(isAxisHorizontal(x, y, style, array, size)) destroyShipH(x, y, array, size);
    }

    private boolean isAxisVertical(int x, int y, String style, String[][]array, int size){
        int v = 0;
        for (int i = 0; i < size; i++) {
            if(array[x+i][y].equals(style)){
                v++;
            }
        }
        return v == size;
    }
    private boolean isAxisHorizontal(int x, int y, String style, String[][]array, int size){
        int h = 0;
        for (int i = 0; i < size; i++) {
            if(array[x][y+i].equals(style)){
                h++;
            }
        }
        return h == size;
    }
    private void destroyShipV(int x, int y, String[][] array, int size) {
        for (int i = 0; i < size; i++) {
            array[x+i][y] = " ";
        }
    }
    private void destroyShipH(int x, int y, String[][] array, int size){
        for (int i = 0; i < size; i++) {
            array[x][y+i] = " ";
        }
    }
}
