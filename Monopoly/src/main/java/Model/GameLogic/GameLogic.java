package Model.GameLogic;

import Model.*;

public abstract class GameLogic {

    private Game game;

    GameLogic(Game game){
        this.game = game;
    }


    abstract public Player BeforeTurn();

    abstract public Player PlayTurn();

    abstract public int AfterTurn();

}