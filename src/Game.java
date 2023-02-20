import Board.CreateBoard;
import Board.DisplayBoard;
import Listeners.Warship;
import Player.*;
import Ship.*;
import Win.Win;

import java.io.IOException;

public class Game {
    static PlayerHuman p;
    static Bot b;
    static Warship[] objShip = {new Submarine(), new Torpedo(),
            new Cruiser(), new Armoured()};
    static String[][] board = new CreateBoard().createBoard();
    static String[][] board_bot = new CreateBoard().createBoard();
    static Warship w = new Warship();

    public static void game() {
        System.out.println("BattleShip Game ! ");
        boolean stop = false;
        int tour = 0;
        p = new PlayerHuman();
        b = new Bot();
        p.setPlayerName();
        w.deployShip((Submarine) objShip[0], (Torpedo) objShip[1],
                (Cruiser) objShip[2], (Armoured) objShip[3], board);
        b.placeShipBot(board_bot);
        Utils.Utils.clearConsole();
        new DisplayBoard().displayBoard(board);
        while(!stop){
            tour++;
            System.out.println("Tour Number " + tour);
            p.shoot(board_bot);
            b.shoot(board);
            if(Win.getWinner(board, board_bot, b, p)) stop = true;
            Utils.Utils.clearConsole();
            if(!stop) {
                new DisplayBoard().displayBoard(board);
            }
        }
    }

}
