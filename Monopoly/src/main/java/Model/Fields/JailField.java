package Model.Fields;

import Model.Player;
import gui_fields.GUI_Field;
import gui_fields.GUI_Jail;

import java.awt.*;

public class JailField extends Field {

    JailField(String name, String subText, String description){
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
        player.setLastAction(player.getLastAction() + "\n - Er på besøg i fængsel.");

        System.out.println("[INFO] " + player.getName() + " er på besøg i fængsel.");
    }
}
