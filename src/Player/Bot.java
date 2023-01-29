package Player;

import Listeners.Warship;
import Ship.Armoured;
import Ship.Cruiser;
import Ship.Submarine;
import Ship.Torpedo;
import Utils.Utils;

import java.io.File;
import java.util.ArrayList;
import java.util.Random;

public class Bot extends Player{

    public Bot() {
        setPlayerName();
        setNbShipAlive(5);
    }

    @Override
    protected void setPlayerName() {
        Random r = new Random();
        File f = new File("botName/botName.json");
        ArrayList<String> text = Utils.getTextFromFile(f);

        int nb = r.nextInt(text.size());
        this.playerName = text.get(nb);
    }

    public String getBotName(){
        return this.playerName;
    }
    public void placeShipBot(String[][] array){
        for (int i = 1; i <= Warship.nb_ship;) {
            String sens = processAxis(randomAxis());
            int x = randomPos(array);
            int y = randomPos(array);

            switch (i){
                case 1 -> new Warship().placeSmallShip(x, y, new Submarine() ,array);
                case 2 -> new Warship().placeMediumShip(x, y, sens ,new Torpedo(), array);
                case 3 -> new Warship().placeLargeShip(x, y, sens, new Cruiser(), array);
                case 4 -> new Warship().placeLargiestShip(x, y, sens, new Armoured(), array);
                default -> {
                }
            }
            i++;
        }
    }
    private String processAxis(int axis){
        String sens = "";
        switch (axis){
            case 0 -> sens = "V";
            case 1 -> sens = "H";
        }
        return sens;
    }

    private int randomPos(String[][] array){
        return new Random().nextInt(array.length);
    }

    private int randomAxis(){
        return new Random().nextInt(2);
    }

    @Override
    protected boolean shoot(String[][] array) {
        int x, y;
        Random r = new Random();
        x = r.nextInt(array.length);
        y = r.nextInt(array.length);
        return shoot(x, y, array);
    }

    @Override
    protected boolean shoot(int x, int y, String[][] array) {
       return new PlayerHuman().checkCoordAndHit(array, x, y);
    }
}
