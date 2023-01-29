package Listeners;

import Board.DisplayBoard;
import Ship.*;

import java.util.ArrayList;
import java.util.Objects;

public class Warship extends DisplayBoard{
    protected int size;
    protected String style;
    public static int nb_ship = 4;
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

            int x = input.reqCoordinateX();
            int y = input.reqCoordinateY();
            switch (tmp){
                case "A" -> {
                    placeSmallShip(x, y, s, array);
                    lock.add("S");
                }
                case "B" -> {
                    placeMediumShip(x, y, sens, t, array);
                    lock.add("M");
                }
                case "C" ->{
                    placeLargeShip(x, y, sens, c, array);
                    lock.add("L");
                }
                case "D" -> {
                    placeLargiestShip(x, y, sens, a, array);
                    lock.add("La");
                }
                default -> {
                }
            }
            i++;
        }
    }

    public void placeSmallShip(int x, int y, Submarine s, String[][] array) {
        if(lock.contains("S")){
            shipLock();
            return;
        }
        if(!((x >= 0 && x < DIM) && (y >= 0 && y < DIM) &&
                (Objects.equals(array[x][y], " ")))){
            errorPlaceShip(x, y, array);
            return;
        }
        array[x][y] = s.style;
        displayBoard(array);
    }

    public void placeMediumShip(int x, int y, String sens, Torpedo t, String[][] array){
        if(lock.contains("M")){
            shipLock();
            return;
        }
        boolean e = checkError(x, y, t.size, sens, array);
        place_check_vertical(x, y, sens, t.size, e, t.style, array);
        place_check_horizontal(x, y, sens, t.size, e, t.style, array);
        displayBoard(array);
    }

    public void placeLargeShip(int x, int y, String sens,
                                Cruiser c, String[][] array){
        if(lock.contains("L")){
            shipLock();
            return;
        }
        boolean e = checkError(x, y, c.size, sens, array);
        place_check_vertical(x, y, sens, c.size, e, c.style, array);
        place_check_horizontal(x, y, sens, c.size, e, c.style, array);
        displayBoard(array);
    }

    public void placeLargiestShip(int x, int y, String sens,
                                   Armoured a, String[][] array){
        if(lock.contains("La")){
            shipLock();
            return;
        }
        boolean e = checkError(x, y, a.size, sens, array);
        place_check_vertical(x, y, sens, a.size, e, a.style, array);
        place_check_horizontal(x, y, sens, a.size, e, a.style, array);
        displayBoard(array);
    }


    private void shipLock(){
        System.out.println("You already place that type of ship !");
        nb_ship++;
    }

    private void place_check_horizontal(int x, int y, String sens, int size, boolean e,
                                        String style, String[][]array) {
        if(!sens.equals("H")) return;
        for (int i = 0; i < size; i++) {
            if(e){
                array[x][y +i] = style;
            }else errorPlaceShip(x, y, array);
        }
    }

    private void place_check_vertical(int x, int y, String sens, int size, boolean e,
                                      String style, String[][] array) {

        if(!sens.equals("V")) return;
        for (int i = 0; i < size; i++) {
            if(e){
                array[x + i][y] = style;
            } else errorPlaceShip(x, y, array);
        }
    }

    private void errorPlaceShip(int x, int y, String[][] array){
        if((x >= 0 && x < DIM) && (y >= 0 && y < DIM) && (!Objects.equals(array[x][y], " "))){
            System.out.println("You can't place a same ship on the same location !");
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
