package Win;

import Player.Bot;
import Player.PlayerHuman;

public class Win {

    public static boolean getWinner(String[][] board, String[][] board_bot, Bot b, PlayerHuman p){
        if(PlayerHuman.getNbShipAlive(board) == 0 && b.getNbShipAlive(board_bot) != 0){
            System.out.println("The Winner is " + b.getBotName());
        }else if(PlayerHuman.getNbShipAlive(board) != 0 && b.getNbShipAlive(board_bot) == 0){
            System.out.println("The winner is " + p.getPlayerName());
        }else if(PlayerHuman.getNbShipAlive(board) == 0 && b.getNbShipAlive(board_bot) == 0){
            System.out.println("The winners are both Player !");
        }
        return true;
    }
}
