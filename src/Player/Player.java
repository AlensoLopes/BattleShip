package Player;

import java.util.Scanner;

public abstract class Player {

    protected String playerName;
    private int nbTotalShoot;
    private int nbSuccessShoot;
    private int nbShipAlive;


    protected abstract void setPlayerName();

    protected String getPlayerName(){
        return this.playerName;
    }

    protected void incrementNbTotalShoot(){
        this.nbTotalShoot++;
    }

    protected void incrementNbSuccessShoot(){
            this.nbSuccessShoot++;
        }


    protected abstract boolean shoot(String[][] array);
    protected abstract boolean shoot(int x, int y, String[][] array);

    Scanner input = new Scanner(System.in);
    protected int reqCoordinateX(){
        System.out.print("First select x coordinate: ");
        return input.nextInt();
    }
    protected int reqCoordinateY(){
        System.out.print("First select y coordinate: ");
        return input.nextInt();
    }

    public int getNbShipAlive() {
        return nbShipAlive;
    }

    public void setNbShipAlive(int nbShipAlive) {
        this.nbShipAlive = nbShipAlive;
    }

}