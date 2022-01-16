package Model.ChanceCards;

import Model.Game;
import Model.Player;

public class PayBankCard extends ChanceCard {
    private int money;

    PayBankCard(String text, String name, int money){
        super(text, name);
        this.money = money;
    }


    @Override
    public void cardAction(Player player, Game game){
        player.setLastAction(player.getLastAction() + "\n - Har betalt " + this.money + " kr. til banken.");

        super.cardAction(player, game);
        if (money < 20 && money > 0){
            player.addMoney(-money);
        }
    }
}
