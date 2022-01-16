package Model.ChanceCards;

import Model.Fields.CompanyField;
import Model.Game;
import Model.Global;
import Model.Player;
import Model.Fields.Field;

public class MoveToCard extends ChanceCard {
    private int fieldIndex;
    private boolean isSpecific = false;
    private boolean isFieldOffset = false;
    private boolean isOwned = false;
    private boolean isJail = false;

    public MoveToCard(String text, String name, boolean isJail){
        super(text, name);
        this.fieldIndex = fieldIndex;
        this.isSpecific = isSpecific;
        this.isJail = true;
    }

    public MoveToCard(String text, String name, int fieldOffset){
        super(text, name);
        this.fieldIndex = fieldOffset;
        this.isFieldOffset = true;
    }

    public MoveToCard(String text, String name, int fieldIndex, boolean isSpecific){
        super(text, name);
        this.fieldIndex = fieldIndex;
        this.isSpecific = isSpecific;
    }

    MoveToCard(String text, String name, int fieldIndex, boolean isSpecific, boolean isOwned){
        super(text, name);
        this.fieldIndex = fieldIndex;
        this.isSpecific = isSpecific;
        this.isOwned = isOwned;
    }


    @Override
    public void cardAction(Player player, Game game){
        super.cardAction(player, game);

        if (this.isJail){
            this.fieldIndex = Global.JAIL_INDEX;
            player.setInJail(true);
        } else if (this.isFieldOffset){
            this.fieldIndex = Math.floorMod(player.getField() + this.fieldIndex, Global.FIELD_COUNT);
        } else if (!this.isSpecific){
            this.fieldIndex = game.getGameBoard().getClosestShipping(player.getField());
            Field tempField = game.getGameBoard().getFieldModel(this.fieldIndex);
            if (tempField instanceof CompanyField){
                if (((CompanyField) tempField).getOwner() != null){

                }
            }
        } else {
            if (this.fieldIndex < player.getPreviousField()){
                player.addMoney(Global.ROUND_MONEY);
            }
        }


        player.setField(fieldIndex % Global.FIELD_COUNT);
        player.setLastAction(player.getLastAction() + "\n - Er rykket til " + this.getName() + ".");
    }
}
