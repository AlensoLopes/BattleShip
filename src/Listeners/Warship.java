package Listeners;

import Board.DisplayBoard;
import Ship.*;

import java.util.ArrayList;
import java.util.Objects;

public class Warship extends DisplayBoard{
    protected int size;
    protected String style;
    public static int nb_ship = 1;
    private final ArrayList<String> lock = new ArrayList<>();
    public void deployShip(Submarine s, Torpedo t,
                           Cruiser c, Armoured a, String[][] array){
        System.out.println();
        System.out.println("You need to deploy your ship !");
        for(int i = 1; i<=nb_ship;){
            ProcessInput input = new ProcessInput();
            String tmp = input.processInputTypeShip();
            String sens = "";

            if(!(tmp.equals("A"))) sens = input.processInputAxis();

            int column = InputUser.reqCoordinate("Select the column where to place your ship : ");
            int line = InputUser.reqCoordinate("Select the line where to place your ship : ");
            switch (tmp){
                case "A" -> {
                    placeSmallShip(line, column, s, array);
                    lock.add("S");
                }
                case "B" -> {
                    placeMediumShip(line, column, sens, t, array);
                    lock.add("M");
                }
                case "C" ->{
                    placeLargeShip(line, column, sens, c, array);
                    lock.add("L");
                }
                case "D" -> {
                    placeLargiestShip(line, column, sens, a, array);
                    lock.add("La");
                }
                default -> {
                }
            }
            i++;
        }
    }

    public void placeSmallShip(int x, int y, Submarine s, String[][] array) {
        String type = "S";
        if (lockCheckWithType(type)) return;
        if(!((x >= 0 && x < DIM) && (y >= 0 && y < DIM) &&
                (Objects.equals(array[x][y], " ")))){
            errorPlaceShip(x, y, array, type);
            return;
        }
        array[x][y] = s.style;
        /*displayBoard(array);*/
    }

    public void placeMediumShip(int x, int y, String sens, Torpedo t, String[][] array){
        String type = "M";
        callPlaceShip(x, y, t.getSize(), sens, t.getStyle(), array, type);
    }

    public void placeLargeShip(int x, int y, String sens,
                               Cruiser c, String[][] array){
        String type = "L";
        callPlaceShip(x, y, c.getSize(), sens, c.getStyle(), array, type);
    }

    public void placeLargiestShip(int x, int y, String sens,
                                  Armoured a, String[][] array){
        String type = "La";
        callPlaceShip(x, y, a.getSize(), sens, a.getStyle(), array, type);
    }

    private void callPlaceShip(int x, int y, int size, String sens,
                               String style, String[][] array, String type){
        if (lockCheckWithType(type)) return;
        boolean e = checkError(x, y, size, sens, array);
        place_check_vertical(x, y, sens, size, e, style, array, type);
        place_check_horizontal(x, y, sens, size, e, style, array, type);
        /*displayBoard(array);*/
    }

    private void shipLock(){
        System.out.println("You already place that type of ship !");
        nb_ship++;
    }

    private boolean lockCheckWithType(String type) {
        if(lock.contains(type)){
            shipLock();
            return true;
        }
        return false;
    }

    private void place_check_horizontal(int x, int y, String sens, int size, boolean e,
                                        String style, String[][]array, String type) {
        if(!sens.equals("H")) return;
        for (int i = 0; i < size; i++) {
            if(e){
                array[x][y +i] = style;
            }else errorPlaceShip(x, y, array, type);
        }
    }

    private void place_check_vertical(int x, int y, String sens, int size, boolean e,
                                      String style, String[][] array, String type) {

        if(!sens.equals("V")) return;
        for (int i = 0; i < size; i++) {
            if(e){
                array[x + i][y] = style;
            } else errorPlaceShip(x, y, array, type);
        }
    }

    private void errorPlaceShip(int x, int y, String[][] array, String type){
        if((x >= 0 && x < DIM) && (y >= 0 && y < DIM) && (!Objects.equals(array[x][y], " "))){
            System.out.println("You can't place a same ship on the same location !");
            lock.remove(type);
        }else if((x < 0 || x >=DIM) || (y < 0 || y >= DIM)) {
            System.out.println("You can't place ship outside the board");
        }
        nb_ship++;
    }
    private boolean checkError(int x, int y, int size, String sens, String[][] array) {
        int no_error = 0;
        if (sens.equals("V")) {
            for (int i = 0; i < size; i++) {
                if ((x + i >= 0 && x + i < DIM) && (y >= 0 && y < DIM) && (Objects.equals(array[x + i][y], " "))) {
                    no_error++;
                }
            }
            return no_error == size;
        }
        for (int i = 0; i < size; i++) {
            if ((x >= 0 && x < DIM) && (y + i >= 0 && y + i < DIM) && (Objects.equals(array[x][y + i], " "))) {
                no_error++;
            }
        }
        return no_error == size;
    }

}