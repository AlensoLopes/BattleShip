package Player;


public abstract class Player {

    protected static String playerName;
    private int nbTotalShoot;
    private int nbSuccessShoot;
    private int nbShipAlive;


    protected abstract void setPlayerName();

    public static String getPlayerName(){
        return playerName;
    }

    protected void incrementNbTotalShoot(){
        this.nbTotalShoot++;
    }

    protected void incrementNbSuccessShoot(){
        this.nbSuccessShoot++;
    }


    protected abstract boolean shoot(String[][] array);
    protected abstract boolean shoot(int x, int y, String[][] array);

    public int getNbShipAlive() {
        return nbShipAlive;
    }

    public void setNbShipAlive(int nbShipAlive) {
        this.nbShipAlive = nbShipAlive;
    }

}