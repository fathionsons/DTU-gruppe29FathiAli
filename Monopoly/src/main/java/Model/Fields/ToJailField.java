package Model.Fields;

import Model.Global;
import Model.Player;

public class ToJailField extends Field {

    private final int JAIL_ID = Global.JAIL_INDEX;

    public ToJailField(String name, String subText, String description){
        super(name, subText, description);
    }

    // #--------------Get--------------#

    @Override
    public String getName(){
        return super.getName();
    }

    @Override
    public String getSubText(){
        return super.getSubText();
    }

    @Override
    public String getDescription(){
        return super.getDescription();
    }

    @Override
    public void fieldAction(Player player){
        throwInJail(player);
    }

    private void throwInJail(Player player){
        player.setLastAction(player.getLastAction() + "\n - Er sat i fængsel.");
        player.setField(JAIL_ID);
        player.setInJail(true);
    }
}
