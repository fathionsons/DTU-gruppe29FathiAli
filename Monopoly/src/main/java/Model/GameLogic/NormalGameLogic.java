package Model.GameLogic;

import Model.ChanceCards.ChanceCard;
import Model.Fields.Field;
import Model.Game;
import Model.Global;
import Model.Player;

public class NormalGameLogic {

    private Game game;
    private Player[] players;
    private int nowIndex;
    private int newIndex;
    private int bankruptcies = 0;


    public NormalGameLogic(Game game){
        this.game = game;
        this.players = game.getPlayers();
    }

    public void setupNextPlayer(){
        if (!game.isEnded()){
            this.nowIndex = java.util.Arrays.asList(players).indexOf(game.getActivePlayer());
            int tempindex = nowIndex;
            //this.newIndex = (nowIndex + 1) % players.length;
            for (int i = 0; i < players.length; i++){
                if (!players[(tempindex + 1) % players.length].isBankrupt()){
                    newIndex = (tempindex + 1) % players.length;
                    break;
                } else {
                    tempindex++;
                }
            }

        }
    }

    public void throwDice(boolean alreadyThrown){
        if (!game.isEnded() && !game.getActivePlayer().isBankrupt()){
            int diceThrow;
            if (!alreadyThrown){
                diceThrow = game.setAndGetDiceResult();
            } else {
                diceThrow = game.getDiceResult();
            }

            int fieldId = (game.getActivePlayer().getField() + diceThrow) % Global.FIELD_COUNT;
            game.getActivePlayer().setPreviousField(game.getActivePlayer().getField());

            fieldId = gameRules(fieldId);

            UpdateActivePlayerWithThrow(fieldId, diceThrow);


        } else {
            endPlayerTurn();
        }
    }

    public int gameRules(int fieldId){
        if (game.getActivePlayer().isInJail()){
            if (!game.getActivePlayer().isOutOfJailFree()){
                System.out.println("[INFO] " + game.getActivePlayer().getName() + " Har betalt " +
                        Global.JAIL_PRICE + " for at komme ud af fængslet.");
                game.getActivePlayer().setLastAction("\n - Har betalt" + Global.JAIL_PRICE + " kr. for at blive løsladt.");
                game.getActivePlayer().addMoney(-Global.JAIL_PRICE);
            } else {
                game.getActivePlayer().setLastAction("\n - Har brugt sit løsladelseskort.");
                System.out.println("[INFO] " + game.getActivePlayer().getName() + " er blevet løsladt via løsladelseskort.");
            }

            game.getActivePlayer().setInJail(false);
            checkRound();
        }

        if (!game.isEnded()){
            if (fieldId < game.getActivePlayer().getPreviousField()){
                System.out.println("[INFO] " + game.getActivePlayer().getName() + " Har passeret start og har modtaget " + Global.ROUND_MONEY + " kr.");
                game.getActivePlayer().setLastAction(game.getActivePlayer().getLastAction() + "\n - Har passeret start og har modtaget " + Global.ROUND_MONEY + " kr.");
                addStartMoney(game.getActivePlayer());
            }
            game.getActivePlayer().setPreviousField(fieldId);
            game.getActivePlayer().setField(fieldId);

            Field landedField = game.getGameBoard().getFieldModel(fieldId);
            landedField.fieldAction(game.getActivePlayer());


            if (game.getActivePlayer().isChanceField()){
                chanceFieldAction(game.getActivePlayer());
                return game.getActivePlayer().getField();
            }

        }

        return fieldId;
    }

    public void chanceFieldAction(Player activePlayer){
        activePlayer.setChanceField(false);

        ChanceCard card = game.getGameBoard().randomChanceCard();
        activePlayer.setChanceCard(card);

        activePlayer.getChanceCard().cardAction(activePlayer, game);

    }

    public void UpdateActivePlayerWithThrow(int fieldId, int diceThrow){
        if (game.getActivePlayer().isInJail()){
            game.getActivePlayer().setField(game.getGameBoard().getJail());
            game.getActivePlayer().setLastDiceResult(diceThrow);
            game.getActivePlayer().setLastDicePair(game.getDicePair());
        } else {
            game.getActivePlayer().setField(fieldId);
            game.getActivePlayer().setLastDiceResult(diceThrow);
            game.getActivePlayer().setLastDicePair(game.getDicePair());
        }
    }

    public void addStartMoney(Player player){
        player.addMoney(Global.ROUND_MONEY);
    }

    public void endPlayerTurn(){
        if (!game.isEnded()){
            game.getActivePlayer().setChanceCard(null);
            game.getActivePlayer().setLastAction("");
            System.out.println("[TURN INFO] Sat new player to index " + this.newIndex);
            game.setActivePlayer(players[this.newIndex]);
            checkRound();
        }
    }

    public void checkRound(){

        for (Player player : players){
            if (!player.isBankrupt()){
                if (player.getMoney() <= 0){

                    player.setBankrupt(true);
                    this.bankruptcies++;

                    if (bankruptcies == players.length - 1){
                        game.setEnded(true);
                        game.setWinner(findWinner());
                    }
                }
            }
        }
    }

    public Player findWinner(){
        Player highest = null;
        if (game.isEnded()){
            int max = 0;
            for (Player player : players){
                if (player.getMoney() > max){
                    max = player.getMoney();
                    highest = player;
                }
            }
        }
        return (highest);
    }


}
