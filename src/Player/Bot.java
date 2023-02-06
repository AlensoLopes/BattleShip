package Player;

import Listeners.Warship;
import Ship.Armoured;
import Ship.Cruiser;
import Ship.Submarine;
import Ship.Torpedo;
import Utils.Utils;

import java.io.File;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

public class Bot extends Player {
    public Bot() {
        setPlayerName();
    }

    public int getNbShipAlive(String[][] board) {
        return PlayerHuman.getNbShipAlive(board);
    }

    @Override
    public void setPlayerName() {
        Random r = new Random();
        File f = new File("botName/botName.json");
        ArrayList<String> text = Utils.getTextFromFile(f);

        int nb = r.nextInt(text.size());
        playerName = text.get(nb);
    }

    public String getBotName() {
        return playerName;
    }


    private final ArrayList<String> lockbot = new ArrayList<>();

    public void placeShipBot(String[][] array){
        for (int i = 0; i < Warship.nb_ship;) {
            String sens = processAxis(randomAxis());
            int column = randomPos(array, sens);
            int line = randomPos(array, sens);
            checkIfPlaceShip(array, sens, column, line);
            i++;
        }
    }

    private void checkIfPlaceShip(String[][] array, String sens, int column, int line) {
        if(!lockbot.contains("S")){
            new Warship().placeSmallShip(line, column, new Submarine(), array, true);
            if(isPlacementGood(array, new Submarine().getStyle())) lockbot.add("S");
        }else if(!lockbot.contains("M")){
            new Warship().placeMediumShip(line, column, sens, new Torpedo(), array, true);
            if(isPlacementGood(array, new Torpedo().getStyle())) lockbot.add("M");
        }else if(!lockbot.contains("L")){
            new Warship().placeLargeShip(line, column, sens, new Cruiser(), array, true);
            if(isPlacementGood(array, new Cruiser().getStyle())) lockbot.add("L");
        }else if(!lockbot.contains("La")){
            new Warship().placeLargiestShip(line, column, sens, new Armoured(), array, true);
            if(isPlacementGood(array, new Armoured().getStyle())) lockbot.add("La");
        }
    }

    private String processAxis(int axis) {
        String sens = "";
        switch (axis) {
            case 0 -> sens = "V";
            case 1 -> sens = "H";
        }
        return sens;
    }

    private int randomPos(String[][] array, String sens) {
        if(Objects.equals(sens, "H")) return new Random().nextInt(array.length - 4);
        return new Random().nextInt(array.length);
    }

    private int randomAxis() {
        return new Random().nextInt(2);
    }

    private boolean isPlacementGood(String[][] board, String style){
        for (String[] strings : board) {
            for (int j = 0; j < board.length; j++) {
                if (strings[j].equals(style)) return true;
            }
        }
        return false;
    }

    @Override
    public boolean shoot(String[][] array) {
        int x, y;
        Random r = new Random();
        x = r.nextInt(array.length);
        y = r.nextInt(array.length);
        return shoot(x, y, array);
    }

    @Override
    public boolean shoot(int x, int y, String[][] array) {
        return PlayerHuman.checkCoordAndHit(array, x, y, true);
    }
}