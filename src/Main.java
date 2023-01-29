import Board.CreateBoard;
import Listeners.Warship;
import Player.Bot;
import Player.PlayerHuman;
import Ship.Armoured;
import Ship.Cruiser;
import Ship.Submarine;
import Ship.Torpedo;

public class Main {
    public static void main(String[] args) {
        PlayerHuman p = new PlayerHuman();
        Bot b = new Bot();
        System.out.println(b.getBotName());
        Warship[] w = {new Submarine(), new Torpedo(),
                new Cruiser(), new Armoured()};
        String[][] array = new CreateBoard().createBoard();

        new Warship().deployShip((Submarine) w[0], (Torpedo) w[1],
                (Cruiser) w[2], (Armoured) w[3], array);
            b.placeShipBot(array);

            /*a*/
    }
}