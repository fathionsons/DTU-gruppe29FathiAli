import Controller.GameController;
import Model.*;
import View.GameGUIView;

/**
 * The main class to start the game
 */

public class Main {
    public static void main(String[] args){
        GameBoard board = new GameBoard();
        GameGUIView view = new GameGUIView();
        GameController game = new GameController(board, view);
    }
}

/** Fathi Ali Gruppe 29 DTU final
 * */