package View;

import Model.Fields.PropertyField;
import Model.GameBoard;
import Model.Player;

public abstract class GameView {

    private GameBoard gameBoard;

    public abstract void setPlayers(Player[] players);

    public abstract int getPlayerCount();

    public abstract String getPlayerName(String text);

    public abstract String getRoundChoice(String... choice);

    public abstract String getRoundChoiceWithText(String text, String... choice);

    public abstract String getRoundChoiceDropDownWithText(String text, String... choice);

    public abstract void resetBoard();

    public abstract void setPlayerField(Player player);

    public abstract void renderBuildings();

    public abstract void setPlayerMoney(Player player, int money);

    public abstract void renderPlayerData(Player player, int previousField);

    public abstract void endText(String text);

    public abstract void setDice(int[] pair);

    public abstract void setCenterText(String text);

    public GameBoard getGameBoard(){
        return gameBoard;
    }

    public void setGameBoard(GameBoard gameBoard){
        this.gameBoard = gameBoard;
    }

    public abstract void createViewBoard();
}
