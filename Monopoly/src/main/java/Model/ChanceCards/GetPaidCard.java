package Model.ChanceCards;

import Model.Game;
import Model.Player;

public class GetPaidCard extends ChanceCard {
    private int money;
    private boolean toOthers;

    public GetPaidCard(String text, String name, int money, boolean toOthers){
        super(text, name);
        this.money = money;
        this.toOthers = toOthers;
    }

    private void paidByOthers(Player activePlayer, int money, Player[] players){
        int activePlayerCount = 0;

        for (Player player : players){
            if (!player.isBankrupt() && activePlayer != player){
                player.addMoney(-money);
                activePlayerCount++;
            }
        }

        int moneyToGet = money * activePlayerCount;
        activePlayer.addMoney(moneyToGet);
    }

    public int getMoney(){
        return money;
    }

    public void setMoney(int money){
        this.money = money;
    }

    public boolean isToOthers(){
        return toOthers;
    }

    public void setToOthers(boolean toOthers){
        this.toOthers = toOthers;
    }

    @Override
    public void cardAction(Player player, Game game){
        super.cardAction(player, game);

        if (this.isToOthers()){
            paidByOthers(player, this.getMoney(), game.getPlayers());
            player.setLastAction(player.getLastAction() + "\n - Har fået " + this.getMoney()
                    + " kr. fra hver spiller.");
        } else {
            player.setLastAction(player.getLastAction() + "\n - Har fået " + this.getMoney()
                    + " kr. fra banken.");
            player.addMoney(this.getMoney());
        }
    }
}
