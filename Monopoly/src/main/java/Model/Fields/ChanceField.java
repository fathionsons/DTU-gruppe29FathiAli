package Model.Fields;

import Model.Player;
import gui_fields.GUI_Chance;
import gui_fields.GUI_Field;

import java.awt.*;

public class ChanceField extends Field {

    ChanceField(String name, String subText, String description){
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
        player.setLastAction(player.getLastAction() + "\n - Er landet på et chancefelt.");
        player.setChanceField(true);
    }
}
